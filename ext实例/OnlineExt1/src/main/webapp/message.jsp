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
	<script type="text/javascript" src="ext-5.0.0/ext-all.js"> </script>
	<script type="text/javascript" 
		src="ext-5.0.0/packages/ext-theme-classic/build/ext-theme-classic.js"></script>
	<script type="text/javascript" src="scripts/hello.js"></script>
<body>

</body>
<script>
Ext.onReady(function(){
	
	/* Ext.MessageBox.alert("hello","zhangsan",function(){
		alert("alert关闭");
	},this); */
	
	/*  Ext.MessageBox.confirm("hello","my first Extjs Application",function(type){
		alert(type);
		alert("confirm关闭");
	},this);  */
	
	/* Ext.MessageBox.prompt("hello","my first Extjs Application",function(btn, text){
		console.log(btn);
		console.log(text);
	},this,false,'请输入留言'); */
	
	//Ext.MessageBox.wait('请等待','正在出来中',this);
	/* var timer = Ext.MessageBox.show({
		title : '当前时间',
		width : 300,
		msg : '时间是',
		buttons : Ext.Msg.YESNO,
		fn : function(btn){
			if(btn == 'yes')
				Ext.TaskManager.stop(t);
		}
	});
	
	var t = {
		run : function(){
			timer.updateText('当前时间是 ： ' + Ext.util.Format.date(new Date(),'Y-m-d g:i:s'));
		},
		interval : 1000
	};
	Ext.TaskManager.start(t); */
	
	var progress = Ext.MessageBox.show({
		title : '进度条',
		width : 300,
		progress : true,
		msg : '当前进度',
	});
	var index = 0;
	var t = {
		run : function(){
			progress.updateProgress(index/100, "当前已完成" +index+ "%" , '请等待');
			index += 5
			if(index > 100){
				Ext.TaskManager.stop(t);
			}
		},
		interval : 500
	};
	Ext.TaskManager.start(t);
	
	/*Ext.create('Ext.Panel',{
		width : '100%',
		height : '100%',
		title : 'border 布局',
		layout : 'border',
		renderTo : Ext.getBody(),
		items : [
			{
				xtype : 'panel',
				//title : '第一个',
				html : '中间的面板',
				region : 'center',		//子组件1所在方位
			},
			{
				xtype : 'panel',
				//title : '第一个',
				html : '北方的面板',
				region : 'north',		//子组件1所在方位
				height  : 50,
			},
			{
				xtype : 'panel'	,
				html : '西方的面板',
				region : 'west',
				width : 120,
			},
			{
				xtype : 'panel'	,
				html : '东方的面板',
				region : 'east',
				
				width : 120,
			},
			{
				xtype : 'panel'	,
				html : '南方的面板',
				region : 'south',
				height : 60,
			}
		
		],
	}); */
	
});
</script>
<body>

</body>
</html>