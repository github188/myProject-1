// $(function(){
//     $(".det_main a").click(function(){
//         //里面有个a标签
//         return false;
//     });
//     $(".det_main a").click(function(){
//         $(this).siblings().removeClass("det_main_acoler").end()
//             .addClass ("det_main_acoler");
//     });
// })
$(function(){
    $(".chart_choice a").click(function(){
        //里面有个a标签
        return false;
    });
    $(".chart_choice a").click(function(){
        $(this).siblings().removeClass("i_color").end()
            .addClass ("i_color");
    });
});
function checkboxOnclick(obj){

    if ( obj.checked == true){
        $(obj).parent().prev().html("当前状态：开");
    }else{
        $(obj).parent().prev().html("当前状态：关");
    }

}

function relation_Onclick(obj){

    if ( obj.checked == true){
        $(obj).next().next().html("当前属于打开状态");
    }else{
        $(obj).next().next().html("当前属于关闭状态");
    }

}