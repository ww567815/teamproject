<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Covid 19 Infomation</h2>
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<th>기준일</th>
				<th>확진자수</th>
				<th>사망자수</th>
				<th>누적확진률</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${ fn:length(covidList) gt 0 }">
					<c:forEach var="covid" items="${ covidList }">
						<tr>
							<td>${ covid.stateDt }</td>
							<td>${ covid.decideCnt }</td>
							<td>${ covid.deathCnt }</td>
							<td>${ covid.accDefRate }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

</body>
</html>

