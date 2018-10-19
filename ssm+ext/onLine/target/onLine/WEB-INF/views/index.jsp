<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>Insert title here</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="common/common.jsp"></jsp:include>
<body>	
	<div id="panel"></div>
	
	
	<div id="top">
		<h3 style="text-align:center;align : center;line-height:21px;"></h3>
			<h5 >
				用户登录:<span style="color:red;" id="loginname">${user.username}</span>
			</h5>
	</div>
</body>
<script type="text/javascript">
	Ext.onReady(function(){
		
		// 定义树节点数据模型
		var tm = Ext.define("TreeModel",{
			extend : "Ext.data.Model",
			field : [
				{name : "id",type : "string"},
				{name : "text", type : "string"},
				{name : 'url',type:"string"},
			],
		});
		
		var store = Ext.create("Ext.data.TreeStore",{
			model : tm,
			proxy : {
				type : 'ajax',
				url : "menuFind.action?roleId=1",
				reader : {type : 'json'},
			},
		});
		
		var vp = new Ext.Viewport({
		    layout: {
		        type: 'border'
		    },
			items : [
				{
					region : 'south',
					xtype : 'component',
					cls : '',
					padding : '0 1 3 10',
					height : 30,
					html : '123'
				}, {
					region : 'north',
					xtype : 'component',
					padding : 10,
					height : 60,
					cls : 'appBanner',
					contentEl : 'top'
				},
				{
					xtype : 'treepanel',
					region : 'west',
					title : 'west',
					rootVisible:false, 
					width: 260,
					split : true,
					collapsible : true,		//显示折叠按钮
					//store: store,
					store : new Ext.create("Ext.data.TreeStore",{
						model : new Ext.define("TreeModel",{
							extend : 'Ext.data.Model',
							field : [
								{name : "id",type : "string"},
								{name : "text", type : "string"},
								{name : 'url',type:"string"},
								{name : 'state', type : 'string'},
							],
						}),
						proxy : {
							type : 'ajax',
							url : 'menuFind.action?roleId=1',
							ready : 'json',
						},
					}),
					animate : true,	//显示动画
					enabelDD : false,	//是否拖拽操作
					listeners : {
						itemclick : function(view,record,item,index,e){
							if(record.childNodes.length == 0){
								var tid = record.data.url.split(".")[0];
								var title = record.data.text;
								var url = record.data.url;
								addExtTab(tid,title,url);
							}
						}
					},
					//collapsed : true,		//加载是 panel 关闭
					
				},{
					region : 'center',
					title : 'center',
					id : 'tabPanels',
					padding : '0 10 0 0' ,
					xtype : 'tabpanel',
					items : [
						]
				}
			]
		})
	});
	
	
	
	function addExtTab(tid,title, url){
		var tab = Ext.getCmp(tid);
		var mainTab = Ext.getCmp("tabPanels");
		if(!tab){
			 var tp = new Ext.TabPanel({
				id : tid,		//设置id 避免tab 重复
				title : title,	//设置 标题
				closable : true,	//显示关闭按钮
				layout : 'flt',			
				html:'<iframe id="sub" name="sub" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>',
			});
			 mainTab.add(tp).show();
		}
		mainTab.setActiveTab(tid);
	}

</script>
</html>
