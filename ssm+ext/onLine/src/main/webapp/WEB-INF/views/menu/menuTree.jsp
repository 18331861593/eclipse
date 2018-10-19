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
	<link rel="stylesheet" href="EasyUI/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="EasyUI/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="EasyUI/jquery-1.7.2.min.js"></script> 
	<script type="text/javascript" src="EasyUI/jquery.easyui.min.js">  </script>
	<script type="text/javascript" src="EasyUI/locale/easyui-lang-zh_CN.js" ></script>
	<link rel="stylesheet" href="EasyUI/themes/default/layout.css" type="text/css"></link>
	<link rel="stylesheet" href="EasyUI/demo/demo.css" type="text/css"></link>
  </head>
<body>
	 <div id="menu" style="margin-left : 5px;">
	     <!-- <div id="p" class="easyui-progressbar"  style="width:240px;margin-top:130px;"></div> --> 
	     <input type="hidden" name="roleid" id="roleid"/>
	    <ul id="menu_tree"></ul>   
    </div>
    <script>
    	$(function(){
    		menuTree();
    	})
    	
    	 /*树形菜单加载进度条*/
    	function progressbar(){
    		var value = $("#p").progressbar("getValue");
    		 if (value < 100){ 
	   		     $("#p").show();
	   		     value += Math.floor(Math.random() * 70); 
				$('#p').progressbar('setValue', value); 
	   		     setTimeout(arguments.callee, 200);     
   		     } else{
   		    	 $("#p").hide();
   		     }
    	}
    	
    	function menuTree(){
    		//progressbar();
    		$("#menu_tree").tree({
    			url : "menuAll.action?roleId=${roleId}",
    			lines : true,
    			checkbox : true,
    			cascadeCheck : true,
				onCheck : function(node,checked){
					var  nodes=$("#menu_tree").tree('getChildren',node.target);
					var num=nodes.length;
					if(num>0&&node.checked){
						 for ( var i=0;i<nodes.length;i++) {
						     $("#menu_tree").tree('check',nodes[i].target);
						} 	
					}
					else{
						 for ( var i=0;i<nodes.length;i++) {
						     $("#menu_tree").tree('uncheck',nodes[i].target);
						 }
					}
				}
    		});
    	}
   	</script>
</body>
</html>