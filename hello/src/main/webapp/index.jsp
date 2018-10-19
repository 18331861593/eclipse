<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'arlnDetails.jsp' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="EasyUI/jquery-1.7.2.min.js"></script>
	 <script type="text/javascript" src="WEB-INF/views/role/role.js"></script>
<body>
<h2 id="hello">Hello World!</h2>
<h3>
	${count }
	<br/>
	${list }
	
	<%-- <spring:message code="helloword"/> --%>
</h3>
<script type="text/javascript">
	$(function(){
		
		$("#hello").click(function(){
			$.ajax({
		        type:"get",
		        async: false,
		        url:"nhdzList.action",
		        success : function(){
		        	
		        },
		        error : function(){
		        	
		        }
			})
			
		});
		
	})
</script>
</body>
</html>
