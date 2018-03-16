$(function(){
	getAllEnterprise();
	
});

function getAllEnterprise() {
	baseAjax(false,"apiDeviceInfoAction_getAllEnterprise.do",success,$("#collector").serialize());
	function success(data){
			console.log(data);
			var str = '<option value="">请选择</option>';
			for (var int = 0; int < data.length; int++) {
				str+='<option value="'+data[int].id+'">'+data[int].enterpriseName+'</option>';
			}
			$("#enterpriseId").html(str);
	}
}

function findAlAndDevice() {
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:"apiAlQeadaAction_findAlAndDevice.do",
		data:{"enterpriseId":$("#enterpriseId").val()},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			if (data.code=="000") {
				
				$("#chooseDevice").html("");
				$("#massif").html("");
				$("#allChoose").html('');//清空
				
				var alQeada =data.data.alQeada.data;
				console.log(alQeada);
				var astr='';
				var cstr='';
				var dstr='';
				for (var int = 0; int < alQeada.length; int++) {
					astr+='<li onclick="findMassifByalQeadaId(\''+alQeada[int].id+'\',\''+alQeada[int].alQaedaName+'\')" onmouseover="showMyself(this)" onmouseout="hideMyself(this)">'+alQeada[int].alQaedaName
						+'<span class="editWrap" style="display: none;">'
						+'<i class="edtIcon icon-edit" onclick="editAlQeadaModal(\''+alQeada[int].id+'\',\''+alQeada[int].alQaedaName+'\',\''+alQeada[int].lng+'\',\''+alQeada[int].lat+'\')" title="修改" style="transform: translate(0, 1px)"></i>'
			  			+'<i class="edtIcon icon-trash" onclick="deleteAlQeada(\''+alQeada[int].id+'\',\''+alQeada[int].alQaedaName+'\')" title="删除"></i>'
			  			+'</span>'
			  			+'</li>';
				}
				$("#alQeada").html(astr);
				
				$("#massif").html("");
				$("#allChoose").html('');//清空已绑定装备
			}
			$(".listWrap li").click(function(event){
		  	    $(this).addClass("active1");
		  	    $(this).siblings().removeClass("active1");
		  	});
		}
	});
}
function findMassifByalQeadaId(id,name) {
	$("#massifBaseId").val(id);
	$("#massifBaseName").val(name);
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:"apiAlQeadaAction_findMassifByalQeadaId.do",
		data:{"alQeadaId":id},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			if (data.code=="000") {
				var massif =data.data.massif.data;
				console.log(massif);
				if (massif!=null) {
					var mstr='';
					for (var int = 0; int < massif.length; int++) {
						mstr+='<li onclick="findInfoByMassifId(\''+massif[int].id+'\',\''+massif[int].massifName+'\')" onmouseover="showMyself(this)" onmouseout="hideMyself(this)">'+massif[int].massifName
							+'<span class="editWrap" style="display: none;">'
							+'<i class="edtIcon icon-edit" onclick="editMassifModal(\''+massif[int].id+'\',\''+massif[int].massifName+'\')" title="修改" style="transform: translate(0, 1px)"></i>'
				  			+'<i class="edtIcon icon-trash" onclick="deleteMassif(\''+massif[int].id+'\',\''+massif[int].massifName+'\')" title="删除"></i>'
				  			+'</span>'
				  			+'</li>';
					}
					$("#massif").html(mstr);
					$("#allChoose").html('');//清空已绑定装备
					
					$(".listWrap li").click(function(event){
						$(this).addClass("active1");
						$(this).siblings().removeClass("active1");
					});
				}
			}
		}
	});
}
function findInfoByMassifId(id,name) {
	$("#massifId").val(id);
	$("#massifName").val(name);
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:"apiAlQeadaAction_findInfoByMassifId.do",
		data:{"massifId":id,"enterpriseId":$("#enterpriseId").val()},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			if (data.code=="000") {
				var Info =data.data.mList;
				var dBeans =data.data.dBeans;
				var cameras =data.data.cameras;
				var allList =data.data.allList;
				console.log(Info);
				var astr='<div class="myTwo" style="overflow:auto;">'
			  		+'<p>该地块已添加<span id="count" style="margin-left: 5px; margin-right: 5px;"></span>台设备</p>'
			  		+'<ul id="allChoose" class="listWrap">';
				var cstr="";
				var dstr='<div class="myTwo" style="overflow:auto;">'
					+'<p>选择绑定设备</p>'
					+'<ul id="chooseDevice" class="listWrapIcon">';
				for (var int = 0; int < Info.length; int++) {
					var deviceInfoList = Info[int].deviceInfoList;
					for (var int2 = 0; int2 < deviceInfoList.length; int2++) {
						astr+='<li>'+deviceInfoList[int2].deviceName+'</li>';
					}
					var cameraInfoList = Info[int].cameraInfoList;
					for (var int2 = 0; int2 < cameraInfoList.length; int2++) {
						astr+='<li>'+cameraInfoList[int2].cameraName+'</li>';
					}
				}
				
				for (var int = 0; int < cameras.length; int++) {
					cstr+='<li>'
		  				+'<img src="iot/img/ic_camera.png">'
		  				+cameras[int].cameraName
		  				+'<i id="'+cameras[int].serialNumber.replace(/"/g, '&quot;')+'" onclick="bindDevice(\''+cameras[int].serialNumber.replace(/"/g, '&quot;').replace(/'/g, '&lsquo;')+'\',\''+cameras[int].cameraName.replace(/"/g, '&quot;')+'\','+2+',this)" class="statu fa fa-circle-thin"></i>'
		  				+'</li>';
				}
				for (var int = 0; int < dBeans.length; int++) {
					dstr+='<li id="'+dBeans[int].id+'">'
		  				+'<img src="iot/img/ic_survey.png">'
		  				+dBeans[int].deviceName
		  				+'<i id="'+dBeans[int].serialNumber.replace(/"/g, '&quot;')+'" onclick="bindDevice(\''+dBeans[int].serialNumber.replace(/"/g, '&quot;').replace(/'/g, '&lsquo;')+'\',\''+dBeans[int].deviceName.replace(/"/g, '&quot;')+'\','+1+',this)" class="statu fa fa-circle-thin"></i>'
		  				+'</li>';
				}
				
				$("#third").html(astr+'</ul></div>'+dstr+cstr+'</ul></div>');
				
				$(".listWrap li").click(function(event){
					$(this).addClass("active1");
					$(this).siblings().removeClass("active1");
				});
				var count=0;
				for (var int = 0; int < allList.length; int++) {
					var deviceInfoList = allList[int].deviceInfoList;
					if (allList[int].massifId==id) {
						$("#"+allList[int].deviceSerialNumber).removeClass('fa-circle-thin');
						$("#"+allList[int].deviceSerialNumber).addClass('fa-check-circle');
						$("#"+allList[int].deviceSerialNumber).addClass('active2');
						count++;
					}else{
						$("#"+allList[int].deviceSerialNumber).removeClass('fa-circle-thin');
						$("#"+allList[int].deviceSerialNumber).addClass('fa-check-circle');
					}
				}
				$("#count").html(count);
			}
		}
	});
}
function addAlQeadaModal() {
	if ($("#enterpriseId").val()!="") {
		$("#baseId").val('');
		$("#alQeadaName").val('');
		$("#addAlQeadaModel").modal("show");
		$("#map-al").load("map-d.jsp");
	}else{
		warnTip("请选择企业");
	}
}
function editAlQeadaModal(data1,data2,data3,data4) {
	if ($("#enterpriseId").val()!="") {
		$("#baseId").val(data1);
		$("#alQeadaName").val(data2);
		$("#addAlQeadaModel").modal("show");
		$("#map-al").load("map-d.jsp?lon="+data3+"&lat="+data4); 
	}else{
		warnTip("请选择企业");
	}
}
function deleteAlQeada(data,name) {
	bootbox.confirm({
	    buttons: {  
	        cancel: {  
	            label: '取消',  
	            className: 'btn btn-default'  
	        },  
			confirm: {  
				label: "确认",  
				className: 'btn btn-primary'  
			} 
	    },  
	    message: "是否确定删除【"+name+"】基地？<br>删除该基地后，该基地下的地块、地块下绑定的设备信息将会一起被删除。",  
	    callback: function(result) { 
	    	if (result) {
	    		$.ajax({
	    			cache: false,
	    			type:  "POST",
	    			async : false,
	    			url:"apiAlQeadaAction_deleteAlQeada.do",
	    			data:{"alQeadaId":data},
	    			dataType:'json',
	    			traditional: true,//数组 这里设置为true
	    			success:function(data){
	    				console.log(data);
	    				$("#massif").html('');
	    				$("#allChoose").html('');//清空已绑定设备
	    				$("#massifBaseId").val('');//清空添加地块的基地id
	    				findAlAndDevice();
	    			}
	    		});
	    	}else{
	        	console.log("选择了取消");
	        }
	    },  
	    title: "删除基地",  
    });
}
function deleteMassif(data,name) {
	bootbox.confirm({
	    buttons: {  
	        cancel: {  
	            label: '取消',  
	            className: 'btn btn-default'  
	        },  
			confirm: {  
				label: "确认",  
				className: 'btn btn-primary'  
			} 
	    },  
	    message: "是否确定删除【"+name+"】地块？<br>删除该地块后，该地块下绑定的设备信息将会一起被删除。",  
	    callback: function(result) { 
	    	if (result) {
	    		$.ajax({
	    			cache: false,
	    			type:  "POST",
	    			async : false,
	    			url:"apiAlQeadaAction_deleteMassif.do",
	    			data:{"massifId":data},
	    			dataType:'json',
	    			traditional: true,//数组 这里设置为true
	    			success:function(data){
	    				console.log(data);
	    				$("#massif").html('');
	    				$("#allChoose").html('');//清空已绑定设备
	    				findMassifByalQeadaId($("#massifBaseId").val());
	    			}
	    		});
	    	}else{
	        	console.log("选择了取消");
	        }
	    },  
	    title: "删除地块",  
    });
}
function addAlQeada() {
	console.log($("#lon").val());
	console.log($("#lat").val());
	var lon = $("#lon").val();
	var lat = $("#lat").val();
	if (lat!="" && lon!="") {
		$.ajax({
			cache: false,
			type:  "POST",
			async : false,
			url:"apiAlQeadaAction_addAlQaeda.do",
			data:{"json":'{"baseName":"'+$("#alQeadaName").val()+'","enterpriseId":"'+$("#enterpriseId").val()
				+'","baseId":"'+$("#baseId").val()+'","lon":"'+lon+'","lat":"'+lat+'","comments":""}'},
			dataType:'json',
			traditional: true,//数组 这里设置为true
			success:function(data){
				console.log(data);
				$("#addAlQeadaModel").modal('hide');
				$('.modal-backdrop.fade.in').click();
				successTip("操作成功");
				$("#massif").html('');
				$("#allChoose").html('');//清空已绑定设备
				$("#massifBaseId").val('');//清空添加地块的基地id
				findAlAndDevice();
			}
		});
	}else{
		warnTip("请选择位置信息");
	}
}
function addMassifModal() {
	if ($("#massifBaseId").val()!="") {
		$("#massifId").val('');
		$("#massifName").val('');
		$("#MassifModel").modal("show");
	}else{
		warnTip("请选择基地");
	}
}
function editMassifModal(data1,data2) {
	if ($("#massifBaseId").val()!="") {
		$("#MassifModel").modal("show");
		$("#massifId").val(data1);
		$("#massifName").val(data2);
	}else{
		warnTip("请选择基地");
	}
}
function addMassif() {
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:"apiAlQeadaAction_addMassIf.do",
		data:{"json":'{"massifName":"'+$("#massifName").val()+'","massifId":"'+$("#massifId").val()+'","baseId":"'+$("#massifBaseId").val()+'","comments":""}'},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			$("#MassifModel").modal("hide");
			$("#massif").html('');
			$("#allChoose").html('');//清空已绑定装备
			findMassifByalQeadaId($("#massifBaseId").val())
		}
	});
}
function bindDevice(id,name,type,obj) {
	var objClass = $(obj).attr('class');
	var status = -1;
	var flog = true;
	if (objClass.indexOf('active2')>=0) {
		status=1//取消绑定
		bindDeviceGO(id,name,type,obj,status);
	}else if(objClass.indexOf('fa-check-circle')>=0){
		status=2//转移绑定
		baseAjax(false,"apiMassifProductAction_findBySer.do",success,{"deviceId":id,"type":type,"enterpriseId":$("#enterpriseId").val()});
		function success(data) {
			var ms = data.data;
			bootbox.confirm({
			    buttons: {  
			        cancel: {  
			            label: '取消',  
			            className: 'btn btn-default'  
			        },  
					confirm: {  
						label: "确认",  
						className: 'btn btn-primary'  
					} 
			    },  
			    message: "是否确定转移【"+name+"】设备？<br>该设备已绑定在【"+ms.baseName+"】基地的【"+ms.massIfName+"】地块下，确定要将该设备转移到其他地块吗？",  
			    callback: function(result) { 
			        if(result) {
			        	bindDeviceGO(id,name,type,obj,status);
			        	console.log("选择了确认");
			        }else{
			        	console.log("选择了取消");
			        }
			    },  
			    title: "转移设备",  
		    });
		}
	}else if(objClass.indexOf('fa-circle-thin')>=0){
		status=0//绑定
		bindDeviceGO(id,name,type,obj,status);
	}
	
}
function bindDeviceGO(id,name,type,obj,status){
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:"apiMassifProductAction_bindDevice.do",
		data:{"json":'{"massifName":"'+$("#massifName").val()+'","massifId":"'+$("#massifId").val()
			+'","baseId":"'+$("#massifBaseId").val()+'","baseName":"'+$("#massifBaseName").val()
			+'","enterpriseId":"'+$("#enterpriseId").val()+'","enterpriseName":"'+$("#enterpriseId").find("option:selected").text()
			+'","deviceId":"'+id+'","deviceName":"'+name+'","deviceType":'+type+',"status":'+status+',"comments":""}'},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			$("#third").html('');
			findInfoByMassifId($("#massifId").val(),$("#massifName").val()) 
		}
	});
}
function showMyself(obj){
	$(obj).find('span').show();
}
function hideMyself(obj){
	$(obj).find('span').hide();
}
function confirmBootstrap(str1,str2,flog){
	bootbox.confirm({
	    buttons: {  
	        cancel: {  
	            label: '取消',  
	            className: 'btn btn-default'  
	        },  
			confirm: {  
				label: "确认",  
				className: 'btn btn-primary'  
			} 
	    },  
	    message: str2,  
	    callback: function(result) { 
	    	flog= result;
	        if(result) {
	        	console.log("选择了确认");
	        }else{
	        	console.log("选择了取消");
	        }
	    },  
//	    title: "bootbox confirm也可以添加标题哦",  
    });
	return flog;
}
