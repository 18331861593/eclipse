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
	<jsp:include page="../common/common.jsp"></jsp:include>
  </head>
<body>
	<table id="data"></table>
</body>

    <script type="text/javascript">
	    var dataStore = Ext.create('Ext.data.JsonStore', {
	    	 autoLoad: true,
	        storeId : 'roleStore',
	        fields : ['roleId','roleName','mname'],
	        //page : 10,    //分页
	        proxy : {
	            type : 'ajax',
	            method : "POST",
	            url : "roleFindAll.action",
	            reader : {
	                type : 'json',
	               root : 'rows',
	                totalProperty : 'total',
	            },
	          
	        },
	    });
    	
	   // dataStore.load();
	    
	     var grid = Ext.create("Ext.grid.Panel",{
            stripeRows : true,
            store : dataStore,
            id : 'roleDatagrid',
            viewConfig : {
            	forceFit : true,
            },
            columnLines : true,
            margin : 15,
            columns : [
                new Ext.grid.RowNumberer(),
                {header : '角色名称' ,dataIndex : 'roleName', flex : 1 },
                {header : '权限' ,dataIndex : 'mname', flex :2 },
                {header : ' ', dataIndex : 'roleId', flex : 1.5,align : 'center',
                	renderer : function(value, cellmeta, record, rowIndex, columnIndex, store){
                		var btn = "";
    					btn += '<a class="a" href="javascript:editrole('+value+');">编辑</a>&nbsp;&nbsp;';
    					btn += '<a class="a" href="javascript:delrole('+value+');">删除</a>&nbsp;&nbsp;';
    					btn += '<a class="a" href="javascript:menu('+value+');">分配权限</a>';
    					return btn;
                	}	
                }	
            ],
            tbar : [
            	{
            		text:"添加",
    				handler : function(){
    					editrole(0);
    				},
            	},{
            		text : '刷新',
            		handler : function(){
            			reload();
            		}
            	}        
    		],
        }); 
    	
        Ext.onReady(function(){
        	grid.render(Ext.getBody());
        });
        
        function editrole(roleId){
        	roleId = roleId || 0;
        	var title = "添加角色";
        	if(roleId > 0){
        		title = "更改角色";
        	}
        	Ext.Ajax.request({
        		url : 'roleEdit.action',
        		params : {
        			roleId: roleId,
        		},
        		success: function(response){
        			var role = Ext.JSON.decode(response.responseText);
        			var win = Ext.create("Ext.window.Window",{
        				title : title,
						id: "myWin",
						resizable : false,
						modal : true,
						width: 400,
			 			height : 140,
						renderTo : Ext.getBody(),
						layout: "fit",
						items : [
							{
								//align : 'right',
								xtype : 'form',
								id : 'roleInfo',
								method : 'POST',
								url : 'roleUpdate.action',
								layout : 'vbox',
								defaultType : 'textfield',
								margin : 10,
								height : 200,
								items : [
									{
										margin : 10,
										fieldLabel : '角色名称',
										name : 'roleName',
										id : 'roleName',
										value : role.roleName, 
										allowBlank : true,
									},{
										fieldLabel: 'id',
								        name: 'roleId',
								        id : 'roleId',
								        value : role.roleId
									}      
								]
							}
						],
						buttons : [
							{
								xtype: "button", 
								text : '确定',
								handler : function(){
									submit();
								}
							},{
								xtype : "button", 
								text : '取消',
								handler : function(){
									this.up("window").destroy();
								}
							}           
						],
        			}).show();
        			Ext.getCmp("roleId").hide();
        		}
        	});
        }
        
        function submit(){
        	var form = Ext.getCmp("roleInfo").getForm();
        	console.log(form.getValues());
        	form.submit({
        		waitMsg : '正在提交数据',
        		waitTitle : '提示',
        		params:form.getValues(),
        		success : function(aa,action){
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
                	Ext.Msg.alert("提示","保存失败，请重试");
                },
        	});
        }
        
        function reload(){
        	//dataStore
        	var grid = Ext.getCmp("roleDatagrid");
        	grid.getStore().reload();
        }
        
        function delrole(roleId){
        	Ext.MessageBox.show({
        		title : '提示',
        		message : '是否删除？',
        		buttons : Ext.Msg.YESNO,
        		icon : Ext.Msg.QUESTION,
        		fn : function(btn){
        			if(btn == 'yes'){
        				Ext.Ajax.request({
        					method:"POST",
        					url : 'roleDelete.action?roleId='+roleId,
							success: function(response){
								reload();
								console.log(response.responseText);
							}
        				});
        			}
        		}
        	});
        }
        
        var store = Ext.create("Ext.data.TreeStore",{
			model : tm,
			proxy : {
				type : 'ajax',
				url : "menuFind.action?roleId=1",
				reader : {type : 'json'},
			},
		});
        

		// 定义树节点数据模型
		var tm = Ext.define("TreeModel",{
			extend : "Ext.data.Model",
			field : [
				{name : "id",type : "string"},
				{name : "text", type : "string"},
				{name : 'url',type:"string"},
			],
		});
        
        function menu(roleId){
        	var win = Ext.create("Ext.window.Window",{
        		title : "权限配置",
				id: "myWin",
				modal : true,
				width: 350,
	 			height :400,
				renderTo : Ext.getBody(),
				layout: "fit",
				items : [
					{
						xtype : 'treepanel',
						id : 'roleMenu',
						margin : 10,
						width: 260,
						height : 400,
						rootVisible:false, 
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
								url : 'menuAll.action?roleId='+roleId,
								ready : 'json',
							},
						}),
						animate : true,	//显示动画
						enabelDD : false,	//是否拖拽操作
						listeners : {
							load : function(athis, records, successful, operation, node, eOpts ){
								 for(var i = 0; i < successful.length; i ++){
									var e1 = successful[i].childNodes;
									var count = e1.length;
									var c1 = 0;
									for(var j = 0; j < e1.length; j++){
										var e2 = e1[j];
										if(e2.get("checked") == true){
											c1 ++;
										}
									}  
									console.log("c1 : " + c1);
									console.log("count : " + count);
									if(c1 != count && c1 > 0){
										successful[i].set("cls", "partial_select");
									}
									
								}
								
							},
							checkchange : function(node, checked, eOpts ){
								if(node.childNodes.length > 0){
									var childNodes = node.childNodes;
									Ext.each(childNodes,function(childNode,index,childNodes){
										childNode.set('checked',checked);
									})
								}
								else{
									var childNodes = node.parentNode.childNodes;
									var childCount = node.parentNode.childNodes.length;
									var count = 0;									
									Ext.each(childNodes,function(childNode,index,childNodes){
										if(childNode.get("checked") == checked){
											count ++;
										}
									})
									if(count < childCount){
										node.parentNode.set("checked","true");
										node.parentNode.set("cls", "partial_select");
									}
									else{
										node.parentNode.set("checked",checked);
										node.parentNode.set("cls", "");
									}
								}
							} 
						},
					}
				],
				buttons : [
					{
						xtype: "button", 
						text : '确定',
						handler : function(){
							//submit();
							menuTreeUpdate(roleId);
						}
					},{
						xtype : "button", 
						text : '取消',
						handler : function(){
							this.up("window").destroy();
						}
					}           
				],
			}).show();
        }
        
        function menuTreeUpdate(roleId){
        	Ext.Ajax.request({
        		method:"POST",
        		url : 'menuRoleDelete.action?roleId='+roleId,
        	});
        	var checkeds = Ext.getCmp("roleMenu").getChecked();
        	var content="";
			var cnode="";
			Ext.each(checkeds,function(node,index,a){
				cnode = node.id;
				content  += node.data.text+" ";
				Ext.Ajax.request({
					method:"POST",
     		        async: false,
     		        url:"menuInsert.action?rid="+roleId+"&mid="+cnode
				});
			});
			
			Ext.Ajax.request({
			    method:"POST",
			    async: false,
			    url:"menuNameUpdate.action?mname="+encodeURI(content)+"&roleId="+roleId
		    })
			Ext.getCmp("myWin").destroy();
			reload();
        }
        
        
     </script>
</html>