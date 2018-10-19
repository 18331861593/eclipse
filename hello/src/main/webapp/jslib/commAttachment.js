/**
 * 用户列表的js脚本
 */
$(function(){
	$("#commAttachmentList").datagrid({
		title : "commAttachment列表",
		iconCls:"icon-search",
		url : "commAttachmentPaging.action",
		fitColumns : true,
		singleSelect : true,
		striped : true,
		rownumbers : true,
		pagination : true,
		idField : "id", //如果使用了selectRecord方法必须指定的
		columns:[[
		   {field:"id2",checkbox:true },
		   {field:"id", title:"编号", width:50},
		   {field:"title", title:"title", width:50},
		   
		   {field:"fileUrl", title:"fileUrl", width:50},
		   
		   {field:"createdAt", title:"createdAt", width:50},
		   
		   {field:"fileType", title:"fileType", width:50},
		   
		   {field:"fileSize", title:"fileSize", width:50}
		   
		]],
		toolbar:[{
			iconCls:"icon-add",
			text : "添加",
			handler:function(){
				addCommAttachment();
			}
		},{
			iconCls:"icon-edit",
			text : "编辑",
			handler:function(){
				editCommAttachment();
			}
		},{
			iconCls:"icon-remove",
			text : "删除",
			handler:function(){
				deleteCommAttachment();
			} 
		}],
		/*loadFilter:function(data){
			var d = {};
			d.total = data.total;
			d.rows = data.commAttachmentList;
			return d;
		} */
	});
})

function editCommAttachment(){
	//获取选中的行，只要一行
	var selRow = $("#commAttachmentList").datagrid("getSelected");
	$("#commAttachmentList").datagrid("clearSelections");
	$("#commAttachmentList").datagrid("selectRecord",selRow.id);
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
		href : "commAttachmentEdit.action?id="+selRow.id,
		onClose:function(){$(this).dialog("destroy");},
		onLoad:function(){
			//让dialog垂直居中
			$(this).dialog("vcenter");
			//发送一一步请求，获取要编辑的数据
			$.post("commAttachmentToEdit.action",{"id":selRow.id},function(data){
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
				$("#commAttachmentForm").form("load",d);
			});
		},
		buttons:[{
			iconCls:"icon-ok",
			text : "确定",
			handler:function(){
				$("#commAttachmentForm").form("submit",{
					url:"commAttachmentSave.action",
					success:function(data){
						if(typeof data == "string"){
							data = eval("("+data+")");
						}
						if(data.num > 0){
							d.dialog("close");
							$("#commAttachmentList").datagrid("reload");
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

function deleteCommAttachment(){
	//获取选中的行
	var selRows = $("#commAttachmentList").datagrid("getSelected");
	//如果没有选中的行，弹框提示
	if(selRows == null){
		$.messager.alert("提示","请选择要删除的数据","warning");
		return;
	}
	$.messager.confirm("提示","确定要删除选中的数据吗",function(r){
		if(r){
			//发送异步请求，批量删除数据，
			$.post("commAttachmentDelete.action",{id : selRows.id},function(data){
				//删除成功，重新加载datagrid
				$("#commAttachmentList").datagrid("reload");
			});
		}
		
	});
}


/**
 * 添加
 */
function addCommAttachment(){
	var d = $("<div></div>").appendTo("body");
	d.dialog({
		title : "添加用户",
		iconCls:"icon-add",
		width:600,
		modal : true,
		href : "commAttachmentEdit.action?id=0",
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
				$("#commAttachmentForm").form("submit",{
					url:"commAttachmentSave.action",
					success:function(data){
						if(typeof data == "string"){
							data = eval("("+data+")");
						}
						if(data.num > 0){
							d.dialog("close");
							$("#commAttachmentList").datagrid("reload");
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

