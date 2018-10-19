<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
		 href="ext-5.0.0/packages/ext-theme-classic/build/resources/ext-theme-classic-all.css">
	<script type="text/javascript" src="ext-5.0.0/ext-all.js"> </script>
	<script type="text/javascript" 
		src="ext-5.0.0/packages/ext-theme-classic/build/ext-theme-classic.js"></script>
</head>
<body>
	<div id="panel">
	</div>
<!-- 	<div id="content">
		<table id="tabel4" style="width : 200px;" border="1">
		<tr>
			<td>11 &nbsp;</td>
			<td>12 &nbsp;</td>
			<td>13 &nbsp;</td>
		</tr>
		<tr>
			<td>21 &nbsp;</td>
			<td>22 &nbsp;</td>
			<td>23 &nbsp;</td>
		</tr>
		<tr>
			<td>31 &nbsp;</td>
			<td>32 &nbsp;</td>
			<td>33 &nbsp;</td>
		</tr>
	</table>
	</div> -->
</body>
<script>
	Ext.onReady(function(){
		var panel = Ext.create('Ext.panel.Panel',{
			renderTo : 'panel',
			title : 'panel面板',
			id : 'panels',
			height : 300,
			width : 400,
			autoScroll : true,
			//collapsed : true,  //默认面板是折叠的
			collapsible : true,		//panel 可以折叠
			tbar : [
				{
					text : '查询',
					
				}
			],
			bbar : [{
				text : '退出',
			}],
			tools : [
				{id : 'toggle',},
				{id : 'close',},		//关闭
				{id : 'maximize',},		//最大化
				{id : 'minimize',},		//最小化
				{id : 'restore',},		
				{id : 'pin',},			//
				{id : 'left',},
				{id : 'right'},
				{id : 'up'},
				{id : 'down'},
				{id : 'refresh'},
				{id : 'plus'},
				{id : 'search'},
				{id : 'print'},
				{id : 'help'},
				{id : 'save'},
				{id : 'expand'},
			],
			//html : '这是panel',	
			autoLoad : 'panelContent.jsp',	//自动加载页面
			// contentEl : 'content',
		});
		
		
		var win = new Ext.Window({
			title : '注册',
			width : 450,
			height : 350,
			items : [
					panel
			]
		}).show();
	});
</script>
</html>