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
    <link href="<%=basePath %>iot/css/hsCheckData.css" rel="stylesheet" type="text/css" >
    <script type="text/javascript" src="<%=basePath %>iot/js/hsCheckData.js"></script>
    <link href="<%=basePath %>iot/css/index.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>iot/js/env_detailed.js" type="text/javascript"></script>
    <script src="<%=basePath %>iot/collector/js/addCollector.js" type="text/javascript"></script>
    <script src="<%=basePath %>iot/js/base.js" type="text/javascript"></script>
    <style>
    	.edit_all{
   		    float: left;
		    width: 480px;
		    height: 300px;
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
<form action="" id="collector" onsubmit="return false">
<div class="video_center det_body">
    <div class="edit_tit">采集器基本信息</div>
    <div class="edit_all">
     <ul>
       	<li>
			<p>
	            <span class="msg_span">*</span>采集器名称
	            <input type="text" name="deviceInfo.deviceName" maxlength="32" id="deviceName" placeholder="请输入采集器名称">
	            <span class="tip_span"></span>
	        </p>
	        <div >
		            <span class="msg_span">*</span>关联企业
		        	<div id="demo" style="padding-bottom: 30px;padding-left: 9px; width: 100%;height: auto;float: right;margin-top: -34px;" >
							<div id="end" style=" width: 320px;; float: left;text-align: left;position: relative; left: 130px; top: 7px;height: 34px;">请选择</div>
					</div>
					<input type="hidden" name="deviceInfo.enterpriseId" id="enterpriseId" value="">
		        	<span class="tip_span"></span>
	        </div>
			<p>
	            <span class="msg_span">*</span>设备序列号
	            <input type="text" name="deviceInfo.serialNumber" maxlength="32" id="serialNumber" placeholder="请输入设备序列号">
	            <span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span">*</span>设备型号
	            <select name="deviceInfo.typeId" id="typeId" maxlength="32">
	        	</select>
	        	<span class="tip_span"></span>
	        </p>
			<p>
	            <span class="msg_span"></span>设备验证码
	            <input type="text" name="deviceInfo.verificationCode" maxlength="32" id="verificationCode" placeholder="请输入设备验证码">
	        </p>
       	</li>
       </ul>
    </div>
    
    <div class="edit_tit">关联的探测器信息</div>
    <div id="surey" class="edit_msg">
        <ul>
            <li index="0">
            	<div class="deleteIcon"><a href="#" onclick="deleteMyself(this)"><img alt="" src="<%=basePath %>iot/collector/img/delete.png"></a></div>
                <p style="height:42px;position:relative;top:20px">
                	<span class="msg_span">*</span>探测器名称
                	<input type="hidden" name="probeDefaultIds" value="">
                	<img src="iot/collector/img/default.png" style="margin-left: 48px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">
                	<select id="t00" style="width: 196px;margin-top: -25px;margin-left: -123px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePicc(this)" name="surveyNamePics">
                	</select>
                	<span class="tip_span" style="top: 3px;"></span>
                </p>
                <input type="text" value="" name="surveyName" style="position: relative;top: -21px;width: 177px;left:173px;height:34px;margin-left: 10px" placeholder="请填写或选择探测器">
                
                <p>
                    <span class="msg_span">*</span>单位
                    <input type="text" name="unit" maxlength="8" placeholder="例如土壤温度的单位为℃">
                    <span class="tip_span"></span>
                </p>
                <p>
                    <span class="msg_span">*</span>通道
                    <input type="text" name="surveyNo" pattern="\d*" maxlength="2" placeholder="请输入通道">
                    <span class="tip_span"></span>
                </p>
                <p>
                    <span class="msg_span">*</span>正常范围最小值
                    <input type="text" name="surveyMin" maxlength="5" onblur="clearNoNum(this)" placeholder="请输入正常范围最小值">
                    <span class="tip_span"></span>
                </p>
                <p>
                    <span class="msg_span">*</span>正常范围最大值
                    <input type="text" name="surveyMax" maxlength="5" placeholder="请输入正常范围最大值">
                    <span class="tip_span"></span>
                </p>
              <span class="left" onclick="gotoPre(this)"><i class="fa fa-angle-double-left" aria-hidden="true" title="往前"></i></span>
              <span class="right" onclick="gotoNext(this)"><i class="fa fa-angle-double-right" aria-hidden="true" title="往后"></i></i></span>
            </li>
        </ul>
        <div class="cer-item">
			<img src="<%=basePath %>iot/collector/img/84E58PICDTF_1024.png" class="moreimg" onclick="addNewSurvey($(this))" style="margin-top: 9%;margin-left: 7%;" title="添加探测器"> 
		</div>
	</div>
    <!--分隔  控制器-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_tit" style="margin-top: 30px">关联的控制器信息</div>
    <div class="edit_msg edit_relation" id="controllers">
    	<ul>
    		<li>
    		<div class="deleteIcon"><a href="#" onclick="deleteMyself(this)"><img alt="" src="<%=basePath %>iot/collector/img/delete.png"></a></div>
    			<p style="width:100%;height:80px">
    				<span class="msg_span" style="margin-top: 11px;">*</span>控制器名称
    				<img src="<%=basePath %>iot/collector/img/default.png" id="pic0" style="margin-left: 48px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">
    				<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" name="controllerPics">
	    				<option value=""></option>
	    				<option value="wind.png">通风</option>
	    				<option value="irrigation.png">灌溉</option>
	    				<option value="heating.png">暖气</option>
	    				<option value="lighting.png">照明</option>
	    				<option value="collector.png">其他</option>
    				</select>
   					<span class="tip_span"></span>
    			</p>
   				<input type="text" id="controllerNames" value="" name="controllerNames" maxlength="8" style="position: relative;top: -69px;width: 177px;left:178px;height:34px;margin-left: 10px">
    			<p style="margin-top: -40px;">
    				<span class="msg_span">*</span>通道号
    				<input type="text" name="controllerNos" pattern="\d*" maxlength="2" value="" maxlength="8">
    				<span class="tip_span"></span>
    			</p>
    			<span class="left" onclick="gotoPre(this)"><i class="fa fa-angle-double-left" aria-hidden="true" title="往前"></i></span>
              	<span class="right righta" onclick="gotoNext(this)"><i class="fa fa-angle-double-right" aria-hidden="true" title="往后"></i></i></span>
    		</li>
    	</ul>
    	<div class="cer-item">
			<img src="<%=basePath %>iot/collector/img/84E58PICDTF_1024.png" class="moreimg" onclick="addNewController($(this))" style="margin-top: 3%;margin-left: 7%;" title="添加探测器"> 
		</div>
    </div>
    
    <!--分隔-->
    <div style="width: 100%;height: 1px;clear: both"></div>
    <div class="edit_bottom">
        <input type="button" class="btn btn-primary edit_botn" value="保存" onclick="addCollector()">
        <input type="button" class="btn btn-primary edit_botn" value="返回" onclick="goto1('<%=basePath %>iot/collector/jsp/collectorList.jsp')">
    </div>
</div>
</form>
</body>
</html>
