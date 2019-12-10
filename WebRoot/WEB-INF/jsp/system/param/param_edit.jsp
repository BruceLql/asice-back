<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" id="1530144391979-8656">

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

	<!-- 重写编辑页面 -->
	<link rel="stylesheet" href="static/css/diytheme-edit.css" />
	<!-- 日期框 -->
	<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

	<script type="text/javascript">


		//保存
		function save() {
			if ($("#BIZ_SYS_PROD_C001").val() == "") {
				$("#BIZ_SYS_PROD_C001").tips({
					side: 3,
					msg: '请输入排序号',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C001").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C002").val() == "") {
				$("#BIZ_SYS_PROD_C002").tips({
					side: 3,
					msg: '请输入业务类型编号',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C002").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C003").val() == "") {
				$("#BIZ_SYS_PROD_C003").tips({
					side: 3,
					msg: '请输入业务类型说明',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C003").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C004").val() == "") {
				$("#BIZ_SYS_PROD_C004").tips({
					side: 3,
					msg: '请输入数据源编号',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C004").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C005").val() == "") {
				$("#BIZ_SYS_PROD_C005").tips({
					side: 3,
					msg: '请输入数据源版本号',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C005").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C006").val() == "") {
				$("#BIZ_SYS_PROD_C006").tips({
					side: 3,
					msg: '请输入数据源名称',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C006").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C007").val() == "") {
				$("#BIZ_SYS_PROD_C007").tips({
					side: 3,
					msg: '请输入数据源代号',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C007").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C008").val() == "") {
				$("#BIZ_SYS_PROD_C008").tips({
					side: 3,
					msg: '请输入数据源说明',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C008").focus();
				return false;
			}
			if ($("#BIZ_SYS_PROD_C018").val() == "") {
				$("#BIZ_SYS_PROD_C018").tips({
					side: 3,
					msg: '请输入对接数据源公司',
					bg: '#AE81FF',
					time: 2
				});
				$("#BIZ_SYS_PROD_C018").focus();
				return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

	</script>
</head>

<body>
	<form action="param/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="PARAM_ID" id="PARAM_ID" value="${pd.PARAM_ID}" />
		<div id="zhongxin">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<tr>
					<td class="title-xinxi" colspan="2">
						<b>一、基本信息</b>
					</td>
				</tr>
				<tr>
					<td class="td-right">排序号:</td>
					<td>
						<input type="number" name="BIZ_SYS_PROD_C001" id="BIZ_SYS_PROD_C001" value="${pd.BIZ_SYS_PROD_C001}" maxlength="10" placeholder="这里输入排序号"
						 title="排序号" />
					</td>
				</tr>
				<tr>
					<td class="td-right">业务类型编号:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C002" id="BIZ_SYS_PROD_C002" value="${pd.BIZ_SYS_PROD_C002}" maxlength="255" placeholder="这里输入业务类型编号"
						 title="业务类型编号" />
					</td>
				</tr>
				<tr>
					<td class="td-right">业务类型说明:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C003" id="BIZ_SYS_PROD_C003" value="${pd.BIZ_SYS_PROD_C003}" maxlength="255" placeholder="这里输入业务类型说明"
						 title="业务类型说明" />
					</td>
				</tr>
				<tr>
					<td class="td-right">数据源编号:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C004" id="BIZ_SYS_PROD_C004" value="${pd.BIZ_SYS_PROD_C004}" maxlength="255" placeholder="这里输入数据源编号"
						 title="数据源编号" />
					</td>
				</tr>
				<tr>
					<td class="td-right">数据源版本号:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C005" id="BIZ_SYS_PROD_C005" value="${pd.BIZ_SYS_PROD_C005}" maxlength="255" placeholder="这里输入数据源版本号"
						 title="数据源版本号" />
					</td>
				</tr>
				<tr>
					<td class="td-right">数据源名称:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C006" id="BIZ_SYS_PROD_C006" value="${pd.BIZ_SYS_PROD_C006}" maxlength="255" placeholder="这里输入数据源名称"
						 title="数据源名称" />
					</td>
				</tr>
				<tr>
					<td class="td-right">数据源代号:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C007" id="BIZ_SYS_PROD_C007" value="${pd.BIZ_SYS_PROD_C007}" maxlength="255" placeholder="这里输入数据源代号"
						 title="数据源代号" />
					</td>
				</tr>

				<tr>
					<td class="td-right">数据源说明:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C008" id="BIZ_SYS_PROD_C008" value="${pd.BIZ_SYS_PROD_C008}" maxlength="255" placeholder="这里输入数据源说明"
						 title="数据源说明" />
						<%-- <textarea name="BIZ_SYS_PROD_C008" id="BIZ_SYS_PROD_C008" rows="2" placeholder="这里输入数据源说明" title="数据源说明">${pd.BIZ_SYS_PROD_C008}</textarea> --%>
					</td>
				</tr>
				<tr>
					<td class="td-right">对接数据源公司:</td>
					<td>
						<input type="text" name="BIZ_SYS_PROD_C018" id="BIZ_SYS_PROD_C018" value="${pd.BIZ_SYS_PROD_C018}" maxlength="255" placeholder="这里输入对接数据源公司"
						 title="对接数据源公司" />
					</td>
				</tr>
				<tr>
					<td class="td-right">备注:</td>
					<td>
						<textarea name="BIZ_SYS_PROD_C009" id="BIZ_SYS_PROD_C009" rows="4" placeholder="这里输入备注" title="备注">${pd.BIZ_SYS_PROD_C009}</textarea>
					</td>
				</tr>

				<tr>
					<td class="title-yaoshi" colspan="2">
						<b>二、地址和秘钥</b>
					</td>
				</tr>

				<tr>
					<td class="td-right">ip地址(path):</td>
					<td>
						<%-- <input type="text" name="BIZ_SYS_PROD_C010" id="BIZ_SYS_PROD_C010" value="${pd.BIZ_SYS_PROD_C010}" maxlength="255" placeholder="这里输入ip地址" title="ip地址(path)"/> --%>
							<textarea type="text" name="BIZ_SYS_PROD_C010" id="BIZ_SYS_PROD_C010" rows="2" placeholder="这里输入ip地址" title="ip地址(path)">${pd.BIZ_SYS_PROD_C010}</textarea>
					</td>
				</tr>
				<tr>
					<td class="td-right">秘钥(key):</td>
					<td>
						<%-- <input type="text" name="BIZ_SYS_PROD_C011" id="BIZ_SYS_PROD_C011" value="${pd.BIZ_SYS_PROD_C011}" maxlength="255" placeholder="这里输入秘钥" title="秘钥(access_key)"/> --%>
							<textarea type="text" name="BIZ_SYS_PROD_C011" id="BIZ_SYS_PROD_C011" rows="3" placeholder="这里输入秘钥" title="秘钥(key)">${pd.BIZ_SYS_PROD_C011}</textarea>
					</td>
				</tr>
				<tr>
					<td class="td-right">私钥(secret):</td>
					<td>
						<%-- <input type="text" name="BIZ_SYS_PROD_C012" id="BIZ_SYS_PROD_C012" value="${pd.BIZ_SYS_PROD_C012}" maxlength="255" placeholder="这里输入私钥" title="私钥(app_secret)"/> --%>
							<textarea type="text" name="BIZ_SYS_PROD_C012" id="BIZ_SYS_PROD_C012" rows="3" placeholder="这里输入私钥" title="私钥(secret)">${pd.BIZ_SYS_PROD_C012}</textarea>
					</td>
				</tr>
				<tr>
					<td class="td-right">资源目录(urlPath):</td>
					<td>
						<%-- <input type="text" name="BIZ_SYS_PROD_C013" id="BIZ_SYS_PROD_C013" value="${pd.BIZ_SYS_PROD_C013}" maxlength="255" placeholder="这里输入资源目录" title="资源目录(urlPath)"/> --%>
							<textarea type="text" name="BIZ_SYS_PROD_C013" id="BIZ_SYS_PROD_C013" rows="3" placeholder="这里输入资源目录" title="资源目录(urlPath)">${pd.BIZ_SYS_PROD_C013}</textarea>
					</td>
				</tr>
				<tr>
					<td class="td-right">AppId:</td>
					<td><input type="text" name="BIZ_SYS_PROD_C014" id="BIZ_SYS_PROD_C014" value="${pd.BIZ_SYS_PROD_C014}" maxlength="255" placeholder="这里输入AppId" title="AppId"/></td>
				</tr>
					<%-- <tr>
				<td class="td-right">外部RSA公钥:</td>
				<td><input type="text" name="BIZ_SYS_PROD_C015" id="BIZ_SYS_PROD_C015" value="${pd.BIZ_SYS_PROD_C015}" maxlength="255" placeholder="这里输入外部RSA公钥" title="外部RSA公钥"/></td>
			</tr> --%>
						<%-- <tr>
				<td class="td-right">外部请求JAVA类:</td>
				<td><input type="text" name="BIZ_SYS_PROD_C016" id="BIZ_SYS_PROD_C016" value="${pd.BIZ_SYS_PROD_C016}" maxlength="255" placeholder="这里输入外部请求JAVA类" title="外部请求JAVA类"/></td>
			</tr> --%>
							<%-- <tr>
				<td class="td-right">外部返回JAVA类:</td>
				<td><input type="text" name="BIZ_SYS_PROD_C017" id="BIZ_SYS_PROD_C017" value="${pd.BIZ_SYS_PROD_C017}" maxlength="255" placeholder="这里输入外部返回JAVA类" title="外部返回JAVA类"/></td>
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