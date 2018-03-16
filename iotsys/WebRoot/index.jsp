<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
response.setHeader("Access-Control-Allow-Origin", "*");

%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>物联网</title>

<link rel="stylesheet" href="<%=basePath %>iot/css/zzsc-demo.css"  type="text/css">
<link rel="stylesheet" href="<%=basePath %>iot/css/jquery-accordion-menu.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>iot/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath %>iot/css/base_1.css">
<link rel="stylesheet" href="<%=basePath %>iot/css/index.css" type="text/css" />
	<!--图标-->
<link rel="stylesheet" href="<%=basePath %>iot/font-awesome-4.7.0/css/font-awesome.min.css">
<script src="<%=basePath %>iot/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="<%=basePath %>iot/js/jquery-accordion-menu.js" type="text/javascript"></script>
<script src="<%=basePath %>iot/js/bootstrap.min.js"></script>

<script src="<%=basePath %>iot/js/base.js"></script>
<!-- <script type="text/javascript" src="<%=basePath %>source/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>source/layDate/laydate.js"></script> -->
	<!--地图-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SOngjEUe8tUnf10yPVVtp7kkAfqlnSnq"></script>
<script type="text/javascript" src="<%=basePath %>iot/laydate/laydate.js"></script>
</head>
<body>
 

<input type="hidden" id="enp" value="${param.enp}" />  <!-- 溯源系统调用不显示左边菜单 enp 0显示   1不显示-->
<input type="hidden" id="sonm" value="${param.sonm}" /><!-- 溯源系统调用后显示的子菜单 sonm 0到11-->
<input type="hidden" id="enterpriseId2" value="${requestScope.enterpriseId}" />

<input type="hidden"   id="enterpriseId1"  value="${requestScope.map.enterpriseId}" />
<input type="hidden"   id="phone"  value="${requestScope.map.phone}" />
<input type="hidden"   id="developOrganization"  value="${requestScope.map.developOrganization}" />
<input type="hidden"   id="technicalSupport"  value="${requestScope.map.technicalSupport}" />
<!--左边选择菜单-->
<div class="menu_left" id="menu_left">
	<div class="menu_left-img">
		<img src='<%=basePath %>iot/img/logo.png' alt="">
	</div>
	<div class="content" id="menu_lefta">
		<div id="jquery-accordion-menu" class="jquery-accordion-menu red">
			<ul id="demo-list">  
				<li><a href="#"><i class="fa fa-cog"></i>视频监控 </a>
					<ul class="submenu" style="display: block;">
						<li class="hover_a"><a href="#" onclick="goto1('<%=basePath %>webdemo/video_ocx.jsp')">视频监控 </a></li>
						<li><a href="#" id="sxtgl" onclick="goto1('<%=basePath %>iot/camera/jsp/cameraList.jsp')">摄像头管理 </a></li>
						<li><a href="#" id="sxtglb" onclick="goto1('<%=basePath %>iot/camera/jsp/cameraListServer.jsp')">摄像头管理_后台</a></li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-cog"></i>环境监控 </a>
					<ul class="submenu" >
						<li><a href="#" id="hjjk" onclick="goto1('<%=basePath %>iot/envmonitoring/jsp/env_monitoring.jsp')">环境监控 </a></li>
						<li><a href="#" id="yjtz" onclick="goto1('<%=basePath %>iot/environment/jsp/warningnotify.jsp')">预警通知 </a></li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-cog"></i>采集器管理 </a>
					<ul class="submenu">
                           <li><a href="#" id="cjqgl" onclick="goto1('<%=basePath %>iot/device/jsp/deviceList.jsp')">采集器管理 </a></li>
                           <li><a href="#" id="cjqglh" onclick="goto1('<%=basePath %>iot/collector/jsp/collectorList.jsp')">采集器管理_后台 </a></li>
					</ul>
				</li>
<!-- 				<li><a href="#"><i class="fa fa-cog"></i>系统管理</a> -->
<!-- 					<ul class="submenu"> -->
<!-- 						<li><a href="#" id="qygl" onclick="goto1('<%=basePath %>iot/jsp/business_management.jsp')">企业管理 </a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
				<li><a href="#"><i class="fa fa-cog"></i>基地管理</a>
					<ul class="submenu">
						<li><a href="#" id="jdtj" onclick="goto1('<%=basePath %>iot/alQaeda/jsp/addAlQaeda.jsp')">添加基地 </a></li>
						<li><a href="#" id="jdck" onclick="goto1('<%=basePath %>iot/alQaeda/jsp/showAlQaedaMap.jsp')">基地查看 </a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>


<!--右侧内容-->
<div class="mse_right" id="mse_right" >
	<div class="riht_title" id="riht_title">
		<span class="tiele_left">物联网系统</span>
		<span class="tiele_left" style="font-weight:549;font-size: 16px"></span>
		<span  id="t1" style="margin-left: -16px;font-size: 16px"></span>
			<span class="tiele_left"  style="font-weight:549;font-size: 16px"></span>
		<span  id="t2" style="margin-left: -16px;font-size: 16px"></span>
		<span class="tiele_right"><i class="fa fa-user-circle" aria-hidden="true"></i>
			<!--<span class="user_name"></span>-->
			<div class="dropdown"  style="display: inline-block" id="user_name">
				<button style="margin-top: -4px;background-color: white;outline: none" type="button" class="btn dropdown-toggle" id="dropdownMenu1"
						data-toggle="dropdown">
					developer
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="left: -58px;z-index: 1200">
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="#" data-toggle="modal" data-target="#myModal_all">修改密码</a>
					</li>
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="#">退出登录</a>
					</li>
				</ul>
			</div>
		</span>
	</div>
	<div class="right_content"   id="right_content">
		<!--<iframe align="center" width="100%" height="100%" src=""  id="right_content"frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>-->
	</div>
	<!-- 消息栏 -->
	<div class="tip-box tip-suc-box">
		<span></span> <a>×</a>
	</div>
	<div class="tip-box tip-warn-box">
		<span></span> <a>×</a>
	</div>
</div>
<!-- 修改密码（Modal） -->
<div class="modal fade" id="myModal_all" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="margin-top: 230px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					修改密码
				</h4>
			</div>
			<div class="modal-body">
				<p class="modal_p"><span class="modal_p_sp">用户名称</span><input class="modal_ipt" type="text" placeholder="" value="土豆妹儿"></p>
				<p class="modal_p"><span class="modal_p_sp">登录手机号</span><input class="modal_ipt" type="text" placeholder="" value="18202828271"></p>
				<p class="modal_p"><span class="modal_p_sp">原密码</span><input class="modal_ipt" type="password" placeholder="请输入原密码"><span class="prompt">*<span></span></span></p>
				<p class="modal_p"><span class="modal_p_sp">新密码</span><input class="modal_ipt" type="password" placeholder="请输入新密码"><span class="prompt">*<span></span></span></p>
				<p class="modal_p"><span class="modal_p_sp">新密码</span><input class="modal_ipt" type="password" placeholder="请输入新密码"><span class="prompt">*<span></span></span></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary">
					提交更改
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="<%=basePath %>iot/js/index.js" type="text/javascript"></script>
</body>
<script>

var jishuzhichi = $("#developOrganization").val();
if(jishuzhichi != null && jishuzhichi != ""){
	$("#t1").html("建设单位: "+jishuzhichi);
}
	
jishuzhichi = $("#technicalSupport").val();
if(jishuzhichi != null && jishuzhichi != ""){
	
$("#t2").html("技术支持: "+jishuzhichi);
}
</script>
</script>
</html>
