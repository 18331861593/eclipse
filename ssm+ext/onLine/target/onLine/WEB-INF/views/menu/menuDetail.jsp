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
	<script>
		var treeStore = Ext.create('Ext.data.TreeStore',{
			//model : 'Task',
			proxy : {
				type : 'ajax',
				url : 'menuFindAll1.action',
			},
			fields : ['text','url','mstate','id','iconCls'],
			 folderSort: true 
		});
		
		Ext.onReady(function() {
			var treegrid = Ext.create('Ext.tree.Panel', {  
				useArrows: true,  
				heigth : 400,
		        margin : 15,
				renderTo : Ext.getBody(),
                rootVisible: false,  
                store: treeStore,
               /*  multiSelect: false,  
                singleExpand: true,  */
               // hideHeaders: true,  
              
                columns : [
                	{xtype : 'treecolumn',text : '菜单名称', flex: 2, dataIndex : 'text'},
                	{text : '菜单地址', flex: 2, dataIndex : 'url'},
                	{text:'菜单状态',dataIndex:'mstate',flex : 1,align : 'center',
                		renderer : function(value, cellmeta, record, rowIndex, columnIndex, store){
							if(value == 0){
								return "启用";
							}
							else{
								return "禁用";
							}
						}	
					},
					 {text : ' ', dataIndex : 'id',flex : 1.5,align : 'center',
						renderer : function(value, cellmeta, record, rowIndex, columnIndex, store){
							var row = record.data
						    var result = "";
							result += '<a onclick="update('+row.id+','+"'"+row.parentId+"'" + ');" >修改</a>&nbsp;';
							result += '<a onclick="add('+row.id+','+"'"+row.parentId+"'"+ ',0);" >添加本级</a>&nbsp;';
							if(row.parentId == "root"){
								result += '<a onclick="add('+row.id+','+"'"+row.parentId+"'"+ ',1' + ');" >添加下级</a>&nbsp;';
								
							}
							else{
								result += '<a onclick="add('+row.id+','+"'"+row.parentId+"'"+ ',2' + ');" >添加上级</a>&nbsp;';
							}
							result += '<a onclick="del('+row.id+','+"'"+row.parentId+"'" + ');" >删除</a>';
							return result; 
						}
					} 
				],
			});
		});
		
		
		function reload(){
			treeStore.reload();
		}
		
		//修改
		function update(mid,pid){
			mid = mid || 0;
			if(pid == 'root'){
				pid = 0;
			}
			console.log("stateStore");
			console.log(stateStore);
			Ext.Ajax.request({
				url : "menuEdit.action?add=false&mid=" + mid + "&pid=" + pid+ "&a=-1",
				method : 'POST',
				success : function(response){
					var menu = Ext.JSON.decode(response.responseText);
					var allowblank = true;
					if(menu.parentId == 0){
						allowblank = false;
					}
				
					var win = Ext.create('Ext.window.Window',{
						title : "修改菜单",
						id : 'myWin',
						model : true,
						height : 240,
						width : 400,
						resizable : true,
						layout : 'fit',
						renderTo : Ext.getBody(), 
						items : [
							{
								xtype : 'form',
								id : 'menuInfo',
								method: 'POST',
				        		url : 'menuUpdate.action',
							    layout: "vbox", 
								defaultType: 'textfield',
								margin : 10,
								height : 200,
								items : [
									{
										margin : 5,
										fieldLabel: '菜单名称',
								        name: 'mname',
								        id : 'mname',
								        value : menu.mname,
								        allowBlank: false
									},{
										margin : 5,
										fieldLabel: '菜单地址',
								        name: 'murl',
								        id : 'murl',
								        value : menu.murl,
								        allowBlank: allowblank
									},
									{
										margin : 5,
										fieldLabel : '菜单状态',
										name : 'mstate',
										id : 'mstate',
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
												Ext.getCmp('mstate').setValue(stateStore.getAt(0)); 
											}
										}, 
									}
								],
							}
						]
							
					}).show();
					
				}
			});
		}
		
		//删除		
		function del(id,pid){
			console.log(pid);
			if(pid == 'root'){
				pid = 0;
			}
			Ext.MessageBox.show({
				title : "提示",
				message : "确定删除？",
				buttons : Ext.Msg.YESNO,
				icon : Ext.Msg.YESNO,
				fn : function(btn){
					if(btn == 'yes'){
						Ext.Ajax.request({
							method : "POST", 
							url : "menuDelete.action?mid="+id+"&pid="+pid,
							success: function(response){
								reload();
								console.log(response.responseText);
							}
						});
					}
				}
					
					
			});
		}
	</script>
</body>
</html>
