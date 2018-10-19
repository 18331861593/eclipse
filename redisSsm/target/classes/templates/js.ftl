﻿/**
 * 用户列表的js脚本
 */
$(function(){
	$("#${short_entityName}List").datagrid({
		title : "${short_entityName}列表",
		iconCls:"icon-search",
		url : "${short_entityName}Paging.action",
		fitColumns : true,
		singleSelect : true,
		striped : true,
		rownumbers : true,
		pagination : true,
		idField : "id", //如果使用了selectRecord方法必须指定的
		columns:[[
		   {field:"id2",checkbox:true },
		   {field:"id", title:"编号", width:50},
		   <#list fields as f>
		   <#if f.type != 'Object'>
		   {field:"${f.name}", title:"${f.name}", width:50}<#if f_has_next>,</#if>
		   <#else>
		   {field:"${f.name}", title:"${f.name}", width:50,formatter:function(value,row,index){
		   		if(row.${f.name}){
		   			return row.${f.name}.name;
		   		}
		   }}<#if f_has_next>,</#if>
		   </#if>
		   
		   </#list>
		]],
		toolbar:[{
			iconCls:"icon-add",
			text : "添加",
			handler:function(){
				add${entityName}();
			}
		},{
			iconCls:"icon-edit",
			text : "编辑",
			handler:function(){
				edit${entityName}();
			}
		},{
			iconCls:"icon-remove",
			text : "删除",
			handler:function(){
				delete${entityName}();
			} 
		}],
		/*loadFilter:function(data){
			var d = {};
			d.total = data.total;
			d.rows = data.${short_entityName}List;
			return d;
		} */
	});
})

function edit${entityName}(){
	//获取选中的行，只要一行
	var selRow = $("#${short_entityName}List").datagrid("getSelected");
	$("#${short_entityName}List").datagrid("clearSelections");
	$("#${short_entityName}List").datagrid("selectRecord",selRow.id);
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
		href : "${short_entityName}Edit.action?id="+selRow.id,
		onClose:function(){$(this).dialog("destroy");},
		onLoad:function(){
			//让dialog垂直居中
			$(this).dialog("vcenter");
			//发送一一步请求，获取要编辑的数据
			$.post("${short_entityName}ToEdit.action",{"id":selRow.id},function(data){
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
				$("#${short_entityName}Form").form("load",d);
			});
		},
		buttons:[{
			iconCls:"icon-ok",
			text : "确定",
			handler:function(){
				$("#${short_entityName}Form").form("submit",{
					url:"${short_entityName}Save.action",
					success:function(data){
						if(typeof data == "string"){
							data = eval("("+data+")");
						}
						if(data.num > 0){
							d.dialog("close");
							$("#${short_entityName}List").datagrid("reload");
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

function delete${entityName}(){
	//获取选中的行
	var selRows = $("#${short_entityName}List").datagrid("getSelected");
	//如果没有选中的行，弹框提示
	if(selRows == null){
		$.messager.alert("提示","请选择要删除的数据","warning");
		return;
	}
	$.messager.confirm("提示","确定要删除选中的数据吗",function(r){
		if(r){
			//发送异步请求，批量删除数据，
			$.post("${short_entityName}Delete.action",{id : selRows.id},function(data){
				//删除成功，重新加载datagrid
				$("#${short_entityName}List").datagrid("reload");
			});
		}
		
	});
}


/**
 * 添加
 */
function add${entityName}(){
	var d = $("<div></div>").appendTo("body");
	d.dialog({
		title : "添加用户",
		iconCls:"icon-add",
		width:600,
		modal : true,
		href : "${short_entityName}Edit.action?id=0",
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
				$("#${short_entityName}Form").form("submit",{
					url:"${short_entityName}Save.action",
					success:function(data){
						if(typeof data == "string"){
							data = eval("("+data+")");
						}
						if(data.num > 0){
							d.dialog("close");
							$("#${short_entityName}List").datagrid("reload");
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
