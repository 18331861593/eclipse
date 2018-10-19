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
    <form id="${short_entityName}Form" method="post">
    	<input type="hidden" name="id" id="id"/>
    	<#list fields as f>
    		<#if f.type != 'Object'>
		    	<div class="form-item">
		    		<label class="iblock">${f.name}:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="${f.name}" id="${f.name}"
		    			 data-options="required:true"/>
		    	</div>
		    	<#else>
			    	<div class="form-item">
			    		<label class="iblock">${f.name}:</label>
			    		<input name="${short_entityName}.${f.name}.id" class="easyui-combobox" 
			    		data-options="valueField:'id',textField:'name',url:'sys/${f.name}_search.action',panelHeight:'auto',editable:false" />  
			    	</div>
		    </#if>
		    
    	</#list>
    </form>
  </body>
</html>














