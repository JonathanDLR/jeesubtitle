<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload New Srt File</title>
</head>
<body>

<%@ include file="menu.jsp" %>

<h1>Upload new file to subtitle</h1>

<c:if test="${ !empty form.fileData }"><p><c:out value="Le fichier ${ form.fileData } a été uploadé !" /></p></c:if>
	
    <form method="post" action="upload" enctype="multipart/form-data">
        <p>
            <label for="urfile">Fichier à envoyer : </label>
            <input type="file" name="urfile" id="urfile" />
        </p>
        
        <input type="submit" />
    </form>
</body>
</html>