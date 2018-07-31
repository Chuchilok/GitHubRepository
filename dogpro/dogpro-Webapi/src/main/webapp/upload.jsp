
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单图片上传</title>
</head>
<body>
<fieldset>
<legend>图片上传</legend>
<h2>只能上传单张10M以下的 PNG、JPG、GIF 格式的图片</h2>
<form action="http://192.168.199.81:8081/dogpro-Webapi/UsercenterApi/Apiv2.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file">
    <input type="submit" value="上传"> 
</form>
</fieldset>


<fieldset>
<legend>视频及缩略图上传</legend>
<form action="http://192.168.199.81:8081/dogpro-Webapi/UsercenterApi/Apiv3.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="videofile">
    <input type="file" name="subimage">
    <input type="submit" value="上传"> 
</form>
</fieldset>
</body>
</html>