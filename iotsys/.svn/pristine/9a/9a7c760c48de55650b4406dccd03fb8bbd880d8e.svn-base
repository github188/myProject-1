<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!--  参数控制Modal -->
<div class="modal fade" id="parameterModal" tabindex="-1" role="dialog"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content" style="height:auto">
			<div class="modal-header" style="margin-left:-1px;background-color:#dad7d7">
				<h4 class="modal-title" id="myModalLabel">添加参数控制</h4>
			</div>
			<div class="modal-body">
			    <div class="edit_tit" style="margin:0">如果满足以下条件</div>
				<form action="post" id="parameters">
				<p style="width:100%;height:36px;margin-top:12px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">采集器名称</span>
				<input type="text"   id="deviceNames" name="deviceNames" style="width:180px;margin-left:50px"/>
				<input type="hidden"   id="deviceIds"  name="deviceIds" />  
				</p>
				<p style="width:100%;height:15px;">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:15px">探测器名称</span>
				<select id="surveyID" name="surveyID" style="width:180px;margin-left: 50px;position: relative;left: -243px;height: 28px;">
				</select>
				</p>
				<p style="width:100%;height:36px;margin-top:32px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">控制条件</span>
				<input type="radio"    style="width:15px;height:15px;margin-left:68px;margin-top:-5px" name="condition" value="1" checked/><span style="font-size:16px;position: relative;top: 0px;">大于</span>     
				<input type="radio"    style="width:15px;height:15px;position: relative;top: -4px;margin-left: 19px;" name="condition" value="-1" /><span style="font-size:16px;position: relative;top: 0px;">小于</span>
				</p>
				<p style="width:100%;height:36px;margin-top:12px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">控制值</span>
				<input type="text"   id="conditionValue" name="conditionValue" style="width:180px;margin-left:85px"  onkeyup="this.value=this.value.replace(/[^\d]/g,'') " placeholder="请输入数字"/>
				</p>
				<div class="edit_tit" style="margin:0">就执行以下控制</div>
				<p style="width:100%;height:36px;margin-top:20px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">控制动作</span>
				<input type="radio"    style="width:15px;height:15px;margin-left:68px;margin-top:-5px" name="actions" value="1" checked/><span style="font-size:16px;position: relative;top: 0px;">开</span>     
				<input type="radio"    style="width:15px;height:15px;position: relative;top: -4px;margin-left: 35px;" name="actions" value="0" /><span style="font-size:16px;position: relative;top: 0px;">关</span>
				</p>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="addParameters()">添加</button>
				<button type="button" class="btn btn-danger"  data-dismiss="modal">关闭</button>	
			</div>
		</div>
	</div>
</div>
<script>
//执行一个laydate实例
laydate.render({
  elem: '#blankingStart',//指定元素
  type:'time'
});
laydate.render({
  elem: '#blankingEnd',//指定元素
  type:'time'
});
function changeRegular(){
      var values=$("input[name='regular']:checked").val();
      if(values=="0"){
		$("#startTimes").removeAttr("disabled","disabled");
		$("#endTimes").removeAttr("disabled","disabled");
     }else{
         	$("#startTimes").attr("disabled");
		$("#endTimes").attr("disabled");
     }
}
</script>