package com.team.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
	
	@GetMapping(value = { "/", "/index" })
	public String home() {
		// GET ��û
		// http://localhost:8090/
		System.out.println("home() ȣ���...");
		
		return "index"; // ������ jsp �� �̸��� ������
	}
	
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getImageFile(String fileName) throws IOException {
		System.out.println("fileName : " + fileName);
		
		File file = new File("C:/sye/upload", fileName);
		System.out.println("���� �̹��� ���� ��� : " + file.getPath());
		
		
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
		
		if (resource.exists() == false) { // �ٿ�ε��� ������ �������� ������
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // �ڿ� ���� �����ڵ�� ���亸���� ����
		}
		
		// �ٿ�ε��� ������ �����ϸ�
		
		String resourceName = resource.getFilename();
		System.out.println("resourceName : " + resourceName);
		
		int beginIndex = resourceName.indexOf("_") + 1;
		String originFilename = resourceName.substring(beginIndex); // ���� ���ϸ� ���ϱ�
		System.out.println("originFilename : " + originFilename);
		
		String downloadName = new String(originFilename.getBytes("UTF-8"), "ISO-8859-1"); // �ٿ�ε� ���ϸ����� ��ȯ�ϱ�
		System.out.println("downloadName : " + downloadName);
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Content-Type", "application/octet-stream"); // �ֳ����̼��� produces �Ӽ����� ��ü
		headers.add("Content-Disposition", "attachment; filename=" + downloadName); // �ٿ�ε� ���ϸ��� ����� �����ϱ�
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	
}