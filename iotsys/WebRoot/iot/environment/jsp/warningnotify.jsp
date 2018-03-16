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
    <script src="iot/environment/js/warningnotify.js"  type="text/javascript"></script>
    <script src="iot/js/base.js"  type="text/javascript"></script>
</head>
<body>
<form id="warning">
 <input type="hidden" value="0" id="warningStatus" name="warningStatus">
<div class="video_center det_body">
    <div class="edit_top" style="height: 10px"></div>
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_tit" style="margin-top: 30px">预警发送</div>
    <div class="bma_name">
        <p class="bma_name_fp">当检测器的数据低于或高于您设置的合适范围将会发送预警信息。</p>
        <div class="relation_check" style="position: relative;">
	        <span style="font-weight:bold;font-size:17px">预警通知</span>
	        <div style="display: block;width: 112px;position: absolute;right: 86%;top: -2%;">
	        	<input class="tgl tgl-light" name="tg_checked" id="c2" onclick="relation_Onclick(this)" type="checkbox" >
	            <label class="tgl-btn" for="c2" class="float:left;"></label>
	            <div class="relation_check_div" style="    margin-top: 10px;margin-left: auto;">当前属于关闭状态</div>
	        </div>
	        <p style="margin-top: 40px;"><span style="font-weight:bold">提醒时段</span><input type="checkbox" id="All" name="All" style="width:20px;height:20px;" checked onclick="changeStatus(this)">全天</input></p>
	        <input type="text" name="startTime"   disabled="disabled" id="startTime" style="background-color: #fff;width:156px;margin-left:119px;disabled:disabled" class="form-control"   >至
	        <input type="text" name="endTime" disabled="disabled"   id="endTime" style="background-color: #fff;width:156px;disabled:disabled" class="form-control" >
        <p style="margin-top: 40px;"><span style="font-weight:bold">通知到手机</span><input type="text" placeholder="请输入手机号" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "  id="warningPhone" name="warningPhone"></p>
         <span style="font-size:15px;padding-left: 120px;">注：由于通讯管制原因，含有敏感内容的短信无法成功发送。</span>
        <p><span style="font-weight:bold">通知到邮件</span><input type="text" placeholder="请输入接收预警信息的邮箱地址"  id="warningEmail" name="warningEmail" /></p>
    </div>
    </form>
    <!--分隔-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_bottom">
        <button type="button" class="btn btn-primary edit_botn" onclick="warningSubmit()">
            保存
        </button>
    </div>
</div>
</body>
<script>
//执行一个laydate实例
laydate.render({
  elem: '#startTime',//指定元素
  type:'time'
});
laydate.render({
  elem: '#endTime',//指定元素
  type:'time'
});
</script>
</html>
