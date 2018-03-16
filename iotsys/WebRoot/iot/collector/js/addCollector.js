var probeDefaults=null;
var probeDefaultStr="";
$(function(){
	getAllEnterprise();
	findAllDeviceModel();
});

function addCollector() {
	if (checkCollectorInfo() && checkNo()) {
		$("#enterpriseId").val($("#enterpriseId").val()+"ˇ"+$("#end").html());
		$.ajax({
			cache: false,
			type:  "POST",
			async : false,
			url:$("#basePath").val()+"apiDeviceInfoAction_addDeviceInfo.do",
			data:$("#collector").serialize(),
			dataType:'json',
			traditional: true,//数组 这里设置为true
			success:function(data){
				console.log(data);
				if (data.code=="000") {
					$("#right_content").load($("#basePath").val()+"iot/collector/jsp/collectorList.jsp");
					successTip("操作成功");
					
				}else{
					warnTip(data.msg);
				}
			}
		});
	}
}
function checkCollectorInfo() {
	var flog = true;
	$(".edit_all").find('input[type="text"]').each(function(i){
	    var val = $(this).val();
	    if(val=="" && $(this).attr("id")!="verificationCode"){
	    	if ($(this).attr("id")=="deviceName") {
	    		$(this).next().text('请填写采集器名称');
			}
	    	if ($(this).attr("id")=="serialNumber") {
	    		$(this).next().text('请填写采集器设备序列号');
	    	}
	    	flog = false;
//	    	return false;//跳出循环
	    }else{
	    	$(this).next().text('');
	    }
	});
	var reg = /^[0-9a-zA-Z]+$/;
	if($('#serialNumber').val()!="" && !reg.test($('#serialNumber').val())){
		$('#serialNumber').next().text('采集器序列号不正确，只能填写数字和字母');
	}
	var select = $(".edit_all").find('select');
	for (var int = 0; int < select.length; int++) {
		var val = select[int].value;
		if(val==""){
			if ($(select[int]).attr("id")=="enterpriseId") {
	    		$(select[int]).next().text('请选择采集器关联企业');
	    	}
			flog = false;
//			break;//跳出循环
		}else{
			$(select[int]).next().text('');
		}
	}
	if ($("#enterpriseId").val()=="") {
		$("#enterpriseId").next().text('请选择采集器关联企业');
	}else{
		$("#enterpriseId").next().text('');
	}
	var surey = $("#probe").find('input[type="text"]');
	for (var int = 0; int < surey.length; int++) {
		var val = surey[int].value;
		if(val==""){
			if ($(surey[int]).attr("name")=="surveyName") {
	    		$(surey[int]).prev().children("span:last-child").text('请填写探测器名称')
	    	}
			if ($(surey[int]).attr("name")=="unit") {
				$(surey[int]).next().text('请填写探测器单位');
			}
			if ($(surey[int]).attr("name")=="surveyNo") {
				$(surey[int]).next().text('请填写探测器通道');
			}
			if ($(surey[int]).attr("name")=="surveyMin") {
				$(surey[int]).next().text('请填写探测器最小范围');
			}
			if ($(surey[int]).attr("name")=="surveyMax") {
				$(surey[int]).next().text('请填写探测器最大范围');
			}
			flog = false;
//			return false;//跳出循环
		}else{
			if ($(surey[int]).attr("name")=="surveyMin" || $(surey[int]).attr("name")=="surveyMax") {
				if (val.split(".").length > 1 && val.split(".")[1].length > 2) {    
					$(surey[int]).next().text('只能输入小数点后两位');
			    }else if (val.length > 8) {    
			    	$(surey[int]).next().text('长度不能大于8位');
			    }else{
			    	$(surey[int]).next().text('');
				}
				flog = false;
			}else{
				$(surey[int]).next().text('');
			}
		}
	}
	$("#controllers").find('input[type="text"]').each(function(i){
		var val = $(this).val();
		if(val==""){
			if ($(this).attr("name")=="controllerNames") {
				console.log($(this).prev().children("span:last-child"));
				$(this).prev().children("span:last-child").text('请选择控制器,且控制器名称不能为空');
			}
			if ($(this).attr("name")=="controllerNos") {
				$(this).next().text('请填写控制器通道');
			}
			flog = false;
//			return false;//跳出循环
		}else{
			if ($(this).attr("name")=="controllerNames") {
				console.log($(this).prev().children("span:last-child"));
				$(this).prev().children("span:last-child").text('');
			}
			if ($(this).attr("name")=="controllerNos") {
				$(this).next().text('');
			}
		}
	});
	
	return flog;
}
function getAllEnterprise() {
	baseAjax(false,$("#basePath").val()+"apiDeviceInfoAction_getAllEnterprise.do",success,$("#collector").serialize());
	function success(data){
			console.log(data);
			var str = '<option value="">请选择</option>';
			for (var int = 0; int < data.length; int++) {
				str+='<option value="'+data[int].id+'ˇ'+data[int].enterpriseName+'">'+data[int].enterpriseName+'</option>';
			}
			$("#enterpriseId").html(str);
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
			var str = '<option value="1">其他</option>'
				for (var int = 0; int < data.data.deviceModels.length; int++) {
					str+='<option value="'+data.data.deviceModels[int].id+'">'+data.data.deviceModels[int].typeName+'</option>';
				}
			$("#typeId").html(str);
			probeDefaults = data.data.probeDefaults;
			probeDefaultStr += '<option value=""></option>';
			for(var j=0;j<probeDefaults.length;j++){
				probeDefaultStr+='<option value="'+probeDefaults[j].id+"-"+probeDefaults[j].imgUrl+'">'+probeDefaults[j].surveyName+'</option>';
			}
			$("#t00").html(probeDefaultStr);
		}
	});
}

function addNewSurvey(obj) {
	if (checkPrev(obj)) {
		var ul = $(obj).parent().prev();
		console.log(ul);
		var index = $(ul).find('li').length;
		var str = '<li index="'+(index+1)+'">'
		+'<div class="deleteIcon"><a href="#" onclick="deleteMyself(this)"><img alt="" src="'+$("#basePath").val()+'iot/collector/img/delete.png"></a></div>'
		
		+'<p style="height:42px;position:relative;top:20px">'
		+'<span class="msg_span">*</span>探测器名称'
		+'<input type="hidden" name="probeDefaultIds" value="">'
		+'<img src="iot/collector/img/default.png" style="margin-left: 53px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">'
		+'<select style="width: 196px;margin-top: -25px;margin-left: -123px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePicc(this)" name="surveyNamePics">'
    	+probeDefaultStr
		+'</select>'
		+'<span class="tip_span" style="top: 3px;"></span>'
		+'</p>'
		+'<input type="text" id="surveyName" value="" name="surveyName" style="position: relative;top: -21px;width: 177px;left:173px;height:34px;margin-left: 10px" placeholder="请填写或选择探测器">'
		
		+'<p>'
		+'<span class="msg_span">*</span>单位'
		+'<input type="text" name="unit" maxlength="8" placeholder="例如土壤温度的单位为℃">'
		+'<span class="tip_span"></span>'
		+'</p>'
		+'<p>'
		+'<span class="msg_span">*</span>通道'
		+'<input type="text" name="surveyNo" pattern="\d*" maxlength="2" placeholder="请输入通道">'
		+'<span class="tip_span"></span>'
		+'</p>'
		+'<p>'
		+'<span class="msg_span">*</span>正常范围最小值'
		+'<input type="text" name="surveyMin" maxlength="5" placeholder="请输入正常范围最小值">'
		+'<span class="tip_span"></span>'
		+'</p>'
		+'<p>'
		+'<span class="msg_span">*</span>正常范围最大值'
		+'<input type="text" name="surveyMax" maxlength="5" placeholder="请输入正常范围最大值">'
		+'<span class="tip_span"></span>'
		+'</p>'
		+'<span class="left" onclick="gotoPre(this)"><i class="fa fa-angle-double-left" aria-hidden="true" title="往前"></i></span>'
        +'<span class="right" onclick="gotoNext(this)"><i class="fa fa-angle-double-right" aria-hidden="true" title="往后"></i></i></span>'
		+'</li>';
		ul.append(str);
	}
}
function addNewController(obj) {
	if (checkPrev(obj)) {
		var ul = $(obj).parent().prev();
		console.log(ul);
		var index = $(ul).find('li').length;
		var str = '<li>'
			+'<div class="deleteIcon"><a href="#" onclick="deleteMyself(this)"><img alt="" src="iot/collector/img/delete.png"></a></div>'
			+'<p style="width:100%;height:80px">'
			+'<span class="msg_span" style="margin-top: 11px;">*</span>控制器名称'
			+'<img src="'+$("#basePath").val()+'iot/collector/img/default.png" id="pic0" style="margin-left: 48px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">'
			+'<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" id="t0" name="controllerPics">'
			+'<option value=""></option>'
			+'<option value="wind.png">通风</option>'
			+'<option value="irrigation.png">灌溉</option>'
			+'<option value="heating.png">暖气</option>'
			+'<option value="lighting.png">照明</option>'
			+'<option value="collector.png">其他</option>'
			+'</select>'
			+'<span class="tip_span"></span>'
			+'</p>'
			+'<input type="text" id="controllerNames" value="" maxlength="8" name="controllerNames" style="position: relative;top: -69px;width: 177px;left:173px;height:34px;margin-left: 10px">'
			+'<p style="margin-top: -40px;">'
			+'<span class="msg_span">*</span>通道号'
			+'<input type="text" name="controllerNos" pattern="\d*" maxlength="2" value="">'
			+'<span class="tip_span"></span>'
			+'</p>'
			+'<span class="left" onclick="gotoPre(this)"><i class="fa fa-angle-double-left" aria-hidden="true" title="往前"></i></span>'
			+'<span class="right righta" onclick="gotoNext(this)"><i class="fa fa-angle-double-right" aria-hidden="true" title="往后"></i></i></span>'
			+'</li>';
		ul.append(str);
	}
}
function checkNo(){
	var flag=true
	//校验通道号是否重复
	var surveyNo = document.getElementsByName("surveyNo");
	var arr2 = [];
	for(var t1=0;t1<surveyNo.length;t1++){
		arr2.push(surveyNo[t1].value);
	}
	var reg1 = /^[0-9]+$/;
	for(var i=0;i<arr2.length;i++){
		if(!reg1.test(arr2[i])){
			warnTip("第"+(i+1)+"个探测器通道不正确，只能是数字");
			flag=false;
		}
		 for(var j=i+1;j<arr2.length;j++){
			 if(arr2[i]==arr2[j]){
				 flag=false;
	    	     warnTip("第"+(i+1)+"个探测器通道号与第"+(j+1)+"个探测器通道号重复");
	    	     return  flag;
			 }
		 }
	}
	
	var controllerNos = document.getElementsByName("controllerNos");
	var arr1 = [];
	for(var t1=0;t1<controllerNos.length;t1++){
	     arr1.push(controllerNos[t1].value);
	}
	for(var i=0;i<arr1.length;i++){
		if(!reg1.test(arr1[i])){
			warnTip("第"+(i+1)+"个控制器通道不正确，只能是数字");
			flag=false;
		}
		 for(var j=i+1;j<arr1.length;j++){
			 if(arr1[i]==arr1[j]){
				 flag=false;
				 warnTip("第"+(i+1)+"个控制器通道号与第"+(j+1)+"个控制器通道号重复");
	    	     return  flag;
			 }
		 }
	}
	return  flag;
}
function deleteMyself(obj){
//	bootbox.confirm("确认删除", function(result) {
//		 if (result) {
			 $(obj).parent().parent().remove();
//		}
//	});
}
function checkPrev(obj){
	var flog = true;
	var ul = $(obj).parent().prev();
	var input = $(ul).find('input');
	$(ul).find('input').each(function(i){
	    var val = $(this).val();
	    if(val==""){
	    	warnTip('请完善前一个信息');
	    	flog = false;
	    	return false;//跳出循环
	    }
	});
	return flog;
}
/*控制器下拉图片改变名称以及图片*/
function changePic(obj){
	var s=$(obj).val();
	var sss=$(obj).attr("id");
	var ss=$("#basePath").val()+"iot/img/controller/"+s;
	$(obj).prev().attr("src",ss);
	if(s=="irrigation.png"){
		$(obj).parent().next().attr("value","灌溉");
	}else if(s=="lighting.png"){
		$(obj).parent().next().attr("value","照明");
	}else if(s=="heating.png"){
		$(obj).parent().next().attr("value","暖气");
	}else if(s=="wind.png"){
		$(obj).parent().next().attr("value","通风");
	}else if(s=="collector.png"){
		$(obj).parent().next().attr("value","其他");
	}
}
function gotoNext(obj){
	console.log("往后");
	console.log($(obj).parent().next());
	if ($(obj).parent().next().length>0) {
		$(obj).parent().next().after($(obj).parent());
	}else{
		successTip("已经是最后一个");
	}
}
function gotoPre(obj){
	console.log("往前");
	console.log($(obj).parent().prev());
	if ($(obj).parent().prev().length>0) {
		$(obj).parent().prev().before($(obj).parent());
	}else{
		successTip("已经是最前一个");
	}
}

function clearNoNum(obj) {  
	console.log(obj);
    obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数  

} 
/*探测器下拉图片改变名称以及图片*/
function changePicc(obj){
	console.log($(obj).get(0).selectedIndex); 
	var li = $(obj).parent().parent();
	var index = $(obj).get(0).selectedIndex-1;
	var probeDefault=probeDefaults[index];
	if(probeDefault.id!="2cb099c40df94c3c9aaf32d150af08f2"){
		$(li).find("input[name='surveyName']").val(probeDefault.surveyName);
		$(obj).prev().prev().val(probeDefault.id);
		$(obj).prev().attr("src",'iot/img/survery/'+probeDefault.imgUrl);
		$(li).find("input[name='unit']").val(probeDefault.unit);
		
		$(li).find("input[name='surveyMin']").val(probeDefault.surveyMin);
		$(li).find("input[name='surveyMax']").val(probeDefault.surveyMax);
		$(li).find("input[name='surveyMin']").parent().show();
		$(li).find("input[name='surveyMax']").parent().show();
		
	}else{
		$(li).find("input[name='surveyName']").val(probeDefault.surveyName);
		$(obj).prev().prev().val(probeDefault.id);
		$(obj).prev().attr("src",'iot/img/survery/'+probeDefault.imgUrl);
		$(li).find("input[name='unit']").val(probeDefault.unit);
		
		$(li).find("input[name='surveyMin']").val(-1);
		$(li).find("input[name='surveyMax']").val(-1);
		$(li).find("input[name='surveyMin']").parent().hide();
		$(li).find("input[name='surveyMax']").parent().hide();
	}
}