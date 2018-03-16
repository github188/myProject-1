
$(function(){
	 $(".tip-box").click(function(){$(this).fadeOut();});   //点击消息提示，消息关闭
	 toggleMenu();    //左侧菜单显隐切换
});
 
/**
 * ajax方法
 * @param asyncVal 是否同步   true or false
 * @param url 访问路径（后台）
 * @param functions 回调函数
 * @param datas 传到后台的数据
 */
function baseAjax(asyncVal,url,functions,datas){
	$.ajax({
		cache: false,
	    type:  "POST",
	    async : asyncVal,
		url:url,
		data:datas,
		dataType:'JSONP',
		jsonp: "callbackparam",
		jsonpCallback:"success_jsonpCallback",
		/*beforeSend:function() { 
			parent.$("#main_mb_f").mLoading("show");//显示loading
			  }, */
		success:function(data){
			functions(data);
		},
		/* complete: function () {//完成响应
	    	  parent.$("#main_mb_f").mLoading("hide");//子页面数据加载完隐藏loading
       }*/
	});
}

/**
 * ajax方法
 * @param asyncVal 是否同步   true or false
 * @param url 访问路径（后台）
 * @param functions 回调函数
 * @param datas 传到后台的数据
 */
function baseAjax1(url,functions,datas){
	$.ajax({
		cache: false,
	    type:  "POST",
	    async : false,
		url:url,
		data:datas,
		dataType:'json',
		traditional: true,//数组 这里设置为true
		/*beforeSend:function(){
			parent.$("#main_mb_f").mLoading("show");//显示loading
			},*/
		success:function(data){
			functions(data);
		},
		/* complete: function () {//完成响应
	    	  parent.$("#main_mb_f").mLoading("hide");//子页面数据加载完隐藏loading
      }*/
	});
}
/**
 * ajax向后台发送请求方法(dataType为json)
 * @param formId:form表单的id，
 * @param url:数据请求地址,
 * @param data:提交到后台的附加参数,
 * @param successCallback:发送成功回调函数
 * @return 无
 */
function baseSubmit(formId,url,data,successCallback){
	var submitData = $('#'+formId).serialize();
	if(formId == null || formId == ""){
		submitData = data;
	}
	$.ajax({
	    cache: false,
	    type:  "POST",
	    url:   url,
	    data:  submitData,
	    dataType:'jsonp',
	    success: function(data) {
	    	successCallback(data);
	    },
	    error:function(xhr,codeStatus){
	    	warnMsg(codeStatus);
	    }
	});
}

/**
 * 广告平台打开左侧功能菜单方法
 * @param url:菜单转到的jsp页面地址
 * @return 无
 */
function menu(url){
	hideTip();  //关闭输入框验证框
	loadingHide();   //关闭锁定画面
	if(url==null || url=="")
		$("#main-content").load("javascript:void(0)?s="+Math.random());  //#main-content：右侧页面顶层框的id
	else
		$("#main-content").load(url+"?s="+Math.random());
}

/**
 * 操作信息提示方法
 * @param msg:要提示用户的消息
 * @return 无
 */
function alertMsg(msg){
	BootstrapDialog.show({
		cssClass: 'alert-dialog',
		title: "系统提示",
		message: msg
	});
}

/**
 * 上传图片
 * @param idStr file按钮的ID
 * @param imgId  图片的ID
 */
function uploadFile(url,idStr,imgId,textId){
	$("#"+idStr).uploadify({
	   'uploader':url,   // 后台处理程序的相对路径
       'swf':'source/uploadify.swf',            //uploadify.swf 文件的相对路径
       'preventCaching':true,            //如果为true，则每次上传文件时自动加上一串随机字符串参数，防止URL缓存影响上传结果
       'fileSizeLimit':"1MB",         //上传文件的大小限制    
       'fileObjName':'uploadify',        //文件上传对象的名称
       'width':'80',               //文件浏览按钮的宽度
       'multi': false,             //是否可以上传多个文件
       'fileTypeExts':"*.gif;*.jpeg;*.jpg;*.png;*.bmp;",       //设置可以选择的文件的类型
       'height':'25',              //文件浏览按钮的高度
       'formData':{"cerType":$("#"+idStr).attr("cerType")},          //上传每个文件的同时提交到服务器的额外数据
       'auto': true,               //是否 当选择文件后就自动上传
       'buttonText': "上传图片",        //浏览按钮的文本
       'onUploadSuccess':function(file,data,response){   //上传成功回调方法
       		if(data!=null){
       			data=eval("("+data+")");
       			if(data.code=="1"){
       				$("#"+imgId).attr("src","uploadAction_showImg.do?fileName="+data.msg);
       				$("#"+textId).val(data.msg);
       			}else if(data.code=="001"){
       				$("#"+imgId).attr("src",data.msg);
       				$("#"+textId).val(data.msg);
       			}else{
       				alertMsg(data.msg);
       			}
       		}
       },
       'onInit': function () {                        //载入时触发，将flash设置到最小   隐藏进度条
           $(".uploadify-queue").hide();
       },
       'onSelectError': function (file, errorCode, errorMsg) {   //文件选择出错回调
           switch (errorCode) {  
                  case -100:  
                	  alertMsg("上传的文件数量已经超出系统限制的" + $('#'+idStr).uploadify('settings', 'queueSizeLimit') + "个文件!");  
                      break;  
                  case -110:  
                	  alertMsg("文件大小超出系统限制的" + $('#'+idStr).uploadify('settings', 'fileSizeLimit') + "大小!");  
                      break;  
                  case -120:  
                	  alertMsg("文件大小异常!");  
                      break;  
                  case -130:  
                	  alertMsg("上传文件类型不正确!<br>请上传:gif,jpeg,jpg,png,bmp!");  
                      break;  
               }  
        }
	});	
}
/**
 * 点击上传的图片查看大图
 */
function imgShow(outerdiv, innerdiv, bigimg, _this){
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if(realHeight>windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放

        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}
/**
 * 上传apk和exe文件
 * @param idStr file按钮的ID
 * type   如果是apk就传1  exe就传0
 */
function uploadApkAndExe(idStr,canShu){
	var fileType="*.apk;";
	var btnType="上传apk文件";
	var msgType="apk文件";
	if(canShu.type==0){
		fileType="*.exe;";
		btnType="上传exe文件";
		msgType="exe文件";
	}
	$("#"+idStr).uploadify({
	   'uploader':'uploadAction_uploadApkAndExe.do;jsessionid='+canShu.jsessionid,   // 后台处理程序的相对路径
       'swf':'source/uploadify.swf',            //uploadify.swf 文件的相对路径
       'preventCaching':true,            //如果为true，则每次上传文件时自动加上一串随机字符串参数，防止URL缓存影响上传结果
       'fileSizeLimit':"100MB",         //上传文件的大小限制    
       'fileObjName':'uploadify',        //文件上传对象的名称
       'width':'80',               //文件浏览按钮的宽度
       'multi': false,             //是否可以上传多个文件
       'fileTypeExts':fileType,       //设置可以选择的文件的类型
       'height':'25',              //文件浏览按钮的高度
       'formData':canShu,          //上传每个文件的同时提交到服务器的额外数据
       'auto': true,               //是否 当选择文件后就自动上传
       'buttonText': btnType,        //浏览按钮的文本
       'onUploadSuccess':function(file,data,response){   //上传成功回调方法
       		data1=eval('(' + data + ')');
       		alertMsg(data1.msg);
       },
       'onSelectError': function (file, errorCode, errorMsg) {   //文件选择出错回调
           switch (errorCode) {  
                  case -100:  
                	  alertMsg("上传的文件数量已经超出系统限制的" + $('#'+idStr).uploadify('settings', 'queueSizeLimit') + "个文件!");  
                      break;  
                  case -110:  
                	  alertMsg("文件大小超出系统限制的" + $('#'+idStr).uploadify('settings', 'fileSizeLimit') + "大小!");  
                      break;  
                  case -120:  
                	  alertMsg("文件大小异常!");  
                      break;  
                  case -130:  
                	  alertMsg("上传文件类型不正确!<br>请上传:"+msgType+"!");  
                      break;  
               }  
        }
	});	
}




/**
 * select动态生成option方法
 * @param selectid:需要生成option项的select标签的id
 * @param data:需在option显示的数据(data字段：第一个字段对应option的value,第二个字段对应option的文本)
 * @param valueIndex:option value对应索引
 * @param txtIndex ：option 文本对应索引
 * @param data格式：[{id:1,name:'wang'},{id:2,name:'zhang'}]
 * @return 无
 */
function fn_select(selectid,data,valueIndex,txtIndex){
	$('#'+selectid).empty();
    var row = eval(data);
    var ap_str = '<option class="ap_option" value=""></option>';
    //默认填充一条空数据，并且默认选中为空的这条数据。
    $('#'+selectid).append('<option class="ap_option" value="" selected></option>');
    //如果有数据，将数据填充到input里面。
    for(var i=1;i<row.length+1;i++){
        var arr = [];
        for(items in row[i-1]){
           arr.push( row[i-1][items]);
            var value = arr[valueIndex] || "default";
            var txt = arr[txtIndex] || "default";
        }
        $('#'+selectid).html(ap_str);
        $('#'+selectid).find('.ap_option').eq(i).val(value);
        $('#'+selectid).find('.ap_option').eq(i).text(txt);
    }
}


/**
 * 生成省份select下拉框数据
 */
function getMySheng(){
	var ob = $("#mySheng").val();
	fn_select("sheng",ob,0,1);
}

/**
 * 生成城市select下拉框数据
 */
function getMyShi(){
	$('#myChannel').empty();
	var code =$("#sheng").val();
	$.ajax({
        url : "baseAction_getMyShi.do",  //请求地址对应sAjaxSource
        data : {"code123":code},        //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'post',
        dataType : 'text',
        async : false,
        success : function(result) {
        	fn_select("myShi",result,2,0);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown);
        }
    });
}

/**
 * 生成渠道select下拉框数据
 */
function getMyChannel(){
	var code =$("#myShi").val();
	$.ajax({
        url : "baseAction_getMyChannel.do",
        data : {"code123":code},
        type : 'post',
        dataType : 'text',
        async : false,
        success : function(result) {
        	fn_select("myChannel",result,1,0);
            
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(textStatus);
        	alert(errorThrown);
        }
    });
}




/**
 * 动态生成菜单方法
 * @param menuid: 菜单显示区域的id
 * @param data：后端传过来的菜单数据
 * @param data格式：
 * @param var da2 = '{"data":[{"data1":[{"id":1,"menuName":"用户管理","isActive":"0","menuOrder":1,"menuUrl":"","parentId":""},'+
             '{"id":2,"menuName":"新增用户","isActive":"0","menuOrder":"1","menuUrl":"userList.jsp","parentId":"1"},'+
             '{"id":3,"menuName":"用户列表","isActive":"0","menuOrder":"2","menuUrl":"userList.jsp","parentId":"1"}]},'+
             '{"data1":[{"id":4,"menuName":"订单管理","isActive":"0","menuOrder":"1","menuUrl":"","parentId":""},'+
             '{"id":5,"menuName":"新增订单","isActive":"0","menuOrder":"1","menuUrl":"","parentId":"4"}]}]}';
*/

function fn_menu(menuid,data){
    var obj  = eval("(" + data+ ")");
    var row = obj.data;
    var ap_ul = '<div class="menu-title"><div class="menu-name"></div><span class="menu-icon1"></span></div> <ul class="nav nav-list ul-box"> </ul>';
    var ap_li = ' <li class="li-title"> <a> <i class="my-file"></i> <span></span> </a> </li>';
    var firstMenu=[];
    for(var i=0;i<row.length;i++){
        var secondMenu=[],secondUrl=[];
        $('#'+menuid).append(ap_ul);         //生成一级菜单
        
        var row_item = row[i].data1;
        for(var j=0;j<row_item.length;j++){
            if(!row_item[j].parentId){
                firstMenu.push(row_item[j].menuName);   //获取一级菜单数据
            }else{
                secondMenu.push(row_item[j].menuName);   //获取二级菜单数据
                secondUrl.push(row_item[j].menuUrl);
            }
        }
        $('.menu-name').eq(i).html(firstMenu[i]);    //添加一级菜单标题
        for(var j=0;j<row_item.length-1;j++){
            $('.ul-box').eq(i).append(ap_li);           //生成二级菜单
            $('.ul-box').eq(i).find('.li-title').eq(j).find('span').html(secondMenu[j]);   //添加二级菜单标题
            if(secondUrl[j]){
                $('.ul-box').eq(i).find('.li-title').eq(j).find('a').prop('href','javascript:menu("'+secondUrl[j]+'")'+'');  //二级菜单请求地址
            }
        }
    }      
   
    toggleMenu();
    
    //给菜单添加选中时的颜色
    $("#sidebar li").each(function(){
		$(this).click(function(){
			$(this).css("background","#448AC8").siblings().css("background","#0160b0");
			$(this).parent().siblings("ul").find("li").css("background","#0160b0");
		});
	});
}

/**
 * 左侧菜单显隐切换方法
 */
function toggleMenu(){
    $('.ul-box').hide();
    $('.menu-title').each(function(i){
        $(this).click(function(){
        	$(this).next('.ul-box').slideToggle().siblings('ul').slideUp();
        });
    });
}
$(function(){    
	$(".ul-box li").click(function() {        
		$(this).siblings('li').removeClass('libgcolor');  // 删除其他兄弟元素的样式        
		$(this).addClass('libgcolor');                            // 添加当前元素的样式    
	});
	//没有主菜单的时候高亮
	$(".ul-box1 li").click(function() {        
		$(this).siblings('li').removeClass('libgcolor');  // 删除其他兄弟元素的样式        
		$(this).addClass('libgcolor');                            // 添加当前元素的样式    
	});
}); 


/**
 * 动态角色授权方法
 * @param menuid: 菜单显示区域的id
 * @param data：菜单数据
 * @param data格式：
 * @param var da2 = '{"data":[{"data1":[{"id":1,"menuName":"用户管理","isActive":"0","menuOrder":1,"menuUrl":"","parentId":""},'+
				 '{"id":2,"menuName":"新增用户","isActive":"0","menuOrder":"1","menuUrl":"userList.jsp","parentId":"1"},'+
				 '{"id":3,"menuName":"用户列表","isActive":"0","menuOrder":"2","menuUrl":"userList.jsp","parentId":"1"}]},'+
				 '{"data1":[{"id":4,"menuName":"订单管理","isActive":"0","menuOrder":"1","menuUrl":"","parentId":""},'+
				 '{"id":5,"menuName":"新增订单","isActive":"0","menuOrder":"1","menuUrl":"","parentId":"4"}]}]}';
*/
function fn_roleMenu(menuid,data){
	var obj  = eval("(" + data+ ")");
	var row = obj.data;
	var ap_ul = '<ul class="tree-box file-tree"><li class=""><input type="checkbox" class="tree-first-chk"/>'+
				'<a><div class="hit-area"></div><span class="tree-first-menu"></span></a>'+
				'<ul class="tree-ul-box" style=""></ul></li></ul>'		
	var ap_li = ' <li><input type="checkbox" /><a><div class="hit-area"></div><span class="tree-second-menu"></span></a></li>';

	$('#'+menuid).append('<div class="treemenu-box"></div>');
	var firstMenu=[],firstId=[],firstFlag=[];
	for(var i=0;i<row.length;i++){
		var secondMenu=[],secondId=[],secondFlag=[];
		$('#'+menuid).find('.treemenu-box').append(ap_ul);         //生成一级菜单
		var row_item = row[i].data1;

		for(var j=0;j<row_item.length;j++){			
			if(!row_item[j].parentId){
				firstMenu.push(row_item[j].menuName);   //获取一级菜单数据
				firstId.push(row_item[j].id);    		//获取一级菜单id
				firstFlag.push(row_item[j].isActive);         //获取是否选中标志
			}else{
				secondMenu.push(row_item[j].menuName);   //获取二级菜单数据
				secondId.push(row_item[j].id);           //获取二级菜单id
				secondFlag.push(row_item[j].isActive);
			}
		}

		$('.tree-first-menu').eq(i).html(firstMenu[i]);   //添加一级菜单标题
		$('.tree-first-chk').eq(i).val(firstId[i]);  
		
		if(firstFlag[i]==1){
			$('.tree-first-chk').eq(i).prop("checked","checked");
		}
		
		for(var j=0;j<row_item.length-1;j++){			
			$('.tree-ul-box').eq(i).append(ap_li);           //生成二级菜单
			$('.tree-ul-box').eq(i).find('.tree-second-menu').eq(j).text(secondMenu[j]);   //添加二级菜单标题
			$('.tree-ul-box').eq(i).find(':checkbox').eq(j).val(secondId[j]); 
			if(secondFlag[j]==1){
				$('.tree-ul-box').eq(i).find(':checkbox').eq(j).prop("checked","checked");
			}
		}
	}

}
	

	
/**
 * 对不同角色 菜单授权调用的方法
 * @param id:菜单选择的菜单id
 */
function selectRole(id){
	var roleId=$("#roleId").val();
	$.ajax({
        url : "sysRoleAction_MenuToRole.do",//这个就是请求地址对应sAjaxSource
        data : {"roleId":roleId,"menuId":id},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
        	//alertMsg("添加关联角色成功");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        	warnMsg(errorThrown);
        }
    });
}
	
/**
 * 对不同角色授权 去掉菜单方法
 * @param id:是菜单选择的菜单id
 */
function disSelectRole(id){
	var roleId=$("#roleId").val();
	$.ajax({
        url : "sysRoleAction_MenuToRoleDis.do",//这个就是请求地址对应sAjaxSource
        data : {"roleId":roleId,"menuId":id},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
        	//alert("去掉角色成功");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown);
        }
    });
}
	
	
/**
 * 设定select 下拉框不能选择
 * item : select id
 */
function unSelect(item){
	$('#'+item).bind("mousedown",function(){return false;});
}


/**
 * 恢复下拉框能选
 * item : select id
 */
function inSelect(item){
	$('#'+item).unbind("mousedown");
}
	


/**
 * 不支持输入框 placeholder的处理方法
 */	
function placeholder(){
	//判断浏览器是否支持placeholder属性
	supportPlaceholder='placeholder'in document.createElement('input');
	if(!supportPlaceholder){ 
		$('input').each(function(){
		  text = $(this).attr("placeholder"); 
		  regPlaceholder($(this));
		});
	}
}
 
/**
 * 不支持输入框 placeholder的处理方法
 */	
function regPlaceholder(input){
    var text = input.attr('placeholder'),
    defaultValue = input.defaultValue;

    if(!defaultValue){
	  if(input.prop("type")=="password"){	 
		  input.prop("type","text");
	  }

      input.val(text).css("color","#777");
    }
 
    input.focus(function(){
      if(input.val() == text){  
		if($(this).val()=="密码"){
			$(this).prop("type","password");
		}
        $(this).val("");
      }
    });
   
    input.blur(function(){
      if(input.val() == ""){ 
		if(input.prop("type")=="password"){	 
			input.prop("type","text");
		}
        $(this).val(text).css("color","#777");
      }
    });
 
    input.keydown(function(){  
      $(this).css("color","#000");
    });
	
};


/**
 * 表格插件bootstrap-table方法
 * @param tableId:表格id
 * @param datas:自定义 数据列
 * @param url:数据请求地址
 * @return 点击复选框后获取的id组
 */

var idArr = [];       //全选或多选checkbox获取id组
function getBootTable(tableId,datas,url){   
	$('#'+tableId).bootstrapTable({
		method: 'get',            //数据请求方式
		url:url,                  //数据请求地址
		cache: false,             //是否缓存
		pagination: true,         //是否分页
		striped: true,
		pageList: [10, 20, 50, 100],   
		sortable: true, //是否启用排序
		//sortOrder: "ID asc", //排序方式
		search: false,               //是否显示搜索框
		showColumns: false,          //是否显示筛选按钮
		showRefresh: false,          //是否显示刷新按钮
		//sidePagination:'server',
		//showExport: true,         //是否显示下载按钮（需table-export.js支持）
		clickToSelect: true,	       //点击行是否选中行首复选框
		maintainSelected:true,         //选中复选框，换页码后是否保存选中状态
		showExport: false,             //导出excel按钮
		exportDataType: "all",
		exportTypes:['excel', 'xlsx'],  //导出文件类型
		exportOptions:{  
	           ignoreColumn: [0],  //忽略某一列的索引  
	           worksheetName: 'sheet1',  //表格工作区名称  
	       },  
		columns:datas,                 //处理数据的数组
		onCheck:function(row, $element){         //选中复选框获取id
			var index = $.inArray(row.id,idArr);
			if (index == -1)  idArr.push(row.id);			
		},
		onUncheck:function(row, $element){       //取消复选框移除id
			var index = $.inArray(row.id,idArr);
			if (index > -1)   idArr.splice(index, 1); 			
		},
		onCheckAll:function(rows){	       //选中全选复选框获取全部id		
			for(var i=0;i<rows.length;i++){
				var index = $.inArray(rows[i].id,idArr);
				if (index == -1)  idArr.push(rows[i].id);	
			}			
		},
		onUncheckAll:function(rows){      //取消全选框移除全部id
			for(var i=0;i<rows.length;i++){
				var index = $.inArray(rows[i].id,idArr);
				if (index > -1)   idArr.splice(index, 1); 		
			}				
		},
		responseHandler:function(res){       //返回数据
			return res;
		}
		
	});
	return idArr;               //返回点击复选框后获取的id组
}

/**
 * 数据表格自定义搜索功能 请求方法
 * @param url   ：请求地址
 * @param data  ：要发送的数据
 * @param tableId  ：数据表格的id
 */
function defSearch(url,data,tableId){   	
	$.ajax({
		url:url,        //查询时数据请求地址
  	  	type:"post",    //请求方式get,post
  	    data:data,       //发送给后端的附加参数数据
  	    dataType:"json",	 //返回数据格式	  	 
  	    success:function(data){   //发送成功回调
  	    	// var da = JSON.parse(data);	
			//将查询的数据重新载入表格   
  	    	if(data!=null && data[0]!=null){
  	    		$('#'+tableId).bootstrapTable('load', data);   //#tableID为已经初始化的表格ID，da为查询返回的数据结果
  	    	}else{
  	    		$("#"+tableId+" tbody").html("<tr data-index='0'><td valign='top' colspan='13' class='dataTables_empty'>没有找到符合条件的数据</td></tr>");
  	    	}
 		}					
	});
}

/**
 * bootstrap-table表格局部刷新方法
 * 在增删改查操作后需刷新表格
 * @param tableId:需刷新的表格ID
 */
function tableRefresh(tableId){
	$('#'+tableId).bootstrapTable('refresh',{silent: true});
};






/**
 * 输入框验证提示方法
 * @param msg:要提示的文字信息
 * @param tipdiv:要显示在哪个input后面的input id
 */
function showTip(msg,tipdiv){
	layer.tips(msg,tipdiv);	
}

/**
 * 关闭验证提示方法
 */
function hideTip(){
	$(".layui-layer").hide();
}



/**
 * 锁定页面，提示显示加载中 方法
 */
function loadingTip(){
	$("#loadding-wall").show();
	$("#loadding").show();
}

/**
 * 隐藏加载中的提示 方法
 */
function loadingHide(){
	$("#loadding-wall").hide();
	$("#loadding").hide();
}

/**
 * 省市区选择插件 方法
 * @params cityDiv: 选择框外的div ID ， cityInput:选择输入框ID
 */
function getAddress(cityDiv){
	console.log($("#city-select").attr("display"));
	$("#"+cityDiv).Address({
		callback:function(infos,selected_ids) {
			var str = '';
			for(var i=0;i<infos.length;i++) {			
				str = str+infos[i];
			}
			$('#'+cityDiv+' input').val(str);
		}
	});
}

/**
 * 隐藏省市区插件
 */
function hideAddress(){
	$("#city-select").hide();
}



/**
 * 操作成功提示 
 */
function successTip(msg){
	loadingHide();	
	closeTip();
	$(".tip-suc-box").fadeIn().find("span").html(msg);
	setTimeout(function () {
		$(".tip-suc-box").fadeOut(120);
    },3000);
}

/**
 * 操作 其他结果提示
 * @param msg
 */
function warnTip(msg){
	loadingHide();
	closeTip();
	$(".tip-warn-box").fadeIn().find("span").html(msg);
	setTimeout(function () {
		$(".tip-warn-box").fadeOut(100);
    },5000);
}


/**
 * 关闭操作提示
 */
function closeTip(){
	 $(".tip-box").hide();
}


/**
 * 视频播放插件调用方法
 * @param videoId:视频加载div的ID
 * @param url: 视频资源地址
 * 注：一加载视频播放时需调用该方法，视频上传成功时需调用该方法
 */
function vPlayer(videoId,url){  
	flowplayer(videoId, "source/flowplayer/flowplayer-3.2.12.swf",   //flowplayer-3.2.12.swf相对地址
	    { clip: { url:url, 
				  autoPlay: false,  //是否自动播放
				  autoBuffering: false  //是否自动缓冲
	    		},		
		 showErrors:false, 		
		 play: {
		        label: null,
		        replayLabel: ""
		       }
		}
	);  
}




/**
 * 生成统计图表插件
 * @param divId   加载报表的div
 * @param text    要显示的栏目数组,如：['合作商一','合作商二','合作商三','合作商四','合作商五']  必须
 * @param subtext    标题，如 ：终端数量            可选
 * @param itemArr    副标题，如：单位/台    可选
 * @param dataX      横坐标数据数组，如：['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']      必须
 * @param dataY      纵坐标数据数组 ，格式如：[{name:'合作商一',type:'line',data: [50, 30, 95, 105, 82, 115, 102, 65, 183, 143, 199, 96]}  必须
 */
function createChart(divId,text,subtext,itemArr,dataX,dataY){   
	var myChart = echarts.init(document.getElementById(divId),"macarons");
	var option = {
		title : {
			text: text,
			subtext: subtext
		},
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data:itemArr
		},
		toolbox: {
			show : true,
			feature : {
				magicType : {show: true, type: ['line', 'bar']},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		xAxis : [
			{
				type : 'category',
				data : dataX
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : dataY
    };
    myChart.setOption(option);		        
    window.onresize = myChart.resize;
}

/**
 * 删除图片
 * @param obj a标签对象
 */
function delImg(obj){
	var enterId = $("#enterId").val();
	var agentId = $("#agentId").val();
	var who = $("#who").val();
	//获取存图片名称的隐藏文本框的ID
	var textId=obj.parent().siblings("input").attr("id");
	//获取操作的图片的ID
	var imgId=obj.parent().siblings("img").attr("id");
	//获取文件类型
	var cerType=obj.attr("name");
	//获取存图片名称的隐藏文本框的ID
	var textVal=$("#"+textId).val();
	if(textVal!=null && textVal!=""){
		baseAjax(true,"uploadAction_deleteImg.do",success,{"fileName":textVal,"cerType":cerType,"enterpriseId":enterId,"agentId":agentId,"who":who});
		function success(data) {
			console.log(data);
	    	if(data!=null){
	    		if(data.code=="1"){
	    			$("#"+imgId).attr("src",data.msg);
	    		}else{
	    			alertMsg(data.msg);
	    		}
	    	}
	    }
	}else{
		alertMsg("没有图片");
	}
}

/**
 * 获取当前时间
 * xxxx-xx-xx xx:xx:xx
 * @returns {String}
 */
function getCurrentTime(){
    //shijianchuo是整数，否则要parseInt转换
    var time = new Date(Date.parse(new Date()));
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate()+1;
    var h = time.getHours()+1;
    var mm = time.getMinutes()+1;
    var s = time.getSeconds()+1;
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}
function add0(m){
	return m<10?'0'+m:m;
}
function backspace(obj) {
	var val = $(obj).val().replace(/'|"|\:|/g,"");
	obj.value=val;
}
/**
 * 获取所有的企业
 */
function getAllenterprise(datas,ziduan,flog,id,onchange){
		var daaaas = [{"":"全部"}];
		for ( var int = 0; int < datas.length; int++) {
			var ids = datas[int].id;
			var typeName = datas[int][ziduan];
			var data = "{'"+ids+"':'"+typeName+"'}";
			var obj = eval('(' + data + ')');
			daaaas[daaaas.length] = obj;
		}
		$('#'+id).hsCheckData({
			isShowCheckBox: flog, //默认为false
			minCheck: 0,//默认为0，不限最少选择个数
			maxCheck: 0,//默认为0，不限最多选择个数
			method: onchange,//默认为0，不限最多选择个数
			data:daaaas
		});
}
function getAllenterpriseNoAll(datas,ziduan,flog,id,onchange){
	var daaaas = [];
	for ( var int = 0; int < datas.length; int++) {
		var ids = datas[int].id;
		var typeName = datas[int][ziduan];
		var data = "{'"+ids+"':'"+typeName+"'}";
		var obj = eval('(' + data + ')');
		daaaas[daaaas.length] = obj;
	}
	$('#'+id).hsCheckData({
		isShowCheckBox: flog, //默认为false
		minCheck: 0,//默认为0，不限最少选择个数
		maxCheck: 0,//默认为0，不限最多选择个数
		method: onchange,//默认为0，不限最多选择个数
		data:daaaas
	});
}


/**
 * 获取所有的行业
 */
function getAllTrace(datas,ziduan,flog,id){
		var daaaas = [];
		for ( var int = 0; int < datas.length; int++) {
			var ids = datas[int].id;
			var typeName = datas[int][ziduan];
			var data = "{'"+ids+"':'"+typeName+"'}";
			var obj = eval('(' + data + ')');
			daaaas[daaaas.length] = obj;
		}
		$('#'+id).hsCheckData({
			isShowCheckBox: flog, //默认为false
			minCheck: 0,//默认为0，不限最少选择个数
			maxCheck: 0,//默认为0，不限最多选择个数
			data:daaaas
		});
}