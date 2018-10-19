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
    <form id="menuForm" method="post">
    	<input type="hidden" name="id" id="id"/>
		    	<div class="form-item">
		    		<label class="iblock">mname:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="mname" id="mname"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">mstate:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="mstate" id="mstate"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">murl:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="murl" id="murl"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">micon:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="micon" id="micon"
		    			 data-options="required:true"/>
		    	</div>
		    
			    	<div class="form-item">
			    		<label class="iblock">parentId:</label>
			    		<input name="menu.parentId.id" class="easyui-combobox" 
			    		data-options="valueField:'id',textField:'name',url:'sys/parentId_search.action',panelHeight:'auto',editable:false" />  
			    	</div>
		    
			    	<div class="form-item">
			    		<label class="iblock">userMenu:</label>
			    		<input name="menu.userMenu.id" class="easyui-combobox" 
			    		data-options="valueField:'id',textField:'name',url:'sys/userMenu_search.action',panelHeight:'auto',editable:false" />  
			    	</div>
		    
    </form>
  </body>
</html>














