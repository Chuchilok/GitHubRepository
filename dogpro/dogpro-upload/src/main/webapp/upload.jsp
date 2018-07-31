
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<fieldset>
<legend>单图片上传</legend>
<form action="UsercenterApi/Apiv1.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" value= "http://192.168.1.200:8081/dogpro-upload/uploadPicture/1515031580119.jpg" name="file">
    <input type="submit" value="上传"> 
    userId:<input type="text" name="userId">
    token:<input type="text" name="token">
</form>
</fieldset>

<br>

<fieldset>
<legend>多图片上传</legend>
<form action="UsercenterApi/Apiv2.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file" id="doc0">
    <input type="file"  name="file" id="doc1">
    <input type="file" name="file" id="doc2">
    <input type="submit" value="上传"> 
    userId:<input type="text" name="userId">
    token:<input type="text" name="token">
</form>
</fieldset>


<fieldset>
<legend>视频及缩略图上传</legend>
<form action="UsercenterApi/Apiv3.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file">
    <input type="file" name="subimage">
    <input type="submit" value="上传"> 
    userId:<input type="text" name="userId">
    token:<input type="text" name="token">
</form>
</fieldset>


<fieldset>
<legend>语音文件上传</legend>
<form action="UsercenterApi/Apiv4.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file">
    <input type="submit" value="上传"> 
    userId:<input type="text" name="userId">
    token:<input type="text" name="token">
</form>
</fieldset>
<fieldset>
<legend>第三方图片上传</legend>
<form action="UsercenterApi/Apiv5.do" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file">
    <input type="submit" value="上传">
    access_token:<input type="text" name="access_token">
    openid:<input type="text" name="openid">
    thirdPartyType:<input type="text" name="thirdPartyType">
</form>
</fieldset>
</body>
</html>