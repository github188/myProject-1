var id=$("#cameraId").val();
var flog=0;
$(function(){
	console.log(111);
	getAllEnterprise();
	findAllDeviceModel();
	
	if(id!=null && id!=""){
		getOneById(id);
		$("#flog").val(1);
	}
	//图片上传
	var jsessionid = $("#sessionId").val();
	var url='apiUploadAction_UploadFile.do;jsessionid='+jsessionid;
	uploadFile(url,"cerUp1", "cerImg1","imgText1");
})
function getAllEnterprise() {
	baseAjax(false,"apiDeviceInfoAction_getAllEnterprise.do",success,$("#collector").serialize());
	function success(data){
			getAllenterprise(data,'enterpriseName',false,"end");
	}
}
function findAllDeviceModel() {
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:$("#basePath").val()+"apiDeviceInfoAction_findAllDeviceModel.do",
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			var str = '<option value="">请选择</option>'
				for (var int = 0; int < data.data.length; int++) {
					str+='<option value="'+data.data[int].id+'">'+data.data[int].typeName+'</option>';
				}
			$("#unitType").html(str);
		}
	});
}
function addCamera(){
	if (checkDatas()) {
		$("#enterpriseId").val($("#enterpriseId").val()+"ˇ"+$("#end").html())
		
		baseAjax1($("#basePath").val()+"cameraInfoAction_addCamera.do",success, $("#cameraInfo").serialize()+"&flog="+flog);
		function success(data){
			if(data.code=="001"){
				$("#right_content").load("iot/camera/jsp/cameraListServer.jsp");
				successTip("操作成功");
			}else{
				warnTip(data.msg);
			}
		}
	}
}

function checkDatas(){
	var flog=true;
	if($('#cameraName').val()==''){
		$('#cameraName').next().text('摄像头名称不能为空');
		flog=false;
	}else{
		$('#cameraName').next().text('');
	}
	if($('#enterpriseId').val()==''){
		$('#enterpriseId').next().text('关联企业不能为空');
		flog=false;
	}else{
		$('#enterpriseId').next().text('');
	}
	var reg = /^[0-9a-zA-Z]+$/
	if($('#serialNumber').val()==''){
		$('#serialNumber').next().text('摄像头序列号不能为空');
		flog=false;
	}else if(!reg.test($('#serialNumber').val())){
		$('#serialNumber').next().text('摄像头序列号不正确，只能填写数字和字母');
		flog=false;
	}else{
		$('#serialNumber').next().text('');
	}
	if($('#pkDeviceId').val()==''){
		$('#pkDeviceId').next().text('视频服务器不能为空');
		flog=false;
	}else{
		$('#pkDeviceId').next().text('');
	}
//	var reg1 = /^[0-9]+$/
	if($('#channel').val()==''){
		$('#channel').next().text('通道不能为空');
		flog=false;
	}
//	else if(!reg1.test($('#channel').val())){
//		$('#channel').next().text('通道不正确，只能是数字');
//		flog=false;
//	}
	else{
		$('#channel').next().text('');
	}
	if($('#unitType').val()==''){
		$('#unitType').next().text('设备型号不能为空');
		flog=false;
	}else{
		$('#unitType').next().text('');
	}
	return flog
}

function getOneById(obj){
	baseAjax1($("#basePath").val()+"cameraInfoAction_getCameraById.do",success,{"id":obj});
	function success(data){
		if(data.code=="001"){
			$('#cameraName').val(data.data.cameraName);
			$('#pkDeviceId').val(data.data.pkDeviceId);
			$('#unitType').val(data.data.cameraUnitType);
			$('#serialNumber').val(data.data.serialNumber);
			$('#channel').val(data.data.cameraChannnel);
			$('#loginId').val(data.data.caneraLoginId);
			$('#password').val(data.data.cameraPassword);
			$('#control1').val(data.data.cameraPtzControl);
			$('#control2').val(data.data.cameraZoomControl);
			$('#comments').val(data.data.comments);
			
			$('#cerImg1').attr('src',data.data.cameraImg);
			$('#imgText1').val(data.data.cameraImg);
			$("#enterpriseId").val(data.data.pkEnterpriseId)
			$("#end").html(data.data.enterpriseName)
			
			$('#pkDeviceId').find('option[value="'+data.data.pkDeviceId+'"]').attr('selected',true);
			$('#unitType').find('option[value="'+data.data.pkDeviceId+'"]').attr('selected',true);
		}
	}	
}

