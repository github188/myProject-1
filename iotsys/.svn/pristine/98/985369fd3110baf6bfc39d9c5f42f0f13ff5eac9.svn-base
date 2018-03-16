var probeDefaults=null;
var probeDefaultStr="";
$(function(){
	getAllEnterprise();
	findAllDeviceModel();
	getCollector();
//	yidong();
});
var deleteIds=[];
var count=-1;
function getCollector() {
	$.ajax({
		cache: false,
	    type:  "POST",
	    async : false,
		url:$("#basePath").val()+"apiDeviceInfoAction_findByIdAllInfo.do",
		data:{"deviceId":$("#deviceId").val()},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			if (data.code="000") {
				var device = data.data.deviceInfoBean;
				$("#deviceName").val(device.deviceName);
				$("#enterpriseId").find('option[value="'+device.enterpriseId+'ˇ'+device.enterpriseName+'"]').attr("selected",true);
				$("#serialNumber").val(device.serialNumber);
				$("#typeId").find('option[value="'+device.typeId+'"]').attr("selected",true);
				$("#verificationCode").val(device.verificationCode);
				
				var probeBeans = data.data.probeBeans;
				var PowerControllerBean = data.data.PowerControllerBean;
				count = probeBeans.length-1;
				var str="<ul id='aaa'>";
				for (var int = 0; int < probeBeans.length; int++) {
					var probe = probeBeans[int];
					str+='<li index="'+int+'">'
					+'<div class="deleteIcon"><a href="#" onclick="deleteMyself(this)"><img alt="" src="'+$("#basePath").val()+'iot/collector/img/delete.png"></a></div>'
					+'<input type="hidden" name="id" value="'+probe.id+'">'
	                +'<p style="height:42px;position:relative;top:20px">'
	        		+'<span class="msg_span">*</span>探测器名称'
	        		+'<input type="hidden" name="probeDefaultIds" value="'+probe.probeDefaultId+'">'
	        		+'<img src="iot/collector/img/default.png" style="margin-left: 53px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">'
	        		+'<select style="width: 196px;margin-top: -25px;margin-left: -123px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePicc(this)" name="surveyNamePics">'
	            	+probeDefaultStr
	        		+'</select>'
	        		+'<span class="tip_span" style="top: 3px;"></span>'
	        		+'</p>'
	        		+'<input type="text" value="'+probe.surveyName+'" name="surveyName" style="position: relative;top: -21px;width: 177px;left:173px;height:34px;margin-left: 10px" placeholder="请填写或选择探测器">'
	        		+'<p>'
	                +'<span class="msg_span">*</span>单位'
	                +'<input type="text" name="unit" maxlength="10" placeholder="请输入单位" value="'+probe.unit+'">'
	                +'<span class="tip_span"></span>'
	                +'</p>'
	                +'<p>'
	                +'<span class="msg_span">*</span>通道'
	                +'<input type="text" name="surveyNo" pattern="\d*" maxlength="2" placeholder="请输入通道" value="'+probe.surveyNo+'">'
	                +'<span class="tip_span"></span>'
	                +'</p>';
	                if (probe.probeDefaultId!="2cb099c40df94c3c9aaf32d150af08f2") {
	                	str+='<p>'
	                	+'<span class="msg_span">*</span>最小范围'
	                	+'<input type="text" name="surveyMin" maxlength="5" placeholder="请输入正常范围最小值" value="'+probe.surveyMin+'">'
	                	+'<span class="tip_span"></span>'
	                	+'</p>'
	                	+'<p>'
	                	+'<span class="msg_span">*</span>最大范围'
	                	+'<input type="text" name="surveyMax" maxlength="5" placeholder="请输入正常范围最大值" value="'+probe.surveyMax+'">'
	                	+'<span class="tip_span"></span>'
	                	+'</p>';
					}else{
						str+='<p style="display:none;">'
						+'<span class="msg_span">*</span>最小范围'
						+'<input type="text" name="surveyMin" maxlength="5" placeholder="请输入正常范围最小值" value="'+probe.surveyMin+'">'
						+'<span class="tip_span"></span>'
						+'</p>'
						+'<p style="display:none;">'
						+'<span class="msg_span">*</span>最大范围'
						+'<input type="text" name="surveyMax" maxlength="5" placeholder="请输入正常范围最大值" value="'+probe.surveyMax+'">'
						+'<span class="tip_span"></span>'
						+'</p>';
					}
	                
	                str+='<span class="left" onclick="gotoPre(this)"><i class="fa fa-angle-double-left" aria-hidden="true" title="往前"></i></span>'
	                +'<span class="right" onclick="gotoNext(this)"><i class="fa fa-angle-double-right" aria-hidden="true" title="往后"></i></i></span>'
	                +'</li>';
				}
				str+="</ul>";
				str+='<div class="cer-item">'
					+'<img src="'+$("#basePath").val()+'iot/collector/img/84E58PICDTF_1024.png" class="moreimg" onclick="addNewSurvey($(this))" style="margin-top: 9%;margin-left: 7%;" title="添加探测器">' 
					+'</div>';
				$("#probe").html(str);
				//选中
				var select = $("#probe").find('select');
				for (var int = 0; int < select.length; int++) {
					var val = probeBeans[int].probeDefaultId;
					$(select[int]).find('option[value="'+val+'"]').attr("selected",true);
					var index = $(select[int]).get(0).selectedIndex-1;
					var probeDefault=probeDefaults[index];
					$(select[int]).prev().attr('src','iot/img/survery/'+probeDefault.imgUrl);
				}
				
				var PowerControllerBean = data.data.PowerControllerBean;
				var str2 = "";
				for (var int2 = 0; int2 < PowerControllerBean.length; int2++) {
					str2 += '<li>'
						+'<div class="deleteIcon"><a href="#" onclick="deleteMyself(this)"><img alt="" src="'+$("#basePath").val()+'iot/collector/img/delete.png"></a></div>'
						+'<p style="width:100%;height:80px">'
						+'<span class="msg_span" style="margin-top: 11px;">*</span>控制器名称'
						+'<input type="hidden" name="controllerIds" value="'+PowerControllerBean[int2].id+'">'
						+'<img src="'+$("#basePath").val()+'iot/img/controller/'+PowerControllerBean[int2].icoName+'" id="pic0" style="margin-left: 48px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">'
						+'<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" name="controllerPics">'
						+'<option value="wind.png">通风</option>'
						+'<option value="irrigation.png">灌溉</option>'
						+'<option value="heating.png">暖气</option>'
						+'<option value="lighting.png">照明</option>'
						+'<option value="collector.png">其他</option>'
						+'</select>'
						+'<span class="tip_span"></span>'
						+'</p>'
						+'<input type="text" value="'+PowerControllerBean[int2].controllerName+'" name="controllerNames" maxlength="8" style="position: relative;top: -69px;width: 177px;left:173px;height:34px;margin-left: 10px">'
						+'<p style="margin-top: -40px;">'
						+'<span class="msg_span">*</span>通道号'
						+'<input type="text" pattern="\d*" maxlength="2" name="controllerNos" value="'+PowerControllerBean[int2].surveyNo+'" maxlength="8">'
						+'<span class="tip_span"></span>'
						+'</p>'
						+'<span class="left" onclick="gotoPre(this)"><i class="fa fa-angle-double-left" aria-hidden="true" title="往前"></i></span>'
						+'<span class="right righta" onclick="gotoNext(this)"><i class="fa fa-angle-double-right" aria-hidden="true" title="往后"></i></i></span>'
						+'</li>';
				}
				$("#controller3").html(str2);
				
				var select = $("#controller3").find('select');
				for (var int = 0; int < select.length; int++) {
					var val = PowerControllerBean[int].icoName;
					$(select[int]).find('option[value="'+val+'"]').attr("selected",true);
				}
				
				var str3 ='<div class="cer-item">'
					+'<img src="'+$("#basePath").val()+'iot/collector/img/84E58PICDTF_1024.png" class="moreimg" onclick="addNewController($(this))" style="margin-top: 3%;margin-left: 7%;" title="添加探测器">' 
					+'</div>';
				$("#controller3").after(str3);
			}else{
				warnTip("系统异常");
			}
		}
	});
}
//从mongo项目获取所有企业
function getAllEnterprise() {
	baseAjax(false,$("#basePath").val()+"apiDeviceInfoAction_getAllEnterprise.do",success,$("#collector").serialize());
	function success(data){
			console.log(data);
			var str = '<option value="">请选择</option>';
			for (var int = 0; int < data.length; int++) {
				str+='<option value="'+data[int].id+'ˇ'+data[int].enterpriseName+'">'+data[int].enterpriseName+'</option>';
			}
			$("#enterpriseId").html(str);
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
				probeDefaultStr+='<option value="'+probeDefaults[j].id+'">'+probeDefaults[j].surveyName+'</option>';
			}
			$("#t00").html(probeDefaultStr);
		}
	});
}
function editCollector() {
	console.log($("#collector").serialize());
	if (checkCollectorInfo() && checkNo()) {
		$.ajax({
			cache: false,
			type:  "POST",
			async : false,
			url:$("#basePath").val()+"apiDeviceInfoAction_editDeviceInfo.do",
			data:$("#collector").serialize()+"&deleteIds="+deleteIds,
			dataType:'json',
			traditional: true,//数组 这里设置为true
			success:function(data){
				console.log(data);
				if (data.code=="000") {
					successTip("操作成功");
					$("#right_content").load($("#basePath").val()+"iot/collector/jsp/collectorList.jsp");
				}else{
					warnTip(data.msg);
				}
			}
		});
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
function addNewSurvey(obj) {
	if (checkPrev(obj)) {
		var ul = $(obj).parent().prev();
		console.log(ul);
		var index = $(obj).find('li').length;
		var str = '<li index="'+(index+1)+'">'
		+'<div class="deleteIcon"><a href="#" onclick="deleteMyself(this)"><img alt="" src="'+$("#basePath").val()+'iot/collector/img/delete.png"></a></div>'
		+'<input type="hidden" name="id" value="a">'
		+'<p style="height:42px;position:relative;top:20px">'
		+'<span class="msg_span">*</span>探测器名称'
		+'<input type="hidden" name="probeDefaultIds" value="">'
		+'<img src="iot/collector/img/default.png" style="margin-left: 53px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">'
		+'<select style="width: 196px;margin-top: -25px;margin-left: -123px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePicc(this)" name="surveyNamePics">'
    	+probeDefaultStr
		+'</select>'
		+'<span class="tip_span" style="top: 3px;"></span>'
		+'</p>'
		+'<input type="text" value="" name="surveyName" style="position: relative;top: -21px;width: 177px;left:173px;height:34px;margin-left: 10px" placeholder="请填写或选择探测器">'
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
			+'<input type="hidden" name="controllerIds" value="a">'
			+'<img src="'+$("#basePath").val()+'iot/collector/img/default.png" id="pic0" style="margin-left: 48px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;">'
			+'<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" name="controllerPics">'
			+'<option value=""></option>'
			+'<option value="wind.png">通风</option>'
			+'<option value="irrigation.png">灌溉</option>'
			+'<option value="heating.png">暖气</option>'
			+'<option value="lighting.png">照明</option>'
			+'<option value="collector.png">其他</option>'
			+'</select>'
			+'<span class="tip_span"></span>'
			+'</p>'
			+'<input type="text" value="" name="controllerNames" maxlength="8" style="position: relative;top: -69px;width: 177px;left:173px;height:34px;margin-left: 10px">'
			+'<p style="margin-top: -40px;">'
			+'<span class="msg_span">*</span>通道号'
			+'<input type="text" pattern="\d*" maxlength="2" name="controllerNos" value="">'
			+'<span class="tip_span"></span>'
			+'</p>'
			+'<span class="left" onclick="gotoPre(this)"><i class="fa fa-angle-double-left" aria-hidden="true" title="往前"></i></span>'
			+'<span class="right righta" onclick="gotoNext(this)"><i class="fa fa-angle-double-right" aria-hidden="true" title="往后"></i></i></span>'
			+'</li>';
		ul.append(str);
	}
}
function deleteMyself(obj){
//		 if (confirm("确认删除?")) {
			$(obj).parent().parent().find('input[type="hidden"]').each(function(i){
				console.log($(this).val());
				deleteIds[deleteIds.length]=$(this).val();
			});
			$(obj).parent().parent().remove();
//		}
	
}
function checkCollectorInfo() {
	var flog = true;
	
	var input = $(".edit_all").find('input[type="text"]');
	for (var int = 0; int < input.length; int++) {
		var val = input[int].value;
		if(val=="" && $(input[int]).attr("id")!="verificationCode"){
	    	if ($(input[int]).attr("id")=="deviceName") {
	    		$(input[int]).next().text('请填写采集器名称');
			}
	    	if ($(input[int]).attr("id")=="serialNumber") {
	    		$(input[int]).next().text('请填写采集器设备序列号');
	    	}
	    	flog = false;
//	    	return false;//跳出循环
	    }else{
	    	$(input[int]).next().text('');
	    }
	}
	
	
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
			if ($(select[int]).attr("id")=="typeId") {
				$(select[int]).next().text('请选择采集器设备型号');
			}
			flog = false;
//			break;//跳出循环
		}else{
			$(select[int]).next().text('');
		}
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
					flog = false;
			    }else if (val.length > 8) {    
			    	$(surey[int]).next().text('长度不能大于8位');
			    	flog = false;
			    }else{
			    	$(surey[int]).next().text('');
				}
			}else{
				if ($(surey[int]).attr("name")=="surveyName") {
					$(surey[int]).prev().children("span:last-child").text('')
				}else{
					$(surey[int]).next().text('');
				}
			}
		}
	}
	var controllers = $("#controller3").find('input[type="text"]');
	for (var int = 0; int < controllers.length; int++) {
		var val = controllers[int].value;
		if(val==""){
			if ($(controllers[int]).attr("name")=="controllerNames") {
				$(controllers[int]).prev().children("span:last-child").text('请选择控制器,且控制器名称不能为空');
			}
			if ($(controllers[int]).attr("name")=="controllerNos") {
				$(controllers[int]).next().text('请填写控制器通道');
			}
			flog = false;
//			return false;//跳出循环
		}else{
			if ($(controllers[int]).attr("name")=="controllerNames") {
				$(controllers[int]).prev().children("span:last-child").text('');
			}
			if ($(controllers[int]).attr("name")=="controllerNos") {
				$(controllers[int]).next().text('');
			}
		}
	}
	return flog;
}
function checkPrev(obj){
	var flog = true;
	var ul = $(obj).parent().prev();
	var input = $(ul).find('input');
	$(ul).find('input').each(function(i){
	    var val = $(this).val();
	    if(val==""){
	    	warnTip('请完善前面探头信息');
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
/********************************拖动效果***********************************/
function yidong(){
    var  len=$('#aaa li').length;
    console.log(len,$('#aaa li'))

    var ps={L:[],R:[],T:[],B:[]};
    for(var i=count; i>=0; i--){
        var oLi = $('#aaa li').eq(i),
            gLi = oLi.get(0);

        oLi.css({'left':gLi.offsetLeft+'px','top':gLi.offsetTop+'px','position':'absolute','margin':0});
        ps.L.push(gLi.offsetLeft);
        ps.T.push(gLi.offsetTop);
        ps.R.push(gLi.offsetLeft + gLi.offsetWidth);
        ps.B.push(gLi.offsetTop + gLi.offsetHeight);
    };
    ps.L = ps.L.reverse();
    ps.R = ps.R.reverse();
    ps.T = ps.T.reverse();
    ps.B = ps.B.reverse();  //储存位置


    //初始化
    var b = 0;

    $(document).on('mousedown','#aaa li',function(e){
        e.preventDefault();
        var _this = this;
        if(_this.setCapture){_this.setCapture()};
        var X = e.clientX - _this.offsetLeft,
            Y = e.clientY - _this.offsetTop,
            oList = $('#aaa li'),
            attr = [];
        my_index = $( _this ).attr('index');     //初始保存一个原来的Index,回到原来的数组(位置)
        $(_this).css({'opacity':0.9,'zIndex':1});
        document.index = my_index;             //目的是为了脱离变量作用域
        $('#aaa li').each(function() {
            attr.push( $(this).attr('index') )
        });

        $(document).on('mousemove',function(e){
            var lt = e.clientX - X,
                tp = e.clientY - Y,
                screen_l = e.clientX - _this.parentNode.offsetLeft,
                screen_t = e.clientY - _this.parentNode.offsetTop;
            $(_this).css({'left':lt+'px','top':tp+'px'});

            for(var i=0;i<2;i++){

                var he_index = parseInt(oList.eq(i).attr('index')); 
                console.log(he_index,'he_index',$(_this),'$(_this)',ps,'ps')
                if(screen_l>ps.L[he_index]&&screen_l<ps.R[he_index]&&screen_t>ps.T[he_index]&&screen_t<ps.B[he_index]){
                    var i_index = parseInt($(_this).attr('index'));
                    console.log(i_index, 'i_index')
                    if(he_index == i_index){
                        console.log(he_index,'he_index')
                        continue;
                    };
                    document.index = he_index;              //当找到元素保存要抵达的位置的index
                    document.flag = false;
                    var test = function (num,j){
                        var he_Li = $('#aaa li[index='+j+']');
                        $(he_Li).stop();
                        he_Li.animate({
                            left:ps.L[j+num],
                            top:ps.T[j+num]
                        },'fast');
                        he_Li.attr('index',j+num);
                    };
                    //利用属性选择器找到对应index(也就是找到数组相应位置)的元素;并且变换属性index到相应的数组索引；

                    if(i_index>he_index){
                        for(var j=i_index-1; j>=he_index; j--){
                            test(1,j);
                        }
                    }else{
                        for(var j=i_index+1; j<he_index+1; j++){
                            test(-1,j);
                        }
                    };
                    $(_this).attr('index',he_index);  //变换_this的index
                    document.flag = true;

                }else{
                    if(document.flag){
                        var parent = _this.parentNode,
                            parent_X = e.clientX - parent.offsetLeft,
                            parent_Y = e.clientY - parent.offsetTop;
                        if( parent_X<0||parent_X>parent.offsetWidth||parent_Y<0||parent_Y>parent.offsetHeight ){
                            oList.not(_this).each(function(index, element) {
                                var a = $(element).index();
                                $(element).animate({
                                    left:ps.L[attr[a]],
                                    top:ps.T[attr[a]]
                                },'fast').attr('index',attr[a])
                            });
                            document.index = my_index;
                            $(_this).attr('index',my_index);
                            document.flag = false;
                            //当移出父节点还原
                        }

                    }
                };

            };
        });
        $(document).on('mouseup',function(){
        	console.log(_this,'this000')
            if(_this.releaseCapture){
                _this.releaseCapture();
            };
            $(this).off('mousemove');
            $(this).off('mouseup');

            $(_this).animate({
                left:ps.L[document.index],
                top:ps.T[document.index]
            },'fast',function(){
                $(_this).css({'opacity':1,'zIndex':0})
            });
            delete document.index;
            delete document.flag;

        });
    });


}