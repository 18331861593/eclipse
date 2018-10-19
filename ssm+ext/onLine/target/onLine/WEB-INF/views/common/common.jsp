<%
	String path = request.getContextPath();
	String basePath = path + "/";
%>
<base id="basePath" href="<%=basePath%>">
<script type="text/javascript">
    var _BasePath = "<%=basePath%>";
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
		 href="ext-5.0.0/packages/ext-theme-classic/build/resources/ext-theme-classic-all.css">
<link rel="stylesheet" type="text/css"
	 href="css/myStyle.css"> 
<script type="text/javascript" src="ext-5.0.0/ext-all.js"> </script>
<script type="text/javascript" 
	src="ext-5.0.0/packages/ext-theme-classic/build/ext-theme-classic.js"></script>
<script type="text/javascript" 
	src="ext-5.0.0/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
<script type="text/javascript"
	src="js/public.js" ></script>