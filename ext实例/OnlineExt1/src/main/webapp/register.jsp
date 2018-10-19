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
	<script type="text/javascript" >
		Ext.onReady(function(){
			
			//创建数据模型
			/* Ext.regModel('placeStore1',{
				extend:"Ext.data.Model",
				fields : [
					{
						name : 'palceName',type:"string"
					},{
						name : 'palceValue',type:"string"
					}          
				],
			});
			
			//定义 combo 中的数据源
			 */
			
			
			var form = new Ext.form.FormPanel({
				title : '用户注册',
				id : 'userRegster',
				//renderTo : Ext.getBody(),
				renderTo : 'regster',
				width : 450,
				height : 500,
				items : [
					{
						xtype : 'textfield',
						fieldLabel : '用户名',
						allowBlank : false,
						labelStyle : "text-align:right;",
						blankText : '用户名不能为空',	  
						emptyText : '请输入用户名',
						/* regex : /^\d{3,10}$/,
						regexText : '用户名必须是3到10位的数字', */
						name : 'username',
						id : 'username' 
					}, {
						xtype : 'textfield',
						fieldLabel : '密码',
						labelStyle : "text-align:right;",
						allowBlank : false,
						blankText : '密码不能为空',	
						name : 'password',
						emptyText : '请输入密码',	//相等于 input  placeholder
						id : 'password',
						inputType : 'password'
					},{
						xtype : 'textfield',
						fieldLabel : '真实姓名',
						labelStyle : "text-align:right;",
						allowBlank : false,
						blankText : '真实姓名不能为空',	
						name : 'realName',
						emptyText : '请输入真实姓名',
						id : 'realName',
					}, {
						xtype : 'textfield',
						fieldLabel : '手机号码', 
						allowBlank : false,		//allowBlank 是否允许为空 false 不允许为空 
						blankText : '手机号码不能为空',
						emptyText : '请输入手机号码',	
						name : 'tel',
						labelStyle : "text-align:right;",
						id : 'tel',
						regex : /^1[3|4|5|8][0-9]\d{8}$/,	//正则验证 电话号码
						regexText : '手机号码输入不正确',		//正则验证错误的提示
						//inputType : 'email',
					},{
						fieldLabel : '籍贯', 
						xtype : 'combo',
						id : 'place',
						name : 'place',
					//	store : placeStore,
						queryMode : 'local',
						triggerAction : 'all',
						displayField : 'placeName',
						valueField : 'placeValue',
					}
					
					 ,{
						xtype : 'radiogroup',
						fieldLabel : '性别',
						columns : 2,
						items : [{
							name : 'sex',
							boxLabel : '男',
						},{
							name : 'sex',
							boxLabel : '女',
						}]						
					},{
						xtype : 'checkboxgroup',
						fieldLabel : 'sd',
						items : [
							{
								boxLabel : '1',
							},{
								boxLabel : '2',
							},{
								boxLabel : '3',
							}
							
						],
					},{
						xtype : 'combo',
						fieldLabel : '下拉框',
						readOnly : true,
						
					},{
						xtype : 'timefield',
						id : 'logintime',
						fieldLabel : '时间',
						increment : 20,			//更改两个相邻的时间间隔
						/* maxValue : '19:00',
						maxText : '不能晚于19点', */
					},{
						xtype : 'datefield',
						id : 'birthday',
						name : 'birthday',
						fieldLabel : '日期',
						format : 'Y-m-d',
					}
				],
				buttons : [
					{
						text : '注册',
						
					},
					{
						text : '取消',
					}
				
				]
			});
		});
	</script>
</head>
<body>
	<div id="regster"></div>
</body>
</html>