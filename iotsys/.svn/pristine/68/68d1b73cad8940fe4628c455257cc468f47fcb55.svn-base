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
</head>
<body>
<div class="video_center">
    <div class="video_search">
        <button class="btn btn-primary btn-lg  new_video" data-toggle="modal" data-target="#myModal">
            <i class="fa fa-plus" aria-hidden="true" style="padding:0 10px "></i>添加摄像头
        </button>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="margin-top: 230px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加摄像头
                    </h4>
                </div>
                <div class="modal-body">
                    <p class="modal_p"><span>摄像头名称</span><input class="modal_ipt"    type="text" placeholder="摄像头名称"></p>
                    <p class="modal_p"><span>设备序列号</span><input class="modal_ipt"type="text" placeholder="设备序列号"></p>
                </div>
                <div class="modal-footer">
                    <!--<button type="button" class="btn btn-default" data-dismiss="modal">关闭-->
                    <!--</button>-->
                    <button type="button" class="btn btn-primary">
                        开始连接
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
</body>
</html>
