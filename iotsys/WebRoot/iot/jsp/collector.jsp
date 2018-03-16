<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="iot/css/env_monitoring.css">
    <!--<script src="../js/env_monitoring.js" type="text/javascript"></script>-->
</head>
<body>

<div class="video_center" style="position: relative">
    <div class="env_top">
        <div class="top_ipt_fa"><input class="env_top_ipt" placeholder="按采集器名称搜索" type="text"><i class="fa fa-search" aria-hidden="true"></i></div>
    </div>
    <div class="env_center">
        <table id="collector_table">
            <thead>
                <tr>
                    <th  >
                        采集器名称
                    </th>
                    <th>
                        关联探测器类型
                    </th>
                    <th>
                        关联控制器类型
                    </th>
                    <th>
                        网络状态
                    </th>
                </tr>
            </thead>
            <tbody>

                <tr>
                    <td><a href="#" onclick="goto('iot/jsp/edit_collection.jsp')">青苹果一号园区采集点</a></td>
                    <td>土壤湿度：10-50 %；  土壤盐分：1-3ms/cm；  土壤温度：5-10C；  土壤PH：7-10ph；  空气湿度：40-70%；  光照强度：90-110lux；  空气湿度：30-60C；</td>
                    <td>灌溉：开    电磁阀：关    暖气：关</td>
                    <td>正常</td>
                </tr>
                <tr>
                    <td><a href="#" onclick="goto('iot/jsp/edit_collection.jsp')">青苹果一号园区采集点</a></td>
                    <td>土壤湿度：10-50 %；  土壤盐分：1-3ms/cm；  土壤温度：5-10C；  土壤PH：7-10ph；  空气湿度：40-70%；  光照强度：90-110lux；  空气湿度：30-60C；</td>
                    <td>灌溉：开    电磁阀：关    暖气：关</td>
                    <td class="state_no">不正常</td>
                </tr>
             </tbody>
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
