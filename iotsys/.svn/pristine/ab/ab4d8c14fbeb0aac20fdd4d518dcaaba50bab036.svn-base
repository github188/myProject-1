<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #allmap{width:100%;height:100%;}
        p{margin-left:5px; font-size:14px;}
    </style>
    <!--<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SOngjEUe8tUnf10yPVVtp7kkAfqlnSnq"></script>-->
    <!--<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>-->

    <!--<link href="../css/index.css" rel="stylesheet" type="text/css" />-->
    <title></title>
</head>
<body>
<!--<div class="video_center">-->
    <!--<div id="allmap"></div>-->
<!--</div>-->
<div id="allmap"></div>
</body>
</html>
    <script type="text/javascript">
    // 百度地图API功能
    map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(116.417854,39.921988), 15);
    var data_info = [[116.417854,39.921988,
    "2个采集器\n"
    + ' <br>'+
    "2个摄像头",],
        [116.406605,39.921585,"地址：北京市东城区东华门大街"],
        [116.412222,39.912345,"地址：北京市东城区正义路甲5号"]
    ];
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();
    var opts = {
        width : 250,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        title : "当前已安装" , // 信息窗口标题
        enableMessage:true//设置允许信息窗发送短息
    };
    for(var i=0;i<data_info.length;i++){
        var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
//         marker.enableDragging(); //marker可拖拽
        var content = data_info[i][2];
        map.addOverlay(marker);               // 将标注添加到地图中
        addClickHandler(content,marker);
    }
    function addClickHandler(content,marker){
        marker.addEventListener("click",function(e){
            openInfo(content,e)}
        );
    }
    function openInfo(content,e){
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow,point); //开启信息窗口
    }
</script>
