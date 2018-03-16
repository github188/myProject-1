<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="iot/css/index.css" rel="stylesheet" type="text/css" />
    <script src="iot/js/env_detailed.js" type="text/javascript"></script>
</head>
<body>
<div class="video_center det_body">
    <div class="edit_top" style="height: 20px"></div>
    <div class="edit_tit">企业基本信息</div>
    <div class="bma_name">
        <p><span>企业LOGO</span></p>
        <p><span>企业名称</span><input type="text" value="阳光味道"></p>
    </div>
    <!--分隔-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_tit" style="margin-top: 30px">预警发送</div>
    <div class="bma_name">
        <p class="bma_name_fp">当检测器的数据低于或高于您设置的合适范围将会发送预警信息。</p>
        <p><span>通知到手机</span><input type="text" value="18202828271"></p>
        <p><span>通知到邮件</span><input type="text" placeholder="请输入接收预警信息的邮箱地址" value=""></p>
    </div>
    <!--分隔-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_bottom">
        <button type="button" class="btn btn-primary edit_botn">
            保存
        </button>
    </div>
</div>

</body>
</html>
