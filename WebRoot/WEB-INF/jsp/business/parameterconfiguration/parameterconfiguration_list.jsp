<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" id="1530144008001-7648">
	<head>
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 
	<link rel="stylesheet" href="static/css/table-problem/table-problem.css" />
	</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="parameterconfiguration/list.do" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="NAME" value="${pd.NAME}" placeholder="这里输入名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td><input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:120px;" placeholder="开始日期"/></td>
					<td><input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:120px;" placeholder="结束日期"/></td>
					<td > 
					 	<select  name="TYPE_NAME" id="TYPE_NAME" data-placeholder="请选择类型" style="vertical-align:top;width: 120px;">
							<option value="" disabled selected style="display:none;">请选择类型</option>
							<option value="">全部</option>
							<c:forEach items="${typesList}" var="ta" varStatus="vs">
								<option value="${ta.TYPE_NAME}" <c:if test="${ta.TYPE_NAME==pd.TYPE_NAME}">selected</c:if> >${ta.TYPE_NAME}</option>
							</c:forEach>
					  	</select>
					</td>
					<td ><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center">序号</th>
						<th class="center">名称</th>
						<!-- <th class="center">nick1</th> -->
						<th class="center">值1（KEY1）</th>
						<th class="center">值2（KEY2）</th>
						<th class="center">值3（KEY3）</th>
						<!-- <th class="center">值4</th> -->
						<!-- <th class="center">值5</th> -->
						<!-- <th class="center">值6</th> -->
						<!-- <th class="center">NICK2</th> -->
						<!-- <th class="center">NICK3</th> -->
						<!-- <th class="center">类型</th> -->
						<th class="center">类型名称</th>
						<th class="center">备注</th>
						<!-- <th class="center">创建时间</th> -->
						<!--  <th class="center">识别码（UUID）</th>  -->
						<th class="center">修改时间</th>
						<!-- <th class="center">msp_001</th>
						<th class="center">msp_002</th>
						<th class="center">msp_003</th>
						<th class="center">msp_004</th>
						<th class="center">msp_005</th>
						<th class="center">msp_006</th>
						<th class="center">msp_007</th>
						<th class="center">msp_008</th>
						<th class="center">msp_009</th>
						<th class="center">msp_010</th> -->
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == '1' }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.UUID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td style="width:95px;" class="center">${var.NAME}</td>
										<%-- <td>${var.NICK1}</td> --%>
										<td  class="center">${var.KEY1}</td>
										<td  class="center">${var.KEY2}</td>
										<td  class="center">${var.KEY3}</td>
										<%-- <td>${var.KEY4}</td> --%>
										<%-- <td>${var.KEY5}</td> --%>
										<%-- <td>${var.KEY6}</td> --%>
										<%-- <td>${var.NICK2}</td> --%>
										<%-- <td>${var.NICK3}</td> --%>
										<%-- <td>${var.TYPE}</td> --%>
										<td class="center">${var.TYPE_NAME}</td>
										<td style="width:370px;" class="left">${var.BZ}</td>
										<%-- <td>${var.CDATE}</td> --%>
										 <%-- <td class="center">${var.UUID}</td>  --%>
										 <td class="center">${var.UDATE}</td> 
										<%-- <td>${var.MSP_001}</td>
										<td>${var.MSP_002}</td>
										<td>${var.MSP_003}</td>
										<td>${var.MSP_004}</td>
										<td>${var.MSP_005}</td>
										<td>${var.MSP_006}</td>
										<td>${var.MSP_007}</td>
										<td>${var.MSP_008}</td>
										<td>${var.MSP_009}</td>
										<td>${var.MSP_010}</td> --%>
								<td style="" class="center">
									<div class='hidden-phone btn-group'>
										<a class='btn btn-mini btn-info' title="编辑" onclick="edit('${var.UUID}');"><i class='icon-edit'></i></a>
										<%-- <a class='btn btn-mini btn-danger' title="删除"  onclick="del('${var.UUID}');"><i class='icon-trash'></i></a> --%>
									</div>
								</td>
							</tr>
						
						</c:forEach>
						</c:if>
						<%-- <c:if test="${QX.cha == '0' }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if> --%>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
			
		<div id="bootom-div" style=" margin: 0;" class="page-header position-relative">
		
					<c:if test="${QX.add == '1' }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if>
					<%-- <c:if test="${QX.del == 1 }">
						<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
					</c:if> --%>
					<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
		
		
		
		<%-- <table style="width:1000px;">
			<tr>
				<td >
					<c:if test="${QX.add == '1' }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if>
					<c:if test="${QX.del == 1 }">
					<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
					</c:if>
				</td>
				<td ><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table> --%>
		</div>
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>parameterconfiguration/goAdd.do';
			 diag.Width = 380;
			 diag.Height = 475;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>parameterconfiguration/delete.do?UUID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>parameterconfiguration/goEdit.do?UUID='+Id;
			 diag.Width = 380;
			 diag.Height = 475;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>parameterconfiguration/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>parameterconfiguration/excel.do';
		}
		</script>
		
	</body>
</html>

