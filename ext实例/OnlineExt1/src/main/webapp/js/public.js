/**
 * 状态
 */
var stateStore = new Ext.data.Store({
		fields : ["sname","sid"],
		data : [
			{'sid' : '0', 'sname' : '启用'},
			{'sid' : '1', 'sname' : '禁用'},
		],
	});