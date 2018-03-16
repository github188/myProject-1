$(function(){
	loadAllBase();
	showDatasss();
});

function showDatasss(){
	$("#dataSpace").html('');
	var baseId=$("#baseId").val();
	var massifId=$("#massifId").val();
	var enterpriseId=$("#enterpriseId1").val();
	var deviceName=$("#deviceName").val();
	baseAjax(false,"monitorAction_findProbeInfo.do?enterpriseId="+enterpriseId+"&baseId="+baseId+"&massifId="+massifId+"&deviceName="+deviceName,success,null);
	function success(data) {
		if(data.code =="000"){
			for(var i = 0;i < data.data.length;i++){
				var temp = data.data[i];
				var deviceList = temp.deviceInfoList;
				var cbaseName = temp.baseName;
				var cmassIfName = temp.massIfName;
				for(var j=0;j<deviceList.length;j++){
					goToOne1(deviceList[j].serialNumber,deviceList[j].deviceName,cbaseName,cmassIfName,deviceList);
				}
			}
		}
    }

	//重新加载效果
    var mySwiper = new Swiper('.swiper-container',{
        loop: true,
        autoplay: 5000,
        autoplayDisableOnInteraction : false,    //滑动之后，继续轮播
        pagination : '.swiper-pagination',
        paginationClickable: true,
//        prevButton:'.swiper-button-prev',
//        nextButton:'.swiper-button-next',
    });

}

/**
 * 加载全部基地下拉框
 */
function loadAllBase(){
	var baseId="";
	var massifId="";
	var enterpriseId=$("#enterpriseId1").val();
	debugger;
	//  private String enterpriseId;private String baseId;  enterpriseId&baseId&massifId
	baseAjax(false,"monitorAction_findBase.do?enterpriseId="+enterpriseId+"&baseId="+baseId+"&massifId="+massifId,success,null);
	function success(data){
		var str = "<option value='-1'>全部基地</option>";
		var str1 = "<option value='-1'>全部地块</option>";
		$("#massifId").html(str1);
		$("#baseId").html(str);
		if(data.code=="000"){
			if(data.data.length>0){
				for(var i=0;i<data.data.length;i++){
					str+="<option value='"+data.data[i]['baseId']+"'>"+data.data[i]['baseName']+"</option>";
					$("#baseId").html(str);
				}
			}
			successTip("加载完成");
		}else{
			warnTip("加载失败");
		}
	}
}

/**
 * 加载地块下拉框
 */
function loadMassifAddress(){
	var baseId=$("#baseId").val();
	var massifId="";
	var enterpriseId=$("#enterpriseId1").val();
	//  private String enterpriseId;private String baseId;
	baseAjax(false,"monitorAction_findMassif.do?enterpriseId="+enterpriseId+"&baseId="+baseId+"&massifId="+massifId,success,null);
	function success(data){
		$("#massifId").html("");
		var str = "<option value='-1'>全部地块</option>";
		$("#massifId").html(str);
		if(data.code=="000"){
			if(data.data.length>0){
				for(var i=0;i<data.data.length;i++){
					str+="<option value='"+data.data[i]['massifId']+"'>"+data.data[i]['massIfName']+"</option>";
					$("#massifId").html(str);
				}       
			}
			successTip("加载完成");
		}else{
			warnTip("加载失败");
		}
	}
}


/**
 * 生成界面全部控件
 * @param cdeviceId
 * @param cdeviceName
 * @param cbaseName
 * @param cmassIfName
 * @param ctantou
 */
function goToOne1(cdeviceId,cdeviceName,cbaseName,cmassIfName,ctantou){
	
	var dafangkuang = document.createElement("div");
	dafangkuang.className ="show_env";
	//------------------------头数据区域开始--------------------------------------------------------------------
	var onLine = document.createElement("div");
		onLine.className = "env_not_online";
		var onLineimg = document.createElement("img");
//		onLineimg.setAttribute("src","../img/not_online.png");
		var isonLine = ctantou[0].id;
		if(isonLine=="online"){
			
		}else{
			onLineimg.src="iot/img/not_online.png";
		}
//		onLineimg.setAttribute("alt","");
		onLine.append(onLineimg);
		
		
	var fangkuangHead = document.createElement("div");
		fangkuangHead.className="show_env_tit";
		var fangkuangHeadDeviceAddress = document.createElement("p");
		fangkuangHeadDeviceAddress.className="env_tit_name";
		
			var enva = document.createElement("a");
				enva.setAttribute("href","#");
				enva.setAttribute("onclick","goto1('iot/envmonitoring/jsp/env_monitorData.jsp?deviceId="+cdeviceId+"')");
				enva.innerHTML = cdeviceName; 
				 
				
			var hidePrompt = document.createElement("span");
			hidePrompt.className="hide_prompt env_show_promp";
					var hidei = document.createElement("i");
					hidei.className="fa fa-exclamation-triangle";
					hidei.setAttribute("aria-hidden","true");
					hidei.style="color:#FF0000 "
					var hides = document.createElement("span");
					hides.className="env_tit_prompt";
			hidePrompt.appendChild(hidei);
			hidePrompt.appendChild(hides);
			
			 var deviceAddr= document.createElement("p");
				 deviceAddr.className="progress-bar-inner";
				
				 var dingweiimg = document.createElement("img");
				 dingweiimg.src="iot/envmonitoring/img/dingweiImg.png";
				 dingweiimg.style="margin-bottom :3px";
				 deviceAddr.appendChild(dingweiimg);
				 var deviceApan = document.createElement("span");
				
				 if(cmassIfName !=""){
					 deviceApan.innerHTML=cbaseName+"_"+cmassIfName;
				 }else{
					 deviceApan.innerHTML="无"; 
				 }
				 deviceApan.style="margin-left:13px";
				 deviceAddr.appendChild(deviceApan);
				 var br = document.createElement("br");
				 deviceAddr.appendChild(br);
				 var updataimg = document.createElement("img");
				 updataimg.src="iot/envmonitoring/img/updataImg.png";
				 deviceAddr.appendChild(updataimg);
				var envptime = document.createElement("span");
				if(ctantou[0].probeList[0] !=null){
					envptime.innerHTML=ctantou[0].probeList[0].createTime;
				}else{
					envptime.innerHTML="";	
				}
				envptime.style="margin-left:10px";
				deviceAddr.appendChild(envptime);
			fangkuangHeadDeviceAddress.appendChild(enva);	
			fangkuangHeadDeviceAddress.appendChild(hidePrompt);
			fangkuangHead.appendChild(fangkuangHeadDeviceAddress);
			fangkuangHead.appendChild(deviceAddr);
			
	//------------------------探头数据区域开始--------------------------------------------------------------------		
			 var swiperContainerDiv= document.createElement("div");
			 swiperContainerDiv.className="swiper-container swiper-container-horizontal";
				
				 var swiperWrapper= document.createElement("div");
				 swiperWrapper.className="swiper-wrapper";
//				 swiperWrapper.setAttribute("transform"," translate3d(-4800px, 0px, 0px);");
//				 swiperWrapper.setAttribute("transition-duration","0ms");
//				 swiperWrapper.style="transform: translate3d(-4800px, 0px, 0px);";
//				 swiperWrapper.style="width: 400px;height: 100%;";
				
				 swiperContainerDiv.appendChild(swiperWrapper);
				 
					/////////////////page no
				 var swiperBulletsDiv= document.createElement("div");
				 	swiperBulletsDiv.className="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets";
					
//				 	var swiperBulletDiv= document.createElement("span");  //至少一个插入一个翻页球
//					swiperBulletDiv.className="swiper-pagination-bullet swiper-pagination-bullet-active";
//					swiperBulletsDiv.appendChild(swiperBulletDiv);
//					
//					var swiperBulletDiv2= document.createElement("span");  //至少一个插入一个翻页球
//						swiperBulletDiv2.className="swiper-pagination-bullet";
//						swiperBulletsDiv.appendChild(swiperBulletDiv2);
				 	
					swiperContainerDiv.appendChild(swiperBulletsDiv);
				 	/////////////////page
					
				    //-----------------加载采集器------重要--------------------------
					
					var proList =  ctantou[0].probeList //得到采集器下的探头数据createTime
					var myArray=new Array();
					var arrIn = 0;
					for(var a=0;a<proList.length;a++){
						var dataInfo1 = getRowData("",proList[a]);
						myArray[arrIn] = dataInfo1;
						arrIn = arrIn+1;
						if(arrIn ==7){ //每页最多放7条数据 生成新的一页
							var pageDatas1 = getDataPage(myArray,0,7);
							swiperWrapper.appendChild(pageDatas1);
							myArray=new Array();
							arrIn = 0;
						}
					}

					//-----------------加载控制器-------重要-------------------------
					var conList =  ctantou[0].powerControllerList //得到采集器下的探头数据createTime
					for(var b=0;b<conList.length;b++){
						var dataInfo1 = getRowDataForCon("",conList[b]);
						myArray[arrIn] = dataInfo1;
						arrIn = arrIn+1;
						if(arrIn ==7){ //每页最多放7条数据 生成新的一页
							var pageDatas1 = getDataPage(myArray,0,7);
							swiperWrapper.appendChild(pageDatas1);
							myArray=new Array();
							arrIn = 0;
						}
					}
					//剩下的不足7条（一页）单独插入
					if(myArray.length>0){
						pageDatas1 = getDataPage(myArray,0,arrIn);
						swiperWrapper.appendChild(pageDatas1);
					}
					
		dafangkuang.appendChild(onLine);     /////111
		dafangkuang.appendChild(fangkuangHead);//方框的头放进大方框	222
		dafangkuang.appendChild(swiperContainerDiv);//每页数据放进大方框	333
	  
	  $("#dataSpace").append(dafangkuang);
}

/**
 * 设备控件分页生成
 * @param dataInfo
 * @param startIn
 * @param endIn
 * @returns {___anonymous8521_8530}
 */
function getDataPage(dataInfo,startIn,endIn){
	 var demofather= document.createElement("div");  ////page
	 demofather.className="swiper-slide";
//	 demofather.setAttribute("data-swiper-slide-index","2");
	
	 
	 	var demodiv = document.createElement("div");
		demodiv.className="demo";
		
			var containerdiv = document.createElement("div");
			containerdiv.className="container";
			
				var rowdiv = document.createElement("div");
				rowdiv.className="row";
				
					var colmmddiv = document.createElement("div");
					colmmddiv.className="col-md-offset-3 col-md-6";
					colmmddiv.style="width:380px;margin-left: 0";
					
					for(var n=startIn;n<endIn;n++){
						colmmddiv.appendChild(dataInfo[n]);//数据放入分页
					}
					rowdiv.appendChild(colmmddiv);
				
					containerdiv.appendChild(rowdiv);
					demodiv.appendChild(containerdiv);
					demofather.appendChild(demodiv);

					
	return demofather;
}


/**
 * 得到控制器控件
 * @param url 没使用
 * @param ctantou 参数
 * @returns {___anonymous9450_9457}
 */
function getRowDataForCon(url,ctantou){
	
	var dataInfo = document.createElement("div");
	 	dataInfo.className="progress_bar";
	 	
		 var conName = document.createElement("div");
			 conName.style="width: 80px;float:left;text-align:center";
			 conName.innerHTML=ctantou.controllerName;
		
			 var conData = document.createElement("div");
			 conData.style="width: 270px;float:left; text-align:left ";
			 if(ctantou.powerSwitch=='0'){
				 conData.innerHTML="当前状态:关闭";
			 }else{
				 conData.innerHTML="当前状态:开启";
			 }
			 
	dataInfo.appendChild(conName);
	dataInfo.appendChild(conData);
    
    return dataInfo;
}

/**
 * 探头控件每行
 * @param url
 * @param ctantou
 * @returns {___anonymous10188_10195}
 */
function getRowData(url,ctantou){
	
	 var dataInfo = document.createElement("div");
	 	 dataInfo.className="progress_bar";
	 var D = 0;
	 if(ctantou.probeSpecificDatalist[0]==null){
		 D = 0;
	  }else{
		 D  = ctantou.probeSpecificDatalist[0].probeData
	  }
	 var U =ctantou.unit;
	 var rowSpan = document.createElement("div");
	 
		 var probarspSName = document.createElement("div");
		 probarspSName.style="width: 80px;float:left;text-align:center";
		 probarspSName.innerHTML=ctantou.surveyName;
		 var rowDiv = document.createElement("div");
		 	rowDiv.style="width: 270px;float:left; text-align:left ";
		 	var probarsplow = document.createElement("span");
			probarsplow.className="low";
			
			var probarspnormal = document.createElement("span");
			probarspnormal.className="normal";
			
			var probarsheight = document.createElement("span");
			probarsheight.className="height";
			
			var DU = document.createElement("span");
			DU.className="textSpan"; 
			DU.innerHTML=D+" "+U;
	
	 if(ctantou.surveyMin <= D && ctantou.surveyMax >= D ){
		 probarspnormal.style="background: #33CC33";
	 } 
	 else if(ctantou.surveyMin > D){
		 probarsplow.style="background: #FFCC33";
	 }
	 else if(ctantou.surveyMax < D){
		 probarsheight.style="background: #FF0000";
	 }
	 
	 
	
	 rowDiv.appendChild(probarsplow);
	 rowDiv.appendChild(probarspnormal);
	 rowDiv.appendChild(probarsheight);
	 rowDiv.appendChild(DU);
	 rowSpan.appendChild(probarspSName);
	 rowSpan.appendChild(rowDiv);
     dataInfo.appendChild(rowSpan);
     
     return dataInfo;
}





/**
 * tem
 * @param url
 * @param ctantou
 */
function goToOne(url,ctantou){
	  var tempForm = document.createElement("form");
	    tempForm.id = "tempForm1";
	    tempForm.method = "post";
	    tempForm.action = url;
	    tempForm.target="_blank"; //打开新页面
	    
	    var hideInput1 = document.createElement("input");
	    hideInput1.type = "hidden";
	    hideInput1.id = "json";
	    hideInput1.name="json"; //后台要接受这个参数来取值
	    hideInput1.value = data; //后台实际取到的值
	    tempForm.appendChild(hideInput1);
	    if(document.all){
	        tempForm.attachEvent("onsubmit",function(){});        //IE
	    }else{
	        tempForm.addEventListener("submit",function(){},false);    //firefox
	    }
	    document.body.appendChild(tempForm);
	    if(document.all){
	        tempForm.fireEvent("onsubmit");
	    }else{
	        tempForm.dispatchEvent(new Event("submit"));
	    }
	    tempForm.submit();
	    document.body.removeChild(tempForm);
}





