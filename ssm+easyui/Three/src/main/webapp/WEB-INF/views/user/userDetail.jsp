<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
	<title>菜单列表页</title>   
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
	<style type="text/css">
		.partition{ width:820px;height:25px;}
		.size{text-align:center;}
		*{font-size : 12px;}
	</style>
  </head>
<body>
	<div class="partition"></div>
	<div id="dialog1"></div>
	<table id="dg_project"  style="width:820px;"></table>
	<script type="text/javascript">  
		var d = $("<div></div>").appendTo("body");	
	
		function del(userId){
			$.messager.confirm("信息提示","确定删除？",function(r){  
				if(r){
					$.ajax({
						type:"post",
						url : "userDelete.action?userId="+userId
					});
					$("#dg_project").datagrid("reload");
				}
				$("#dg_project").datagrid("reload");
			});
			$("#dg_project").datagrid("reload");
	    } 
	     
		$(function(){
			loadData();
		})
	     
	     /*进入页面即加载*/
		function loadData(){
			$("#dg_project").datagrid({
				title : "用户列表",
				iconCls:"icon-search",
				url:"userPaging.action",
				method : "post",
				pagination : true,
				pageList: [20,50, 100, 200],
				rownumbers:true,
				striped : true,
				fitColumns : false,  
				columns:[[ 
					{title:"用户名称",field:"username",width:80},
					{title:"真实姓名",field:"realname",width:100},
					{title:"电话",field:"tel",width:130},
					{title:"邮箱",field:"email",width:200},
					{title:"角色",field:"roleId",width:140,
						formatter : function(value,row,index){
							if(row.role){
								return row.role.roleName;
							}
						}	
					},
					 {title : ' ', field : 'userid',width : 110,align : 'center',
						formatter : function (value, row,index){
							var result = "";
							result += "<a onclick='userUpdate("+row.userid+");' >修改</a>&nbsp;";
							result += "<a onclick='del("+row.userid+");' >删除</a>";
							return result;
						}
					} 
					]], 
			     //工具栏
				toolbar: [{
					text:"添加",
				    iconCls: "icon-add",
				    handler: function(){
				    	userUpdate(0);
					}
				}]
			})
		}
		
		//更改
		function userUpdate(userid){
			userid = userid || 0;
			var title = "添加用户";
			if(userid > 0){
				title = "更改用户";
			}
			d.dialog({
				title : title,
				closed: false,
				href : "userEdit.action?userId="+userid,
	 			modal:true,
	 			top : 100,
	 			width: 400,
	 			onClose:function(){
	 				console.log("dialog destroy");
	 				$(this).dialog("destroy");
	 			},
	 			buttons : [
					{
						text:'确定',
						iconCls: 'icon-ok',
						handler: function(){
							onSave();
						}
					},
					{
						text:'取消',
						iconCls:'icon-edit',
						handler:function(){
							d.dialog("close");
						}
					}
				],
			});
		}
		
		
		
		//保存
		function onSave(){
			console.log("userForm  sumbit");
			$("#userForm").form("submit",{
				url:"userSave.action",
				success:function(data){
					if(typeof data == "string"){
						data = eval("("+data+")");
					}
					if(data.num > 0){
						d.dialog("close");
						$("#dg_project").datagrid("reload");
					}
					else{
						$.messager.alert("信息提示","添加失败，请重试","info");
					}
				}
			});
		}
		
     </script>
</body>
</html>