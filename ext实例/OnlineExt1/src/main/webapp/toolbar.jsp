<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>hello extjs</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
		 href="ext-5.0.0/packages/ext-theme-classic/build/resources/ext-theme-classic-all.css">
	<link rel="stylesheet" type="text/css"
		 href="css/icon.css">
	<script type="text/javascript" src="ext-5.0.0/ext-all.js"> </script>
	<script type="text/javascript" 
		src="ext-5.0.0/packages/ext-theme-classic/build/ext-theme-classic.js"></script>
	<script type="text/javascript">
		Ext.onReady(function(){
			var tool = new Ext.toolbar.Toolbar({
				renderTo : 'boolbar',
				width : 500
			});
			tool.add([
				{
					text : '新建文件',
					handler : btnClick,
					iconCls : 'icon-edit',
					menu : new Ext.menu.Menu({
						shadow : 'drop',
						items : [
							{
								text : '新建word文件',
								icon : 'images/filesave.png',
								menu : new Ext.menu.Menu({
									shadow : 'drop',
									items : [
										{
											icon : 'images/filesave.png',
											text : '新建97-2003文件',
											menu : new Ext.menu.Menu({
												shadow : 'drop',
												items : [
												      {
												    	  text : '颜色',
												    	  menu : new Ext.menu.ColorPicker()
												      },
												      {
												    	  text : '日期',
												    	  menu : new Ext.menu.DatePicker()
												      },
												]
											})
										},
										{
											icon : 'images/filesave.png',
											text : '新建2006文件',
										},
									]
								})
							},      
							{
								icon : 'images/filesave.png',
								text : '新建ppt文件'
							},
							{
								icon : 'images/filesave.png',
								text : '新建txt文件'
							},
						]
						
					})
				}, 
				{
					text : '保存文件',
					handler : btnClick,
					iconCls : 'icon-remove',
					menu : new Ext.menu.Menu({
						shadow : 'drop',
						items : [
							{
								icon : 'images/filesave.png',
								text : '保存word文件'
							},      
							{
								icon : 'images/filesave.png',
								text : '保存ppt文件'
							},
							{
								icon : 'images/filesave.png',
								text : '保存txt文件'
							},
						]
						
					})
				},
				{
					text : '打开文件',
					handler : btnClick,
					iconCls : 'icon-cut',
					menu : new Ext.menu.Menu({
						shadow : 'drop',
						items : [
							{
								icon : 'images/filesave.png',
								text : '打开word文件'
							},      
							{
								
								icon : 'images/filesave.png',
								text : '打开ppt文件'
							},
							{
								icon : 'images/filesave.png',
								text : '打开txt文件'
							},
						]
						
					})
				},'-',
				{
					xtype : 'textfield',
					hideLabel : true,
					width : 100
				},	'->', '<a href="http://www.baidu.com" target="_block">百度</a>','百度'
				
			]);
			
			function btnClick(btn){
				console.log(btn.text);
			}
			
			Ext.get('enable').on("click",function(){
				tool.enable();
			})
			
			Ext.get('disable').on("click",function(){
				tool.disable();
			})
		});
	</script>
<body>
	<div id="boolbar"></div>
	<input type="button" id="enable" value="启用工具栏" />
	<input type="button" id="disable" value="禁用工具栏" />
</body>

</html>
