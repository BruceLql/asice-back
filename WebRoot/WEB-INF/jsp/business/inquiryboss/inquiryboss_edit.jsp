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
			if($("#INQUIRY_BOSS_COMPAN").val()==""){
			$("#INQUIRY_BOSS_COMPAN").tips({
				side:3,
	            msg:'请输入公司名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INQUIRY_BOSS_COMPAN").focus();
			return false;
		}
		if($("#INQUIRY_BOSS").val()==""){
			$("#INQUIRY_BOSS").tips({
				side:3,
	            msg:'请输入联系人名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INQUIRY_BOSS").focus();
			return false;
		}
		if($("#INQUIRY_BOSS_TELEPHONE").val()==""){
			$("#INQUIRY_BOSS_TELEPHONE").tips({
				side:3,
	            msg:'请输入联系电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INQUIRY_BOSS_TELEPHONE").focus();
			return false;
		}
		if($("#INQUIRY_BOSS_QQ").val()==""){
			$("#INQUIRY_BOSS_QQ").tips({
				side:3,
	            msg:'请输入联系邮箱',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INQUIRY_BOSS_QQ").focus();
			return false;
		}
		if($("#INQUIRY_BOSS_WECHAT").val()==""){
			$("#INQUIRY_BOSS_WECHAT").tips({
				side:3,
	            msg:'请输入微信号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INQUIRY_BOSS_WECHAT").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="inquiryboss/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="INQUIRY_BOSS_ID" id="INQUIRY_BOSS_ID" value="${pd.INQUIRY_BOSS_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">公司名称:</td>
				<td><input type="text" name="INQUIRY_BOSS_COMPAN" id="INQUIRY_BOSS_COMPAN" value="${pd.INQUIRY_BOSS_COMPAN}" maxlength="32" placeholder="这里输入公司名称" title="公司名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系人名称:</td>
				<td><input type="text" name="INQUIRY_BOSS" id="INQUIRY_BOSS" value="${pd.INQUIRY_BOSS}" maxlength="32" placeholder="这里输入联系人名称" title="联系人名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系电话:</td>
				<td><input type="text" name="INQUIRY_BOSS_TELEPHONE" id="INQUIRY_BOSS_TELEPHONE" value="${pd.INQUIRY_BOSS_TELEPHONE}" maxlength="32" placeholder="这里输入联系电话" title="联系电话"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系邮箱:</td>
				<td><input type="text" name="INQUIRY_BOSS_QQ" id="INQUIRY_BOSS_QQ" value="${pd.INQUIRY_BOSS_QQ}" maxlength="32" placeholder="这里输入联系邮箱" title="联系邮箱"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信号:</td>
				<td><input type="text" name="INQUIRY_BOSS_WECHAT" id="INQUIRY_BOSS_WECHAT" value="${pd.INQUIRY_BOSS_WECHAT}" maxlength="32" placeholder="这里输入微信号" title="微信号"/></td>
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