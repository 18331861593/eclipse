	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>未授权</title>
</head>

<body>
	<div class="Errorbg imgbg">
  <dl class="ErrorNum">
    <dt>404</dt>
    <dd>ERROR...PAGE NOT FOUND</dd>
  </dl>
  <ul class="ErrorTitle">
    <li class="titbot"><span class="font54">请求参数错误！</span><span class="font22"></span></li>
    <li>您要查看的网址可能已被删除或者暂时不可用。 </li>
    <li>对不起，请求参数错误。请点击 </li>
    <li><a href="javascript:history.go(-1)">>>&nbsp;返回上一级页面</a></li>
    <li style="display:none;"><a href="#">>>&nbsp;返回网站首页</a></li>
    <li></li>
  </ul>
</div>
</body>
</html>
