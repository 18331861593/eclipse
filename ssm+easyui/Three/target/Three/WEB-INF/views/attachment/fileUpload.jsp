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
    <script type="text/javascript" src="EasyUI/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css">


    <style type="text/css">
    </style>
</head>
<body>

    <form>
        <div id="queue"></div>
        <input id="file_upload" name="file" type="file" mutiple="false">
        <div id="uploadfiles"></div>
        <div id="fileQueue" style="clear : both"></div>


    </form>

    <a href="javascript:$('#file_upload').uploadifyUpload()">上传</a>

    <script type="text/javascript">
        var timestamp = new Date().getTime();
        var token = Math.floor(Math.random() * 1000) + timestamp;
        $("#file_upload").uploadify({
            'buttonText' : '上传文件',
            'fileObjName' : 'file',
            'auto' : true,        //设置自动上传
            'method' : 'post',
            'muti' : false,        //禁止上传多个文件
            'queueID' : 'fileQueue',
            //buttonImage : ''
            'swf': 'uploadify/uploadify.swf',
            'uploader' : '/upload.action',
            onUploadStart : function(){
                console.log("onUploadStart");
            },
            formData: {
                timestamp : timestamp,
                token : token
            },
            onUploadSuccess : function(){
                console.log("onUploadSuccess");
            }
        });
    </script>

</body>
</html>
