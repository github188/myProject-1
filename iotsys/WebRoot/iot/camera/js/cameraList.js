//企业id
var enterpriseId=$("#end").attr("data-id");

$(function(){
	findAlQeada();
	defaultTable();
});
function defaultTable(){
	var datas = [
					{field: 'id',title: 'ID',class: 'nodisplay',switchable:false}, 
					{field: 'cameraName',title: '摄像头名称'},
					{field: 'massifProductbean.baseName',title: '所在基地'},
					{field: 'massifProductbean.massIfName',title: '所在地块'},
					{field: 'comments',title: '备注'},
					{field: 'pkEnterpriseId',title: '网络状态'},
					{field: 't8',title: '操作',
						formatter: function(value, row, index) {
							 return [
							           "<a href=javascript:void(0) onclick=goto1('iot/camera/jsp/cameraInfo.jsp?id="+row.id+"') title='修改'>",
							           '编辑',			           
							         ].join('');
							    }},
					];
		var idArr = getBootTable("plantTable",datas,$("#basePath").val()+"cameraInfoAction_findAllCamera.do?enterpriseId="+$("#enterpriseId1").val());
		return idArr;	
}
function defTable(){
	defSearch($("#basePath").val()+"cameraInfoAction_findAllCamera.do?alQeada="+$("#alQeada").val()
			+"&massif="+$("#massif").val()+"&aORb="+$("#cameraName").val()+"&enterpriseId="+$("#enterpriseId1").val(),null,"plantTable");
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
function findAlQeada() {
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:$("#basePath").val()+"apiAlQeadaAction_findAlAndDevice.do",
		data:{"enterpriseId":$("#enterpriseId1").val()},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			if (data.code=="000") {
				var alQeada =data.data.alQeada.data;
				console.log(alQeada);
				if (alQeada!=null && alQeada.length>0) {
					var astr='<option value="">全部基地</option>';
					for (var int = 0; int < alQeada.length; int++) {
						astr+='<option value="'+alQeada[int].id+'">'+alQeada[int].alQaedaName+'</option>'
					}
					$("#alQeada").html(astr);
				}
			}
			
		}
	});
}
function findAlAndDevice() {
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:"apiAlQeadaAction_findAlAndDevice.do",
		data:{"enterpriseId":enterpriseId},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			if (data.code=="000") {
				var alQeada =data.data.alQeada.data;
				
				var str = "<option value=''>全部基地</option>";
				var str1 = "<option value=''>无基地</option>";
				var str2 = "<option value=''>全部地块</option>";
				var str3= "<option value=''>无地块</option>";
				if(alQeada.length>0){
					var arr = [];
					for(var i=0;i<alQeada.length;i++){
						if (arr.indexOf(alQeada[i]['id'])<0) {
							str+="<option value='"+alQeada[i]['id']+"'>"+alQeada[i]['alQaedaName']+"</option>";
							arr[arr.length]=alQeada[i]['id'];
						}
					}
					$("#alQeada").html(str);
					$("#massif").html(str2);
				}else{
					$("#alQeada").html(str1);
					$("#massif").html(str3);
				}
			}
		}
	});
}
function findMassifByalQeadaId() {
	$.ajax({
		cache: false,
		type:  "POST",
		async : false,
		url:"apiAlQeadaAction_findMassifByalQeadaId.do",
		data:{"alQeadaId":$("#alQeada").val()},
		dataType:'json',
		traditional: true,//数组 这里设置为true
		success:function(data){
			console.log(data);
			var str = "<option value=''>全部地块</option>";
			var str1= "<option value=''>无地块</option>";
			if (data.code=="000") {
				var massif =data.data.massif.data;
				console.log(massif);
				if(massif!=null && massif.length>0){
					var arr = [];
					for(var i=0;i<massif.length;i++){
						if (arr.indexOf(massif[i]['id'])<0) {
							str+="<option value='"+massif[i]['id']+"'>"+massif[i]['massifName']+"</option>";
							arr[arr.length]=massif[i]['id'];
						}
					}
					$("#massif").html(str);
				}else if($("#alQeada").val()!=""){
					$("#massif").html(str1);
				}else{
					$("#massif").html(str);
				}
			}else{
				warnTip(data.msg);
			}
		}
	});
}