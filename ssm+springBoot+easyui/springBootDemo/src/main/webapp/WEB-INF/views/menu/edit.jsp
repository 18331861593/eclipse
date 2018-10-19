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
	<form id="menuForm" method="post" >
		<table class="ttt">
			<tr>
				<td width="115" align="right">菜单名称:</td>
				<td><input id="mname" type="text"  name="mname" value="${menu.mname }"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td align="right">菜单地址:</td>
				<td><input type="text" id="murl" name="murl" value="${menu.murl }"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<%-- <tr>
				<td align="right">菜单图标:</td>
				 <td><input type="text" name="micon" id="micon" value="${menu.micon }"/>
				 	<input class="easyui-searchbox" data-options="width:150,searcher:search"/>
				 </td> 
			</tr> --%>
			<tr>
				<td valign="top" align="right">菜单状态:</td>
				<td>
					<select id="state" name="mstate" class="easyui-combobox" data-options="width:150,editable : false">
						<option value="0">启用</option>
						<option value="1">禁用</option>
					</select>
				</td>
			</tr>
			<input type="hidden" name="mid" value="${menu.mid }"/>
			<input type="hidden" name="parentId" value="${menu.parentId }"/>
			<input type="hidden" name="micon" id="micon" value="${menu.micon }"/>
		</table>
	</form>
    <script>
    	$(function(){
    		if('${menu.parentId}' == '0'){
    			$("#murl").validatebox({
    				required:false,
    			});
    		}
    	})
    	
    </script>
</body>
</html>
