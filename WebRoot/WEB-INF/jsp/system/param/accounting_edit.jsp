<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" id="1530144353014-1849">

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
	<!-- 重写编辑页面 -->
	<link rel="stylesheet" href="static/css/diytheme-edit.css" />
	<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

	<script type="text/javascript">


		//保存
		function save() {
			if ($("#RISK_A_EXT_SYS_PARAM_ID").val() == "") {
				$("#RISK_A_EXT_SYS_PARAM_ID").tips({
					side: 3,
					msg: '请输入对应数据源',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_PARAM_ID").focus();
				return false;
			}
			if ($("#RISK_A_EXT_SYS_CURRENTUSERID").val() == "") {
				$("#RISK_A_EXT_SYS_CURRENTUSERID").tips({
					side: 3,
					msg: '请输入当前操作用户id',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_CURRENTUSERID").focus();
				return false;
			}
			
			if ($("#RISK_A_EXT_SYS_PRICE").val() == "") {
				$("#RISK_A_EXT_SYS_PRICE").tips({
					side: 3,
					msg: '请输入原价',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_PRICE").focus();
				return false;
			}
			if ($("#RISK_A_EXT_SYS_DISCOUNT").val() == "") {
				$("#RISK_A_EXT_SYS_DISCOUNT").tips({
					side: 3,
					msg: '请输入折扣',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_DISCOUNT").focus();
				return false;
			}
			if ($("#RISK_A_EXT_SYS_FINALPRICE").val() == "") {
				$("#RISK_A_EXT_SYS_FINALPRICE").tips({
					side: 3,
					msg: '请输入成交价',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_FINALPRICE").focus();
				return false;
			}
			if ($("#RISK_A_EXT_SYS_TYPE").val() == "") {
				$("#RISK_A_EXT_SYS_TYPE").tips({
					side: 3,
					msg: '请输入收费类型',
					bg: '#AE81FF',
					time: 2
				});
				$("#RISK_A_EXT_SYS_TYPE").focus();
				return false;
			}
			
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

	</script>
</head>

<body>
	<form action="accounting/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ACCOUNTING_ID" id="ACCOUNTING_ID" value="${pd.ACCOUNTING_ID}" />
		<div id="zhongxin">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<tr>
					<td>对应数据源:</td>
					<td>
						<input type="text" name="RISK_A_EXT_SYS_PARAM_ID" id="RISK_A_EXT_SYS_PARAM_ID" value="${pd.RISK_A_EXT_SYS_PARAM_ID}"
						 maxlength="32" placeholder="这里输入对应数据源" title="对应数据源" />
					</td>
				</tr>
				<tr>
					<td>当前操作用户id:</td>
					<td>
						<input type="text" name="RISK_A_EXT_SYS_CURRENTUSERID" id="RISK_A_EXT_SYS_CURRENTUSERID" value="${pd.RISK_A_EXT_SYS_CURRENTUSERID}"
						 maxlength="32" placeholder="这里输入当前操作用户id" title="当前操作用户id" />
					</td>
				</tr>
				<tr>
					<td>收费类型:</td>
					<td>
						<select name="RISK_A_EXT_SYS_TYPE" id="RISK_A_EXT_SYS_TYPE">
							<option value="0">不收费</option>
							<option value="1">按条收费</option>
							<option value="2">按天收费</option>
							<option value="3">按月收费</option>
							<option value="4">按季收费</option>
							<option value="5">按年收费</option>
						</select>
					</td>
				</tr>
				<%-- 		<tr>
				<td>成本原价:</td>
				<td><input type="text" name="RISK_A_EXT_SYS_INTER_PRICE" id="RISK_A_EXT_SYS_INTER_PRICE" value="${pd.RISK_A_EXT_SYS_INTER_PRICE}" maxlength="32" placeholder="这里输入成本原价" title="成本原价"/></td>
			</tr>
			<tr>
				<td>成本系数（折扣）:</td>
				<td><input type="text" name="RISK_A_EXT_SYS_INTER_DISCOUNT" id="RISK_A_EXT_SYS_INTER_DISCOUNT" value="${pd.RISK_A_EXT_SYS_INTER_DISCOUNT}" maxlength="32" placeholder="这里输入成本系数（折扣）" title="成本系数（折扣）"/></td>
			</tr>
			<tr>
				<td>内部成交价:</td>
				<td><input type="text" name="RISK_A_EXT_SYS_INTER_FINALPRICE" id="RISK_A_EXT_SYS_INTER_FINALPRICE" value="${pd.RISK_A_EXT_SYS_INTER_FINALPRICE}" maxlength="32" placeholder="这里输入内部成交价" title="内部成交价"/></td>
			</tr> --%>
					<tr>
						<td>原价:</td>
						<td>
							<input type="text" name="RISK_A_EXT_SYS_PRICE" id="RISK_A_EXT_SYS_PRICE" value="${pd.RISK_A_EXT_SYS_PRICE}" maxlength="32"
							 placeholder="这里输入原价" title="原价" />
						</td>
					</tr>
					<tr>
						<td>折扣:</td>
						<td>
							<input type="text" name="RISK_A_EXT_SYS_DISCOUNT" id="RISK_A_EXT_SYS_DISCOUNT" value="${pd.RISK_A_EXT_SYS_DISCOUNT}"
							 maxlength="32" placeholder="这里输入折扣" title="折扣" />
						</td>
					</tr>
					<tr>
						<td>成交价:</td>
						<td>
							<input type="text" name="RISK_A_EXT_SYS_FINALPRICE" id="RISK_A_EXT_SYS_FINALPRICE" value="${pd.RISK_A_EXT_SYS_FINALPRICE}"
							 maxlength="32" placeholder="这里输入成交价" title="成交价" />
						</td>
					</tr>


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