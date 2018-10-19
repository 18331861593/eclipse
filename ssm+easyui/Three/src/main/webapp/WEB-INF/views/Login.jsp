<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0024)http://admin.518fax.net/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    <title>登录</title>
 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
	<link rel="stylesheet" type="text/css" href="css/User_Login.css" />
	<SCRIPT type="text/javascript" src="js/jquery-1.7.2.min.js"></SCRIPT>
 	<SCRIPT language=javascript>
		function onChgAuth()
		{
		  var imgsrc = document.getElementById("imgauthcode");
		  imgsrc.src = "codeServlet.action?rnd=" + Math.random();
		}
		function doLogin()
		{
		  if(!checkData()) return false;
		  document.getElementById("theform").action="index.action";
		  document.getElementById("theform").submit();
		}
		function doEnterKey()
		{
		  if(event.keyCode == 13)
		  {
		    doLogin();
		  }
		}
		function checkData()
		{
		  var acct = document.getElementById("account").value;
		  var pwd = document.getElementById("password").value;
		  var auth = document.getElementById("userauthcode").value;
		
		  if(acct.length < 1) {
		    alert("请输入用户名!");
			document.getElementById("account").focus();
			return false;
		  } else if(pwd.length < 1) {
		    alert("请输入密码!");
			document.getElementById("password").focus();
			return false;
		  } else if(auth.length < 1) {
		    alert("请输入验证码!");
			document.getElementById("userauthcode").focus();
			return false;
		  }
		  
		  return true;
		}
</SCRIPT>
</HEAD>
</head>
<BODY onkeypress=doEnterKey(); id=userlogin_body>
<FORM id=theform name=theform 
method=post><INPUT type=hidden value=login name=action> 
<DIV id=panSiteFactory>
<DIV id=siteFactoryLogin>
<DIV id=user_login>
<DL>
  <DD id=user_top>
  <UL>
    <LI class=user_top_l></LI>
    <LI class=user_top_c></LI>
    <LI class=user_top_r></LI></UL>
  <DD id=user_main>
  <UL>
    <LI class=user_main_l></LI>
    <LI class=user_main_c>
    <DIV class=user_main_box>
    <UL>
    	<li style="color : red">${error }</li>
    <UL>
    <UL>
      <LI class=user_main_text>用户名： </LI>
      <LI class=user_main_input><INPUT class=TxtUserNameCssClass id=account 
      maxLength=20 name=username> </LI></UL>
    <UL>
      <LI class=user_main_text>密 码： </LI>
      <LI class=user_main_input><INPUT class=TxtPasswordCssClass id=password 
      type=password name=password maxlength="20"> </LI></UL>
    <UL>
      <LI class=user_main_text>验证码： </LI>
      <LI class=user_main_input><INPUT class=TxtYanzheng id=userauthcode 
      name=userauthcode maxlength=4> <IMG id=imgauthcode style="PADDING-LEFT: 20px" 
      onclick=onChgAuth();  src="codeServlet.action" > </LI></UL></DIV></LI>
    <LI class=user_main_r><IMG class=IbtnEnterCssClass id=IbtnEnter 
    style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
    onclick=doLogin(); src="Images/user_botton.gif" name=IbtnEnter> </LI></UL>
  <DD id=user_bottom>
  <UL>
     <LI class=user_bottom_l></LI>
    <LI class=user_bottom_c><SPAN style="MARGIN-TOP: 40px"> </SPAN></LI> 
    <LI 
class=user_bottom_r></LI></UL></DD></DL></DIV></DIV></DIV></FORM></BODY>
</html>