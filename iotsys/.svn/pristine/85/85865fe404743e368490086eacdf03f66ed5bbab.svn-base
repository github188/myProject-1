var enterpriseId=$("#enterpriseId").val();
$(function(){
	getAllEnterprise();
	defaultTable();
});
function getAllEnterprise() {
	baseAjax(false,$("#basePath").val()+"apiDeviceInfoAction_getAllEnterprise.do",success,$("#collector").serialize());
	function success(data){
			console.log(data);
			var str = '<option value="">请选择</option>';
			for (var int = 0; int < data.length; int++) {
				str+='<option value="'+data[int].id+'">'+data[int].enterpriseName+'</option>';
			}
			$("#enterpriseId").html(str);
	}
}

function defaultTable(){
	var datas = [
					{field: 'id',title: 'ID',class: 'nodisplay',switchable:false}, 
					{field: 'deviceName',title: '采集器名称'},
					{field: 'enterpriseName',title: '企业名称'},
					{field: 'serialNumber',title: '设备序列号'},
					{field: 'probeNumber',title: '关联探测器（个）'},
					{field: 'controllerNumber',title: '关联控制器（个）'},
					{field: 'updateTime',title: '数据更新时间',
						formatter:function(value,row,index){
							if(value==""){
								return[row.createTime].join("");
							}else {
								return[value].join("");
							}
						}},
					{field: 'enterpriseId',title: '网络状态'}, 
					{field: 't8',title: '操作',
						formatter: function(value, row, index) {
							return [ 
							         '<a  title="修改" href=javascript:void(0) onclick="goto1(\''+$("#basePath").val()+'iot/collector/jsp/editCollector.jsp?deviceId='+row.id+'\')">编辑</a>&nbsp;&nbsp;'
							         +'<a  title="删除" href="javascript:deleteDevice(\''+row.serialNumber+"\',\'"+row.deviceName+'\')">删除</a>'
							         ].join('');
						}
					}];
		var idArr = getBootTable("collector_table",datas,$("#basePath").val()+"apiDeviceInfoAction_findAll.do");
		return idArr;	
}

function createTable(){
	defSearch($("#basePath").val()+"apiDeviceInfoAction_findAllByCondition.do",$("#searchProduct").serialize(),"collector_table");
}
function deleteDevice(obj,name){
	bootbox.confirm({
	    buttons: {  
	        cancel: {  
	            label: '取消',  
	            className: 'btn btn-default'  
	        },  
			confirm: {  
				label: "确认",  
				className: 'btn btn-primary'  
			} 
	    },  
	    message: "是否确定删除【"+name+"】采集器？<br>删除该采集器后，关联企业将无法再看到此采集器收集的环境信息，并且历史数据将会清空。",  
	    callback: function(result) { 
	    	if (result) {
				 $.ajax({
						cache: false,
						type:  "POST",
						async : false,
						url:$("#basePath").val()+"apiDeviceInfoAction_deleteDevice.do",
						data:{"id":obj},
						dataType:'json',
						traditional: true,//数组 这里设置为true
						success:function(data){
							console.log(data);
							if (data.code=="000") {
								successTip("采集器已删除成功");
								defSearch($("#basePath").val()+"apiDeviceInfoAction_findAllByCondition.do",$("#searchProduct").serialize(),"collector_table");
							}else{
								warnTip("系统异常");
							}
						}
					});
			}else{
	        	console.log("选择了取消");
	        }
	    },  
	    title: "删除摄像头",  
    });
}