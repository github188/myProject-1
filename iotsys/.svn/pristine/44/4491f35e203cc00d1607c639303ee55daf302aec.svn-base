<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <!--<link href="../css/index.css" rel="stylesheet" type="text/css" />-->
    <script src="iot/js/env_detailed.js" type="text/javascript"></script>
</head>
<body>
<div class="video_center det_body">
    <div class="det_tit">
        <p class="det_p1">青苹果一号园区采集点 </p>
        <p  class="det_p2">数据更新时间：2017-12-11  <span>11:05:15</span></p>
    </div>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                探测器信息
            </a>
        </li>
        <li><a href="#ios" data-toggle="tab">控制器信息</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="home">
            <div class="det_chart">
                <div class="chart_choice">
                    <select name="" id="" class="chart_choice_select">
                        <option value="">近一个月</option>
                    </select>
                    <a href="javascript:void(0);" class="i_color"><i class="fa fa-bar-chart fa-2x " aria-hidden="true"></i></a>
                    <a href="javascript:void(0);"><i class="fa fa-pie-chart fa-2x" aria-hidden="true"></i></a>
                </div>
                <div class="env_chart">
                    <ul>
                        <li>
                            <div class="env_li_name">
                                <p class="p_1">土壤湿度（%）</p>
                                <p class="p_2">22</p>
                            </div>
                            <div class="env_li_chart"></div>
                        </li>
                        <li>
                            <div class="env_li_name">
                                <p class="p_1">土壤湿度（%）</p>
                                <p class="p_2">22</p>
                            </div>
                            <div class="env_li_chart"></div>
                        </li>
                        <li>
                            <div class="env_li_name">
                                <p class="p_1">土壤湿度（%）</p>
                                <p class="p_2">22</p>
                            </div>
                            <div class="env_li_chart"></div>
                        </li>
                        <li>
                            <div class="env_li_name">
                                <p class="p_1">土壤湿度（%）</p>
                                <p class="p_2">22</p>
                            </div>
                            <div class="env_li_chart"></div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="ios">
            <div class="env_chart env_controller">
                <ul>
                    <li>
                        <div class="env_li_name">
                            <p class="p_1">灌溉</p>
                            <p class="p_2">当前状态：开</p>
                            <p>
                                <input class="tgl tgl-light" name="tg_checked" id="cb1" onclick="checkboxOnclick(this)" type="checkbox" checked ='checked'>
                                <label class="tgl-btn" for="cb1"></label>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="env_li_name">
                            <p class="p_1">电磁阀</p>
                            <p class="p_2">当前状态：关</p>
                            <p>
                                <input class="tgl tgl-light" name="tg_checked" id="cb2" onclick="checkboxOnclick(this)" type="checkbox">
                                <label class="tgl-btn" for="cb2"></label>
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
