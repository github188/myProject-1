/*全局变量企业ID*/
var enterpriseId=$("#enterpriseId1").val();
$(function(){
	findAlAndDevice();
	defaultTable();
});
 
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
			if (data.code=="000") {
				var str = "<option value=''>全部基地</option>";
				var str1 = "<option value=''>无基地</option>";
				var str2 = "<option value=''>全部地块</option>";
				var str3= "<option value=''>无地块</option>";
	            if(data.data.alQeada!=null){
					var alQeada =data.data.alQeada.data;
					if(alQeada.length>0){
						var arr = [];
						for(var i=0;i<alQeada.length;i++){
							if (arr.indexOf(alQeada[i]['id'])<0) {
								str+="<option value='"+alQeada[i]['id']+"'>"+alQeada[i]['alQaedaName']+"</option>";
								arr[arr.length]=alQeada[i]['id'];
							}
						}
						$("#baseId").html(str);
						$("#massifId").html(str2);
					}else{
						$("#baseId").html(str1);
						$("#massifId").html(str3);
					}
	            }else{
					$("#baseId").html(str1);
					$("#massifId").html(str3);
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
		data:{"alQeadaId":$("#baseId").val()},
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
					$("#massifId").html(str);
				}else if($("#baseId").val()!=""){
					$("#massifId").html(str1);
				}else{
					$("#massifId").html(str);
				}
			}else{
				warnTip(data.msg);
			}
		}
	});
}
/*页面首次加载执行table*/
function defaultTable(){
	var datas = [
					{field: 't1',title: 'ID',class: 'nodisplay',switchable:false}, 
					{field: 't2',title: '采集器名称'},
					{field: 't3',title: '所在基地'},
					{field: 't4',title: '所在地块'},
					{field: 't5',title: '关联探测器类型'},
					{field: 't6',title: '关联控制器类型'},
					{field: 't7',title: '网络状态',
						formatter:function(value,row,index){
							if(value=="正常"){
								return['<p>'+value+'</p>'].join("");
							}else if(value=="不在线"){
								return['<p style="color:red;">'+value+'</p>'].join("");
							}
						}},
					{field: 't8',title: '操作',
						formatter: function(value, row, index) {
							return [ '<a  title="修改" href="javascript:goEditJsp(\''+row.t1+'\')">编辑</a>'].join('');
						}
					}];
		var idArr = getBootTable("plantTable",datas,$("#basePath").val()+"apiMassifProductAction_findMassifProductByColumns.do?"+$("#searchProduct").serialize()+"&mass.enterpriseId="+enterpriseId);
		return idArr;	
}

/*动态查询table*/
function createTable(){
	defSearch($("#basePath").val()+"apiMassifProductAction_findMassifProductByColumns.do",$("#searchProduct").serialize()+"&mass.enterpriseId="+enterpriseId,"plantTable");
}

/*编辑操作*/
function goEditJsp(rowid){
	$("#right_content").load($("#basePath").val()+"iot/device/jsp/deviceManage.jsp?id="+rowid+"&mass.enterpriseId="+$("#enterpriseId").val());
}
