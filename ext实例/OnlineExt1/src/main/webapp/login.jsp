<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title></title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
		 href="ext-5.0.0/packages/ext-theme-classic/build/resources/ext-theme-classic-all.css">
	<link rel="stylesheet" type="text/css"
		 href="css/myStyle.css"> 
	<script type="text/javascript" src="ext-5.0.0/ext-all.js"> </script>
	<script type="text/javascript" 
		src="ext-5.0.0/packages/ext-theme-classic/build/ext-theme-classic.js"></script>
	<script type="text/javascript" src="scripts/hello.js"></script>
	<style type="text/css">
		
	</style>
	<script type="text/javascript">
		Ext.onReady(function(){
			var form = new Ext.form.FormPanel({
				title : '用户登录',
				id : 'userLogin',
				//renderTo : Ext.getBody(),
				renderTo : 'login',
				width : 450,
				height : 170,
				items : [
					{
						xtype : 'textfield',
						fieldLabel : '用户名',
						fieldClass : 'field-align',
						allowBlank : false,
						blankText : '用户名不能为空',	  
						emptyText : '请输入用户名',
						msgTarget : 'side',	
						/* regex : /^\d{3,10}$/,
						regexText : '用户名必须是3到10位的数字', */
						name : 'username',
						id : 'username' 
					},
					{
						xtype : 'textfield',
						fieldLabel : '密码',
						name : 'password',
						id : 'password',
						inputType : 'password'
					}
				],
				buttons : [
					{
						text : '登录',
						
					},
					{
						text : '关闭',
					}
				
				]
			});
		});
	</script>
<body>
	<div id="login">
		
	</div>
</body>
</html>
