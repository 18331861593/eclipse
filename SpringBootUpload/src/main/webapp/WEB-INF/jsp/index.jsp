<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>Insert title here</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<body>
	
	<form action="/upload" method="post" enctype="multipart/form-data">
		文件上传：
		<input type="file" name="file" />
		<br/>
		<input type="submit" value="提交" />
	</form>
	<br/>
	
	<form action="/uploads" method="post" enctype="multipart/form-data">
		文件上传：
		<input type="file" name="file" />
		<br/>
		文件上传：
		<input type="file" name="file" />
		<br/>
		文件上传：
		<input type="file" name="file" />
		<br/>
		文件上传：
		<input type="file" name="file" />
		<br/>
		文件上传：
		<input type="file" name="file" />
		<br/>
		文件上传：
		<input type="file" name="file" />
		<br/>
		<input type="submit" value="提交" />
	</form>
	
	
</body>
</html>
