<!-- <script>
	// 公告开关
	var numb;
	function Notive() {
		if (numb == true) {
			$('#Notice').hide();
			numb = false;
		}
		else {
			$('#Notice').show();
			numb = true;
		}
	}
</script> -->
<style>
	.navbar-inverse .navbar-inner {
		background-image: linear-gradient(164deg, #0152b0 0%, #2387da 100%);
		background-image: -webkit-linear-gradient(164deg, #0152b0 0%, #2387da 100%);
		background-image: -moz-linear-gradient(164deg, #0152b0 0%, #2387da 100%);
		background-image: -o-linear-gradient(164deg, #0152b0 0%, #2387da 100%);
		/* opacity: 0.9; */
	}

	.notive {
		width: 80%;
	}

	/* 弹出层品偏移下移 */

	#user_menu {
		top: 45px;
	}

	.brand {}
	
	@media (max-width: 600px) {
		small,
		.brand {
			font-size: 100%;
		}
		.navbar-inverse .brand {
			text-align: center;
			font-size: 16px !important;
		}
	}

	/* 取消iphone5屏幕小用户头像显示异常 */

	@media (min-width:420px) and (max-width: 979px) {
		.brand {
			padding-left: 10% !important;
		}
	}

	/* 解决在小于480px情况的设备出现 弹出层品偏移 */

	@media only screen and (max-width: 767px) {
		.ace-nav>li:nth-child(1)>.dropdown-menu.pull-right {
			right: 0;
		}
		.ace-nav>li:nth-child(1)>.dropdown-menu.pull-right:before,
		.ace-nav>li:nth-child(1)>.dropdown-menu.pull-right:after {
			right: .7rem;
		}

	}

	/*  头部-用户栏 */

	@media only screen and (max-width: 422px) {
		.ace-nav {
			background-color: transparent;
		}
	}
</style>
<div class="navbar navbar-inverse">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="brand">
				<small>
					<!-- <i class="icon-leaf"></i>  -->
					<!-- <img src="static/img/favicon.ico" width="21" height="21"> -->&nbsp;${pd.SYSNAME}</small>
					<!-- <img src="static/login/favicon5.ico" width="21" height="21">&nbsp;citydo城市大数据运营</small> -->
			</a>
			<!-- <div class="notive">
				<marquee id="Notice">系统已维护-请立即停止使用</marquee>
				<span id="open" onclick="Notive()">开关</span>
			</div> -->
			<ul class="nav ace-nav pull-right">
				<!-- 隐藏这个按钮  -->
				<!-- <li class="purple" onclick="creatw();">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-bell-alt icon-animated-bell icon-only"></i>
						</a>
					</li> -->
				<li style="background: none; border: 0; position: relative;" class="light-blue user-profile">
					<a class="user-menu dropdown-toggle" href="javascript:;" data-toggle="dropdown">
						<img alt="" src="static/avatars/user.jpg" class="nav-user-photo" />
						<span id="user_info">
						</span>
						<i class="icon-caret-down"></i>
					</a>
					<ul id="user_menu" class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
						<li>
							<a onclick="editUserH();" style="cursor:pointer;">
								<i class="icon-user"></i> 修改资料</a>
						</li>
						<li id="systemset">
							<a onclick="editSys();" style="cursor:pointer;">
								<i class="icon-cog"></i> 系统设置</a>
						</li>
						<li id="productCode">
							<a onclick="productCode();" style="cursor:pointer;">
								<i class="icon-cogs"></i> 代码生成</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="logout">
								<i class="icon-off"></i> 退出</a>
						</li>
					</ul>
				</li>
			</ul>
			<!--/.ace-nav-->
		</div>
		<!--/.container-fluid-->
	</div>
	<!--/.navbar-inner-->
</div>
<!--/.navbar-->
<!--引入属于此页面的js -->
<script type="text/javascript" src="static/js/myjs/head.js"></script>