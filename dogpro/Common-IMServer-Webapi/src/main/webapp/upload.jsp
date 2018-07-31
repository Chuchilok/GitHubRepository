
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单图片上传</title>
</head>
<body>
<fieldset>
<legend>文件上传</legend>
<form action="UsercenterApi/uploadImages.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file" id="doc0">
    <input type="file" name="file" id="doc1">
    <input type="file" name="file" id="doc2">
    <input type="submit" value="上传"> 
</form>
</fieldset>




</body>
</html>