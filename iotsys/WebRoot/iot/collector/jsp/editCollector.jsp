<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Access-Control-Allow-Origin", "*");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="<%=basePath %>iot/css/index.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>iot/js/env_detailed.js" type="text/javascript"></script>
    <script src="<%=basePath %>iot/collector/js/editCollector.js" type="text/javascript"></script>
    <script src="<%=basePath %>iot/js/base.js" type="text/javascript"></script>
    <script src="<%=basePath %>iot/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <style>
    	.edit_all{
   		    float: left;
		    width: 480px;
		    height: 380px;
		    background-color: #F2F2F2;
		    margin: 10px 10px 10px 0;
		    font-size: 16px;
		    font-weight: 400;
		    padding: 10px;
    	}
    	.edit_all input,select{    
    		width: 320px;
		    height: 34px;
		    float: right;
		    padding-left: 8px;
		    font-size: 12px;
		    font-weight: 400;
		 }
    	.edit_all p{    
   		    padding: 10px;
		 }
		 
		 * { padding:0;margin:0;list-style: none }
        html,body { height:100%; overflow:hidden; }
       #aaa ul { margin:50px auto;position:relative; width:1100px; height:500px;}
       #aaa ul li { float:left;  width:25%; cursor:pointer; padding:10px; box-sizing:border-box; height:33%;}
       
       .deleteIcon{
		 	display: block;
		    width: 24px;
		    float: right;
		    top: -8px;
		    position: absolute;
		    right: -8px;
		 }
		  .left{
		 	display: block;
		    width: 20px;
		    height: 30px;
		    line-height：30px;
		    text-align: center;
		    vertical-align: middle;
		    position: relative;
	        top: -61%;
    		left: -3%;
		 }
		 .right{
		 	display: block;
		    width: 20px;
		    height: 30px;
		    line-height：30px;
		    text-align: center;
		    vertical-align: middle;
		    position: relative;
	        top: -69%;
    		right: -98%;
		 }
		 .righta{
	        top: -80%;
		 }
		 .tip_span{
		 	width: 310px;
		 	left: 140px;
		 	height: 24px;
		 	top:12px;
		 	white-space: nowrap;
		 }
    </style>
</head>
<body>
<input type="hidden" id="basePath" value="<%=basePath %>"/>
<form action="" id="collector">
<input type="hidden" name="deviceInfo.id"  id="deviceId" value="${param.deviceId }">
<div class="video_center det_body">
    <div class="edit_tit">采集器基本信息</div>
    <div class="edit_all">
     <ul>
       	<li>
			<p>
	            <span class="msg_span">*</span>采集器名称
	            <input type="text" name="deviceInfo.deviceName" id="deviceName" placeholder="请输入采集器名称">
	        	<span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>关联企业
	        	<select name="deviceInfo.enterpriseId" id="enterpriseId" >
	        	</select>
	        	<span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>设备序列号
	            <input type="text" name="deviceInfo.serialNumber" id="serialNumber" placeholder="请输入设备序列号">
	            <span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>设备型号
	            <select name="deviceInfo.typeId" id="typeId" >
	        	</select>
	        	<span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>设备验证码
	            <input type="text" name="deviceInfo.verificationCode" id="verificationCode" placeholder="请输入设备验证码">
	            <span class="tip_span"></span>
	        </p>
       	</li>
       </ul>
       
    </div>
    
    <!--分隔  探测器-->
    <div class="edit_tit">关联的探测器信息</div>
    <div id="probe" class="edit_msg">
    </div>
    
    <!--分隔  控制器-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_tit" style="margin-top: 30px">关联的控制器信息</div>
    <div class="edit_msg edit_relation">
        <ul id="controller3">
    	</ul>
    </div>
    <!--分隔-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_bottom">
        <input type="button" class="btn btn-primary edit_botn" value="保存" onclick="editCollector()">
   		<input type="button" class="btn btn-primary edit_botn" value="返回" onclick="goto1('<%=basePath %>iot/collector/jsp/collectorList.jsp')">
    </div>
</div>
</form>
</body>
</html>
