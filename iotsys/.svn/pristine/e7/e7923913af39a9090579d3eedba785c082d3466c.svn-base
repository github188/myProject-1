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
    <link href="<%=basePath %>source/css/uploadify.css" rel="stylesheet" type="text/css" >
    <script type="text/javascript" src="<%=basePath %>source/js/jquery.uploadify.min.js"></script>
     <link href="iot/css/index.css" rel="stylesheet" type="text/css" />
     <link href="<%=basePath %>iot/css/hsCheckData.css" rel="stylesheet" type="text/css" >
   <script type="text/javascript" src="<%=basePath %>iot/js/hsCheckData.js"></script>
	<script src="<%=basePath %>iot/js/base.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath %>iot/camera/js/addCamera.js"></script>  
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
		
		.edit_all{
   		    float: left;
		    width: 550px;
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
		 textarea{
		 	width: 320px;
		    height: 80px;
		 	float: right;
		    padding-left: 8px;
		    font-size: 12px;
		    font-weight: 400;
		 }
    	.edit_all p{    
   		    padding: 15px;
		 }
    	
	</style>
  </head>
  
  <body>
  <form action="" id="cameraInfo">
  <input type="hidden" id="basePath" value="<%=basePath %>"/>
  <input type="hidden" id="cameraId" name="id" value="${param.id}"/>
  <input type="hidden" id="flog" name="flog" value=0/>
  <input type="hidden" name="" id="sessionId" value="${pageContext.session.id}"/>	
<div class="video_center det_body">
    <div class="edit_tit">摄像头基本信息</div>
    <div class="edit_all">
     <ul>
       	<li>
			<p style="height: 200px;">
	            <span class="msg_span">*</span>封面图片
	            
					<input type="hidden" id="imgText1" name="imgText1">
					<img class="pimg" src="<%=basePath %>source/img/none.png" id="cerImg1" style="float: right;margin-right: 27px;width: 293px;height: 220px;"/>
					<div class="cer-lay4" style="margin-top: 44px;margin-bottom: -40px; margin-left: 194px;"><input type="file" id="cerUp1" cerType="1"/></div>
				
	            <span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>摄像头名称
	            <input type="text"  id="cameraName" name="cameraName" placeHolder="摄像头名称" maxlength="32">
	            <span class="tip_span"></span>
	        </p>
			<div style="padding: 15px;">
		            <span class="msg_span">*</span>关联企业
		        	<div id="demo" style="     padding-left: 9px; width: 100%;height: auto;float: right;margin-top: -34px;" >
							<div id="end" style="width: 320px;; float: left;text-align: left;position: relative;left: 171px;top: 7px;height: 34px;">请选择</div>
					</div>
					<input type="hidden" name="pkEId" id="enterpriseId" value="">
		        	<span class="tip_span"></span>
	        </div>
			<p>
	            <span class="msg_span">*</span>摄像头序列号
	            <input type="text"  id="serialNumber" name="serialNumber" placeHolder="摄像头序列号" maxlength="64">
	            <span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>视频服务器
	            <select  id="pkDeviceId" name="pkDeviceId">
	        		<option value="123123123123123-测试用虚拟企业">测试用虚拟企业</option>
	        	</select>
	        	<span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>通道
	            <input type="text" id="channel" name="channel" placeHolder="通道">
	       		<span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>设备型号
	            <select  id="unitType" name="unitType">
	        	</select>
	        	<span class="tip_span"></span>
	        </p>
	        <p>
	            <span class="msg_span"></span>摄像头登录ID
	            <input type="text"  id="loginId" name="loginId" placeholder="摄像头登录ID" maxlength="11">
	            <span class="tip_span"></span>
	        </p>
	        <p>
	            <span class="msg_span"></span>设备密码
	            <input type="text"  id="password" name="password1" placeholder="设备密码" maxlength="50">
	            <span class="tip_span"></span>
	        </p>
	        <p>
	            <span class="msg_span">*</span>云台控制
	            <input type="text"  id="control1" name="control1" placeholder="请输入云台控制">
	            <span class="tip_span"></span>
	        </p>
	        <p>
	            <span class="msg_span">*</span>变焦控制
	            <input type="text"  id="control2" name="control2" placeholder="请输入变焦控制">
	            <span class="tip_span"></span>
	        </p>
	        <p>
	            <span class="msg_span"></span>备注
	            <textarea id="comments" name="comments" placeholder="备注" maxlength="128"></textarea>
	        </p>
       	</li>
       </ul>
    </div>
    
    <!--分隔-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_bottom">
        <input type="button" class="btn btn-primary edit_botn" value="保存" onclick="addCamera()">
        <input type="button" class="btn btn-primary edit_botn" value="返回" onclick="goto1('iot/camera/jsp/cameraListServer.jsp')">
    </div>
</div>
</form>
  </body>
</html>
