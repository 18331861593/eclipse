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
    <form id="commAttachmentForm" method="post">
    	<input type="hidden" name="id" id="id"/>
		    	<div class="form-item">
		    		<label class="iblock">title:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="title" id="title"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">fileUrl:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="fileUrl" id="fileUrl"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<!-- <div class="form-item">
		    		<label class="iblock">createdAt:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="createdAt" id="createdAt"
		    			 data-options="required:true"/>
		    	</div> -->
		    
		    	<div class="form-item">
		    		<label class="iblock">fileType:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="fileType" id="fileType"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">fileSize:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="fileSize" id="fileSize"
		    			 data-options="required:true"/>
		    	</div>
		    
    </form>
  </body>
</html>














