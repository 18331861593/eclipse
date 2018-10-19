/**
 * 用户列表的js脚本
 */
$(function(){
	$("#roleList").datagrid({
		title : "role列表",
		iconCls:"icon-search",
		url : "rolePaging.action",
		fitColumns : true,
		striped : true,
		rownumbers : true,
		pagination : true,
		idField : "id", //如果使用了selectRecord方法必须指定的
		columns:[[
		   {field:"id2",checkbox:true },
		   {field:"id", title:"编号", width:50},
		   {field:"roleName", title:"roleName", width:50},
		   
		   {field:"mname", title:"mname", width:50}
		   
		]],
		toolbar:[{
			iconCls:"icon-add",
			text : "添加",
			handler:function(){
				addRole();
			}
		},{
			iconCls:"icon-edit",
			text : "编辑",
			handler:function(){
				editRole();
			}
		},{
			iconCls:"icon-remove",
			text : "删除",
			handler:function(){
				deleteRole();
			} 
		}],
		/*loadFilter:function(data){
			var d = {};
			d.total = data.total;
			d.rows = data.roleList;
			return d;
		} */
	});
})

function editRole(){
	//获取选中的行，只要一行
	var selRow = $("#roleList").datagrid("getSelected");
	$("#roleList").datagrid("clearSelections");
	$("#roleList").datagrid("selectRecord",selRow.id);
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
		href : "role/edit.jsp",
		onClose:function(){$(this).dialog("destroy");},
		onLoad:function(){
			//让dialog垂直居中
			$(this).dialog("vcenter");
			//发送一一步请求，获取要编辑的数据
			$.post("roleToEdit.action",{"role.id":selRow.id},function(data){
				//javascript中的foreach格式循环
				//1. 可以循环字符串 和数组 ： k代表下标
				//2. 循环json对象：k 代表属性名
				var d = {};
				for(var k in data){
					if(typeof data[k] == "object"){
						for(var j in data[k]){
							d["role."+k+"."+j] = data[k][j];
						}
					}
					d["role."+k] = data[k];
				}
				$("#roleForm").form("load",d);
			});
		},
		buttons:[{
			iconCls:"icon-ok",
			text : "确定",
			handler:function(){
				$("#roleForm").form("submit",{
					url:"roleSave.action",
					success:function(data){
						if(data.num > 0){
							d.dialog("close");
							$("#roleList").datagrid("reload");
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

function deleteRole(){
	//获取选中的行
	var selRows = $("#roleList").datagrid("getSelections");
	//如果没有选中的行，弹框提示
	if(selRows.length == 0){
		$.messager.alert("提示","请选择要删除的数据","warning");
		return;
	}
	$.messager.confirm("提示","确定要删除选中的数据吗",function(r){
		if(r){
			var postData = {};
			$.each(selRows,function(i){
				postData["ids["+i+"]"] = this.id;
			});
			//发送异步请求，批量删除数据，
			$.post("roleDelete.action",postData,function(data){
				//删除成功，重新加载datagrid
				$("#roleList").datagrid("reload");
			});
		}
		
	});
	
}

/**
 * 添加
 */
function addRole(){
	var d = $("<div></div>").appendTo("body");
	d.dialog({
		title : "添加用户",
		iconCls:"icon-add",
		width:600,
		modal : true,
		href : "role/edit.jsp",
		onClose:function(){$(this).dialog("destroy");},
		onLoad:function(){
			//让dialog垂直居中
			$(this).dialog("vcenter");
		},
		buttons:[{
			iconCls:"icon-ok",
			text : "确定",
			handler:function(){
				$("#roleForm").form("submit",{
					url:"roleSave.action",
					success:function(data){
						if(data.num > 0){
							d.dialog("close");
							$("#roleList").datagrid("reload");
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

