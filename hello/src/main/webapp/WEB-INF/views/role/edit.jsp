<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <form id="roleForm" method="post">
    	<input type="hidden" name="id" id="id"/>
		    	<div class="form-item">
		    		<label class="iblock">name:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="name" id="name"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">mname:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="mname" id="mname"
		    			 data-options="required:true"/>
		    	</div>
		    
    </form>
  </body>
</html>














