<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>基地管理</title>
     <jsp:include page="/mHead.jsp" />
	<style type="text/css">
		.myThree{
			display: block;
			float: left;
			width: 33.33%;
			height: 92%;
			border: 1px solid;
		}
		
		.myTwo{
			display: block;
			border-bottom: 1px solid #BCBCBC;
			height: 50%;
		}
		
		span{
			margin-left: 2px;
			margin-right: 11px;
		}
		
		p{
			margin-left: 15px;
		}
		
		.listWrap, .listWrapIcon {
			padding: 0;
			margin: 0;
		}
		
		.listWrap li {
			border: 3px solid #E6E6E6;
			border-left: 15px solid #E6E6E6;
			border-right: 15px solid #E6E6E6;
			margin-bottom: 2px;
			padding: 5px;
			display:flex;
            flex-direction:row;
            justify-content:space-between;
            align-items:center;
		}
		
		.listWrap .active1 {
			border: 3px solid #99D6EB;
			border-left: 15px solid #99D6EB;
			border-right: 15px solid #99D6EB;
		}
		
		.listWrapIcon li {
			margin-bottom: 2px;
			padding: 5px;
		}
		
		.iconI{
			margin-right: 5px;	
		}
		.statu {
			float: right;
		    font-size: 20px;
		    margin-right: 15px;
			color: #666666;
		}
		.active2 {
			color: #0099CC;
		}
		table td{
			border: none;
		}
		i{
			margin-left: 5px;	
		}
		.editWrap {
			display:flex;
            align-items: center;
            text-align: center;
		}
		.edtIcon {
			display: flex;
			font-size: 18px;
		}
		.modal {
			box-shadow: none;
			background: none;
			border: none;
			top:10%;
			width:600px;
		}
		.modal-header{
			width:590px;
		    background: #ffffff;
	        margin-left: 3px;
		}
		li{
			font-size: 15px;
		}
	</style>
  </head>
  <body>
    <div style="margin: 1% 1%;">
	  	<div style="border: 1px solid;width: 100%">
	  		<select id="enterpriseId" style="margin-top: 10px;margin-left: 10px;"onchange="findAlAndDevice()">
	  			<option value="">请选择企业</option>
	  		</select>
	  	</div>
	  	<div class="myThree">
	  		<p><span>基地</span><span style="float: right"><a href="#" onclick="addAlQeadaModal()">+添加基地</a></span></p>
	  		<ul id="alQeada" class="listWrap">
	  		</ul>
	  	</div>
	  	<div class="myThree">
	  		<p><span>地块</span><span style="float: right"><a href="#" onclick="addMassifModal()">+添加地块</a></span></p>
	  		<ul id="massif" class="listWrap">
	  		</ul>
	  	</div>
	  	
	  	<div id="third" class="myThree">
	  	</div>
  	</div>
  	
  			<!--  添加属性的Modal -->
	<div class="modal fade" id="addAlQeadaModel" tabindex="-1" role="dialog" style="display: none;box-shadow: none;background: none;border: none;top:5%">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header" style="background-color: #FFFFFF">
			<input type="hidden" id="baseId" value="">
	       <table  class="input-table">
					<tr>
						<td class="before-labels" style="width: 3%;" >
							基地名称：
						</td>
						<td class="td-input">
							<input type="text" id="alQeadaName" onkeyup="backspace(this)" class="form-control" maxlength="32">
						</td>
					</tr>
					<tr>
						<td class="before-labels" style="width: 3%;" >
						基地位置
						</td>
						<td class="td-input">
						请搜索详细位置拖动红点标注出基地位置
						</td>
					</tr>
			   </table>
	      </div>
	      <div class="modal-body" style="overflow: hidden;">
	      		<div id="map-al">
	      		</div>
	       </div>
	       <div class="modal-footer">
	        <button type="button" class="btn btn-primary" onclick="addAlQeada()">保存</button>
	        <button type="button" class="btn btn-danger" data-dismiss="modal" >关闭</button>
	      </div>
	  </div>
	 </div>
    </div>
  			<!--  添加属性的Modal -->
	<div class="modal fade" id="MassifModel" tabindex="-1" role="dialog" style="display: none;box-shadow: none;background: none;border: none;top:10%">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header" style="background-color: #FFFFFF">
	      	编辑地块
	      </div>
	      <div class="modal-body">
	      <input type="hidden" id="massifBaseId" value="">
	      <input type="hidden" id="massifBaseName" value="">
	      <input type="hidden" id="massifId" value="">
	       <table  class="input-table">
					<tr>
						<td class="before-labels" style="width: 3%;" >
							地块名称：
						</td>
						<td class="td-input">
							<input type="text" id="massifName" class="form-control" maxlength="32">
						</td>
					</tr>
			   </table>
	       </div>
	       <div class="modal-footer">
	        <button type="button" class="btn btn-primary" onclick="addMassif()">保存</button>
	        <button type="button" class="btn btn-danger" data-dismiss="modal" >关闭</button>
	      </div>
	  </div>
	 </div>
    </div>
  </body>
  <script type="text/javascript" src="<%=basePath %>iot/alQaeda/js/addAlQaeda.js"></script>  
</html>
