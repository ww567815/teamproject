package com.team.util;

public class Script {
	
	public static String href(String alertMessage, String locationPath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + alertMessage + "');");
		sb.append("location.href = '" + locationPath + "';"); // Ư�� �̵���η� ��û
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String back(String alertMessage) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + alertMessage + "');");
		sb.append("history.back();"); // �ڷΰ���
		sb.append("</script>");
		return sb.toString();
	}
}
