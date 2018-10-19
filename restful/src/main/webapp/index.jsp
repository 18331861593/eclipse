<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>restful接口调用</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<style type="text/css">
table, table tr th, table tr td {
	border: 1px solid #0094ff;
}

table {
	width: 200px;
	min-height: 25px;
	line-height: 25px;
	text-align: center;
	border-collapse: collapse;
}
</style>

</head>

<BODY>
	<table id="table">
		<tr >
			<td>name</td>
			<td>gender</td>
			<td>telephone</td>
			<td>education</td>
			<td>descr</td>
		</tr>
	</table>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : 'ttt',
				type : 'get',
				success : function(result) {
					var template = document.querySelector('#template').innerHTML;
					var fragment = "";
					console.log(result);
					result.forEach(function(i){
						fragment += template.replace( /\{\{name\}\}/, i.name)
					      .replace( /\{\{gender\}\}/, i.gender)
					      .replace( /\{\{telephone\}\}/, i.telephone)
					     .replace( /\{\{education\}\}/, i.education)
					    .replace( /\{\{descr\}\}/,i.descr);
					});				
					console.log(fragment);
					$("table").append(fragment);
				}
			});
		})
	</script>
	
	<script type="template" id="template">
		<tr >
			<td>{{name}}</td>
			<td>{{gender}}</td>
			<td>{{telephone}}</td>
			<td>{{education}}</td>
			<td>{{descr}}</td>
		</tr>
	</script>
	
</BODY>
</html>