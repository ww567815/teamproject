package com.team.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.team.domain.Covid19DTO;


@Controller
public class HomeController {
	
	@GetMapping(value = { "/", "/index" })
	public String home() {
		// GET 요청
		// http://localhost:8090/
		System.out.println("home() 호출됨...");
		
		return "index"; // 실행할 jsp 뷰 이름을 리턴함
	}
	
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getImageFile(String fileName) throws IOException {
		System.out.println("fileName : " + fileName);
		
		File file = new File("C:/sye/upload", fileName);
		System.out.println("실제 이미지 파일 경로 : " + file.getPath());
		
		
		HttpHeaders headers = new HttpHeaders();
		
		String contentType = Files.probeContentType(file.toPath());
		headers.add("Content-Type", contentType); // "image/jpeg"  "image/png"
		
		byte[] imageData = FileCopyUtils.copyToByteArray(file);
		
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(imageData, headers, HttpStatus.OK);
		return responseEntity;
	}
	
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) throws UnsupportedEncodingException {
		System.out.println("fileName : " + fileName);
		
		File file = new File("C:/sye/upload", fileName);
		
		Resource resource = new FileSystemResource(file);
		System.out.println("resource : " + resource);
		
		if (resource.exists() == false) { // 다운로드할 파일이 존재하지 않으면
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // 자원 없음 응답코드로 응답보내고 종료
		}
		
		// 다운로드할 파일이 존재하면
		
		String resourceName = resource.getFilename();
		System.out.println("resourceName : " + resourceName);
		
		int beginIndex = resourceName.indexOf("_") + 1;
		String originFilename = resourceName.substring(beginIndex); // 순수 파일명 구하기
		System.out.println("originFilename : " + originFilename);
		
		String downloadName = new String(originFilename.getBytes("UTF-8"), "ISO-8859-1"); // 다운로드 파일명으로 변환하기
		System.out.println("downloadName : " + downloadName);
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Content-Type", "application/octet-stream"); // 애노테이션의 produces 속성으로 대체
		headers.add("Content-Disposition", "attachment; filename=" + downloadName); // 다운로드 파일명을 헤더에 설정하기
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@GetMapping("/covid19")
	public String covid19Service(
			@RequestParam(required = false, defaultValue = "1") String pageNo,
			@RequestParam(required = false, defaultValue = "10") String numOfRows,
			@RequestParam(required = false, defaultValue = "20200610") String startCreateDt,
			@RequestParam(required = false, defaultValue = "20200615") String endCreateDt,
			Model model) throws Exception {
		
		List<Covid19DTO> covidList = getCovid19Inf(pageNo, numOfRows, startCreateDt, endCreateDt);
		
		model.addAttribute("covidList", covidList);
		
		return "covid19Info";
	} // covid19Service

	
	private List<Covid19DTO> getCovid19Inf(String pageNo, String numOfRows, String startCreateDt, String endCreateDt) throws Exception {
		
		List<Covid19DTO> covidList = new ArrayList<Covid19DTO>(); // 리턴할 List 준비
		
		// 데이터 가져올 요청주소 만들기
		StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=5HDLlx8mwCkPRBBcL2BAfV3aD5z6KN2kKQw7ng9F22KlLdcco8dvSssRCrVFYWOJt%2FYpBYraniXrMRBQU%2BZExQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(startCreateDt, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(endCreateDt, "UTF-8")); /*검색할 생성일 범위의 종료*/
        
		// 이하 내용은 요청주소를 통해 가져온 XML 응답을 자바 오브젝트로 파싱하는 코드임.
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder(); // DocumentBuilder가 XML 파서이다.
		
		Document document = builder.parse(urlBuilder.toString()); // 요청주소를 DocumentBuilder에 인자로 전달하면 XML 응답을 Document 객체로 리턴받음.
		document.getDocumentElement().normalize();
		System.out.println("Root Element : " + document.getDocumentElement().getNodeName());
		
		NodeList nodeList = document.getElementsByTagName("item"); // 행단위로 반복되는 item 태그명을 기준으로 NodeList 가져옴.
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // Date 객체로 파싱할 SimpleDateFormat 준비.
		
		// 필요한 데이터만 DTO에 담아서 준비된 covidList에 추가하기
		for (int i=0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			System.out.println("Current Element : " + node.getNodeName());
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;  // item 요소(Element)
				Covid19DTO covidDto = new Covid19DTO();
				
				String accDefRate = element.getElementsByTagName("accDefRate").item(0).getTextContent();
				covidDto.setAccDefRate(Double.parseDouble(accDefRate));
				
				String accExamCnt = element.getElementsByTagName("accExamCnt").item(0).getTextContent();
				covidDto.setAccExamCnt(Integer.parseInt(accExamCnt));
				
				String accExamCompCnt = element.getElementsByTagName("accExamCompCnt").item(0).getTextContent();
				covidDto.setAccExamCompCnt(Integer.parseInt(accExamCompCnt));
				
				String careCnt = element.getElementsByTagName("careCnt").item(0).getTextContent();
				covidDto.setCareCnt(Integer.parseInt(careCnt));
				
				String clearCnt = element.getElementsByTagName("clearCnt").item(0).getTextContent();
				covidDto.setClearCnt(Integer.parseInt(clearCnt));
				
				String createDt = element.getElementsByTagName("createDt").item(0).getTextContent();
				covidDto.setCreateDt(sdf.parse(createDt));
				
				String deathCnt = element.getElementsByTagName("deathCnt").item(0).getTextContent();
				covidDto.setDeathCnt(Integer.parseInt(deathCnt));
				
				String decideCnt = element.getElementsByTagName("decideCnt").item(0).getTextContent();
				covidDto.setDecideCnt(Integer.parseInt(decideCnt));
				
				String examCnt = element.getElementsByTagName("examCnt").item(0).getTextContent();
				covidDto.setExamCnt(Integer.parseInt(examCnt));
				
				String resutlNegCnt = element.getElementsByTagName("resutlNegCnt").item(0).getTextContent();
				covidDto.setResutlNegCnt(Integer.parseInt(resutlNegCnt));
				
				String seq = element.getElementsByTagName("seq").item(0).getTextContent();
				covidDto.setSeq(Integer.parseInt(seq));
				
				String stateDt = element.getElementsByTagName("stateDt").item(0).getTextContent();
				covidDto.setStateDt(stateDt);
				
				String stateTime = element.getElementsByTagName("stateTime").item(0).getTextContent();
				covidDto.setStateTime(stateTime);
				
				String updateDt = element.getElementsByTagName("updateDt").item(0).getTextContent();
				covidDto.setUpdateDt(sdf.parse(updateDt));
				
				covidList.add(covidDto); // 리스트에 DTO를 추가
			} // if
		} // for
		
		System.out.println("파싱결과 : " + covidList);
        
		return covidList; // 파싱된 List를 리턴
	} // getCovid19XmlData
	
}