$(function(){
	//加载控制器和探头切换按钮
	loadClass();
	
	//初始化时间和页面
	 $("#mmy").html('');
	 getLineAndPie();
	 
	 

	
});


function getLineAndPie(){
	if(checkData()){
		 //显示探头页面数据
		 showDataLine();
		 showDataPie();
		 //显示控制器页面数据
		 showCoData();
	}else{
		warnTip("查询时间不能超过一年");
	}
}



function showDataLine(){
	 $("#mmy").html('');
	var deviceId=$("#deviceId").val();  
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var cnm=$("#chaxunshijian").val();//时间段
	baseAjax(false,"probeSpecificDataAction_getSpecificDataCount.do?deviceId="+deviceId+"&startTime="+startTime+"&endTime="+endTime+"&cnm="+cnm ,success,null);
	function success(data) {
		console.log(11);
		console.log(data);
		if(data.code =="000"){
			for(var i = 0;i < data.data.length;i++){
				showDatasss(1,data.data[i]);
			}
		}
    }
}
function showDataPie(){
	$("#mym").html('');
	var deviceId=$("#deviceId").val();  
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var cnm=$("#chaxunshijian").val();//时间段
	baseAjax(false,"probeSpecificDataAction_getSpecificDataCountForPie.do?deviceId="+deviceId+"&startTime="+startTime+"&endTime="+endTime+"&cnm="+cnm,success,null);
	function success(data) {
		if(data.code =="000"){
			for(var i = 0;i < data.data.length;i++){
				showPieDatasss(1,data.data[i]);
			}
		}
    }
}


function showCoData(){
	$("#ymm").html('');
	var deviceId=$("#deviceId").val();  
	baseAjax(false,"probeSpecificDataAction_getPowerController.do?deviceId="+deviceId+"&startTime="+startTime+"&endTime="+endTime,success,null);
	function success(data) {
		if(data.code =="000"){
			for(var i = 0;i < data.data.length;i++){
				showPowerControllerDatasss(1,data.data[i]);
			}
		}
   }
}

function switch1(obj){

	alert("test msg");
	$("#addTimerController_btn").toggle();

}

// 显示控制器信息  
function showPowerControllerDatasss(id,dataBean){

	var demofather= document.createElement("div");   
	demofather.className="coller";
	demofather.style="float:left;border:0px solid green;";
	
	var container=document.createElement("div");
	container.style="border:1px solid blue;width:90% ;height:230px;margin-left:50px;margin-bottom:30px";
	
	var right_content=document.createElement("div");
	right_content.style="float:left;border:1px solid black;height:230px;width:1385px";
	
	var right_up_content=document.createElement("div");
	right_up_content.style="border:1px solid red;height:50px;width:1385px";
	
	var right_bottom_content=document.createElement("div");
	right_bottom_content.style="border:1px solid red;height:180px;width:1385px";
	
	//定时控制
	var timerController_span=document.createElement("span");
	timerController_span.style="color:gray;font-size:20px;padding-left:15px";
	timerController_span.innerHTML="时间控制";
	timerController_span.id=dataBean.id;
	timerController_span.setAttribute("onclick", "switch1(this)");
	//timerController_span.onclick();
	
	//参数控制
	var parameterController_span=document.createElement("span");
	parameterController_span.style="color:gray;font-size:20px;padding-left:15px";
	parameterController_span.innerHTML="参数控制";
	
	//Tab标签Div
	var tabDiv=document.createElement("div");
	tabDiv.className="muea";
	tabDiv.style="border:1px solid red;width:200px;height:50px;float:left";	
	tabDiv.appendChild(timerController_span);
	tabDiv.appendChild(parameterController_span);
	//添加定时控制按钮
	var addTimerController_btn=document.createElement("input");
	addTimerController_btn.style="float:right;width:150px; height:40px;background-color: gray:white ;font-size:16px";
	addTimerController_btn.type="button";
	addTimerController_btn.value="+添加定时控制";
	addTimerController_btn.id="addTimerController_btn";
	addTimerController_btn.setAttribute("onclick", "timingModal()");
	
	//添加参数控制按钮
	var addParameterController_btn=document.createElement("input");
	addParameterController_btn.style="float:right;width:150px; height:40px;background-color: gray:white ;font-size:16px";
	addParameterController_btn.type="button";
	addParameterController_btn.value="+添加参数控制";
	addParameterController_btn.setAttribute("onclick", "parameterModal()");
	
	//table
	var showdata_table=document.createElement("table");

	
	
	var A= document.createElement("div");  
		A.style="font-family:'Arial Negreta', 'Arial';font-weight:700;text-align:center; margin-top:40px;height: 40px;border:1px solid green";
		A.innerHTML=dataBean.controllerName;
	var B= document.createElement("img");  
		B.src="iot/img/controller/"+dataBean.icoName;//控制器图片
	var C= document.createElement("div");  
		C.style="font-family:'Arial Normal', 'Arial';font-weight:400; text-align:center; margin-top:15px;";
	var switchDiv= document.createElement("div");   
		switchDiv.className="relation_check";
		
		var switchi= document.createElement("input");   
		switchi.className="tgl tgl-light";
		switchi.id=dataBean.id;
		switchi.name="tg_checked"+dataBean.id;
		switchi.setAttribute("onclick","relation_Onclick(this)");
		switchi.type="checkbox";
		
		var switchMsg= document.createElement("div");   
		switchMsg.className="relation_check_div";
		if(dataBean.powerSwitch=="0"){//0关
			switchMsg.innerHTML="当前状态:关";
		}
		else if(dataBean.powerSwitch=="3"){ //3开
			switchMsg.innerHTML="当前状态:开";
			switchi.setAttribute("checked","checked");
		}
		
		
		var switchl= document.createElement("label");   
		switchl.className="tgl-btn";
		switchl.id=dataBean.id;
		switchl.setAttribute("for",dataBean.id);
		switchDiv.appendChild(switchMsg);
		switchDiv.appendChild(switchi);
		switchDiv.appendChild(switchl);


	demofather.appendChild(A);
	demofather.appendChild(B);
	demofather.appendChild(C);
	demofather.appendChild(switchDiv);
	
//	right_up_content.appendChild(timerController_span);
//	right_up_content.appendChild(parameterController_span);
	right_up_content.appendChild(tabDiv);
	right_up_content.appendChild(addParameterController_btn);
	right_up_content.appendChild(addTimerController_btn);
	
	
	right_bottom_content.appendChild(showdata_table);
	
	right_content.appendChild(right_up_content);
	right_content.appendChild(right_bottom_content);
	
	
	container.appendChild(demofather);
	container.appendChild(right_content);

	
	$("#ymm").append(container);  //显示到控制器div

}



/*     原版
 * function showPowerControllerDatasss(id,dataBean){ 

	var demofather= document.createElement("div");   
	demofather.className="coller";
	demofather.style="border:1px solid green;";
	
	var A= document.createElement("div");  
		A.style="font-family:'Arial Negreta', 'Arial';font-weight:700;text-align:center; margin-top:40px;height: 40px;border:1px solid green";
		A.innerHTML=dataBean.controllerName;
	var B= document.createElement("img");  
		B.src="iot/img/controller/"+dataBean.icoName;//控制器图片
	var C= document.createElement("div");  
		C.style="font-family:'Arial Normal', 'Arial';font-weight:400; text-align:center; margin-top:15px;";
	var switchDiv= document.createElement("div");   
		switchDiv.className="relation_check";
		
		var switchi= document.createElement("input");   
		switchi.className="tgl tgl-light";
		switchi.id=dataBean.id;
		switchi.name="tg_checked"+dataBean.id;
		switchi.setAttribute("onclick","relation_Onclick(this)");
		switchi.type="checkbox";
		
		var switchMsg= document.createElement("div");   
		switchMsg.className="relation_check_div";
		if(dataBean.powerSwitch=="0"){//0关
			switchMsg.innerHTML="当前状态:关";
		}
		else if(dataBean.powerSwitch=="3"){ //3开
			switchMsg.innerHTML="当前状态:开";
			switchi.setAttribute("checked","checked");
		}
		
		
		var switchl= document.createElement("label");   
		switchl.className="tgl-btn";
		switchl.id=dataBean.id;
		switchl.setAttribute("for",dataBean.id);
		switchDiv.appendChild(switchMsg);
		switchDiv.appendChild(switchi);
		switchDiv.appendChild(switchl);


	demofather.appendChild(A);
	demofather.appendChild(B);
	demofather.appendChild(C);
	demofather.appendChild(switchDiv);
	$("#ymm").append(demofather);  //显示到控制器div

}*/

//加载定时控制的模态框，对应timingModal.jsp
function timingModal(){
	$("#timingModal").modal("show");
}

//加载参数控制的模态框,对应parameterModal.jsp
function parameterModal(){
	var deviceId=$("#deviceId").val();
	//通过设备序列号查询设备信息及其包含的探测器信息
	baseAjax1("apiDeviceInfoAction_findBySerialNumber.do?deviceId="+deviceId ,success,null);
	function success(data){
		var data=data.data;
		//设置采集器名称
		$("#deviceNames").val(data.deviceName);
		$("#deviceNames").attr("disabled","disabled");
		//设置采集器ID，即主键，因为数据结构未确定，确定后添加对应属性即可。
		$("#deviceIds").val(data.id);
		var probes=data.probeList;
		str="";
		for(var i=0;i<probes.length;i++){
			str+='<option value="'+probes[i].id+'" >'+probes[i].surveyName+'</option>';
		}
		$("#surveyID").html(str);
	}
	$("#parameterModal").modal("show");
}

//智能控制参数控制执行函数，后台处理接口未写
function addParameters(){
	var deviceNames=$("#deviceNames").val();
	baseAjax1("apiDeviceInfoAction_fund.do?deviceNames="+deviceNames,success,$("#parameters").serialize());
	function success(data){
		console.log(1);
	}
}

function showPieDatasss(id,dataBean){
	var h = 0;
	var s = 0;
	var l = 0;
	if(dataBean.commonBean !=null){  //没得数据也要在页面上显示图形（探头数据）
		h = dataBean.commonBean.t9;
		s = dataBean.commonBean.t10;
		l = dataBean.commonBean.t8;
	}
	var demofather= document.createElement("div");   
	demofather.style="width:430px ;height: 250px; margin:20px; float:left;"//border: solid 2px yellow;
		
	var leftDiv= document.createElement("div");  ////page
	leftDiv.style="width:150px ;height: 150px; margin: 38px 0px 0px 0px; float:left;"
		var nameDiv= document.createElement("div");  ////page
		nameDiv.innerHTML=dataBean.surveyName;
		nameDiv.style="text-align: center; margin: 20px 0px 0px 0px;"
		leftDiv.appendChild(nameDiv);
		
		var unitDiv= document.createElement("div");  ////page
		unitDiv.innerHTML=dataBean.unit;
		unitDiv.style="text-align: center; margin: 5px 0px 0px 0px;"
		leftDiv.appendChild(unitDiv);
		
		
	var rightDiv= document.createElement("div");  ////page
	rightDiv.style="width:250px ;height: 220px; margin-left: 20px; float:left;"
		
	var myChart = echarts.init(rightDiv);
	var app = {};
	option = null;
	option = {
		    title : {
//		        text: '某站点用户访问来源',
//		        subtext: '纯属虚构',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
//		    legend: {
//		        orient: 'vertical',
//		        left: 'left',
//		        data: ['高于','合适','低于']
//		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '65%',
		            center: ['50%', '50%'],
		            data:[
		                  {value:l, name:'低于'},
		                  {value:h, name:'高于'},
		                  {value:s, name:'合适'}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10000,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};

	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
	
	demofather.appendChild(leftDiv);
	
	demofather.appendChild(rightDiv);

	$("#mym").append(demofather);


}



function showDatasss(id,dataBean){
	
	var demofather= document.createElement("div");   
	demofather.style="width:530px ;height: 250px; margin:10px; float:left;"

	var leftDiv= document.createElement("div");  
		leftDiv.style="width:120px ;height: 120px; margin:60px 0px 0px 0px; float:left;"
		var A= document.createElement("div");  
			A.style="font-family:'Arial Negreta', 'Arial';font-weight:700;text-align: center;height: 20px; ";
			A.innerHTML=dataBean.t1;
		var B= document.createElement("div");  
			B.style="font-family:'Arial Normal', 'Arial';font-weight:400; text-align: center;height: 30px;";
			B.innerHTML=dataBean.t7;
		var C= document.createElement("div");  
			C.style="font-family:'Arial Normal', 'Arial';font-weight:400; text-align: center;height: 20px; ";
			var newData =0.0;
			if(dataBean.t4[0]==null)
				newData=0.0;
			else
				newData=dataBean.t4[dataBean.t4.length-1];
			C.innerHTML="最新值:"+newData;
		var D= document.createElement("div");   
			D.style="font-family:'Arial Normal', 'Arial';font-weight:400;text-align: center;height: 20px; ";
			var avgData =0.0;
			if(dataBean.t2==null)
				avgData=0.0;
			else
				avgData=dataBean.t2;
			D.innerHTML="平局值:"+avgData;
	leftDiv.appendChild(A);
	leftDiv.appendChild(B);
	leftDiv.appendChild(C);
	leftDiv.appendChild(D);
			
		
	var rightDiv= document.createElement("div");  ////page
	rightDiv.style="width:400px ;height: 200px; margin-left: 0px; float:left;"

	var myChart = echarts.init(rightDiv);
	var app = {};
	option = null;
	option = {
	    title: {
	        text: ''
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['最大范围',dataBean.t1,'最小范围']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: dataBean.t3
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [
	        {
	            name:'最小范围',
	            type:'line',
	            data:dataBean.t9
	        },{
	            name:dataBean.t1,
	            type:'line',
	            data:dataBean.t4
	        }
	        ,{
	            name:'最大范围',
	            type:'line',
	            data:dataBean.t10
	        }
	    ]
	};
	;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
	
	demofather.appendChild(leftDiv);
	demofather.appendChild(rightDiv);

	$("#mmy").append(demofather);


}


//显示控制器
function showPowerController(){
	document.getElementById('caijikuang').style.display='none';
    document.getElementById('ymm').style.display='';

}
//显示探头数据
function showProbe(){
	document.getElementById('caijikuang').style.display='';
	document.getElementById('ymm').style.display='none';
}

function loadClass(){
	$(".muea span").click(function(event){
		$(this).addClass("active1");
		$(this).siblings().removeClass("active1");
	});
	$("#showProbe").click();  
}

////////////////////////////////////////////////////////////////////
//采集器数据线形图和饼状图切换                                                                                                                                       //
function showLine(){                                              //
	document.getElementById('mmy').style.display='';              //                                  
	document.getElementById('mym').style.display='none';          //                                     
}                                                                 //            
//采集器数据线形图和饼状图切换                                                                                                                                       //
function showPie(){                                               //
	document.getElementById('mmy').style.display='none';          //
	document.getElementById('mym').style.display='';              //
}                                                                 //
////////////////////////////////////////////////////////////////////

function relation_Onclick(obj){
    if (obj.checked == true){
    	if(window.confirm("控制器当前属于关闭状态，现在确定将其打开么? ") == 1){ 
	    	baseAjax(false,"probeSpecificDataAction_setControllerTurnOnOff.do?controllerId="+obj.id+"&powterSwitch=3",success,null);
	    	function success(data) {
	    		if(data.code =="000"){
	    			$(obj).prev().text("当前状态:开Testing");
	    		}
	        }
    	} else{
    		obj.checked = false;
    	}
    }else{
    	if(window.confirm("控制器当前属于打开状态，现在确定将关闭其么? ") == 1){ 
	    	baseAjax(false,"probeSpecificDataAction_setControllerTurnOnOff.do?controllerId="+obj.id+"&powterSwitch=0",success,null);
	    	function success(data) {
	    		if(data.code =="000"){
	    			 $(obj).prev().html("当前状态:关");
	    		}
	        }
    	}else{
    		obj.checked = true;
    	}
    }
}

//返回到设备界面
function back(){
	$("#right_content").load("iot/envmonitoring/jsp/env_monitoring.jsp");
}

function setDatetime(){
	var timeType = $("#chaxunshijian").val();//时间段
	$("#startTime").attr("disabled","disabled");
	$("#endTime").attr("disabled","disabled");
	if(timeType==0){
		$('#startTime').val(GetDateStr(0));
	}else if(timeType==1){
		$('#startTime').val(GetDateStr(1));
	}else if(timeType==2){
		$('#startTime').val(GetDateStr(2));
	}else if(timeType==3){
		$('#startTime').val(GetDateStr(3));
	}else{
		$("#startTime").attr("disabled",null);
		$("#endTime").attr("disabled",null);
	}
}
function GetDateStr(type) { 
	var dd = new Date(); 
	if(type==0){
	     var y = dd.getFullYear(); 
	     var m = dd.getMonth()+1;//获取当前月份的日期 
	     var d = dd.getDate();
	}else if(type==1){
		dd.setDate(dd.getDate()-7);//一周减七天
	     var y = dd.getFullYear(); 
	     var m = dd.getMonth()+1;//获取当前月份的日期 
	     var d = dd.getDate();
	}else if(type==2){
		 var y = dd.getFullYear(); 
	     var m = dd.getMonth();//一个月前
	     if(m==0){
	    	 y=y-1;
	    	 m=m+12;
	     }
	     var d = dd.getDate();
	}else if(type==3){
		 var y = dd.getFullYear(); 
	     var m = dd.getMonth();//半年前
	     m=m-5;
	     if(m<=0){
	    	 y=y-1;
	    	 m=m+12;
	     }
	     var d = dd.getDate();
	}
	     return y+"-"+m+"-"+d; 
 }
function retime(){
	document.getElementById("chaxunshijian").value="-1";
}
function checkData(){
	var flag=true;
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var start=new Date(startTime);
	var end=new Date(endTime);
	start = start.valueOf();
	end = end.valueOf();
     var c = end - start;
     c = new Date(c);
     if((c.getFullYear() - 1970)>0){
     	flag=false;
     }
     return flag;
}

