(function($) {
	//加载基地
	loadAllBase();
	
	
    $.expr[":"].Contains = function(a, i, m) {
        return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
    };
    function filterList(header, list) {
        //@header 头部元素
        //@list 无序列表
        //创建一个搜素表单
        var form = $("<form>").attr({
            "class":"filterform",
            action:"#"
        }), input = $("<input>").attr({
            "class":"filterinput",
            "placeholder":"按照摄像头名称搜索",
            type:"text"
        });
        $(form).append(input).appendTo(header);
        setTimeout(function(){
            $(".filterinput").addClass("filterinput_video");
        },100);
        $(input).change(function() {
            var filter = $(this).val();
            if (filter) {
                $matches = $(list).find("a:Contains(" + filter + ")").parent();
                $("li", list).not($matches).slideUp();
                $matches.slideDown();
            } else {
                $(list).find("li").slideDown();
            }
            return false;
        }).keyup(function() {
            $(this).change();
        });
    }
    $(function() {
        filterList($("#form_video"), $("#demo-list_video"));
    });
})(jQuery);


function loadAllBase(){
	var baseId="";
	var massifId="";
	var enterpriseId=$("#enterpriseId1").val();
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
		}else{
			alert(data.msg);
		}
		loadMassifAddress();
	}
}

function loadMassifAddress(){
	var baseId=$("#baseId").val();
	var massifId="";
	var enterpriseId=$("#enterpriseId1").val();
	//  private String enterpriseId;private String baseId;
	baseAjax(false,"monitorAction_findMassif.do?enterpriseId="+enterpriseId+"&baseId="+baseId+"&massifId="+massifId,success,null);
	function success(data){
		var str = "<option value='-1'>全部地块</option>";
		if(data.code=="000"){
			if(data.data.length>0){
				for(var i=0;i<data.data.length;i++){
					str+="<option value='"+data.data[i]['massifId']+"'>"+data.data[i]['massIfName']+"</option>";
					$("#massifId").html(str);
				}       
			}
		}else{
			alert(data.msg);
		}
	}
}

