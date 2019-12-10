<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" id="1530143995116-2364">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="js/bootbox.min.js"></script>
		
<script type="text/javascript">
	
	function ff(){
		var in_tn = prompt("请输入类型名称:");
		if (in_tn != null && in_tn != ''){
			var x=document.getElementById("select_typename");
			var doc=document;  
			var option=doc.createElement("option");  
			option.innerHTML=in_tn;
			option.setAttribute("value", in_tn);
			option.setAttribute("selected", "selected");
			x.appendChild(option);
			console.info(document.getElementById("select_typename"));
		}
	}
	
	//保存
	function save(){
			if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#KEY1").val()==""){
			$("#KEY1").tips({
				side:3,
	            msg:'请输入值1',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEY1").focus();
			return false;
		}
		if($("#KEY2").val()==""){
			$("#KEY2").tips({
				side:3,
	            msg:'请输入值2',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEY2").focus();
			return false;
		}
		if($("#KEY3").val()==""){
			$("#KEY3").tips({
				side:3,
	            msg:'请输入值3',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEY3").focus();
			return false;
		}
		if($("#KEY4").val()==""){
			$("#KEY4").tips({
				side:3,
	            msg:'请输入值4',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEY4").focus();
			return false;
		}
		if($("#KEY5").val()==""){
			$("#KEY5").tips({
				side:3,
	            msg:'请输入值5',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEY5").focus();
			return false;
		}
		if($("#KEY6").val()==""){
			$("#KEY6").tips({
				side:3,
	            msg:'请输入值6',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEY6").focus();
			return false;
		}
		if($("#TYPE_NAME").val()==""){
			$("#TYPE_NAME").tips({
				side:3,
	            msg:'请输入类型名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TYPE_NAME").focus();
			return false;
		}
		if($("#BZ").val()==""){
			$("#BZ").tips({
				side:3,
	            msg:'请输入备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BZ").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="parameterconfiguration/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="UUID" id="UUID" value="${pd.UUID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">

			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">名称:</td>
				<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入名称" title="名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">值1(KEY1):</td>
				<td> 
					<input type="text" name="KEY1" id="KEY1" value="${pd.KEY1}" maxlength="32" placeholder="这里输入值1" title="值1"/>
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">值2(KEY2):</td>
				<td><input type="text" name="KEY2" id="KEY2" value="${pd.KEY2}" maxlength="32" placeholder="这里输入值2" title="值2"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">值3(KEY3):</td>
				<td><input type="text" name="KEY3" id="KEY3" value="${pd.KEY3}" maxlength="32" placeholder="这里输入值3" title="值3"/></td>
			</tr>
			<tr >
				<td style="width:70px;text-align: right;padding-top: 13px;">类型名称:</td>
				<td>
					<select class="" name="TYPE_NAME" id="select_typename" data-placeholder="请选择" style="vertical-align:top;width:180px;">
						<option value="">请选择</option>
						<!-- <option value=""></option> -->
						<c:forEach items="${typesList}" var="ta" varStatus="vs">
							<option value="${ta.TYPE_NAME}" <c:if test="${ta.TYPE_NAME == pd.TYPE_NAME}">selected="selected"</c:if>>${ta.TYPE_NAME}</option>
						</c:forEach>
					</select>
					<a class="btn btn-small btn-success" onclick="ff();">+</a>
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">备注:</td>
				<%-- <td><input type="text" name="BZ" id="BZ" value="${pd.BZ}" maxlength="32" placeholder="这里输入备注" title="备注"/></td> --%>
				<td><textarea rows="4" cols="2"  name="BZ" id="BZ" maxlength="200" placeholder="这里输入备注" title="备注">${pd.BZ}</textarea></td>
			</tr>
			<tr >
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>