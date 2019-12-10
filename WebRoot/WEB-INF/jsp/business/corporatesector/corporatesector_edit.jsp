<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" id="1530143976782-5553">

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
			if ($("#mrc_name").val() == "") {
				$("#mrc_name").tips({
					side: 3,
					msg: '请输入名称',
					bg: '#AE81FF',
					time: 2
				});
				$("#mrc_name").focus();
				return false;
			}
			
			if ($("#mrc_type").val() == "") {
				$("#mrc_type").tips({
					side: 3,
					msg: '请选择类型',
					bg: '#AE81FF',
					time: 2
				});
				$("#mrc_type").focus();
				return false;
			}
			if ($("#mrc_state").val() == "") {
				$("#mrc_state").tips({
					side: 3,
					msg: '请选择状态',
					bg: '#AE81FF',
					time: 2
				});
				$("#mrc_state").focus();
				return false;
			}
			
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

		function sele(v) {
			
			if (v == '1') {//gs
				$("#mrc_pid").val("");
				$("#ssgs").hide();
			}
			if (v == '2') {//bm
				$("#ssgs").show();
				$("#mrc_pid").val("");
			}
		}
	</script>
</head>

<body>
	<form action="corporatesector/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="mrc_uuid" id="mrc_uuid" value="${pd.mrc_uuid}" />
		<div id="zhongxin">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<tr>
					<td>
						<c:if test="${pd.mrc_type=='1'}">公司</c:if>
						<c:if test="${pd.mrc_type=='2'}">部门</c:if>名称:</td>
					<td>
						<input type="text" name="mrc_name" id="mrc_name" value="${pd.mrc_name}" maxlength="32" placeholder="这里输入名称" title="名称"
						/>
					</td>
				</tr>
				<tr>
					<td>
						<c:if test="${pd.mrc_type=='1'}">公司</c:if>
						<c:if test="${pd.mrc_type=='2'}">部门</c:if>编号:</td>
					<td>
						<input type="text" name="mrc_code" id="mrc_code" value="${pd.mrc_code}" maxlength="32" placeholder="这里输入编号" title="编号"
						/>
					</td>
				</tr>
				<tr <c:if test="${msg=='edit'}">style="display:none;"</c:if>>
					<td>类型:</td>
					<td style="vertical-align:top;">
						<select class="chzn-select" id="mrc_type" onchange="sele(this.value)" name="mrc_type" style="vertical-align:top;width:220px;">
							<option value="1" <c:if test="${pd.mrc_type=='1'}">selected="selected"</c:if>>
								<font color="">公司</font>
							</option>
							<option value="2" <c:if test="${pd.mrc_type=='2'}">selected="selected"</c:if>>
								<font color="">部门</font>
							</option>
						</select>
					</td>
				</tr>
				<tr id="ssgs" <c:if test="${pd.mrc_type!='2' || msg=='edit'}">style="display:none;"</c:if>>
					<td>所属公司</td>
					<td style="vertical-align:top;">
						<select class="chzn-select" name="mrc_pid" id="mrc_pid" data-placeholder="请选择所属公司" style="vertical-align:top;width:205px;">
							<c:forEach items="${list_gs}" var="g" varStatus="vs">
								<option value="${g.mrc_uuid}" <c:if test="${g.mrc_uuid==pd.mrc_pid}">selected="selected"</c:if>>${g.mrc_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td>状态:</td>
					<td style="vertical-align:top;">
						<select class="chzn-select" name="mrc_state" id="mrc_state" style="vertical-align:top;width:220px;">
							<option value="enable" <c:if test="${pd.mrc_state=='enable'}">selected="selected"</c:if>>
								<font color="">可用</font>
							</option>
							<option value="disable" <c:if test="${pd.mrc_state=='disable'}">selected="selected"</c:if>>
								<font color="">禁用</font>
							</option>
						</select>
					</td>
				</tr>
				<%-- <tr>
				<td>级别:</td>
				<td><input type="number" name="mrc_jb" id="mrc_jb" value="${pd.mrc_jb}" maxlength="32" placeholder="这里输入级别" title="级别"/></td>
			</tr> --%>
					<%-- <tr>
				<td>排序号:</td>
				<td><input type="number" name="mrc_order" id="mrc_order" value="${pd.mrc_order}" maxlength="32" placeholder="这里输入排序号" title="排序号"/></td>
			</tr> --%>
						<tr>
							<td>备注说明:</td>
							<td>
								<input type="text" name="mrc_bz" id="mrc_bz" value="${pd.mrc_bz}" maxlength="32" placeholder="这里输入备注说明" title="备注说明"
								/>
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