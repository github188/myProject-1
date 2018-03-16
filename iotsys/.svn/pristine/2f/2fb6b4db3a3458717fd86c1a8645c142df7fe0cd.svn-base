<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!--  新增菜单Modal -->
<div class="modal fade" id="modalTest" tabindex="-1" role="dialog"
	style="display: none">
	<div class="modal-dialog">
		<div class="modal-content" style="height:auto">
			<div class="modal-header" style="margin-left:-1px;background-color:#dad7d7">
				<h4 class="modal-title" id="myModalLabel">添加定时控制</h4>
			</div>
			<div class="modal-body">
			    <div class="edit_tit" style="margin:0">在以下时间段位</div>
				<form action="post" id="addTest">
				
				<p style="width:100%;height:36px;margin-top:12px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">控制时间</span><span style="margin-left: 34px;font-size: 15px;">每隔</span>
				<input type="text"   id="blanking" name="blanking" style="width:40px"/><span style="font-size: 15px;">天</span>
				</p>
				
				<p style="width:100%;height:25px;margin-top:12px;font-size:15px;padding-left:105px">指间隔的天数，如间隔0天，是指每天，间隔1天，是指每隔1天</p>
				<p style="width:100%;height:30px;">
				<input style="width:100px;margin-left:100px" id="blankingStart" name="blankStart" />
				<span style="font-size: 15px;">至</span><input style="width:100px" id="blankingEnd" name="blankEnd"/>
				</p>
				<p style="width:100%;height:25px;margin-top:12px;font-size:15px;padding-left:105px;margin-bottom:30px">指当天的时间段，设置成功后，系统将在指定的时间段内进行定时控制开关</p>
				
				<p style="width:100%;height:15px;">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:15px">是否定期</span>
				<input type="radio"    style="width:15px;height:15px;margin-left:25px" name="regular" value="1"  onclick="changeRegular()" checked /><span style="font-size:16px;position: relative;top: 3px;">永久</span>     
				<span style="font-size:16;margin-top:10px;height:30px;margin-left:30px;position: relative;top: 3px" >开始时间从今天开始算起且没有结束时间</span>
				</p>
				<p style="width:100%;height:30px;">
				<input type="radio"    style="width:15px;height:15px;margin-left:101px" name="regular" value="0" onclick="changeRegular()" /><span style="font-size:16px;position: relative;top: 4px;">定期</span>     
				<span style="font-size:16;margin-top:10px;height:30px;margin-left:30px;position: relative;top: 4px;">有固定的开始和结束时间</span>
				</p>
				
				<p style="width:100%;height:36px;margin-top:12px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">开始时间</span>
				<input type="text"  name="startTimes" disabled="disabled"  id="startTimes" style="width:220px;margin-left:25px" onclick="laydate({elem:'#startTimes',isclear:false});"/>
				</p>
				<p style="width:100%;height:36px;margin-top:12px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">结束时间</span>
				<input type="text"  name="endTimes"  disabled="disabled"  id="endTimes" style="width:220px;margin-left:25px" onclick="laydate({elem:'#endTimes',isclear:false});"/>
				</p>
				<div class="edit_tit" style="margin:0">执行以下控制</div>
				<p style="width:100%;height:36px;margin-top:20px">
				<span style="font-weight:bold;font-size:18px;margin-top:10px;height:30px">控制动作</span>
				<input type="radio"    style="width:15px;height:15px;margin-left:25px;margin-top:-5px" name="status" value="1" checked/><span style="font-size:16px;position: relative;top: 0px;">永久</span>     
				<input type="radio"    style="width:15px;height:15px;position: relative;top: -4px;margin-left: 19px;" name="status" value="0" /><span style="font-size:16px;position: relative;top: 0px;">关</span>
				</p>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="tttt()">添加</button>
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