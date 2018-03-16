//企业id
var enterpriseId=$("#end").attr("data-id");

$(function(){
	findAllEnterprises();
	defaultTable();
});
function defaultTable(){
	var datas = [
					{field: 'id',title: 'ID',class: 'nodisplay',switchable:false}, 
					{field: 'cameraName',title: '摄像头名称'},
					{field: 'enterpriseName',title: '企业名称'},
					{field: 'pkDeviceId',title: '视频服务器'},
					{field: 'cameraChannnel',title: '通道'},
					{field: 'pkEnterpriseId',title: '网络状态'},
					{field: 't8',title: '操作',
						formatter: function(value, row, index) {
							 return [
							           "<a href=javascript:void(0) onclick=goto1('iot/camera/jsp/addCamera.jsp?id="+row.id+"') title='编辑'>",
							           '编辑&nbsp;&nbsp;',			           
							           "<a href=javascript:void(0) onclick=deleteCamera(\'"+row.id+"\',\'"+row.cameraName+"\') title='删除'>",
							           '删除',			           
							         ].join('');
							    }},
					];
		var idArr = getBootTable("plantTable",datas,$("#basePath").val()+"cameraInfoAction_findAllCamera.do");
		return idArr;	
}
function defTable(){
	defSearch("cameraInfoAction_findAllCamera.do?enterpriseId="+$("#enterpriseId").val()+"&aORb="+$("#aORb").val(),null,"plantTable");
}
//查询所有的企业名称
//function findAllEnterprises(){
//	baseAjax(false,"http://localhost:8080/myMongo3/apiAlQaedaInBackAction_findAll2.do",success);
//	function success(data){
//		console.log(data);
//		getAllenterpriseNoAll(data,'enterpriseName',false,"end");
//		$("#end").html("全部");
//		$("#end").attr("data-id","");
//	}
//}
function findAllEnterprises() {
	baseAjax(false,"apiDeviceInfoAction_getAllEnterprise.do",success,$("#collector").serialize());
	function success(data){
			console.log(data);
			var str = '<option value="">请选择</option>';
			for (var int = 0; int < data.length; int++) {
				str+='<option value="'+data[int].id+'">'+data[int].enterpriseName+'</option>';
			}
			$("#enterpriseId").html(str);
	}
}
//查询所有的企业名称
function deleteCamera(id,name){
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
	    message: "是否确定删除【"+name+"】摄像头？<br>删除该摄像头后，关联企业将无法再看到此摄像头的视频，并且历史数据将会清空。",  
	    callback: function(result) { 
	    	 if (result) {
				 $.ajax({
					cache: false,
					type:  "POST",
					async : false,
					url:$("#basePath").val()+"cameraInfoAction_deletCamera.do",
					data:{"cameraId":id},
					dataType:'json',
					traditional: true,//数组 这里设置为true
					success:function(data){
						console.log(data);
						if (data.code=="001") {
							successTip("操作成功");
							tableRefresh("plantTable");
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
