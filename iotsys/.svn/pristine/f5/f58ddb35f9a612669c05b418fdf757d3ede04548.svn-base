$(function(){
	var nepa=   $("#enp").val();
	var sonma=   $("#sonm").val();
	if(nepa==1){
		if(sonma==1)
			$("#sxtgl").click();
			
			if(sonma==2)
			$("#sxtglb").click();
			
			if(sonma==3)
			$("#sxttj").click();
			
			if(sonma==4)
			$("#hjjk").click();
			
			if(sonma==5)
			$("#yjtz").click();
			
			if(sonma==6)
			$("#cjqgl").click();
			
			if(sonma==7)
			$("#cjqglh").click();
			
			if(sonma==8)
			$("#cjqtj").click();
			
			if(sonma==9)
			$("#qygl").click();
			
			if(sonma==10)
			$("#jdtj").click();
			
			if(sonma==11)
			$("#jdck").click();
			
	}else if(nepa==0){
		 hid();
		$("#cjqglh").hide();
		$("#sxtglb").hide();       
		$("#jdtj").hide();
	}
	else{
		hid();
	}
});
// 左侧菜单样式
$(document).ready(function () {
//    $('#right_content').load('video.html');
    jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
    // $('#right_content').load('video.html');
    // $('#right_content').load('video.html');
    $(".submenu li").click(function(){
        $(this).siblings('li').removeClass('hover_a');  // 删除其他兄弟元素的样式
        $(this).addClass('hover_a');
    })
});
 
// 页面跳转
function  goto1(num) {
    // $("#mse_right").css("border","none");
    // 取消下拉条
    $("#mse_right").css({"overflow-y": "hidden"});
    $('#right_content').css('margin-top','100%');

    setTimeout(function(){
        $('#right_content').css('margin-top','0');
        if(num.indexOf("video_ocx.jsp")>=0){
        	$("#right_content").html("<iframe src=' "+num+" ' width='100%'  height='100%' scrolling='no' frameborder='0'> </iframe>");
        }else{
        	$("#right_content").load(num);
        }
        // 显示下拉条
        setTimeout(function(){
            $("#mse_right").css({"overflow-y": "auto"});
        },500);
    },500);
}
 

//页面跳转
function  hid() {
	//隐藏
   /* $("#mse_right").css("margin-left","0px");  
    $("#mse_right").css("padding","0px");  
    $("#menu_left").css("display","none");  
    $("#riht_title").css("display","none");  */
	//显示
	$("#mse_right").css("margin-left","190px");  
    $("#mse_right").css("padding"," 50px 0 0");  
    $("#menu_left").css("display","block");  
    $("#riht_title").css("display","block"); 
}

