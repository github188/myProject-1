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
     
	<script type="text/javascript" src="<%=basePath %>iot/camera/js/cameraListServer.js"></script>  
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
		.modal{
			box-shadow: none;
			background: none;
			border: none;
			top:10%;
			width:600px;
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
	  		<select id="enterpriseId" onchange="defTable()" style="margin-top: 10px;margin-left: 10px;">
	  			<option value="">请选择企业</option>
	  		</select>
	  		<input type="text" style="margin-top: 10px;margin-left: 10px;width: 228px" value="" id="aORb" placeHolder="按摄像头名称、视频服务器搜索">
	  		<button class="btn btn-primary" onclick="defTable()"><i class="fa fa-search" aria-hidden="true">搜索</i></button>
	  	</div>
	  	<div class="add-btn" style="width: 95.8%;text-align: right;">
		<input type="button" value="+添加摄像头" class="btn btn-primary" onclick="goto1('iot/camera/jsp/addCamera.jsp')">
<!-- 		<input type="button" value="批量删除" class="btn btn-danger" onclick="delProductType()"> -->
	 </div>
		<div class="content-box" id="plantDiv">
			<table id="plantTable"></table>		
		</div>
	</div>
		
  </body>
</html>
