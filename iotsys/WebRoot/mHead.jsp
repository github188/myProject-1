<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="<%=basePath %>source/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>source/css/uploadify.css" rel="stylesheet" type="text/css" >
<link href="<%=basePath %>source/css/queryDate.css" rel="stylesheet"   type="text/css">
<link href="<%=basePath %>source/css/address.css" rel="stylesheet" type="text/css" >
<link href="<%=basePath %>source/css/page.css" rel="stylesheet" type="text/css" >
<link href="<%=basePath %>source/css/common.css" rel="stylesheet" type="text/css" >
<link href="<%=basePath %>source/css/bootstrap-dialog.css" rel="stylesheet" type="text/css" >
 <link href="<%=basePath %>Font-Awesome-3.2.1/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="<%=basePath %>source/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>source/js/bootbox.min.js"></script>
<script type="text/javascript" src="<%=basePath %>source/js/bootstrap-dialog.js"></script>
<!-- 表格插件bootstrap-table --> 
<script type="text/javascript" src="<%=basePath %>source/js/bootstrap-table.js" ></script>
<script type="text/javascript" src="<%=basePath %>source/js/bootstrap-table-export.js"></script>
<script type="text/javascript" src="<%=basePath %>source/js/tableExport.js"></script>

<!-- 文件上传 --> 
<script type="text/javascript" src="<%=basePath %>source/js/jquery.uploadify.min.js"></script>

<!-- 视频播放插件 -->
<script type="text/javascript" src="<%=basePath %>source/flowplayer/flowplayer.min.js"></script> 

 <!-- 日历插件 -->
<%-- <script type="text/javascript" src="<%=basePath %>source/layDate/laydate.js"></script>   --%>

<!-- 输入验证提示框插件 -->
<script type="text/javascript" src="<%=basePath %>source/layer/layer.js"></script> 

<!-- 类似淘宝  省市区地址选择插件 -->
<script type="text/javascript" src="<%=basePath %>source/js/jquery.address.min.js"></script> 

<!-- 前端分页插件 -->
<script type="text/javascript" src="<%=basePath %>source/js/page.js"></script>
<!-- 统计图表插件 --> 
<script type="text/javascript"  src="<%=basePath %>source/js/echarts-all.js" ></script>

<!-- 查看空闲时间日历 -->
<script type="text/javascript" src="<%=basePath %>source/js/queryDate.js"></script> 

<link rel="stylesheet" href="<%=basePath%>source/css/jquery.mloading.css">
<script src="<%=basePath%>source/js/jquery.mloading.js"></script>
<script type="text/javascript" src="<%=basePath%>source/js/echarts_baidu_3.5.4.js"></script>




