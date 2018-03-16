<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>生产信息</title>
	<script type="text/javascript" src="<%=basePath %>iot/device/js/deviceManage.js"></script>  
	<link rel="stylesheet" href="<%=basePath %>iot/device/css/deviceManage.css">
	<style>    	
    </style>
</head>
<body>
<form action="" id="collector">
<input type="hidden" value="${param.id}" id="Id">
<input type="hidden" id="basePath" value="<%=basePath %>"/>
<input type="hidden" id="enterpriseId" value="${param.enterpriseId}"/>	
<input type="hidden" id="deviceId" name="deviceId" />
<input type="hidden" id="deviceNames" name="deviceNames" />
<div  class="top" style="min-width:1500px;">
  <div class="left" >
    <img id="u1727_img" src="iot/device/img/u1727.png" width="100%" style=" max-width: 240px;min-width: 180px">
  </div>
  <div class="right" >
   <div class="right_one" id="m"><p  id="deviceName" name="deviceName" class="p1"></p><a  title="修改" href="javascript:goEditJsp()" class="a1"   style="border: 1px solid #c5c5c5"><i class="fa fa-align-justify" aria-hidden="true" style="margin-left: 6px;"></i><span style="margin-left: 6px;">编辑</span></a></div>
    <div class="right_one" id="m1"  style="display:none"><input type="text"  id="oldDeviceName" onblur="edit()"/></div>
    <div class="right_one"><p  id="address" name="address" class="p2"></p></div>
   <div class="right_one"><p  id="deviceNumber" name="deviceNumber" class="p3"></p></div>
   <div class="right_one"><p  id="deviceStatus" name="deviceStatus" class="p4"></p></div>
   </div>
</div>
    
    <div class="edit_tit">关联的探测器信息</div>
    <div class="edit_msg" id="surveys">
    </div>
    <!--分隔  控制器-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_tit" style="margin-top: 30px">关联的控制器信息</div>
    <div class="edit_msg edit_relation" id="controllers">
    </div>
    <!--分隔-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_bottom">
        <input type="button" class="btn btn-primary" style="background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#08c), to(#04c));" value="保存" onclick="UpdateDevice()">
         <input type="button" class="btn btn-default" value="取消" onclick="back()">
    </div>
</div>
</form>
</body>
</html>
