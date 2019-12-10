<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" id="1530144111536-6769">

<head>
	<title>${pd.SYSNAME}</title>
	<meta charset="UTF-8" />
	<!-- 青亭logo -->
	<link rel="shortcut icon" href="./favicon.ico">
	<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"
	/>
	<meta name="format-detection" content="telephone=no, email=no">
	<link rel="stylesheet" href="static/login/css/login.css">

	<!-- 加载动画 -->
	<link rel="stylesheet" href="static/login/css/loading.css">
	<script type="text/javascript" src="static/js/jquery-1.5.1.min.js"></script>
</head>

<body>
	<div class="login">
		<div class="login_title">
			<p>${pd.SYSNAME}</p>
		</div>
		<div class="login_main">
			<div class="main_left"></div>
			<div class="main_right">
				<div class="right_title">用户登录</div>
				<form action="">
					<div class="username">
						<img src="static/login/images/username.png" alt="">
						<input class="item" name="loginname" id="loginname" type="text" placeholder="请输入用户名">
					</div>
					<div class="password">
						<img src="static/login/images/passwordlogin.png" alt="">
						<input class="item" name="password" id="password"   type="password"  maxlength="18" placeholder="请输入密码">
					</div>
					<div class="code">
						<input class="checbox" type="checkbox" id="saveid" onclick="savePaw();">
						<label  for="saveid">记住密码</label>
					</div>
					<div class="yes_login"><input id="to-recover" onclick="severCheck();"  type="button" value="登录"></div>
				</form>
			</div>
		</div>
		<div class="login_footer">
			<!--<p class="name">北京XXXX信息科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;京ICP：京B2-20170028</p>-->
			<!--<p>联系电话：18588888888&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;010-2888888</p>-->
			<%--<p>Copyright © 浙江XXX公司</p>--%>
		</div>
	</div>
	
<script>
	var loading='<div class="loading"><div class="spinner"><div class="rect1"></div>&nbsp;<div class="rect2"></div>&nbsp;<div class="rect3"></div>&nbsp;<div class="rect4"></div>&nbsp;<div class="rect5"></div>&nbsp;</div></div>';
	$('body').append(loading);
	document.onreadystatechange=function(){
		if(document.readyState=="complete"){
			$('.loading').fadeOut();
		}
	}
	
</script>
<script src="static/login/js/login.js"></script>
<script>
	//TOCMAT重启之后 点击左侧列表跳转登录首页 
	if (window != top) {
		top.location.href = location.href;
	}
</script>
<!-- <script src="static/js/bootstrap.min.js"></script> -->
<script src="static/js/jquery-1.7.2.js"></script>
<!-- <script src="static/login/js/jquery.easing.1.3.js"></script>
	<script src="static/login/js/jquery.mobile.customized.min.js"></script>
	<script src="static/login/js/camera.min.js"></script> -->
<!-- <script src="static/login/js/templatemo_script.js"></script> -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>


</body>

</html>