package com.team.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team.domain.AttachVO;
import com.team.domain.Criteria;
import com.team.domain.MemberVO;
import com.team.domain.PageDTO;
import com.team.domain.ProductVO;
import com.team.service.AttachService;
import com.team.service.ProductService;
import com.team.util.Script;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/products/*")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	   @GetMapping("/productsList") 
	   public String products(Criteria cri, Model model) {
	      System.out.println("productsList 호출...");
	      List<ProductVO> proList = productService.getProducts(cri);
	      
	      //int totalCount = productService.getTotalCount(); //전체 글 개수
	      int totalCount = productService.getTotalCountBySearch(cri); //검색한 전체 글개수
	      
	      PageDTO pageDTO = new PageDTO(totalCount, cri);//페이지 블록 화면 만들때 필요한 정보
	      
	      
	      model.addAttribute("proList", proList);
	      model.addAttribute("pageMaker", pageDTO);
	      return "products/productsList_";
	   }
	
	
	// 새로운 상품등록 폼 화면 요청
	@GetMapping("/write")
	public String write(@ModelAttribute("pageNum") String pageNum) {
		// 사용자 로그인 권한 확인
//		if (session.getAttribute("id") == null) { // 로그인 안한 사용자면
//			return "redirect:/member/login";
//		}
		System.out.println("컨트롤러 write작동중..");
		return "products/productWrite";
	} // get write
	
	// 년/월/일 형식의 폴더명 리턴하는 메소드
	private String getFolder() {
		System.out.println("컨트롤러 getFolder작동중..");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	}
	
	// 이미지 파일인지 여부 리턴하는 메소드
	private boolean checkImageType(File file) throws IOException {
		System.out.println("컨트롤러 checkImageType작동중..");
		boolean isImage = false;
		
		String contentType = Files.probeContentType(file.toPath()); // "image/jpg"
		System.out.println("contentType : " + contentType);
		
		isImage = contentType.startsWith("image");
		return isImage;
	}
	
	// 첨부파일 업로드(썸네일 생성) 후 attacList 리턴하는 메소드
	private List<AttachVO> uploadFilesAndGetAttachList(List<MultipartFile> files, int bno) throws IllegalStateException, IOException {
		System.out.println("컨트롤러 uploadFilesAndGetAttachList");
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		// 생성할 파일정보가 없으면 종료
		if (files == null || files.size() == 0) {
			System.out.println("업로드한 첨부파일 개수 : " + files.size());
			return attachList;
		}
		
		
		String uploadFolder = "C:/Dev_jun/upload";  // 업로드 기준경로
		
		File uploadPath = new File(uploadFolder, getFolder()); // C:/Dev_jun/upload/2021/09/25
		System.out.println("uploadPath : " + uploadPath.getPath());
		
		if (uploadPath.exists() == false) {  // !uploadPath.exists()
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : files) {
			// 업로드 시 file type의 input 태그가 총 5개 사용되었는데
			// 그중에 3개만 파일을 선택하고 2개는 파일선택안하고 비워두면
			// files.size()는 5가 되므로, 실제 선택한 파일정보만 가져오려면  isEmpty()로 걸러야됨
			if (multipartFile.isEmpty()) {
				continue;
			}
			
			String originalFilename = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String uploadFilename = uuid.toString() + "_" + originalFilename;
			
			File file = new File(uploadPath, uploadFilename); // 생성할 파일이름 정보
			
			// 파일1개 업로드(파일 생성) 완료
			multipartFile.transferTo(file);
			// ======================================================
			
			// 현재 업로드한 파일이 이미지 파일이면 썸네일 이미지를 추가로 생성하기
			boolean isImage = checkImageType(file); // 이미지 파일여부 확인 
			
			if (isImage == true) {
				File outFile = new File(uploadPath, "s_" + uploadFilename);
				
				Thumbnailator.createThumbnail(file, outFile, 100, 100);  // 썸네일 이미지 파일 생성하기
			}
			
			
			//===== insert할 주글 AttachVO 객체 데이터 생성 ======
			AttachVO attachVO = new AttachVO();
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(originalFilename);
			attachVO.setFiletype( (isImage == true) ? "I" : "O" );
			attachVO.setBno(bno);
			
			attachList.add(attachVO); // 리스트에 추가
		} // for
		
		return attachList;
	} // uploadFilesAndGetAttachList
	
	@PostMapping("/write")
	public ResponseEntity<String> write(List<MultipartFile> files, ProductVO productVO, HttpServletRequest request, RedirectAttributes rttr )throws IOException{
		// insert할 새 글번호 가져오기
		int pnum = productService.nextNum();
		
		// 첨부파일 업로드(썸네일 생성) 후 attacList 리턴
		List<AttachVO> attachList = uploadFilesAndGetAttachList(files, pnum);
		
		
		// ===== insert할 ProductVO 객체 데이터 설정 ======
		productVO.setPnum(pnum);
		productVO.setProductName(productVO.getProductName());
		productVO.setTitle(productVO.getTitle());
		productVO.setStatus(productVO.getStatus()); 
		productVO.setSell_price(productVO.getSell_price());  
		productVO.setDescription(productVO.getDescription());    
		productVO.setSeller_id(productVO.getSeller_id());   
		
//		productService.register(productVO);
		productService.registerProductsAndAttaches(productVO, attachList); // 주글 한개와 첨부파일 여러개 등록 - 트랜잭션 처리
		//===============================================
		
		// 리다이렉트시 서버에 전달할 데이터를 저장하면 스프링이 자동으로 쿼리스트링으로 변환하여 처리해줌
		rttr.addAttribute("pnum", productVO.getPnum());
		rttr.addAttribute("pageNum", 1);
		System.out.println("컨트롤러 post write작동중");
		HttpHeaders headers = new HttpHeaders();
		String str = Script.href("새 글 등록 성공!", "/products/productsList");
		headers.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(str, headers, HttpStatus.FOUND);
	}
	
	
	@PostMapping("/products") //물품조회관련 PostMapping
	public ResponseEntity<String> products(MemberVO memberVO){
		
		String str = Script.href("물품조회!", "/products/productsList");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	}
	
	@GetMapping("/details") //물품 상세보기
	public String details() {
		System.out.println("details 호출...");
		
		return "products/details";
	}
	
	@GetMapping("/products/modify")
	public String modifyForm(int num, @ModelAttribute("pageNum") String pageNum, Model model) {
		
		ProductVO boardVO = productService.getProductAndAttaches(num);
		
		model.addAttribute("board", boardVO);
		model.addAttribute("attachList", boardVO.getAttachList());
		//model.addAttribute("pageNum", pageNum); // @ModelAttribute 애노테이션으로 처리함과 동일
		
		return "products/productModify";
	} // modifyForm
	
	
}
