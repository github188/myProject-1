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
    <link rel="stylesheet" href="iot/css/video.css">
    <script src="iot/js/video.js" type="text/javascript"></script>
</head>
<body>

<div class="video_center" style="position: relative">
    <div class="video_left">
        <div class="zzsc-container">
            <div class="content">
                <div id="jquery-accordion-menu" class="jquery-accordion-menu red video_all" style="max-height: 600px;  overflow: auto;">
                    <div class="jquery-accordion-menu-header" id="form_video"></div>
                    <ul id="demo-list_video">
                        <li class="active">
                            <a href="#">
                                <div class="video_fa_tit">Events</div>
                                <div class="video_fa"><img src="iot/img/video_tie.png" alt=""></div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="video_fa_tit">Events</div>
                                <div class="video_fa"><img src="iot/img/video_tie.png" alt=""></div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="video_fa_tit">Events</div>
                                <div class="video_fa"><img src="iot/img/video_tie.png" alt=""></div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="video_fa_tit">aaaa</div>
                                <div class="video_fa"><img src="iot/img/video_tie.png" alt=""></div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="video_fa_tit">Events</div>
                                <div class="video_fa"><img src="iot/img/video_tie.png" alt=""></div>
                            </a>
                        </li>
                    </ul>
                    <!--<div class="jquery-accordion-menu-footer">-->
                        <!--Footer-->
                    <!--</div>-->
                </div>
            </div>
        </div>
    </div>
    <div class="video_right">
        <div class="video_right_title">青苹果视屏监控</div>
        <video src="" width="100%" height="auto"></video>
    </div>
</div>
<script>
    jQuery(document).ready(function () {
        jQuery("#jquery-accordion-menu").jqueryAccordionMenu()
    });
    $(function(){
        //列表项背景颜色切换
        $("#demo-list_video li").click(function(){
            $("#demo-list_video li.active").removeClass("active")
            $(this).addClass("active");
        })
    })
</script>
</body>
</html>