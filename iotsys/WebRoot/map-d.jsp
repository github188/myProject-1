<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
        #l-map{height:100%;width:100%;}
        #r-result{width:100%;}
        .tangram-suggestion-main{
        	z-index: 9999;
        }
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SOngjEUe8tUnf10yPVVtp7kkAfqlnSnq"></script>
    
    
    <script type="text/javascript" src="<%=basePath %>source/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>source/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>iot/js/base.js"></script>
    <title>关键字输入提示词条</title>
</head>
<body>
<form action="" id="datas">
	<input type="hidden" id="address" name="address" value="${param.address}">
	<input type="hidden" id="lon" name="lon" value="${param.lon}">
	<input type="hidden" id="lat" name="lat" value="${param.lat}">
	<input type="hidden" id="enterpriseId" name="enterpriseId" value="${param.enterpriseId}">
</form>
<div id="r-result" style="position:absolute;z-index:999;" ><input  type="text" PLACEHOLDER="请输入地址" id="suggestId" size="20"  value="百度" style="width:150px;" class="form-control" /></div>
<div id="l-map"></div>
<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
</body>



<!--基础功能+搜索-->
<script type="text/javascript">
    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }

    var map = new BMap.Map("l-map");
//   map.centerAndZoom("成都",12);                   // 初始化地图,设置城市和地图级别。
    // 添加带有定位的导航控件
    var navigationControl = new BMap.NavigationControl({
        // 靠左上角位置
        anchor: BMAP_ANCHOR_TOP_LEFT,
        // LARGE类型
        type: BMAP_NAVIGATION_CONTROL_LARGE,
        // 启用显示定位
        enableGeolocation: true
    });
    map.addControl(navigationControl);
    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl();
    geolocationControl.addEventListener("locationSuccess", function(e){
        // 定位成功事件
        var address = '';
        address += e.addressComponent.province;
        address += e.addressComponent.city;
        address += e.addressComponent.district;
        address += e.addressComponent.street;
        address += e.addressComponent.streetNumber;
        alert("当前定位地址为：" + address);
    });
    geolocationControl.addEventListener("locationError",function(e){
        // 定位失败事件
        alert(e.message);
    });
    map.addControl(geolocationControl);

    var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
        {"input" : "suggestId"
            ,"location" : map
        });

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

        setPlace();
    });


    function setPlace(){  

        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
        	
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果   
            $("#lat").val(pp.lat);
            $("#lon").val(pp.lng); 
            console.log(pp)
//            console.log(pp) 这里是红点的坐标
            map.centerAndZoom(pp, 18); //  18是地图打开的放大 缩小倍数 pp是坐标，把这个pp放到打开地图的位置就行了
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }

    //地图控件添加函数：
    function addMapControl() {
        //向地图中添加缩放控件
        var ctrl_nav = new BMap.NavigationControl( {
            anchor : BMAP_ANCHOR_TOP_LEFT,
            type : BMAP_NAVIGATION_CONTROL_LARGE
        });
        map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
        var ctrl_ove = new BMap.OverviewMapControl( {
            anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
            isOpen : 1
        });
        map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
        var ctrl_sca = new BMap.ScaleControl( {
            anchor : BMAP_ANCHOR_BOTTOM_LEFT
        });
        map.addControl(ctrl_sca);
    }
//<!--没有经纬度定位到当前城市，有经纬度定位到经纬度-->
    // 百度地图API功能
    var map = new BMap.Map("l-map");
    var point;
	if ($("#lon").val()!="" && $("#lat").val()!="") {
		point = new BMap.Point($("#lon").val(),$("#lat").val());//经纬度坐标   打开的经纬度坐标
	}else{
		point = new BMap.Point();
	}
	
//     map.centerAndZoom(point,12);
//    console.log(Object.keys(point).length);
if(point.lng == 0 || point.lat == 0){       //判断是否有经纬度坐标
         function myFun(result){
        var cityName = result.name ;
        map.setCenter(cityName);
        alert(1)
    }
    var myCity = new BMap.LocalCity();
    myCity.get(myFun);
    function myFun(result){
        var cityName = result.name ;
        map.setCenter(cityName);
    }
    var myCity = new BMap.LocalCity();
    myCity.get(myFun);
 }else{
    map.centerAndZoom(point, 16);
    map.addOverlay(new BMap.Marker(point));      //添加标注
}
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用


//<!--获取定位的经纬度-->
// 百度地图API功能
map.addEventListener("mouseover",getAttr);
var i=1;
function getAttr(e){
	var ic=-1;
//console.log(e.target.wa.TANGRAM__4s)
    if(i=1){
        for(var i in e.target.wa){
            try {
                e.target.wa[i].enableDragging();
                for(var i in e.target.wa){
                    if(e.target.wa[i]!='uc' && e.target.wa[i].Md!=null){
                        try {
                            e.target.wa[i].enableDragging();
                            console.log(e.target.wa[i].getPosition());
                            var lat = e.target.wa[i].getPosition()['lat'];
                            var lng = e.target.wa[i].getPosition()['lng'];
                            $("#lat").val(lat);
                            $("#lon").val(lng); 
                            ic=i;
                            var asd = document.getElementsByClassName('BMap_Marker BMap_noprint');
                            for ( var int = 0; int < asd.length; int++) {
                           	asd[int].onmouseup =function(){
//                                 if(confirm("是否保存为此位置？")){
                                	console.log(e.target.wa[ic].getPosition());
		                            var lat = e.target.wa[ic].getPosition()['lat'];
		                            var lng = e.target.wa[ic].getPosition()['lng'];
		                            $("#lat").val(lat);
		                            $("#lon").val(lng); 
//                                 }
                            };
            				}
                        }catch (e){}
                    }
                }
            }catch (e){}

        }
    }
    i++;
}

<%--map.addEventListener("mouseup",getAttr1);--%>
</script>
</html>




