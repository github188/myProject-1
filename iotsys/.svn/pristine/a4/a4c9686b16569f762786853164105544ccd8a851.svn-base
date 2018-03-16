$(function(){
	var id=$("#Id").val();
	baseAjax(false,$("#basePath").val()+"apiMassifProductAction_findMassifProductById.do?mass.id="+id,success,null);
	function success(data){
		var status=data.data.status;
		var bean=data.data.massifProductbean;
		var device=bean.deviceInfoList;
		var probeDefaults=data.data.probeDefaults;
		var probes=device[0].probeList;
		var controllers=device[0].powerControllerList;
		$("#deviceNames").val(device[0].deviceName);
		$("#deviceId").val(device[0].id);
		$("#deviceName").html(device[0].deviceName);
		$("#address").html("所在位置:"+bean.baseName+"_"+bean.massIfName);
		$("#deviceNumber").html("设备序列号:"+bean.deviceSerialNumber);
		$("#deviceStatus").html("网络状态:"+status);
		var str="<ul id='survey'>";
		var str1="<ul id='controller'>";
		for(var i=0;i<probes.length;i++){
			//如果探测器类型不是风向
			if(probes[i].probeDefaultId!="2cb099c40df94c3c9aaf32d150af08f2"){
				str+='<li style="height:250px"><p style="height:42px;position:relative;top:20px"><span class="msg_span">*</span>探测器名称<input type="hidden" name="ids" value="'+probes[i].id+'">'+
				'<input type="hidden"  name="surveyNos" value="'+probes[i].surveyNo+'" maxlength="8"></input>'+'<input type="hidden"  id="probeDefaultId'+i+'" name="probeDefaultIds" value="'+probes[i].probeDefaultId+'" maxlength="8"></input>';
				str+='<img src="iot/img/survery/'+probes[i].probeDefaultBean.imgUrl+'" id="picc'+i+'" style="margin-left: 52px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;"></img>';
				str+='<select style="width: 196px;margin-top: -25px;margin-left: -123px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePicc(this)" id="tt'+i+'" name="surveyNamePics">';
				for(var j=0;j<probeDefaults.length;j++){
					str+='<option value="'+probeDefaults[j].id+"-"+probeDefaults[j].imgUrl+'">'+probeDefaults[j].surveyName+'</option>';
				}
				str+='</select></p>';
				str+='<input type="text" id="surveyName'+i+'" value="'+probes[i].surveyName+'" name="surveyNames" style="position: relative;top: -21px;width: 177px;left:173px;height:34px;margin-left: 10px">';
				str+='<p><span class="msg_span">*</span>单位<input type="text" id="surveryUnit' +i+'" name="units" value="'+probes[i].unit+'" maxlength="8"></input></p>';
				str+='<p id="surveryMin'+i+'"><span class="msg_span">*</span>最小范围<input type="number"  id="surveryMins'+i+'" name="surveyMins" value="'+probes[i].surveyMin+'" maxlength="8"  onblur="checkNumber(this)"></input></p>';
				str+='<p id="surveryMax'+i+'"><span class="msg_span">*</span>最大范围<input type="number" id="surveryMaxs'+i+'"  name="surveyMaxs" value="'+probes[i].surveyMax+'" maxlength="8"  onblur="checkNumber(this)"></input></p></li>';
			}else{
				str+='<li style="height:250px"><p style="height:42px;position:relative;top:20px"><span class="msg_span">*</span>探测器名称<input type="hidden" name="ids" value="'+probes[i].id+'">'+
				'<input type="hidden"  name="surveyNos" value="'+probes[i].surveyNo+'" maxlength="8"></input>'+'<input type="hidden"  id="probeDefaultId'+i+'" name="probeDefaultIds" value="'+probes[i].probeDefaultId+'" maxlength="8"></input>';
				str+='<img src="iot/img/survery/'+probes[i].probeDefaultBean.imgUrl+'" id="picc'+i+'" style="margin-left: 52px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;"></img>';
				str+='<select style="width: 196px;margin-top: -25px;margin-left: -123px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePicc(this)" id="tt'+i+'" name="surveyNamePics">';
				for(var j=0;j<probeDefaults.length;j++){
					str+='<option value="'+probeDefaults[j].id+"-"+probeDefaults[j].imgUrl+'">'+probeDefaults[j].surveyName+'</option>';
				}
				str+='</select></p>';
				str+='<input type="text" id="surveyName'+i+'" value="'+probes[i].surveyName+'" name="surveyNames" style="position: relative;top: -21px;width: 177px;left:173px;height:34px;margin-left: 10px">';
				str+='<p><span class="msg_span">*</span>单位<input type="text" id="surveryUnit' +i+'" name="units" value="'+probes[i].unit+'" maxlength="8"></input></p>';
				str+='<p id="surveryMin'+i+'" style="display:none"><span class="msg_span">*</span>最小范围<input type="number" id="surveryMins'+i +
				'"  name="surveyMins" value="'+probes[i].surveyMin+'" maxlength="8"  onblur="checkNumber(this)"></input></p>';
				str+='<p id="surveryMax'+i+'" style="display:none"><span class="msg_span">*</span>最大范围<input type="number" id="surveryMaxs'+i +
				'"  name="surveyMaxs" value="'+probes[i].surveyMax+'" maxlength="8"  onblur="checkNumber(this)"></input></p></li>';
			}
		}
		str+="</ul>";
		$("#surveys").html(str);
		for(var i=0;i<controllers.length;i++){
			str1+='<li style="height:120px"><input type="hidden" name="controllerIds" value="'+controllers[i].id+'"><input type="hidden" name="controllerNos" value="'+controllers[i].surveyNo+'" maxlength="8"></input>'
			+'<p style="width:100%;height:80px"><span class="msg_span" style="margin-top: 11px;">*</span>控制器名称'
			+'<img src="iot/img/controller/' +controllers[i].icoName+'" id="pic'+i+'" style="margin-left: 52px;border: 1px;height: 34px;margin-top: 1px;border-style: solid;border-color: gray;"></img>';
			if(controllers[i].icoName=="irrigation.png"){
				str1+='<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" id="t'+i+'" name="controllerPics">';
				str1+='<option value="irrigation.png" selected="true">灌溉</option>';
				str1+='<option value="heating.png">暖气</option>';
				str1+='<option value="wind.png">通风</option>';	
				str1+='<option value="lighting.png">照明</option>';	
				str1+='<option value="collector.png">其他</option></select></p>';
			}else if(controllers[i].icoName=="heating.png"){
				str1+='<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" id="t'+i+'" name="controllerPics">';
				str1+='<option value="irrigation.png">灌溉</option>';
				str1+='<option value="heating.png" selected="true">暖气</option>';
				str1+='<option value="wind.png">通风</option>';	
				str1+='<option value="lighting.png">照明</option>';	
				str1+='<option value="collector.png">其他</option></select></p>';
			}else if(controllers[i].icoName=="wind.png"){
				str1+='<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" id="t'+i+'" name="controllerPics">';
				str1+='<option value="irrigation.png">灌溉</option>';
				str1+='<option value="heating.png">暖气</option>';
				str1+='<option value="wind.png"  selected="true">通风</option>';	
				str1+='<option value="lighting.png">照明</option>';	
				str1+='<option value="collector.png">其他</option></select></p>';
			}else if(controllers[i].icoName=="lighting.png"){
				str1+='<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" id="t'+i+'" name="controllerPics">';
				str1+='<option value="irrigation.png">灌溉</option>';
				str1+='<option value="heating.png">暖气</option>';
				str1+='<option value="wind.png" >通风</option>';	
				str1+='<option value="lighting.png" selected="true">照明</option>';	
				str1+='<option value="collector.png">其他</option></select></p>';
			}else if(controllers[i].icoName=="collector.png"){
				str1+='<select style="width: 86px;margin-top: -5px;margin-left: -2px;float: left;position: absolute;top: 36px;left: 306px;" onchange="changePic(this)" id="t'+i+'" name="controllerPics">';
				str1+='<option value="irrigation.png">灌溉</option>';
				str1+='<option value="heating.png">暖气</option>';
				str1+='<option value="wind.png" >通风</option>';	
				str1+='<option value="lighting.png">照明</option>';	
				str1+='<option value="collector.png"  selected="true">其他</option></select></p>';
			}
			str1+='<input type="text" id="controllerName'+i+'" value="'+controllers[i].controllerName+'" name="controllerNames" style="position: relative;top: -69px;width: 177px;left:173px;height:34px;margin-left: 10px">';
			str1+='</li>';
		}
		str1+="</ul>";
		$("#controllers").html(str1);
	}
	$( "#survey" ).sortable({
	      revert: false
	    });
	$( "#controller" ).sortable({
	      revert: false
	    });
	$( "ul, li" ).disableSelection();
});

/*更新设备*/
function UpdateDevice(){
	if(checkData()){
		baseAjax(false,$("#basePath").val()+"apiDeviceInfoAction_BatchUpdateDevice.do",success,$("#collector").serialize());
		function success(data){
			if(data.code=="000"){
				successTip(data.msg);
				$("#right_content").load("iot/device/jsp/deviceList.jsp");
			}else if(data.code=="001"){
				warnTip(data.msg);
				$("#right_content").load("iot/device/jsp/deviceList.jsp");
			}
		}
	}
}

/*数据检验*/
function checkData(){
	var flag=true;
    //校验探测器名称是否为空
	var surveyNames = document.getElementsByName("surveyNames");
	 for(var i=0;i<surveyNames.length;i++){
        if(surveyNames[i].value==""){
        	flag=false;
        	Msg("第"+(i+1)+"个探测器名称为空");
        	return  flag;
        }
    }
	 
	//校验探测器单位是否为空
	 var units = document.getElementsByName("units");
	 for(var i=0;i<units.length;i++){
        if(units[i].value==""){
        	flag=false;
        	Msg("第"+(i+1)+"个探测器单位数据为空");
        	return  flag;
        }
    }
	 
	/*//校验探测器通道号是否为空
	 var surveyNos = document.getElementsByName("surveyNos");
	 for(var i=0;i<surveyNos.length;i++){
        if(surveyNos[i].value==""){
        	flag=false;
    	     Msg("第"+(i+1)+"个探测器通道号为空");
        	return  flag;
        }
    }
	 
	//校验探测器通道号是否重复
	 var arr = [];
	 for(var t=0;t<surveyNos.length;t++){
	      arr.push(surveyNos[t].value);
	 }
	 for(var i=0;i<arr.length;i++){
		 for(var j=i+1;j<arr.length;j++){
			 if(arr[i]==arr[j]){
				 flag=false;
	     	     Msg("第"+(i+1)+"个探测器通道号与第"+(j+1)+"个探测器通道号重复");
	     	      return  flag;
			 }
		 }
	 }*/
	 
	//校验探测器最小范围是否为空
	 var surveyMins = document.getElementsByName("surveyMins");
	 for(var i=0;i<surveyMins.length;i++){
		 if(surveyMins[i].value==""){
	        	flag=false;
	        	 Msg("第"+(i+1)+"个探测器最小范围数据为空");
	        	return  flag;
	        }
    }
	 
	//校验探测器最大范围是否为空
	 var surveyMaxs = document.getElementsByName("surveyMaxs");
	 for(var i=0;i<surveyMaxs.length;i++){
		 if(surveyMaxs[i].value==""){
	        	flag=false;
	        	 Msg("第"+(i+1)+"个探测器最大范围数据为空");
	        	return  flag;
	        }
	 }
	 
	//校验探测器最大范围、最小范围的规范化
	 for(var k=0;k<surveyMaxs.length;k++){
		 if(parseInt(surveyMaxs[k].value)<parseInt(surveyMins[k].value)){
			 flag=false;
			 Msg("第"+(k+1)+"个探测器最大范围数值不能小于最小范围数值");
	        	return  flag;
		 }
	 }
	 
	//校验控制器名称是否为空
	 var controllerNames = document.getElementsByName("controllerNames");
	 for(var i=0;i<controllerNames.length;i++){
		 if(controllerNames[i].value==""){
	        	flag=false;
	        	Msg("第"+(i+1)+"个控制器名称为空");
	        	return  flag;
	        }
	 }
	 
/*	//校验控制器通道号是否为空
	 var controllerNos = document.getElementsByName("controllerNos");
	 for(var i=0;i<controllerNos.length;i++){
		 if(controllerNos[i].value==""){
	        	flag=false;
	        	 Msg("第"+(i+1)+"个控制器通道号为空");
	        	return  flag;
	        }
	 }
	 
	//校验控制器通道号是否重复
	 var arr1 = [];
	 for(var t1=0;t1<controllerNos.length;t1++){
	      arr1.push(controllerNos[t1].value);
	 }
	 for(var i=0;i<arr1.length;i++){
		 for(var j=i+1;j<arr1.length;j++){
			 if(arr1[i]==arr1[j]){
				 flag=false;
	     	     Msg("第"+(i+1)+"个控制器通道号与第"+(j+1)+"个控制器通道号重复");
	     	      return  flag;
			 }
		 }
	 }*/
		 return flag;
}

//提示信息提醒
function Msg(msg){
	bootbox.alert({ 
		  size: "small",
		  title: "系统提示",
		  message:msg, 
		});
}

/*返回按钮*/
function back(){
	$("#right_content").load("iot/device/jsp/deviceList.jsp");
}

/*探测器下拉图片改变名称以及图片*/
function changePicc(obj){
	var s=$(obj).val();
	var sss=$(obj).attr("id");
	var num=sss.substr(sss.length-1,sss.length);
	var a=s.split("-");
	var ss="iot/img/survery/"+a[1];
	$("#picc"+num).attr("src",ss);
	baseAjax1("apiProbeDefaultAction_findById.do?id="+a[0],success,null);
	function success(data){
		console.log(data);
		var probeDefault=data.data;
		if(probeDefault.id!="2cb099c40df94c3c9aaf32d150af08f2"){
			$("#surveyName"+num).attr("value",probeDefault.surveyName);
			$("#probeDefaultId"+num).attr("value",probeDefault.id);
			$("#surveryUnit"+num).attr("value",probeDefault.unit);
			$("#surveryMin"+num).css("display","block");
			$("#surveryMax"+num).css("display","block");
			$("#surveryMins"+num).attr("value",probeDefault.surveyMin);
			$("#surveryMaxs"+num).attr("value",probeDefault.surveyMax);
		}else{
			$("#surveyName"+num).attr("value",probeDefault.surveyName);
			$("#probeDefaultId"+num).attr("value",probeDefault.id);
			$("#surveryUnit"+num).attr("value",probeDefault.unit);
			$("#surveryMin"+num).css("display","none");
			$("#surveryMax"+num).css("display","none");
			$("#surveryMins"+num).attr("value",probeDefault.surveyMin);
			$("#surveryMaxs"+num).attr("value",probeDefault.surveyMax);
		}
	}
}

/*控制器下拉图片改变名称以及图片*/
function changePic(obj){
	var s=$(obj).val();
	var sss=$(obj).attr("id");
	var num=sss.substr(sss.length-1,sss.length);
	var ss="iot/img/controller/"+s;
	$("#pic"+num).attr("src",ss);
	if(s=="irrigation.png"){
		$("#controllerName"+num).attr("value","灌溉");
	}else if(s=="lighting.png"){
		$("#controllerName"+num).attr("value","照明");
	}else if(s=="heating.png"){
		$("#controllerName"+num).attr("value","暖气");
	}else if(s=="wind.png"){
		$("#controllerName"+num).attr("value","通风");
	}else if(s=="collector.png"){
		$("#controllerName"+num).attr("value","其他");
	}
}

//修改采集器名称
function goEditJsp(){
	var name=$("#deviceNames").val();
	$("#oldDeviceName").val(name);
	$("#m1").toggle();
}

//采集器input下拉框失去焦点
function edit(){
	var name1=$("#oldDeviceName").val();
	$("#deviceNames").val($("#oldDeviceName").val());
	$("#deviceName").html(name1);
	$("#m1").toggle();
}

//小数点数据校验
function checkNumber(obj){
	if (obj.value.split(".").length > 1 && obj.value.split(".")[1].length > 2) {    
        Msg("只能输入小数点后两位");    
    }    
}