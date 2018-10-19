<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
	<title>用户列表页</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../common/common.jsp"></jsp:include> 
  </head>
<body>
	<table id="data" ></table>
	<script type="text/javascript">
	var roleStore = Ext.create('Ext.data.Store', {
		fields: ['roleId', 'roleName','mname'],
		// model: 'role',
		 autoLoad: false,
		 proxy : {
			type : 'ajax', 
			url : 'roleFindAll1.action',
		 },
	});
	
	/* roleStore.load({
		  scope: this,
		  callback: function (records, operation, success) {
			  var index = roleStore.find('roleId','${user.roleId}');
			  Ext.getCmp("roleId").select(roleStore.getAt(index));
		  }
	}); */
	
	
	
		var store = Ext.create('Ext.data.JsonStore', {
		    storeId:'userStore',
		    pageSize: 10,
		    fields:['userid', 'username','tel', 'realname', 'role', 'email', 'phone'],
		    proxy: {
			         type: 'ajax',
			         url: 'userPaging.action',
			         reader: {
			             type: 'json',
			             root:"rows",
	                     totalProperty: 'total'
			         }
			     },
			     //autoLoad: true
		});	
	 	store.loadPage(1);
	 	
	 	
		var grid = Ext.create("Ext.grid.Panel", {
	        stripeRows : true,		//隔行换色
	        store : store,
			id : 'userDataGrid',
			viewConfig : {
				forceFit : true,
			}, 
			columnLines: true,
			// selModel:Ext.create('Ext.selection.CheckboxModel',{mode:"SIMPLE"}),
			margin : 15,
			 columns : [
			    new Ext.grid.RowNumberer(),		//显示行号
			   
				{ header : '姓名', dataIndex : 'username',flex: 1.2},
				{ header : '邮箱', dataIndex : 'email',flex: 1.5 },
				{ header : '真实姓名', dataIndex : 'realname',flex: 1.5},
				{ header : '电话', dataIndex : 'tel',flex: 1.5},
				{ header : "角色", dataIndex : 'role',flex: 1.1,
					renderer : function(value, cellmeta, record, rowIndex, columnIndex, store){
						if(value.roleName){
							return value.roleName;
						}
					}	
				},
				{header : " ", dataIndex : 'userid', buttonAlign : 'center',flex: 1.2,
					renderer : function(value, cellmeta, record, rowIndex, columnIndex, store){
						var btn = "";
						btn += '<a class="a" href="javascript:edituser('+value+');">编辑</a>&nbsp;&nbsp;';
						btn += '<a class="a" href="javascript:deluser('+value+');">删除</a>';
						return btn;
					}	
				},
			], 
			tbar : [
				{
					text:"添加",
					handler : function(){
						edituser(0);
					},
				},
				 {  
	                text: '刷新',  
	                handler : function(){
	                	//getCheckData();
	                	reload();
	                }
	            }  
			],
			 bbar: Ext.create('Ext.PagingToolbar', {
                 store: store,
                 displayInfo: true,
                 displayMsg: '显示{0}-{1}条，共{2}条',
                 emptyMsg: "没有数据"
             }),
			 listeners: {
			        itemclick: function (me, record, item, index, e, eOpts) {
			        	
			        }
			    },
		});
		
		Ext.onReady(function(){
			grid.render(Ext.getBody());
			
		});
		
		function getCheckData(){
			
		}
		
		function deluser(userId){
			console.log("接受到的userid :" + userId );
			Ext.MessageBox.show({
				title : '提示',
				message : '确定删除？',
				buttons: Ext.Msg.YESNO,
				icon : Ext.Msg.QUESTION,
				fn : function(btn){
					  if(btn == 'yes'){
						  Ext.Ajax.request({
								method:"POST",
								url : "userDelete.action?userId="+userId,
								success: function(response){
									reload();
							       // var text = response.responseText;
							    }
							});
					  }
				}
			});
		}
		
		function reload(){
			var g = Ext.getCmp("userDataGrid");
			g.getStore().reload();
		}
		
		function submit(){
			var q = Ext.getCmp("userInfo").getForm();
			var userid;
			console.log(q.getValues());
			 q.submit({
        		waitMsg: '正在提交数据',
        		waitTitle: '提示', 
        		method: 'POST',
        		url : 'userSave.action',
        		params:q.getValues(),
        		success : function(aa,action){
                	console.log("success");
                    console.log(action.result);
                    if(action.result.num > 0){
                    	Ext.Msg.alert("提示","提交成功");
                    	Ext.getCmp("myWin").destroy();
                        reload();	
                    }
                    else{
                    	Ext.Msg.alert("提示","保存失败，请重试");
                    }
                    
                },
               failure : function(aa,action) {
                	console.log("failure");
                	Ext.Msg.alert("提示","保存失败，请重试");
                },
               /* scope : this */
			});  
			
			
			
			
		}
		
		
		function edituser(userid){	
			userid = userid || 0;
			var title = "添加用户";
			if(userid > 0){
				title = "更改用户";
			}
			
			Ext.Ajax.request({
			    url: 'userEdit.action',
			    params: {
			    	userId: userid
			    },
			    success: function(response){
					var user = Ext.JSON.decode(response.responseText);
					if(user.userid !=0){
						roleStore.load({
					 		scope: this,
					 		callback: function (records, operation, success) {
					 			var index = roleStore.find('roleId',user.roleId);
					 			Ext.getCmp("roleId").select(roleStore.getAt(index));
								Ext.getCmp("password").hide();
					 			Ext.getCmp("userid").hide();
					 		  }
					 	});
			       } 
			       
			       var win = Ext.create("Ext.window.Window", {
						title : title,
						id: "myWin",
						height : 300,
						modal : true,
						width : 400,
						resizable : false,
						//closeAction: 'close',
					    renderTo:Ext.getBody(), 
						layout: "fit",
						items : [
							{
								 //labelAlign: 'center', 
								xtype : 'form',
								id : 'userInfo',
								method: 'POST',
				        		url : 'userSave.action',
							    layout: "vbox", 
								defaultType: 'textfield',
								margin : 10,
								height : 200,
								items : [{
									margin : 5,
									fieldLabel: '用户姓名',
							        name: 'username',
							        id : 'username',
							        value : user.username,
							        allowBlank: false
								}, {
									margin : 5,
									fieldLabel: '密码',
							        name: 'password',
							        id : 'password',
							        inputType : 'password' 
								}, {
									margin : 5,
									fieldLabel: '真实姓名',
							        name: 'realname',
							        id : 'realname',
							        value : user.realname,
							        allowBlank: false
								}, {
									margin : 5,
									fieldLabel: '电话',
							        name: 'tel',
							        id : 'tel',
							        value : user.tel,
							        regex : /^1[3|4|5|8][0-9]\d{8}$/,
							        regexText : '该输入项必须是手机号码',
							        allowBlank: false
								}, {
									margin : 5,
									fieldLabel: '邮箱',
							        name: 'email',
							        id : 'email',
							        value : user.email,
							        allowBlank: false,
							        vtype: 'email'  
								},{
									margin : 5,
									fieldLabel : '角色',
									name : 'roleId',
									id : 'roleId',
									xtype : 'combo',
									triggerAction:'all',
									displayField: 'roleName',
									editable:false,
									allowBlank: false,
									valueField: 'roleId',
									store : roleStore,
								},{
									margin : 5,
									fieldLabel : '状态',
									name : 'state',
									id : 'state',
									xtype : 'combo',
									triggerAction:'all',
									queryMode : 'local',
									allowBlank: false,
									editable:false,
									displayField: 'sname',
									valueField: 'sid',
									store : stateStore,
									listeners : {
										afterrender: function(c) {  
											Ext.getCmp('state').setValue(stateStore.getAt(0)); 
										}
									}, 
								},{
									fieldLabel: 'id',
							        name: 'userid',
							        id : 'userid',
							        value : user.userid
							        
								}], 
							}
						],
						beforeshow : function(){
							
						},
						buttons: [
						    {
						    	xtype: "button", text: "确定",
						        handler : function(){
						        	submit();
						        }
							},
					        { xtype: "button", text: "取消", handler: function () { this.up("window").destroy(); } }
						],	
					}).show();
			       Ext.getCmp("userid").hide();
			    }
			});
		}
	</script>
</body>
</html>