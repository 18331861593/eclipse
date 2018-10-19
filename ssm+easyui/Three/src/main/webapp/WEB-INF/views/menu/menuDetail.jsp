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
	<script>
		$(function(){
			loadData();
		})
		
		var d = $("<div></div>").appendTo("body");
		
		//删除		
		function del(id,pid){
			$.messager.confirm("信息提示","确定删除？",function(r){  
				if(r){
					$.ajax({
						type:"post",
						url : "menuDelete.action?mid="+id+"&pid="+pid
					});
					$("#dg_project").treegrid("reload");
				}
				$("#dg_project").treegrid("reload");
			});
			$("#dg_project").treegrid("reload");
		}
		
		//添加
		function add(mid,pid,a){
			mid = mid || 0;
			pid = pid || 0;
			//a == 0 添加本级  a == 1 添加下级 a == 2 添加上级
			d.dialog({
				title : "添加菜单",
				closed: false,
				href : "menuEdit.action?add=true&mid=" + mid + "&pid=" + pid + '&a=' + a,
	 			modal:true,
	 			top : 100,
	 			width: 450,
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
		
		//更改
		function update(mid,pid){
			mid = mid || 0;
			pid = pid || 0;
			d.dialog({
				title : "修改菜单",
				closed: false,
				href : "menuEdit.action?add=false&mid=" + mid + "&pid=" + pid+ "&a=-1",
	 			modal:true,
	 			top : 100,
	 			width: 450,
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
		
		function onSave(){
			console.log("menuForm  sumbit");
			$("#menuForm").form("submit",{
				url:"menuUpdate.action",
				success:function(data){
					if(typeof data == "string"){
						data = eval("("+data+")");
					}
					if(data.num > 0){
						d.dialog("close");
						$("#dg_project").treegrid("reload");
					}
					else{
						$.messager.alert("信息提示","添加失败，请重试","info");
					}
				}
			});
		}
		
		//加载数据
		function loadData(){
			$("#dg_project").treegrid({
				title : "菜单列表",
				iconCls:"icon-search",
				url : 'menuFindAll.action',
				idField:'id',
				treeField:'text',
				parentField : 'parentId',
				striped : true,
				fitColumns : false,  
				columns : [[
					{title:'菜单名称',field:'text',width:170},    
					{title:'菜单地址',field:'url',width:280},    
					/* {title:'菜单图标',field:'iconCls',width:100,align : 'center',
						formatter : function(value,row,index){
							if(row.iconCls != null){
								//return '<img src="'+row.micon+'"/>';
								return '<img src="EasyUI/themes/icons/'+row.iconCls+'" />';
							}
						}	
					},     */
					{title:'菜单状态',field:'mstate',width:120,align : 'center',
						formatter: function(value,row,index){
							if(row.mstate == 0){
								return "启用";
							}
							else{
								return "禁用";
							}
						}	
					},
					{title : ' ', field : 'id',width : 220,align : 'center',
						formatter : function (value, row,index){
							var result = "";
							result += "<a onclick='update("+row.id+");' >修改</a>&nbsp;";
							result += "<a onclick='add("+row.id+','+row.parentId+ ',0' + ");' >添加本级</a>&nbsp;";
							if(row.parentId == 0){
								result += "<a onclick='add("+row.id+','+row.parentId+ ',1' + ");' >添加下级</a>&nbsp;";
							}
							else{
								result += "<a onclick='add("+row.id+','+row.parentId+ ',2' +");' >添加上级</a>&nbsp;";
							}
							result += "<a onclick='del("+row.id+','+row.parentId+");' >删除</a>";
							return result;
						}
					}
				]]
			});
		}
	</script>
</body>
</html>
