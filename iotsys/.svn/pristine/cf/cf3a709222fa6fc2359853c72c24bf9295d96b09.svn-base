$(function(){
	getOneById();
	uploadGiftFiles("attach","img1");
});

function getOneById(){
	baseAjax1($("#basePath").val()+"cameraInfoAction_getCameraById.do",success,{"id":$("#Id").val()});
	function success(data){
		if(data.code=="001"){
			$('#cameraName').val(data.data.cameraName);
			var imgurl=$("#basePath").val()+'upload/camera/'+data.data.cameraImg;
       		$("#img1").attr("src",imgurl);
       		$("#imgname").val(data.data.cameraImg);
			var address = "未绑定地块";
			if (data.data.massifProductbean.baseName!="") {
				address=data.data.massifProductbean.baseName+'_'+data.data.massifProductbean.massIfName;
			}
			$('#address').html("所在位置："+address);
			$('#pkDeviceId').html("视频服务器："+data.data.pkDeviceId);
			$('#cameraStatus').html("网络状态："+data.msg);
			$('#comments').html(data.data.comments);
		}
	}	
}
function updateCamera(){
	if (checkDatas()) {
		var cameraName=$('#cameraName').val();
		var comments=$('#comments').val() ;
		var json='{"cameraId":"'+$("#Id").val()+'","cameraName":"'+cameraName+'","comments":"'+comments+'"}'
		console.log(json);
		baseAjax1($("#basePath").val()+"cameraInfoAction_updateCamera.do",success, $("#collector").serialize());
		function success(data){
			if(data.code=="001"){
				successTip("修改成功");
				$("#right_content").load("iot/camera/jsp/cameraList.jsp");
			}else{
				warnTip("系统异常");
			}
		}
	}
}
function checkDatas(){
	var flog = true;
	var cameraName=$('#cameraName').val();
	if (cameraName=="") {
		$('#cameraName').next().html("摄像头名称不能为空");
		flog=false;
	}
	return flog;
}

function uploadGiftFiles(idStr,imgId){
	var url=$("#basePath").val()+'apiUploadAction_UploadFile.do';
	$("#"+idStr).uploadify({
		method:"post",
		'uploader':url,
       'swf':$("#basePath").val()+'source/uploadify.swf',
       'preventCaching':true,
       'fileSizeLimit':"1MB",
       'fileObjName':'uploadify', //对应input中的value值
       'width':'180',
       'multi': false,  
       'fileTypeExts':"*.gif;*.jpeg;*.jpg;*.png;*.bmp;",
       'height':'35',
       'auto': true,
       'buttonText': "上传图片",
       'onUploadSuccess':function(file,data,response){
  			data=eval("("+data+")");
       		var imgurl=$("#basePath").val()+data.data.imgUrl;
       		$("#"+imgId).attr("src",imgurl);
       		$("#imgname").val(data.data.filenames);
       },
       'onSelectError': function (file, errorCode, errorMsg) {  
           switch (errorCode) {  
                  case -100:  
                      alertMsg("上传的文件数量已经超出系统限制的" + $('#'+idStr).uploadify('settings', 'queueSizeLimit') + "个文件!");  
                      break;  
                  case -110:  
                	  alertMsg("文件大小超出系统限制的" + $('#'+idStr).uploadify('settings', 'fileSizeLimit') + "大小!");  
                      break;  
                  case -120:  
                	  alertMsg("文件大小异常!");  
                      break;  
                  case -130:  
                	  alertMsg("上传文件类型不正确!<br>请上传:gif,jpeg,jpg,png,bmp,mp4!");  
                      break;  
               }  
        }
	});	
}