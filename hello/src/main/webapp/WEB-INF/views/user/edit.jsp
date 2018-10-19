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
    <form id="userForm" method="post">
    	<input type="hidden" name="id" id="id"/>
		    	<div class="form-item">
		    		<label class="iblock">roleId:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="roleId" id="roleId"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">username:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="username" id="username"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">password:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="password" id="password"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">email:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="email" id="email"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">realname:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="realname" id="realname"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">tel:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="tel" id="tel"
		    			 data-options="required:true"/>
		    	</div>
		    
		    	<div class="form-item">
		    		<label class="iblock">logintime:</label>
		    		<input type="text" class="ipt easyui-validatebox" name="logintime" id="logintime"
		    			 data-options="required:true"/>
		    	</div>
		    
			    	<div class="form-item">
			    		<label class="iblock">state:</label>
			    		<input name="user.state.id" class="easyui-combobox" 
			    		data-options="valueField:'id',textField:'name',url:'sys/state_search.action',panelHeight:'auto',editable:false" />  
			    	</div>
		    
			    	<div class="form-item">
			    		<label class="iblock">role:</label>
			    		<input name="user.role.id" class="easyui-combobox" 
			    		data-options="valueField:'id',textField:'name',url:'sys/role_search.action',panelHeight:'auto',editable:false" />  
			    	</div>
		    
    </form>
  </body>
</html>














