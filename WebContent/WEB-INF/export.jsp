<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Export</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<c:if test="${ !empty error }"><p style="color:red;"><c:out value="${ error }" /></p></c:if>
	<c:if test="${ !empty message }"><p style="color:red;"><c:out value="${ message }" /></p></c:if>

	<form method="post" action="export">
		<label for="choose">Choose the file: </label>
        <select id="choose" name="choose">
        	<option><c:out value=" " /></option>
        	<c:forEach items="${ files }" var="theFile">
        		<option value="${ theFile.getFileName() }"><c:out value="${ theFile.getFileName() }" /></option>
        	</c:forEach>
        </select>
        
        <input id="sendChoose" type="submit" value="Export" />
	</form>
</body>
</html>