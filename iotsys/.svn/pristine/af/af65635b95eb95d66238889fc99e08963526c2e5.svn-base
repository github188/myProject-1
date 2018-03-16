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
    <title>Title</title>
 	<jsp:include page="/mHead.jsp" />
    <script src="<%=basePath %>iot/collector/js/collectorList.js" type="text/javascript"></script>
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
			border: none;top:10%;
		}
		#aORb{
			margin-top: 10px;
    		margin-left: 10px;
    		width: 228px;
		}
	</style>
</head>
<body>
<input type="hidden" id="basePath" value="<%=basePath %>"/>
<div style="position: relative">
    <div class="env_top">
        <div class="top_ipt_fa" style="border: 1px solid;width: 100%">
	    	<form action="#" id="searchProduct" onsubmit="return false">
		        <select id="enterpriseId" name="enterpriseId" onchange="createTable()" style="margin-top: 10px;margin-left: 10px;">
		  			<option value="">请选择企业</option>
		  		</select>
		        <input class="env_top_ipt" id="aORb" name="aORb" placeholder="按采集器名称、设备序列号搜索" type="text">
       			<button class="btn btn-primary" onclick="createTable()"><i class="fa fa-search" aria-hidden="true">搜索</i></button>
	    	</form>
        </div>
    </div>
     <div class="add-btn" style="width: 95.8%;text-align: right;">
		<input type="button" value="+添加采集器" class="btn btn-primary" onclick="goto1('<%=basePath %>iot/collector/jsp/addCollector.jsp')">
	 </div>
    <div class="env_center">
        <table id="collector_table">
        </table>
    </div>
</div>
<script>
    // 右侧搜索框进入效果（采集器，环境监控等 页面）
    $(function(){
        setTimeout(function(){
            $(".top_ipt_fa").addClass("top_ipt_fa1");
        },100);
    })
</script>
</body>
</html>
