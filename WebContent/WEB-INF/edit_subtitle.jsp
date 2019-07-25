<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Editer les sous-titres</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<c:if test="${ !empty error }"><p style="color:red;"><c:out value="${ error }" /></p></c:if>
	
	<h1>Edit Subtitle</h1>
	
	<form method="post" action="edit">
		<label for="choose" style="position:fixed; top: 55px; right: 210px;">Choose the file: </label>
        <select id="choose" name="choose" style="position:fixed; top: 50px; right: 10px;">
        	<option><c:out value=" " /></option>
        	<c:forEach items="${ fileList }" var="theFile">
        		<option value="${ theFile }"><c:out value="${ theFile }" /></option>
        	</c:forEach>
        </select>
        
        <input id="sendChoose" type="submit" value="Choose" style="position:fixed; top: 100px; right: 10px;" />
	</form>
	
    <form method="post" action="send">   
        <label for="send" style="position:fixed; top: 15px; right: 80px;">Send to BDD: </label>
        <input id="send" type="submit" style="position:fixed; top: 10px; right: 10px;" />
        
        <label for="fileName">Nom du fichier: </label>
        <input type="text" name="fileName" id="fileName" value="${ nameFile }" />
         
	    <table>
	    	<c:set var="i" value="0" />
	    	<c:set var="j" value="0" />
	        <c:forEach items="${ subtitles }" var="line" varStatus="status">
	        	<tr>
	        		<c:if test="${ line.matches('[0-9]+') }">
	        			<c:set var="i" value="${ i + 1 }" />
	        		</c:if>
	        		
	        		<td style="text-align:right;" id="origin${ status.index }"><c:out value="${ line }" /></td>
	        		<c:choose>
	        		
		        		<c:when test="${ not empty line  && not line.contains('-->') &&  not line.matches('[0-9]+') }">
		        			<c:set var="j" value="${ j + 1 }" />
		        			<input type="hidden" name="sub${ j }" id="sub${ j }" value="${ j }" />
		        			<td><input type="text" name="line${ j }" id="line${ j }" size="35" value="${ subs[j-1].getLine_text() }"/></td>
		        		</c:when>
		        		
		        		<c:when test="${ line.contains('-->') }">
		        			<td><input type="hidden" name="min${ i }" id="min${ i }" value="${ line }"/></td>
		        		</c:when>
		        		
		        		<c:when test="${ line.matches('[0-9]+') }">
		        			<td><input type="hidden" name="number${ i }" id="number${ i }" value="${ line }"/></td>
		        		</c:when>
		        	</c:choose>
	        	</tr>
	    	</c:forEach>
	    </table>
    </form>
</body>
</html>