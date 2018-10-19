<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>文件列表页</title>
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

    <link rel="stylesheet" href="Huploadify/Huploadify.css"></link>
    <script type="text/javascript" src="Huploadify/jquery.Huploadify.js">  </script>

    <style type="text/css">
        .partition{ width:820px;height:25px;}
        .size{text-align:center;}
        *{font-size : 12px;}
    </style>
</head>
<body>


    <div id="photoDiv"  class="uploadify"></div>
    <div class="partition"></div>
    <div id="dialog1"></div>
    <table id="dg_project"  style="width:820px;"></table>

    <script type="text/javascript" >

        var _BasePath = "http://localhost:8080/"

        $(function(){
            loadData();
            uploadInit();
        })

        function uploadInit(){
            $("#photoDiv").Huploadify({
                uploader : 'upload.action',    //文件上传地址
                auto:true,      //开启自动上传
                multi:false,    //不允许上传多个文件
                fileSizeLimit:20480, //上传文件大小
                removeTimeout: 100,//上传完成后进度条的消失时间，单位毫秒
                onUploadError : function(){
                    $.messager.alert("提示","上传出错，请重试","info");
                },
                onUploadSuccess : function(){
                    reload();
                }
            });
        }

        function loadData(){
            $("#dg_project").datagrid({
                title : "文件列表",
                iconCls:"icon-search",
                url:"filePaging.action",
                method : "post",
                pagination : true,
                pageList: [20,50, 100, 200],
                rownumbers:true,
                striped : true,
                fitColumns : true,
                columns : [[
                    {title:"标题",field:"title",width:130},
                    {title : '文件大小',field : "fileSize", width : 80},
                    {title : '文件类型',field : "fileType", width : 80},
                    {title : '上传时间', field : "createdAt", width : 120},
                    {title : ' ', field : "id", width : 100,align : 'center',
                        formatter : function (value, row,index){
                            var result = "";
                            //result += '<a onclick="del('+row.id+','+"'"+row.parentId+"'" + ');" >删除
                            result += '<a onclick="download11('+"'"+row.fileUrl+"'" + ')">下载</a> &nbsp;'
                            result += "<a onclick='del("+row.id+");' >删除</a>";
                            return result;
                    }}
                ]],
                toolbar : [{
                    text : '上传文件',
                    handler : function(){
                        $('#photoDiv').trigger("click");
                    }
                }],
            })
        }

        function download11(fileUrl){
            console.log(fileUrl);
            window.location.href="download.action?fileName="+fileUrl;
        }

        function del(id){
            $.messager.confirm("信息提示","确定删除？",function(r){
                if(r){
                    $.ajax({
                        type: 'GET',
                        url: "fileDelete.action",
                        data: {id : id},
                        success : function(data){
                            reload();
                        }
                    });
                }
            });
        }

        function reload(){
            $("#dg_project").datagrid("reload");
        }

    </script>
</body>
</html>
