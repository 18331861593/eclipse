<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>上传文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body style="padding: 0;margin: 0;">
  	<form enctype="multipart/form-data" action="/servlet/fileUpload" method="post" >
        <input type="file" name="file"/><br>
        <input type="file" name="file"/><br>
        <input type="file" name="file"/><br>
        <input type="file" name="file"/><br>
        <input type="file" name="file"/><br>
        <input type="file" name="file"/><br>
         <button type="submit"  name="sub" id="sub">提交</button>
  	</form>
  </body>
</html>
