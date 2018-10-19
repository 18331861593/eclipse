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
	<jsp:include page="../common/common.jsp"></jsp:include> 
  </head>
<body>
<!-- <div id="userInfo"></div> -->
	<script>
		Ext.onReady(function(){
			
			var roleStore = Ext.create('Ext.data.Store', {
				fields: ['roleId', 'roleName','mname'],
				// model: 'role',
				 autoLoad: false,
				 proxy : {
					type : 'ajax', 
					url : 'roleFindAll1.action',
				 },
			});
			
			var stateStore = new Ext.data.Store({
				fields : ["sname","sid"],
				data : [
					{'sid' : '0', 'sname' : '启用'},
					{'sid' : '1', 'sname' : '禁用'},
				],
			});
			
			roleStore.load({
				  scope: this,
				  callback: function (records, operation, success) {
					  var index = roleStore.find('roleId','${user.roleId}');
					  Ext.getCmp("roleId").select(roleStore.getAt(index));
				  }
			});
			
			
			var userinfo = Ext.create("Ext.form.Panel",{
				renderTo: Ext.getBody(),
				margin : 10,
				height : 200,
				id : 'userInfo',
				defaultType: 'textfield',
				items : [
					{
						fieldLabel: '用户姓名',
				        name: 'username',
				        id : 'username',
				        value : '${user.username}',
				        allowBlank: false
					}, {
						fieldLabel: '密码',
				        name: 'password',
				        id : 'password',
				        allowBlank: false,
				        value : '${user.password}',
				        inputType : 'password' 
					}, {
						fieldLabel: '真实姓名',
				        name: 'realname',
				        id : 'realname',
				        value : '${user.realname}',
				        allowBlank: false
					}, {
						fieldLabel: '电话',
				        name: 'tel',
				        id : 'tel',
				        value : '${user.tel}',
				        regex : /^1[3|4|5|8][0-9]\d{8}$/,
				        regexText : '该输入项必须是手机号码',
				        allowBlank: false
					}, {
						fieldLabel: '邮箱',
				        name: 'email',
				        id : 'email',
				        value : '${user.email}',
				        allowBlank: false,
				        readOnly : true,
				        vtype: 'email'  
					},{
						fieldLabel : '角色',
						name : 'roleId',
						id : 'roleId',
						xtype : 'combo',
						triggerAction:'all',
						displayField: 'roleName',
						editable:false,
						valueField: 'roleId',
						store : roleStore,
						/* listeners : {
							afterrender: function(c) {  
								
							}
						}, */
						
					   
					},{
						fieldLabel : '状态',
						name : 'state',
						id : 'state',
						xtype : 'combo',
						triggerAction:'all',
						queryMode : 'local',
						editable:false,
						displayField: 'sname',
						valueField: 'sid',
						store : stateStore,
						listeners : {
							afterrender: function(c) {  
								Ext.getCmp('state').setValue(stateStore.getAt(0)); 
							}
						}, 
						
					}
					
				], 
			});
			
			if('${user.userid}' != ''){
				Ext.getCmp("password").hide();
			}
			
		});
	</script>
</body>
</html>