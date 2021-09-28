package com.team.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.team.domain.MemberVO;
import com.team.domain.ProfilePicVO;
import com.team.service.MemberService;
import com.team.service.ProfilePicService;
import com.team.util.Script;

import net.coobird.thumbnailator.Thumbnailator;

@Controller // @Component 계열 애노테이션
@RequestMapping("/member/*")
public class MemberController {

   private MemberService memberService;

   @Autowired
   private ProfilePicService profilePicService;

   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

   // @Autowired 애노테이션이 생성자에서는 생략가능
   public MemberController(MemberService memberService) {
      this.memberService = memberService;
   }

   @GetMapping("/join") // /member/join
   public String join() {
      System.out.println("join 호출됨...");
      return "member/join";
   }

   @PostMapping("/join")
   public ResponseEntity<String> join(MemberVO memberVO) {

      // 비밀번호 암호화 하기
      String passwd = memberVO.getPasswd();
      String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt()); // 암호화된 비밀번호 리턴받음
      memberVO.setPasswd(hasedPw); // 암호화된 비밀번호로 재설정

      // 연월일 구분문자("-") 제거하기
      String birthday = memberVO.getBirth(); // "2021-08-25"
      birthday = birthday.replace("-", ""); // "20210825"
      memberVO.setBirth(birthday);

      // 현재시점 날짜 객체 설정
      memberVO.setCreate_at(new Date());

      System.out.println(memberVO);
      memberService.register(memberVO); // 회원등록 처리

      // 서버에서 데이터를 추가,수정,삭제 후 화면응답을 바로 줄때는
      // 새로고침 발생시 서버에 오류가 발생될수 있으므로
      // 리다이렉트를 통해 사용자가 봐야될 화면 주소로 요청하게 만든다.
      // "redirect:/member/login";

      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", "text/html; charset=UTF-8");

      String str = Script.href("회원가입 성공!", "/");

      return new ResponseEntity<String>(str, headers, HttpStatus.OK);
   }

   @GetMapping("/login")
   public String login() {
      return "member/login";
   }

   @PostMapping("/login")
   public ResponseEntity<String> login(String id, String passwd, String rememberMe, HttpSession session,
         HttpServletResponse response) {

      MemberVO memberVO = memberService.getMemberById(id);

      boolean isPasswdSame = false;
      String message = "";

      if (memberVO != null) {
         isPasswdSame = BCrypt.checkpw(passwd, memberVO.getPasswd());

         if (isPasswdSame == false) { // 비밀번호 일치하지 않음
            message = "비밀번호가 일치하지 않습니다.";
         }
      } else { // memberVO == null // 일치하는 아이디가 없음
         message = "존재하지 않는 아이디 입니다.";
      }

      // 로그인 실패시 (없는 아이디거나 비밀번호 틀렸을때)
      if (memberVO == null || isPasswdSame == false) {

         HttpHeaders headers = new HttpHeaders();
         // HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "text/html; charset=UTF-8");

         String str = Script.back(message);

         return new ResponseEntity<String>(str, headers, HttpStatus.OK);
      }

      // 로그인 성공시, 로그인 인증하기
      session.setAttribute("id", id);

      // 로그인 상태유지가 체크되었으면
      if (rememberMe != null) {
         Cookie cookie = new Cookie("id", id); // 로그인 아이디로 쿠키정보 생성
         cookie.setPath("/");
         cookie.setMaxAge(60 * 10); // 초단위. 60초 * 10 -> 10분

         response.addCookie(cookie); // 응답객체에 쿠키를 추가해놓으면 최종응답시 쿠키를 클라이언트에게 전송해줌
      }

      // 썸네일
      ProfilePicVO profilePicVO = profilePicService.getProfilePic(id);
      // 로그인 성공시 썸네일
      session.setAttribute("profilePicVO", profilePicVO);

      HttpHeaders headers = new HttpHeaders();
      headers.add("Location", "/"); // redirect 경로를 "/"로 지정

      // 리다이렉트일 경우 HttpStatus.FOUND 로 지정해야 함
      return new ResponseEntity<String>(headers, HttpStatus.FOUND);
   } // login

   @GetMapping("/logout")
   public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
      // 사용자 로그인 권한 확인
      if (session.getAttribute("id") == null) { // 로그인 안한 사용자면
         return "redirect:/member/login";
      }

      // 세션 비우기
      session.invalidate();

      // 로그인 상태유지용 쿠키 있으면 삭제하기
      Cookie[] cookies = request.getCookies();

      if (cookies != null) {
         for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
               cookie.setMaxAge(0); // 브라우저가 삭제할 수 있도록 0초로 설정
               cookie.setPath("/");

               response.addCookie(cookie);
            }
         } // for
      }

      // 홈 화면으로 리다이렉트 이동
      return "redirect:/";
   }

   // *********************************************** 예은,수진 의 채팅
   // ************************************************************
   @GetMapping("/chat")
   public String chat() {
      return "member/chat";
   }

   /*
    * @PostMapping("/chat") public String chat(String id, HttpServletRequest
    * request) {
    * 
    * logger.info("Welcome " + id);
    * 
    * HttpSession session = request.getSession(); session.setAttribute("id", id);
    * return "/index";
    * 
    * }
    */

   // ************************************ 내정보 수정
   // ********************************************
   @GetMapping("/modify") // /member/modify
   public String modify(String id, Model model) {

      System.out.println("modify 호출됨...");
      
      // 회원정보 조회
      MemberVO memberVO = memberService.getMemberById(id);

      // 회원정보 프로필 사진 조회
      ProfilePicVO profilePicVO = profilePicService.getProfilePic(id);

      // 뷰에서 사용할 데이터를 Model 객체에 저장 -> requestScope로 옮겨줌
      model.addAttribute("memberVO", memberVO);
      model.addAttribute("profilePicVO", profilePicVO);
      
      
      return "member/modify"; // jsp이름 modify.jsp
   }

   // 첨부파일 업로드(썸네일 생성) 후 ProfilePicVO 리턴하는 메소드
   private ProfilePicVO uploadProfilePic(MultipartFile file, String id,String isProfilePic) throws IllegalStateException, IOException {
      
      ProfilePicVO profilePicVO = new ProfilePicVO();
      
      // 생성할 파일정보가 없으면 종료
      if (file == null) {
         return profilePicVO;
      }
      
      String uploadFolder = "C:/sye/upload";  // 업로드 기준경로
      
      File uploadPath = new File(uploadFolder, getFolder()); //C:/sye/upload/2021/09/22
      
      // 프로필 사진일 경우(경로 변경)
      if(isProfilePic != null) {
         uploadFolder =    "C:/sye/upload/profilePic/" + id;
         uploadPath = new File(uploadFolder); // C:/sye/upload/profilePic/해당 유저 아이디
      }
      
      
      if (uploadPath.exists() == false) {  // !uploadPath.exists()
         uploadPath.mkdirs();
      }
      
      // file이 비어있지 않다면 파일 생성
      if (!file.isEmpty()) {
         String originalFilename = file.getOriginalFilename();
         UUID uuid = UUID.randomUUID();
         String uploadFilename = uuid.toString() + "_" + originalFilename;
         
         File proFile = new File(uploadPath, uploadFilename); // 생성할 파일이름 정보
         
         // 파일 업로드(파일 생성) 완료
         file.transferTo(proFile);
         // ======================================================
         
         // 현재 업로드한 파일이 이미지 파일이면 썸네일 이미지를 추가로 생성하기
         File outFile = new File(uploadPath, "s_" + uploadFilename);
         
         Thumbnailator.createThumbnail(proFile, outFile, 300, 250);  // 썸네일 이미지 파일 생성하기
         
         //===== insert할 주글 profilePicVO 객체 데이터 생성 ======
         
         profilePicVO.setUuid(uuid.toString());
         profilePicVO.setUploadpath(isProfilePic != null ? "profilePic" : getFolder());
         profilePicVO.setFilename(originalFilename);
         profilePicVO.setMid(id);
      }
      
      return profilePicVO;
   } // uploadProfilePic
   
   private void deleteAttachFile(ProfilePicVO profilePicVO, String isProfilePic) {
      // 삭제할 파일정보가 없으면 메소드 종료
      if (profilePicVO == null ) {
         return;
      }
      
      
      String basePath = "C:/sye/upload";
      
      String uploadpath = basePath + "/" + profilePicVO.getUploadpath();

      // 프로필 사진일 경우 경로 변경
      if(isProfilePic != null){         
         // C:/sye/upload/profilePic/해당아이디
         basePath = "C:/sye/upload/profilePic/" + profilePicVO.getMid();
         uploadpath = basePath + "/";
      }
      
   
      String filename = profilePicVO.getUuid() + "_" + profilePicVO.getFilename();
      
      
      System.out.println("uploadpath" + uploadpath);
      
   
      File file = new File(uploadpath, filename);
      
      if (file.exists() == true) { // 해당 경로에 첨부파일이 존재하면
         file.delete(); // 첨부파일 삭제하기
      }
      
      // 섬네일 이미지 삭제하기
      File thumbnailFile = new File(uploadpath, "s_" + filename);
      System.out.println("thumbnailFile" + thumbnailFile.getPath());   
      
      if (thumbnailFile.exists() == true) {
         thumbnailFile.delete();
      }
         
   } // deleteAttachFile
   
   // 년/월/일 형식의 폴더명 리턴하는 메소드
   private String getFolder() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      String str = sdf.format(new Date());
      return str;
   }
   
   
   @PostMapping("/modify") // /member/modify
   public ResponseEntity<String> modify(MultipartFile file,MemberVO memberVO,HttpSession session) throws IllegalStateException, IOException {
      //========================== 프로필 설정 ===================================
      // 첨부파일 업로드(썸네일 생성) 후 ProfilePicVO 리턴
      ProfilePicVO profilePicVO = uploadProfilePic(file, memberVO.getId(),"profilePic");
      
      // 업로드 또는 변경할 이미지 파일이 있는경우 
      if(profilePicVO != null){         
         // 현재 해당 id의 회원정보 프로필 사진 조회
         ProfilePicVO profileVO = profilePicService.getProfilePic(memberVO.getId());
         
         // 프로필 사진이 있다면
         if(profileVO != null) {
         
            // 전 프로필 사진 삭제
            deleteAttachFile(profileVO,"profilePic");
            
            // 프로필 사진 테이블에서 정보 업데이트
            profilePicService.updateProfilePic(profilePicVO);
            
            
         }else {
            // 프로필 사진 테이블에서 정보 인서트
            profilePicService.insertProfilePic(profilePicVO);
         }
         

         // 로그인 성공시 썸네일
         session.setAttribute("profilePicVO", profilePicVO);
      }
      
      // 생년월일 구분문자("-") 제거하기
      String birthday = memberVO.getBirth();
      birthday = birthday.replace("-", "");
      memberVO.setBirth(birthday);

      memberService.updateMyInfo(memberVO,profilePicVO);

      // 파일 형식이 무엇인지 알림창에 알려줌
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", "text/html; charset=UTF-8");

      // Script에 alert가 포함되어있음
      String str = Script.href("회원수정 성공!", "/");

      return new ResponseEntity<String>(str, headers, HttpStatus.OK);

   }

   // *************************비밀번호 변경**********************************
   @GetMapping("/passwd") // /member/passwd
   public String passwd() {

      System.out.println("passwd 호출됨...");
      return "member/passwd"; // jsp이름 passwd.jsp
   }

   @PostMapping("/passwd") // /member/passwd
   public ResponseEntity<String> passwd(MemberVO memberVO) {
      // 비밀번호 암호화 하기
      String passwd = memberVO.getPasswd();
      String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt()); // 암호화 메소드
      memberVO.setPasswd(hasedPw); // 암호화된 비밀번호로 저장
      memberService.updatePasswd(memberVO);

      // 파일 형식이 무엇인지 알림창에 알려줌
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", "text/html; charset=UTF-8");

      // Script에 alert가 포함되어있음
      String str = Script.href("비밀번호 수정 성공!", "/");

      return new ResponseEntity<String>(str, headers, HttpStatus.OK);

   }
	// ******************************temper**********************************
	   @GetMapping("/temperature")
	   public String info(Model model, String id, HttpSession session) {
	      session.setAttribute("id", id);
	      MemberVO memberVO = memberService.getMemberById(id);

	      System.out.println("info ...");
	      
	      model.addAttribute("memberVO", memberVO);
	      
	      
	      return "member/temperature";
	   }
}
