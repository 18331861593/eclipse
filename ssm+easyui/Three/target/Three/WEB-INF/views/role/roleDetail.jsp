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
	<script type="text/javascript" src="<%=basePath%>WEB-INF\views\role/role.js"></script>
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
	
		function del(roleId){
			$.messager.confirm("信息提示","确定删除？",function(r){  
				if(r){
					$.ajax({
						type:"post",
						url : "roleDelete.action?roleId="+roleId
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
				title : "菜单列表",
				iconCls:"icon-search",
				url:"roleFindAll.action",
				method : "post",
				rownumbers:true,
				striped : true,
				fitColumns : false,  
				columns:[[ 
					{title:"角色名称",field:"roleName",width:105},
					{title:"权限",field:"mname",width:450},
					{title : ' ', field : 'roleId',width : 200,align : 'center',
						formatter : function (value, row,index){
							var result = "";
							result += "<a onclick='update("+row.roleId+");' >修改角色名称</a>&nbsp;";
							result += "<a onclick='menu("+row.roleId+");' >分配权限</a>&nbsp;";
							result += "<a onclick='del("+row.roleId+");' >删除</a>";
							return result;
						}
					}
					]], 
			     //工具栏
				toolbar: [{
					text:"添加",
				    iconCls: "icon-add",
				    handler: function(){
						update(0);
					}
				}]
			})
		}
		
		//更改
		function update(roleId){
			roleId = roleId || 0;
			var title = "添加角色";
			if(roleId > 0){
				title = "更改角色";
			}
			d.dialog({
				title : title,
				closed: false,
				href : "roleEdit.action?roleId="+roleId,
	 			modal:true,
	 			top : 100,
	 			width: 400,
	 			height : 140,
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
		
		//
		function menu(roleId){
			d.dialog({
				title : "权限配置",
				closed: false,
				href : "menuTree.action?roleId="+roleId,
	 			modal:true,
	 			top : 100,
	 			width:280,
				height:400,
				onClose:function(){
	 				console.log("dialog destroy");
	 				$(this).dialog("destroy");
	 			},
	 			buttons : [
					{
						text:'确定',
						iconCls: 'icon-ok',
						handler: function(){
							menuTreeUpdate(roleId);
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
		
		//
		function menuTreeUpdate(roleId){
			  var a = $("#menu_tree").tree('getChecked');
			//先删除权限
			$.ajax({
				type : 'post',
				url : 'menuRoleDelete.action?roleId='+roleId,
				async: false
			}); 
			var content="";
			var cnode="";
			console.log(a);
			for(var i = 0; i<a.length; i++){
				cnode=a[i].id;
			    content+=a[i].text+" ";
			    $.ajax({
			        type:"get",
			        async: false,
			        url:"menuInsert.action?rid="+roleId+"&mid="+cnode
				})
			}
			$.ajax({
			    type:"post",
			    async: false,
			    url:"menuNameUpdate.action?mname="+encodeURI(content)+"&roleId="+roleId
		    })
		    d.dialog('close');
			$("#dg_project").datagrid("reload");
		}
		
		
		//保存
		function onSave(){
			console.log("roleForm  sumbit");
			$("#roleForm").form("submit",{
				url:"roleUpdate.action",
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