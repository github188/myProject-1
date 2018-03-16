<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Access-Control-Allow-Origin", "*");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>生产信息</title>
	<script type="text/javascript" src="<%=basePath %>iot/camera/js/cameraInfo.js"></script>  
	<link rel="stylesheet" href="<%=basePath %>iot/device/css/deviceManage.css">
		<link href="<%=basePath %>source/css/uploadify.css" rel="stylesheet" type="text/css" >
	<style>    	
    </style>
</head>
<body>
<form action="" id="collector">
<input type="hidden" id="Id" name="cameraId" value="${param.id}">
<input type="hidden" id="basePath" value="<%=basePath %>"/>
<input type="hidden" id="enterpriseId" value="${param.enterpriseId}"/>	
<div  class="top" style="min-width:1500px;">
  <div class="left">
    <img id="img1" src="iot/device/img/u1727.png" width="100%" style=" max-width: 240px;min-width: 180px;width:200px;height:200px">
  	<input type="hidden"  name="imgname"  id="imgname" />
  	<input type="file" name="attach" id="attach" />
  </div>
  <div class="right">
   <div class="right_one"><p  class="p1"><input type="text" id="cameraName" name="cameraName" maxlength="32" placeHolder="摄像头名称"></p></div>
   <div class="right_one"><p  id="address" name="address" class="p2"></p></div>
   <div class="right_one"><p  id="pkDeviceId" name="pkDeviceId" class="p3"></p></div>
   <div class="right_one"><p  id="cameraStatus" name="cameraStatus" class="p4"></p></div>
   </div>
</div>
    
    <!--分隔  控制器-->
    <div style="width: 100%;height: 1px;clear: both">
    	<p class="p4" style="margin-left: 345px;margin-top: -30px;position: relative;">
    		<span style="margin-top: 8px;display: block;position: absolute;">备注：</span>
    		<textarea id="comments" name="comments" maxlength="128" style="width: 300px;height: 100px;margin-left: 100px;"></textarea>
    	</p>
    </div>
     <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_bottom" style="top: 300px;position: relative;">
        <input type="button" class="btn btn-primary edit_botn" value="保存" onclick="updateCamera()">
    </div>
</form>
</body>
</html>
