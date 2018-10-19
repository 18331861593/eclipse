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
	<link rel="stylesheet" href="EasyUI/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="EasyUI/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="EasyUI/jquery-1.7.2.min.js"></script> 
	<script type="text/javascript" src="EasyUI/jquery.easyui.min.js">  </script>
	<script type="text/javascript" src="EasyUI/locale/easyui-lang-zh_CN.js" ></script>
	<link rel="stylesheet" href="EasyUI/themes/default/layout.css" type="text/css"></link>
	<link rel="stylesheet" href="EasyUI/demo/demo.css" type="text/css"></link>
  </head>
<body>
	<form id="userForm" method="post" >
		<table class="ttt"> 
			<tr>
				<td width="115" align="right">用户姓名:</td>
				<td><input id="username" type="text"  name="username" value="${user.username }"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr id="password1">
				<td width="115" align="right">用户密码:</td>
				<td><input id="password" type="text"  name="password" value="${user.password }"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td width="115" align="right">真实姓名:</td>
				<td><input id="realname" type="text"  name="realname" value="${user.realname }"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td width="115" align="right">电话:</td>
				<td><input id="tel" type="text" name="tel" value="${user.tel }"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td width="115" align="right">邮箱 :</td>
				<td><input id="email" type="text"  name="email" value="${user.email }"
					 class="easyui-validatebox" data-options="required:true,validType:'email'" /></td>
			</tr>	
			<tr>
				<td width="115" align="right">角色:</td>
				<td><input id="roleId1" type="text"  name="roleId" /></td>
			</tr>
			<tr>
				<td width="115" align="right">状态:</td>
				<td>
					<select id="state" name="state" 
						class="easyui-combobox" data-options="required:true,editable : false, width : 150"  >
						<option value = "0">启用</option>
						<option value = "1">禁用</option>
					</select>
				</td>
			</tr>
			<input type="hidden" name="userid" value="${user.userid }"/>
		</table>
	</form>
	<script>
		$(function(){
			$("#roleId1").combobox({
				url : "roleFindAll1.action",
				valueField: 'roleId',    
				textField: 'roleName',    
				editable : false,
				onLoadSuccess: function () { //加载完成后,设置选中第一项
					if("${update}" == "true"){
						$("#roleId1").combobox('setValue', '${user.roleId}');
					}

				} 
			});
			
			if("${update}" == "true"){
				$("#password1").remove();
			}
			
		})
	</script>
</body>
</html>