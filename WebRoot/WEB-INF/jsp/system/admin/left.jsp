<%
	String pathl = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathl+"/";
%>
	<style>
		/* 取消菜单子级小横线 */ .iconfont { font-size: 18px; } .nav-list>li>.submenu>li:before { /* content: none; */ } /* 取消竖线 */ .nav-list>li>.submenu>li:after { /* content: none; */ } /* 取消点击菜单子级选项弹出右边的蓝色线条 */ .nav-list>li.active:after { /* content: none; */ } /* 隐藏白底黑色右箭头 */ .icon-double-angle-right { /* display: none !important; */ } .nav-list>li>.submenu>li>a:hover { background-color: #2286d9; color: #fff; } .menu-min .nav-list>li>a>span { color: rgb(92, 91, 91); } .menu-min .nav-list>li>a>span:hover { background-color: #2286d9; color: #ffffff; } /* 取消虚线 */ .nav-list>li>.submenu>li:before { content: none; } .nav-list>li>.submenu>li:after { content: none; } /* 取消点击子选项实线 */ .nav-list>li.active:after { content: none; }
	</style>
	<!-- 本页面涉及的js函数，都在head.jsp页面中     -->
	<div id="sidebar" class="menu-min">

		<div id="sidebar-shortcuts">

			<div id="sidebar-shortcuts-large">
				<!-- 
						<button class="btn btn-small btn-success" onclick="changeMenu();" title="切换菜单"><i class="icon-pencil"></i></button>

						<button class="btn btn-small btn-info" title="UI实例" onclick="window.open('<%=basePathl%>static/UI_new');"><i class="icon-eye-open"></i></button>
 						-->
				<button class="btn btn-small btn-warning" title="数据字典" id="adminzidian" onclick="zidian();">
					<i class="icon-book"></i>
				</button>

				<button class="btn btn-small btn-danger" title="菜单管理" id="adminmenu" onclick="menu();">
					<i class="icon-folder-open"></i>
				</button>

			</div>

			<div id="sidebar-shortcuts-mini">
				<span class="btn btn-success"></span>

				<span class="btn btn-info"></span>

				<span class="btn btn-warning"></span>

				<span class="btn btn-danger"></span>
			</div>

		</div>
		<!-- #sidebar-shortcuts -->


		<ul class="nav nav-list">

			<li class="active" id="mbfwindex">
				<a href="main/index">
					<i class="icon-dashboard"></i>
					<span>系统首页</span>
				</a>
			</li>



			<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.hasMenu}">
					<li id="lm${menu.MENU_ID }">
						<a style="cursor:pointer;" class="dropdown-toggle">
							<i class="${menu.MENU_ICON == null ? 'icon-desktop' : menu.MENU_ICON}"></i>
							<span>${menu.MENU_NAME }</span>
							<b class="arrow icon-angle-down"></b>
						</a>
						<ul class="submenu">
							<c:forEach items="${menu.subMenu}" var="sub">
								<c:if test="${sub.hasMenu}">
									<c:choose>
										<c:when test="${not empty sub.MENU_URL}">
											<li id="z${sub.MENU_ID }">
												<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z${sub.MENU_ID }','lm${menu.MENU_ID }','${sub.MENU_NAME }','${sub.MENU_URL }')">
													<!-- <i class="icon-double-angle-right"></i>${sub.MENU_NAME }</a> -->
													<i style="background-color: transparent; color: #fff" class="icon-arrow-right iconfont"></i>
													${sub.MENU_NAME }</a>

											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="javascript:void(0);">
													<!-- <i class="icon-double-angle-right"></i>${sub.MENU_NAME }</a> -->
													<i style="background-color: transparent; color: #fff" class="icon-arrow-right iconfont"></i>
													${sub.MENU_NAME }</a>

											</li>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</ul>
					</li>
				</c:if>
			</c:forEach>

		</ul>
		<!--/.nav-list-->

		<div id="sidebar-collapse">
			<i class="icon-double-angle-left"></i>
		</div>

	</div>
	<!--/#sidebar-->