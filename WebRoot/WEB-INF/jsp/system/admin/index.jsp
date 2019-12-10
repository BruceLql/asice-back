<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en" id="1530144102543-1390">

<head>
	<base href="<%=basePath%>">
	<!-- 青亭logo -->
	<link rel="shortcut icon" href="static/login/favicon.ico">
	<!-- jsp文件头和头部 -->
	<%@ include file="top.jsp"%>
		<style type="text/css">
		
			.commitopacity{position:absolute;width:100%;height:100px;background:#7f7f7f;filter:alpha(opacity=50);-moz-opacity:.8;-khtml-opacity:.5;opacity:.5;top:0;z-index:99999}
			#_DialogDiv_0,.overflow-yichu{overflow:auto;-webkit-overflow-scrolling:touch}
			.spinner{height:60px;text-align:center;font-size:10px;position:absolute;top:0;left:0;right:0;bottom:0;margin:auto}
			.spinner>div{background-color:#3ea7fd;height:100%;width:6px;display:inline-block;-webkit-animation:stretchdelay 1.2s infinite ease-in-out;animation:stretchdelay 1.2s infinite ease-in-out}
			.spinner .rect2{-webkit-animation-delay:-1.1s;animation-delay:-1.1s}
			.spinner .rect3{-webkit-animation-delay:-1s;animation-delay:-1s}
			.spinner .rect4{-webkit-animation-delay:-.9s;animation-delay:-.9s}
			.spinner .rect5{-webkit-animation-delay:-.8s;animation-delay:-.8s}
			@-webkit-keyframes stretchdelay{0%,100%,40%{-webkit-transform:scaleY(.4)}
			20%{-webkit-transform:scaleY(1)}
			}
			@keyframes stretchdelay{0%,100%,40%{transform:scaleY(.4);-webkit-transform:scaleY(.4)}
			20%{transform:scaleY(1);-webkit-transform:scaleY(1)}
			}
			
		</style>

		<!-- 即时通讯 -->
		<script type="text/javascript">var wimadress = "${pd.WIMIP}:${pd.WIMPORT}";</script>
		<script type="text/javascript">var oladress = "${pd.OLIP}:${pd.OLPORT}";</script>
		<link rel="stylesheet" type="text/css" href="plugins/websocketInstantMsg/ext4/resources/css/ext-all.css">
		<link rel="stylesheet" type="text/css" href="plugins/websocketInstantMsg/css/websocket.css" />
		<script type="text/javascript" src="plugins/websocketInstantMsg/ext4/ext-all-debug.js"></script>
		<script type="text/javascript" src="plugins/websocketInstantMsg/websocket.js"></script>
		<!-- 即时通讯 -->

</head>

<body>

	<!-- 页面顶部¨ -->
	<%@ include file="head.jsp"%>
		<div id="websocket_button"></div>
		<div class="container-fluid" id="main-container">
			<a href="#" id="menu-toggler"></a>
			<!-- menu toggler -->

			<!-- 左侧菜单 -->
			<%@ include file="left.jsp"%>

				<div id="main-content" class="clearfix">

					<!-- <div id="jzts" style="display:none; width:100%; position:fixed; z-index:99999999;">
						<div class="commitopacity" id="bkbgjz"></div>
						<div style="padding-left: 70%;padding-top: 1px;">
							<div style="float: left;margin-top: 3px;">
								<img src="static/images/loadingi.gif" /> </div>
							<div style="margin-top: 5px;">
								<h4 class="lighter block red">&nbsp;加载中 ...</h4>
							</div>
						</div>
					</div> -->
					<div id="jzts" style="display: none;width: 100%; height: 100%;  position: fixed; z-index: 100000;">
						<div style="background-color: #fff;" class="commitopacity" id="bkbgjz">
							<div class="spinner">
								<div class="rect1"></div>
								<div class="rect2"></div>
								<div class="rect3"></div>
								<div class="rect4"></div>
								<div class="rect5"></div>
							</div>
						</div>
					</div>
					<div>
						<iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe>
					</div>

					<!-- 换肤 -->
					<%-- 		<div id="ace-settings-container">
				<div class="btn btn-app btn-mini btn-warning" id="ace-settings-btn">
					<i class="icon-cog"></i>
				</div>
				<div id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hidden">
								<option data-class="default" value="#438EB9"
									<c:if test="${user.SKIN =='default' }">selected</c:if>>#438EB9</option>
								<option data-class="skin-1" value="#222A2D"
									<c:if test="${user.SKIN =='skin-1' }">selected</c:if>>#222A2D</option>
								<option data-class="skin-2" value="#C6487E"
									<c:if test="${user.SKIN =='skin-2' }">selected</c:if>>#C6487E</option>
								<option data-class="skin-3" value="#D0D0D0"
									<c:if test="${user.SKIN =='skin-3' }">selected</c:if>>#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; 选择皮肤</span>
					</div>
					<div>
						<label><input type='checkbox' name='menusf' id="menusf"
							onclick="menusf();" /><span class="lbl" style="padding-top: 5px;">菜单缩放</span></label>
					</div>
				</div>
			</div> --%>
						<!--/#ace-settings-container-->

				</div>
				<!-- #main-content -->
		</div>
		<!--/.fluid-container#main-container-->
		<!-- basic scripts -->
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<!-- 引入 -->

		<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="static/js/myjs/menusf.js"></script>

		<!--引入属于此页面的js -->
		<script type="text/javascript" src="static/js/myjs/index.js"></script>
		
</body>

</html>