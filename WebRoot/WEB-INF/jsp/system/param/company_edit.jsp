<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" id="1530144375557-2697">

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
	<link rel="stylesheet" href="static/css/datepicker.css" />
	<!-- 日期框 -->
	<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!-- 重写编辑页面 -->
	<link rel="stylesheet" href="static/css/diytheme-edit.css" />
	<script type="text/javascript">
		//保存
		function save() {
			if ($("#RISK_A_EXT_SYS_CP_ID").val() == "") {
				$("#RISK_A_EXT_SYS_CP_ID").tips({
					side: 3,
					msg: '请输入客户（公司编号）',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_CP_ID").focus();
				return false;
			}
			if ($("#RISK_A_EXT_SYS_CP_NAME").val() == "") {
				$("#RISK_A_EXT_SYS_CP_NAME").tips({
					side: 3,
					msg: '请输入客户（公司）',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_CP_NAME").focus();
				return false;
			}
			if ($("#RISK_A_EXT_SYS_CP_DEPID").val() == "") {
				$("#RISK_A_EXT_SYS_CP_DEPID").tips({
					side: 3,
					msg: '请输入公司下属部门编号',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_CP_DEP").focus();
				return false;
			}
			if ($("#RISK_A_EXT_SYS_CP_DEP").val() == "") {
				$("#RISK_A_EXT_SYS_CP_DEP").tips({
					side: 3,
					msg: '请输入公司下属部门',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_CP_DEP").focus();
				return false;
			}			
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	</script>
</head>

<body>
	<form action="company/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="COMPANY_ID" id="COMPANY_ID" value="${pd.COMPANY_ID}" />
		<div id="zhongxin">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<tr>
					<td>客户（公司编号）:</td>
					<td>
						<input type="text" name="RISK_A_EXT_SYS_CP_ID" id="RISK_A_EXT_SYS_CP_ID" value="${pd.RISK_A_EXT_SYS_CP_ID}" maxlength="32"
						 placeholder="默认A00开头" title="客户（公司id）" />
					</td>
				</tr>
				<tr>
					<td>客户（公司）名称:</td>
					<td>
						<input type="text" name="RISK_A_EXT_SYS_CP_NAME" id="RISK_A_EXT_SYS_CP_NAME" value="${pd.RISK_A_EXT_SYS_CP_NAME}" maxlength="32"
						 placeholder="这里输入客户（公司）" title="客户（公司）" />
					</td>
				</tr>
				<tr>
					<td>公司部门编号:</td>
					<td>
						<input type="text" name="RISK_A_EXT_SYS_CP_DEPID" id="RISK_A_EXT_SYS_CP_DEPID" value="${pd.RISK_A_EXT_SYS_CP_DEPID}"
						 maxlength="32" placeholder="默认A00X-01" title="客户（公司bm）" />
					</td>
				</tr>
				<tr>
					<td>公司部门名称:</td>
					<td>
						<input type="text" name="RISK_A_EXT_SYS_CP_DEP" id="RISK_A_EXT_SYS_CP_DEP" value="${pd.RISK_A_EXT_SYS_CP_DEP}" maxlength="32"
						 placeholder="这里输入客户部门" title="客户（公司bm）" />
					</td>
				</tr>
				<%-- 
			<tr>
				<td>部门下属人员ID:</td>
				<td><input type="text" name="RISK_A_EXT_SYS_CP_PERSONID" id="RISK_A_EXT_SYS_CP_PERSONID" value="${pd.RISK_A_EXT_SYS_CP_PERSONID}" maxlength="32" placeholder="这里输入部门下属人员ID" title="部门下属人员ID"/></td>
			</tr>
			<tr>
				<td>部门下属人员:</td>
				<td><input type="text" name="RISK_A_EXT_SYS_CP_PERSON" id="RISK_A_EXT_SYS_CP_PERSON" value="${pd.RISK_A_EXT_SYS_CP_PERSON}" maxlength="32" placeholder="这里输入部门下属人员" title="部门下属人员"/></td>
			</tr>
			<tr>
				<td>人员在职状态:</td>
				<td><input type="text" name="RISK_A_EXT_SYS_CP_STATE" id="RISK_A_EXT_SYS_CP_STATE" value="${pd.RISK_A_EXT_SYS_CP_STATE}" maxlength="32" placeholder="这里输入人员在职状态" title="人员在职状态"/></td>
			</tr> --%>
					<tr>
						<td class="td-botom" colspan="10">
							<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
						</td>
					</tr>
			</table>
		</div>

		<div id="zhongxin2" class="center" style="display:none">
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<img src="static/images/jiazai.gif" />
			<br/>
			<h4 class="lighter block green">提交中...</h4>
		</div>

	</form>
	<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript">
		$(top.hangge());
		$(function () {
			//单选框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({ allow_single_deselect: true });

			//日期框
			$('.date-picker').datepicker();
		});
	</script>
</body>

</html>