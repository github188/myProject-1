<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript" src="<%=basePath %>iot/camera/js/cameraList.js"></script>  
	<style type="text/css">
		.modal-backdrop{
			z-index:0 !important
		}
		.navbar-box:after {
		    background: #ffffff;
		}
		.hcd_btn_div{
			display: none;
		}
	</style>
  </head>
  
  <body>
  <input type="hidden" id="basePath" value="<%=basePath %>"/>
	<div>
<!-- 		<div id="demo" style="padding-left: 23px;"> -->
<!-- 			<div id="end" style="    width: 16%;float: left;text-align: left;margin-left: 40px;height: 32px;border: 1px solid #A8BDD0;">全部</div> -->
<!-- 		</div> -->
		<div style="border: 1px solid;width: 100%">
	  		<select id="alQeada" style="margin-top: 10px;margin-left: 10px;" onchange="findMassifByalQeadaId()">
	  			<option value="">全部基地</option>
	  		</select>
	  		<select id="massif" style="margin-top: 10px;margin-left: 10px;">
	  			<option value="">全部地块</option>
	  		</select>
	  		<input type="text" style="margin-top: 10px;margin-left: 10px;width: 228px" value="" id="cameraName" placeHolder="按摄像头名称搜索">
<!-- 	  		<input type="button" class="btn btn-primary" value="搜索" onclick="defTable()"> -->
	  		<button class="btn btn-primary" onclick="defTable()"><i class="fa fa-search" aria-hidden="true">搜索</i></button>
	  	</div>
	  	
		<div class="content-box" id="plantDiv">
			<table id="plantTable"></table>		
		</div>
	</div>
		
  </body>
</html>
