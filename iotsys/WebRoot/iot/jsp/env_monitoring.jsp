<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>

<link rel="stylesheet" href="iot/envmonitoring/css/env_monitoring.css" />
</head>
<body>

	<div class="video_center" style="position: relative">
	<br/>
	<div style="float: left;width:200px; margin-left: 20px">
		<select class="form-control" style=""  id="baseId" onchange="loadMassifAddress()" ></select>
	</div>
	<div style="float: left;width:200px; margin-left: 20px">
		<select class="form-control" style="" id="massifId" ></select>
	</div>
	
	<div style="float: left;width:20%">
		<input  placeholder="按采集器名称搜索" type="text" id="deviceName" style="width: 200px;height: 34px;margin-left: 20px;padding-left: 8px;font-size: 12px;font-weight: 400;" >
		<i class="fa fa-search" aria-hidden="true" onclick="showDatasss()"></i>
	</div>
	
		<div class="env_top">
			<!-- <div class="top_ipt_fa">
				<input class="env_top_ipt" placeholder="按采集器名称搜索" type="text"><i
					class="fa fa-search" aria-hidden="true"></i>
			</div> -->
		</div>
		<div id="haha" class="env_center">
		
		
		
	</div>
	<script src="iot/js/swiper.min.js"></script>
	<script>
    // 右侧搜索框进入效果（采集器，环境监控等 页面）
  /*   $(function(){
        setTimeout(function(){
            $(".top_ipt_fa").addClass("top_ipt_fa1");
        },100);
    }) */
</script>
<script type="text/javascript" src="<%=basePath%>iot/envmonitoring/js/env_monitoring.js"></script>

</body>
</html>
