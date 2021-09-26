package com.team.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.domain.MemberVO;
import com.team.service.MemberService;
import com.team.util.Script;


@Controller // @Component �迭 �ֳ����̼�
@RequestMapping("/member/*")
public class MemberController {

	private MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// @Autowired �ֳ����̼��� �����ڿ����� ��������
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/join") // /member/join
	public String join() {
		System.out.println("join ȣ���...");
		return "member/join";
	}
	
	
	@PostMapping("/join")
	public ResponseEntity<String> join(MemberVO memberVO) {
		
		// ��й�ȣ ��ȣȭ �ϱ�
		String passwd = memberVO.getPasswd();
		String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt()); // ��ȣȭ�� ��й�ȣ ���Ϲ���
		memberVO.setPasswd(hasedPw); // ��ȣȭ�� ��й�ȣ�� �缳��
		
		// ������ ���й���("-") �����ϱ�
		String birthday = memberVO.getBirth(); // "2021-08-25"
		birthday = birthday.replace("-", ""); // "20210825"
		memberVO.setBirth(birthday);
		
		// ������� ��¥ ��ü ����
		memberVO.setCreate_at(new Date());
		
		System.out.println(memberVO);
		memberService.register(memberVO); // ȸ����� ó��
		
		// �������� �����͸� �߰�,����,���� �� ȭ�������� �ٷ� �ٶ���
		// ���ΰ�ħ �߻��� ������ ������ �߻��ɼ� �����Ƿ�
		// �����̷�Ʈ�� ���� ����ڰ� ���ߵ� ȭ�� �ּҷ� ��û�ϰ� �����.
		// "redirect:/member/login";
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		String str = Script.href("ȸ������ ����!", "/");
		
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

			if (isPasswdSame == false) { // ��й�ȣ ��ġ���� ����
				System.out.println("��Ʈ�ѷ��׽�Ʈ" + memberVO.getId());
				message = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			}
		} else { // memberVO == null // ��ġ�ϴ� ���̵� ����
			message = "�������� �ʴ� ���̵� �Դϴ�.";
		}

		// �α��� ���н� (���� ���̵�ų� ��й�ȣ Ʋ������)
		if (memberVO == null || isPasswdSame == false) {

			HttpHeaders headers = new HttpHeaders();
			// HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = Script.back(message);

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}

		// �α��� ������, �α��� �����ϱ�
		session.setAttribute("id", id);

		// �α��� ���������� üũ�Ǿ�����
		if (rememberMe != null) {
			Cookie cookie = new Cookie("id", id); // �α��� ���̵�� ��Ű���� ����
			cookie.setPath("/");
			cookie.setMaxAge(60 * 10); // �ʴ���. 60�� * 10 -> 10��

			response.addCookie(cookie); // ���䰴ü�� ��Ű�� �߰��س����� ��������� ��Ű�� Ŭ���̾�Ʈ���� ��������
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/"); // redirect ��θ� "/"�� ����

		// �����̷�Ʈ�� ��� HttpStatus.FOUND �� �����ؾ� ��
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	} // login




	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		//����� �α��� ���� Ȯ��
		if (session.getAttribute("id") == null) { // �α��� ���� ����ڸ�
			return "redirect:/member/login";
		}
		
		
		// ���� ����
		session.invalidate();
		
		// �α��� ���������� ��Ű ������ �����ϱ�
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					cookie.setMaxAge(0); // �������� ������ �� �ֵ��� 0�ʷ� ����
					cookie.setPath("/");
					
					response.addCookie(cookie);
				}
			} // for
		}
		
		// Ȩ ȭ������ �����̷�Ʈ �̵�
		return "redirect:/";
	}
	
	//*********************************************** ����,���� �� ä�� ************************************************************
	@GetMapping("/chat")
	public String chat() {
		return "member/chat";
	}

	@PostMapping("/chat")
	public String chat(String id, HttpServletRequest request) {

		logger.info("Welcome " + id);

		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		return "/index";

	}
	
	// ************************************ ������ ����
	// ********************************************
	@GetMapping("/modify") // /member/modify
	public String modify() {

		System.out.println("join ȣ���...");
		return "member/modify"; // jsp�̸� modify.jsp
	}

	@PostMapping("/modify") // /member/modify
	public ResponseEntity<String> modify(MemberVO memberVO) {

		// ������� ���й���("-") �����ϱ�
		String birthday = memberVO.getBirth();
		birthday = birthday.replace("-", "");
		memberVO.setBirth(birthday);

		memberService.updateMember(memberVO);

		// ���� ������ �������� �˸�â�� �˷���
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		// Script�� alert�� ���ԵǾ�����
		String str = Script.href("ȸ������ ����!", "/");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);

	}
	
	// *************************��й�ȣ ����**********************************
	@GetMapping("/passwd") // /member/passwd
	public String passwd() {

		System.out.println("passwd ȣ���...");
		return "member/passwd"; // jsp�̸� passwd.jsp
	}

	@PostMapping("/passwd") // /member/passwd
	public ResponseEntity<String> passwd(MemberVO memberVO) {
		// ��й�ȣ ��ȣȭ �ϱ�
		String passwd = memberVO.getPasswd();
		String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt()); // ��ȣȭ �޼ҵ�
		memberVO.setPasswd(hasedPw); // ��ȣȭ�� ��й�ȣ�� ����
		memberService.updatePasswd(memberVO);

		// ���� ������ �������� �˸�â�� �˷���
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		// Script�� alert�� ���ԵǾ�����
		String str = Script.href("��й�ȣ ���� ����!", "/");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);

	}
	
	// ******************************단호박 온도**********************************
		@GetMapping("/temperature") // /member/info
		public String info(String name, HttpSession session) {

			System.out.println("info 호출...");
			return "member/temperature";
		}
	
}
