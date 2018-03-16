$(function(){
	var enterpriseId=$("#enterpriseId1").val();
	baseAjax(false,$("#basePath").val()+"apiMassifProductAction_CountDevice.do?mass.enterpriseId="+enterpriseId,success,null);
	function success(data){
		if(data.code=="000"){
			var data=data.data;
			var baseNames=data.baseNames;
			var surveyCounts=data.surveyCounts;
			var cameraCounts=data.cameraCounts;
			var lngs=data.lngs;
			var lats=data.lats;
			if(lngs.length==0){
				baseShow();//溯源基地查询不到坐标
			}else{
				showMap(baseNames,surveyCounts,cameraCounts,lngs,lats);
			}
		}else if(data.code=="001")
			baseShow();//不存在任何设备显示默认地图
	}
});

function showMap(baseNames,surveyCounts,cameraCounts,lngs,lats){
	 // 百度地图API功能
	var searchInfoWindow=[];
    var marker=[];
    var maxLng = lngs[0];  
    var minLng = lngs[0];  
    var maxLat = lats[0];  
    var minLat = lats[0];
    map = new BMap.Map("allmap");
    //设置经纬度最大范围和最小范围
    for(var j=0;j<lngs.length;j++){
    	if(lngs[j]> maxLng) maxLng =parseFloat(lngs[j]); 
    	if(lngs[j]< minLng) minLng =parseFloat(lngs[j]); 
    	if(lats[j]> maxLat) maxLat =parseFloat(lats[j]); 
    	if(lats[j]< minLat) minLat =parseFloat(lats[j]); 
    }
    var cenLng =(parseFloat(maxLng)+parseFloat(minLng))/2;  
    var cenLat = (parseFloat(maxLat)+parseFloat(minLat))/2;  
    var zoom = getZoom(maxLng, minLng, maxLat, minLat);  
     map.centerAndZoom(new BMap.Point(cenLng,cenLat), zoom);  
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();
    for(var i=0;i<baseNames.length;i++){
    	var opts = {
    	        width : 250,     // 信息窗口宽度
    	        height: 100,     // 信息窗口高度
    	        title :"<p style='font-size:20px;color:#FF0000;background-color:#FFFFFF'>"+baseNames[i]+"</p>", // 信息窗口标题
    	        enableMessage:true//设置允许信息窗发送短息
    	    };
    	var marker = new BMap.Marker(new BMap.Point(lngs[i],lats[i]));  // 创建标注
    	var content ='该基地已安装:'+'<br>'+surveyCounts[i]+'个采集器'+'<br>'+cameraCounts[i]+'个摄像头';//要显示的文本信息
    	 map.addOverlay(marker);               // 将标注添加到地图中
    	 addClickHandler(content,marker,opts);
    }
}

//标注触发事件
function addClickHandler(content,marker,opts){
	marker.addEventListener("click",function(e){
		openInfo(content,e,opts)}
	);
}

//根据经纬度设置信息窗口
function openInfo(content,e,opts){
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
	map.openInfoWindow(infoWindow,point); //开启信息窗口
}

//设置空白地图
function baseShow(){
	map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 7);
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();
}

//根据经纬极值计算缩放级别。  
function getZoom (maxLng, minLng, maxLat, minLat) {  
    var zoom = ["20","50","100","200","500","1000","2000","5000","10000","20000","25000","50000","100000","200000","500000","1000000","2000000","5000000","10000000"]//级别21到3。  
    var pointA = new BMap.Point(maxLng,maxLat);  // 创建点坐标A  
    var pointB = new BMap.Point(minLng,minLat);  // 创建点坐标B  
    var distance = map.getDistance(pointA,pointB).toFixed(1);  //获取两点距离,保留小数点后两位  
    for (var i = 0,zoomLen = zoom.length; i < zoomLen; i++) {  
        if(zoom[i] - distance > 0){  
            return 18-i+3;//地图缩放等级从21到3，所以最低为3级，数组以0开始所以从18开始减  
        }  
    };  
}  