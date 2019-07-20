<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Send OK</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<c:choose>
		<c:when test="${ !empty error }"><p style="color:red;"><c:out value="${ error }" /></p></c:when>
		
		<c:otherwise>
			<p>File <c:out value="${ fileName }" /> send to BDD</p>
		</c:otherwise>
	</c:choose>
	
</body>
</html>