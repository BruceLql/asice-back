<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
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
		
<script type="text/javascript">
	
	
	//保存
	function save(){
		if($("#CONTACTS_NAME").val()==""){
			$("#CONTACTS_NAME").tips({
				side:3,
	            msg:'请输入联系人姓名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTACTS_NAME").focus();
			return false;
		}
		if($("#CONTACTS_EMAIL").val()==""){
			$("#CONTACTS_EMAIL").tips({
				side:3,
	            msg:'请输入联系人邮箱',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTACTS_EMAIL").focus();
			return false;
		}
		if($("#CONTACTS_COMPANY").val()==""){
			$("#CONTACTS_COMPANY").tips({
				side:3,
	            msg:'请输入联系人所属公司',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTACTS_COMPANY").focus();
			return false;
		}
		if($("#CONTACTS_MOBILEPHONE").val()==""){
			$("#CONTACTS_MOBILEPHONE").tips({
				side:3,
	            msg:'请输入联系人电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTACTS_MOBILEPHONE").focus();
			return false;
		}
		if($("#CONTACTS_ADDRESS").val()==""){
			$("#CONTACTS_ADDRESS").tips({
				side:3,
	            msg:'请输入联系人地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTACTS_ADDRESS").focus();
			return false;
		}
		if($("#CONTACTS_NOTE").val()==""){
			$("#CONTACTS_NOTE").tips({
				side:3,
	            msg:'请输入备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTACTS_NOTE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="contacts/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="CONTACTS_ID" id="CONTACTS_ID" value="${pd.CONTACTS_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系人姓名:</td>
				<td><input type="text" name="CONTACTS_NAME" id="CONTACTS_NAME" value="${pd.CONTACTS_NAME}" maxlength="32" placeholder="这里输入联系人姓名" title="联系人姓名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系人邮箱:</td>
				<td><input type="text" name="CONTACTS_EMAIL" id="CONTACTS_EMAIL" value="${pd.CONTACTS_EMAIL}" maxlength="32" placeholder="这里输入联系人邮箱" title="联系人邮箱"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系人所属公司:</td>
				<td><input type="text" name="CONTACTS_COMPANY" id="CONTACTS_COMPANY" value="${pd.CONTACTS_COMPANY}" maxlength="32" placeholder="这里输入联系人所属公司" title="联系人所属公司"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系人电话:</td>
				<td><input type="text" name="CONTACTS_MOBILEPHONE" id="CONTACTS_MOBILEPHONE" value="${pd.CONTACTS_MOBILEPHONE}" maxlength="32" placeholder="这里输入联系人电话" title="联系人电话"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系人地址:</td>
				<td><input type="text" name="CONTACTS_ADDRESS" id="CONTACTS_ADDRESS" value="${pd.CONTACTS_ADDRESS}" maxlength="32" placeholder="这里输入联系人地址" title="联系人地址"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">备注:</td>
				<td><input type="text" name="CONTACTS_NOTE" id="CONTACTS_NOTE" value="${pd.CONTACTS_NOTE}" maxlength="32" placeholder="这里输入备注" title="备注"/></td>
			</tr>
			<tr>
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