/**
 * 用户列表的js脚本
 */
$(function(){
	$("#userList").datagrid({
		title : "user列表",
		iconCls:"icon-search",
		url : "userPaging.action",
		fitColumns : true,
		singleSelect : true,
		striped : true,
		rownumbers : true,
		pagination : true,
		idField : "id", //如果使用了selectRecord方法必须指定的
		columns:[[
		   {field:"id2",checkbox:true },
		   {field:"id", title:"编号", width:50},
		   {field:"roleId", title:"roleId", width:50},
		   
		   {field:"username", title:"username", width:50},
		   
		   {field:"password", title:"password", width:50},
		   
		   {field:"email", title:"email", width:50},
		   
		   {field:"realname", title:"realname", width:50},
		   
		   {field:"tel", title:"tel", width:50},
		   
		   {field:"logintime", title:"logintime", width:50},
		   
		   {field:"state", title:"state", width:50,formatter:function(value,row,index){
		   		if(row.state){
		   			return row.state.name;
		   		}
		   }},
		   
		   {field:"role", title:"role", width:50,formatter:function(value,row,index){
		   		if(row.role){
		   			return row.role.name;
		   		}
		   }}
		   
		]],
		toolbar:[{
			iconCls:"icon-add",
			text : "添加",
			handler:function(){
				addUser();
			}
		},{
			iconCls:"icon-edit",
			text : "编辑",
			handler:function(){
				editUser();
			}
		},{
			iconCls:"icon-remove",
			text : "删除",
			handler:function(){
				deleteUser();
			} 
		}],
		/*loadFilter:function(data){
			var d = {};
			d.total = data.total;
			d.rows = data.userList;
			return d;
		} */
	});
})

function editUser(){
	//获取选中的行，只要一行
	var selRow = $("#userList").datagrid("getSelected");
	$("#userList").datagrid("clearSelections");
	$("#userList").datagrid("selectRecord",selRow.id);
	//没有选中，弹框提示
	if(selRow == null){
		$.messager.alert("提示","请选择要编辑的数据","warning");
		return;
	}
	//弹出编辑的对话框，加载数据
	var d = $("<div></div>").appendTo("body");
	d.dialog({
		title : "编辑用户",
		iconCls:"icon-edit",
		width:600,
		modal : true,
		href : "userEdit.action?id="+selRow.id,
		onClose:function(){$(this).dialog("destroy");},
		onLoad:function(){
			//让dialog垂直居中
			$(this).dialog("vcenter");
			//发送一一步请求，获取要编辑的数据
			$.post("userToEdit.action",{"id":selRow.id},function(data){
				//javascript中的foreach格式循环
				//1. 可以循环字符串 和数组 ： k代表下标
				//2. 循环json对象：k 代表属性名
				var d = {};
				for(var k in data){
					if(typeof data[k] == "object"){
						for(var j in data[k]){
							d[k+"."+j] = data[k][j];
						}
					}
					d[k] = data[k];
				}
				$("#userForm").form("load",d);
			});
		},
		buttons:[{
			iconCls:"icon-ok",
			text : "确定",
			handler:function(){
				$("#userForm").form("submit",{
					url:"userSave.action",
					success:function(data){
						if(typeof data == "string"){
							data = eval("("+data+")");
						}
						if(data.num > 0){
							d.dialog("close");
							$("#userList").datagrid("reload");
						}
					}
				});
			}
		},{
			iconCls:"icon-cancel",
			text : "取消",
			handler:function(){
				d.dialog("close");
			}
		}]
	});
}

function deleteUser(){
	//获取选中的行
	var selRows = $("#userList").datagrid("getSelected");
	//如果没有选中的行，弹框提示
	if(selRows == null){
		$.messager.alert("提示","请选择要删除的数据","warning");
		return;
	}
	$.messager.confirm("提示","确定要删除选中的数据吗",function(r){
		if(r){
			//发送异步请求，批量删除数据，
			$.post("userDelete.action",{id : selRows.id},function(data){
				//删除成功，重新加载datagrid
				$("#userList").datagrid("reload");
			});
		}
		
	});
}


/**
 * 添加
 */
function addUser(){
	var d = $("<div></div>").appendTo("body");
	d.dialog({
		title : "添加用户",
		iconCls:"icon-add",
		width:600,
		modal : true,
		href : "userEdit.action?id=0",
		onClose:function(){$(this).dialog("destroy");},
		onLoad:function(){
			//让dialog垂直居中
			$("#id").val("0");
			$(this).dialog("vcenter");
		},
		buttons:[{
			iconCls:"icon-ok",
			text : "确定",
			handler:function(){
				$("#userForm").form("submit",{
					url:"userSave.action",
					success:function(data){
						if(typeof data == "string"){
							data = eval("("+data+")");
						}
						if(data.num > 0){
							d.dialog("close");
							$("#userList").datagrid("reload");
						}
					}
				});
			}
		},{
			iconCls:"icon-cancel",
			text : "取消",
			handler:function(){
				d.dialog("close");
			}
		}]
	});
}

