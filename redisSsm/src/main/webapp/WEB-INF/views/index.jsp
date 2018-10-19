<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>欢迎使用</title>
 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="EasyUI/themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="EasyUI/themes/icon.css" type="text/css"></link>
    <script type="text/javascript" src="EasyUI/jquery-1.7.2.min.js"></script> 
    <script type="text/javascript" src="EasyUI/jquery.easyui.min.js">  </script>
    <script type="text/javascript" src="EasyUI/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="EasyUI/themes/default/layout.css" type="text/css"></link>
    <link rel="stylesheet" href="EasyUI/demo/demo.css" type="text/css"></link>
    
  </head>
<body onload="tabClose();tabCloseEven()"> 
	<input type="hidden" value="${user.roleId}" id="roleId" />
	<!-- <input type="hidden" value="" id="roleId" /> -->
	<div style="width:1100px;height:1260px;margin:0px auto;" id="easyui-layout1" class="easyui-layout">
		<div id="north" data-options="region:'north',title:'North Title',
				split:true,collapsible:false,border:true" style="height:155px;">
			<br/>	<h3 style="text-align:center;align : center;line-height:21px;"></h3>
			<h5 >
				用户登录:<span style="color:red;" id="loginname">${user.username}</span>
				<%-- 上次登录时间:<span style="color:red;" id="loginname">${user.logintime}</span> --%>
			</h5>
		</div>
		<div id="south" data-options="region:'south',split:true,collapsible:false,border:false" style="height:100px;">    
		</div>
		<div data-options="region:'west',title:'西部',split:true,collapsible:false" style="width:236px;">          
	 	    <ul id="some_tree"></ul>    
	    </div>   
	    <div data-options="region:'center',title:'中部'" style="padding:1px;background:#eee;"> 
		    <!-- tab面板 -->
		    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
		    	<div id="tabs" class="easyui-tabs"  fit="true" border="false"  style="height:973px;">
		    		<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">				
	    				<h1>欢迎使用</h1>
	    			</div>
	    		</div>
	   		 </div>
	    </div> 
	</div>
    <div id="mm" class="easyui-menu" style="width:150px;">
    	<div id="mm-tabcloseleft">当前页左侧全部关闭</div>	
	    <div id="mm-tabcloseright">当前页右侧全部关闭</div>	
		<div id="mm-tabcloseother">除此之外全部关闭</div> 
	    <div class="menu-sep"></div> 
	    <div id="mm-tabcloseall">全部关闭</div> 
    </div>
	
	
	<script>
	
		$(function(){
			$("#some_tree").tree({
				url : "menuFind.action?roleId="+$("#roleId").val(),
				lines : true,
				onLoadSuccess : function(node, data){
					console.log(data);
				},
				onClick: function(node){
					/* if(node.children.length == 0){ */
					if(node.parentId != 0){
						var tabTitle =node.text;
						var url = node.url;
						addTab(tabTitle,url);
					}
					$(".easyui-accordion li div").removeClass("selected");
					$(this).parent().addClass("selected");
					tabCloseEven();
				},
				onLoadError:function(arguments){
					console.log(arguments);
				}
			});
		})

	
	//添加tab	
	function addTab(subtitle, url){
		if(!$("#tabs").tabs('exists',subtitle)){
			$("#tabs").tabs('add',{
				 title:subtitle,
				 content:createFrame(url),
				 closable:true,
				 width:$("#mainPanle").width()-10,
				 height:$("#mainPanle").height()-26
			});
			tabClose();
		}
		else{
			$("#tabs").tabs("select",subtitle);
			tabClose();
		}
	}
	
	 /*双击关闭TAB选项卡*/
	 function tabClose(){ 
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children("span").text();
		    $("#tabs").tabs('close',subtitle);
		});
		var subtitle = $(this).children("span").text();
		$("#mm").data("currtab",subtitle);
		$(".tabs-inner").bind("contextmenu",function(e){
			$("#mm").menu('show',{left : e.pageX,top : e.pageY});
			return false;
		});
	}	
	
	 function createFrame(url){
		 var frame = "<iframe name='mainFrame' scrolling='auto' frameborder='0' src='"+url+"' style='width:100%;height:100%;'></iframe>";
		 return frame;
	 }
	 
	//绑定右键菜单事件
	function tabCloseEven(){
		//关闭全部
		$("#mm-tabcloseall").click(function(){
			$(".tabs-inner span").each(function(i,n){
				var t = $(n).text();
				$("#tabs").tabs('close',t);
			});
		});
		//除此之外全部关闭
		$("#mm-tabcloseother").click(function(){
			var title = $(".tabs-selected").text();
			$(".tabs-inner span").each(function(i,n){
				var t = $(n).next();
				if(t!=title)
					$("#tabs").tabs("close",t);
			});
		});
		//当前页左侧全部关闭
		$("#mm-tabcloseleft").click(function(){
			var prevall = $(".tabs-selected").prevAll();
			if(prevall.length == 0){
				return false;
			}
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				$("#tabs").tabs('close',t);
			});
		});
		//当前页右侧全部关闭
		$("#mm-tabcloseright").click(function(){
		var nextAll = $(".tabs-selected").nextAll();
		if(nextAll.length == 0){
			return false;
		}
		nextAll.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$("#tabs").tabs('close',t);
		});
		});
	}
</script>
	
</body>
</html>
