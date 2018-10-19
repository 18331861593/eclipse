<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>参数错误</title>
<!-- <link href="/html/error/css/error.min.css" rel="stylesheet" type="text/css" /> -->
</head>

<body>
<div class="Errorbg">
  <dl class="ErrorNum">
    <dt>500</dt>
    <dd>ERROR...PAGE NOT FOUND</dd>
  </dl>
  <ul class="ErrorTitle">
    <li class="titbot"><span class="font54">抱歉!</span><span class="font22">参数错误。</span></li>
    <li>您要查看的网址提交参数错误。 </li>
    <li>点击以下链接继续浏览网站</li>
    <li><a href="javascript:history.go(-1)">>>&nbsp;返回</a></li>
    <li></li>
  </ul>
</div>
</body>
</html>
