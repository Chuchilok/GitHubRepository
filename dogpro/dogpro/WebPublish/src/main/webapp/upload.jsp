
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
<form action="UsercenterApi/Apiv2.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file">
    <input type="submit" value="上传"> 
    userId:<input type="text" name="userId">
    token:<input type="text" name="token">  
</form>
</fieldset>

<fieldset>
<legend>文件上传</legend>
<form action="http://127.0.0.1:8081/WebPublish/UsercenterApi/Apiv3.do" method="post" enctype="multipart/form-data">
   			<input type="file" name="file">
 			  <input type="hidden" name="userId" >
			  <input type="hidden" name="token" >  
   			<input type="submit" value="上传" class="submit"> 
			</form>
</fieldset>
</body>
</html>