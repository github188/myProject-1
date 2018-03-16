$(function(){
	// 查询事件
		$("#submitBtn").click(function(){
			$("#loginForm").submit();
		})
		if($("#flog").val()!= ""){
			alertMsg($("#flog").val());
		}
		
	
		//IE9.0以下版本给出提示
	var ua = navigator.userAgent.toLowerCase();
	var isIE = ua.indexOf("msie")>-1;
	var safariVersion;
	if(isIE){
	    safariVersion =  ua.match(/msie ([\d.]+)/)[1];
	}

	if(parseInt(safariVersion) < 9 ){
		 $("body").html("<div style='text-align:center;position:relative;top:300px;font-size:25px;font-weight:800;'>您的浏览器版本太低，请选择高版本浏览器！</div>");;
	} 
	
	//不支持placeholder的浏览器处理方法
	placeholder();
	
});