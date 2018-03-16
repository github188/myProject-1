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
     <jsp:include page="/mHead.jsp" />
     		<link rel="stylesheet" href="<%=basePath %>iot/css/jquery-ui-1.10.4.custom.css">
  <script src="<%=basePath %>iot/js/jquery-ui-1.10.4.custom.js"></script>
	<script type="text/javascript" src="<%=basePath %>iot/device/js/deviceList.js"></script>  
	<style type="text/css">
	.env_top_ipt{
		margin-top: 10px;
    	margin-left: 10px;
    	width: 228px;
	}
	</style>
  </head>
  
  <body>
  <div class="env_top">
        <div class="top_ipt_fa" style="border: 1px solid;width: 100%;">
    	<form id="searchProduct" method="post" onsubmit="return false">
	    	<input type="hidden" id="basePath" value="<%=basePath %>"/>
			<select  name="mass.baseId"  id="baseId" onchange="findMassifByalQeadaId()" style="margin-top: 10px;margin-left: 10px;">
			</select>								
			<select  name="mass.massifId"  id="massifId" style="margin-top: 10px;margin-left: 10px;">								
			</select>
	         <input type="text" class="env_top_ipt" name="deviceInfo.deviceName" id="deviceName"  placeholder="按采集器名称搜索"  />
	<!-- 					<input type="button" class="btn btn-primary" value="查询"   onclick="createTable()"> -->
			<button class="btn btn-primary" onclick="createTable()"><i class="fa fa-search" aria-hidden="true">搜索</i></button>
		</form>	
	  </div>
    </div>
  	<div>
		<div class="content-box" id="plantDiv">
			<table id="plantTable"></table>		
		</div>
	</div>
  </body>
</html>
