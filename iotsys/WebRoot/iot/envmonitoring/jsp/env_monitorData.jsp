<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>

<jsp:include page="/mHead.jsp"></jsp:include>
<jsp:include page="parameterModal.jsp"></jsp:include>
<jsp:include page="timingModal.jsp"></jsp:include>
<link href="iot/css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="iot/envmonitoring/css/env_monitorData.css" />
 <!-- 日历插件 -->
<%-- <script type="text/javascript" src="<%=basePath %>source/layDate/laydate.js"></script>  --%>

<!-- 输入验证提示框插件 -->
<script type="text/javascript" src="<%=basePath %>source/layer/layer.js"></script> 

<script type="text/javascript" src="<%=basePath%>iot/envmonitoring/js/echarts.js"></script>
<script type="text/javascript" src="<%=basePath%>iot/envmonitoring/js/env_monitorData.js"></script>

<!-- <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script> -->


<meta charset="UTF-8">  
<title>Title</title>
</head>

<body style="margin: 0">

<div id="muea" class="muea">
	<span id="showProbe" onclick="showProbe()">探测器信息</span>
	<span onclick="showPowerController()">控制器信息</span>
</div>


<!-- 探测器信息DIV框 -->
<div id="caijikuang" style="width:100% ;float:left; "> 
	
	<div id="tiaojiankuang" style="margin-left: 20px">
		<input type="hidden" class="edit_all" id="deviceId" name="deviceId" placeHolder="关联企业" value="${param.deviceId}">
		<input type="text" name="startTime" readonly="readonly" disabled="disabled" id="startTime" style="background-color: #fff;width:200px;float: left;" class="form-control" onfocus="retime()" > 
		<span style="float: left;">~</span>
		<input type="text" name="endTime" readonly="readonly" disabled="disabled"   id="endTime" style="background-color: #fff;width:200px;float: left;" class="form-control"  onfocus="retime()" > 
		<select class="form-control" style="background-color: #fff;width:200px;float: left;margin-left: 20px;width: 170px" onchange="setDatetime()" class="form-control"  id="chaxunshijian" >
			<option value='0'>最近8小时</option>
			<option value='1'>最近1周</option>
			<option value='2'>最近1个月</option>
			<option value='3'>最近半年</option>
			<option value='-1'>自定义时间范围</option>
		</select>
		
		<input type="button" class="btn btn-primary" value="查询"  style="background-color:#006dcc;" onclick="getLineAndPie()">
		<input type="button" class="btn btn-primary" value="折线图" style="background-color:#006dcc;"  onclick="showLine()">
		<input type="button" class="btn btn-primary" value="饼图"  style="background-color:#006dcc;"  onclick="showPie()">
		<input type="button" class="btn btn-primary" style="margin-right: 100px;background-color:#006dcc;" value="返回"    onclick="back()">
	</div>
	<div id="mym" style="width:100% ;float:left;display:none" ></div> <!-- 探头数据  饼图-->
	
	<div id="mmy" style="width:100% ;float:left;" ></div>  <!-- 探头数据  折线图-->
 </div> 
 
<!-- 控制器信息DIV框 --> 
<div id="ymm" style="width:100% ;height:100%; float:left; border: 1px solid red" >

</div>  
<!-- 	<button type="button" onclick="timingModal()">控制器定时控制样式按钮</button>
	<button type="button" onclick="parameterModal()">控制参数控制样式按钮</button>  -->
</body>
<script>

//执行一个laydate实例
laydate.render({
  elem: '#startTime',//指定元素
  value:new Date()
});
laydate.render({
  elem: '#endTime',//指定元素
    value:new Date()
});
</script>
</html>
