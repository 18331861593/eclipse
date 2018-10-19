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
	<div id="getFun"></div>
</body>
<script type="text/javascript">
	Ext.onReady(function(){
		//get 函数
		var getF = Ext.get("getFun");	
		//alert(getF);
		console.log(getF);
	
		//alert("加载完成");
		
		new Ext.Window({
			title : 'getCmp',
			id : 'cmp',
			width : 500,
			height : 150,
			items : [
				{
					xtype : 'textfield',
					id : 'username',
					name : 'username',
					fieldLabel : '用户名'
				},{
					xtype : 'datefield',
					id : 'birthday',
					format : 'Y-m-d',
					fieldLabel : '生日' 
				}
			],
			buttons : [
				{ 
					text : '保存',
					handler : function(){
						if(Ext.getCmp("username").getValue() == ""){
							alert("用户名不能为空");
							return;		
						}
						console.log("用户名是 ： " + Ext.getCmp("username").getValue());
					}
				}, { 
					text : '修改标题',
					handler : function(){
						Ext.getCmp("cmp").setTitle("123");
					} 
				},{
					text : "each函数",
					handler : function(){
						var arr = new Array();
						for(var i = 0; i< 10; i++){
							var r = parseInt(Math.random()*10);
							arr[i] = i + r;
						}
						console.log(arr);
						var sum = 0;
						Ext.each(arr,function(value,index,arr){
							if(index == 4){
								return false;
							}
							console.log('第' + index + "个值是 ： " + value);
							sum += value;
						});
						console.log(sum);
					}
				},{
					text : 'json decode处理',
					handler : function(){
						var json = "{name : '张三', age : 7, sex : '男', address : '北京市'}";
						console.log(json);
						var j = Ext.JSON.decode(json);
						console.log(j.name);
						
						var jsStr = Ext.JSON.encode(j);
						alert(jsStr);
						var jsss = decode(jsss, "UTF-8");
						console.log(jsss);
						Ext.getCmp("username").setValue(jsStr);
						
					}
				} ,{
					text : 'apply 函数',
					handler : function(){
						var old = {};
						old.tool = "123";
						var news = {};
						news.name = "张三";
						
						news.age = 200;
						news.address = "花果山";
						Ext.apply(old,news);
						console.log(Ext.JSON.encode(old));
					}
				},{
					text : 'date 函数',
					handler : function(){
						var birth = Ext.util.Format.date(Ext.getCmp("birthday").getValue(),'Y-m-d');
						alert(birth);
					}
				}
			]
		}).show();
		
	})
</script>
</html>