var enterpriseId=$("#enterpriseId1").val();
$(function(){
	$("#warningPhone").val($("#phone").val());
	InitByEnterpriseId();
});

//数据校验
function checkText(){
	//校验手机号
	var flag=true;
	 var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var phone=$("#warningPhone").val();
	var email=$("#warningEmail").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var a='2000-01-02 '+startTime;
	var b='2000-01-02 '+endTime;
	var d1 = new Date(a.replace(/\-/g, "\/"));  
	 var d2 = new Date(b.replace(/\-/g, "\/"));
	 if(startTime!=""&&endTime!=""&&d1 >=d2)  
	 {  
			flag=false;
			warnTip("开始时间不能大于结束时间");
			return;
	 }
	 if(phone.length<11){
		flag=false;
		warnTip("手机号码长度小于11");
		return;
	}
	//检验邮箱格式
	 if(!reg.test(email)){
		      	flag=false;
		      	warnTip('提示\n\n请输入有效的E_mail！');      	
	}
	return flag;
}

//状态变更
function relation_Onclick(obj){
    if ( obj.checked == true){
        $("#warningStatus").val("1");
        $(obj).next().next().text("当前属于打开状态");
    }else{
    	 $("#warningStatus").val("0");
        $(obj).next().next().html("当前属于关闭状态");
    }
}

//预警通知信息提交
function warningSubmit(){
	if(checkText()){
		DateDetail();
		baseAjax1("apiWarningAction_WarningDetail.do?enterpriseId="+enterpriseId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val(),warn,$("#warning").serialize());
		function warn(data){
			console.log(data);
			if(data.code=="0"){
				warnTip(data.msg);
			}else if(data.code=="1"){
				successTip(data.msg);
			}
	  } 
   }
}

function InitByEnterpriseId(){
	baseAjax1("apiWarningAction_FindByEnterpriseId.do?enterpriseId="+enterpriseId,success,null);
	function success(data){
		console.log(data);
		if(data.code=="000"){
			if(data.data!=null){
				var datas=data.data;
				if(datas.warnInfo!=null){
					console.log(datas.warnInfo);
					var warnInfo=datas.warnInfo;
					$("#warningPhone").val(warnInfo.warningPhone);
					$("#warningEmail").val(warnInfo.warningEmail);
					$("#warningStatus").val(warnInfo.warningStatus);
					if(warnInfo.warningStatus=="0"){
						$("#c2").attr("checked",false);
						   $("#c2").next().next().text("当前属于关闭状态");
				      }else{
				    	  $("#c2").attr("checked",true);
						  $("#c2").next().next().text("当前属于打开状态");
				      }
					}
				}
			}
		}
	}

function changeStatus(obj){
	if ( obj.checked == true){
		$("#startTime").val("00:00:00");
		$("#endTime").val("23:59:59");
		$("#startTime").attr("disabled","disabled");
		$("#endTime").attr("disabled","disabled");
    }else{
    	$("#startTime").val("");
		$("#endTime").val("");
    	$("#startTime").removeAttr("disabled");
		$("#endTime").removeAttr("disabled");
    }
}

function DateDetail(){
	if($("#All").is(':checked')){
		$("#startTime").val("00:00:00");
		$("#endTime").val("23:59:59");
	}else{
		alert(2);
	}
}