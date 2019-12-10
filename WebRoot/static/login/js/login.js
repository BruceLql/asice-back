
// 用户名事件
function empty_input() {
	var user_input =document.getElementById('loginname');
	if (user_input.value != "") {
		$('#messagesss').css({"margin-top":"-30px"});
		$('#messagesss').css({ "display": "block" });
		$('#un_del_1').css({ "display": "block" });
		
		return false;
	}
	else if(user_input.value == ""){
		$('#un_del_1').css({ "display": "none" });
		$('#messagesss').css({"margin-top":"10px"});
		return false;
	}
	
}
// 键盘密码事件
function empty_password() {
	var password =document.getElementById('password');
	if (password.value != "") {
		$('#un_del_2').css({ "display": "block" });
		$('#messagesss_pass').css({ "display": "block" });
		$('#messagesss_pass').css({"margin-top":"-30px"});
		return false;
	}
	
	else if(password.value == ""){
		$('#un_del_2').css({ "display": "none" });
		$('#messagesss_pass').css({ "display": "block" });
		$('#messagesss_pass').css({"margin-top":"10px"});
		$('#messagesss_pass').text("密码不能为空");
		return false;
	}
	
}
// 用户名清空

// 密码清空
function clear_passw() {
	$('#password').val('');
	$('#password').focus();
	$('#un_del_2').css({ "display": "none" });
}

//客户端校验
function check() {

	if ($("#loginname").val() == "") {

		$("#loginname").tips({
			side: 1,
			msg: '用户名不得为空',
			bg: '#0064c8',
			time: 3
		});

		$("#loginname").focus();
		return false;
	}



	if ($("#password").val() == "") {

		$("#password").tips({
			side: 1,
			msg: '密码不得为空',
			bg: '#0064c8',
			time: 3
		});
		


		$("#password").focus();
		return false;
	}



	if ($("#code").val() == "") {

		$("#code").tips({
			side: 1,
			msg: '验证码不得为空',
			bg: '#AE81FF',
			time: 3
		});

		$("#code").focus();
		return false;
	}

	$("#loginbox").tips({
		side: 1,
		msg: '正在登录 , 请稍后 ...',
		bg: '#68B500',
		time: 10
	});

	return true;
}


//服务器校验
function severCheck() {
	
	if (check()) {
		
		var loginname = $("#loginname").val();
		var password = $("#password").val();
		var code = "qq123456789mbfw" + loginname + ",mbfw," + password + "QQ123456789mbfw" + ",mbfw," + $("#code").val();
		$.ajax({
			type: "POST",
			url: 'login_login',
			data: {
				KEYDATA: code,
				tm: new Date().getTime()
			},
			dataType: 'json',
			cache: false,
			success: function (data) {
				if ("success" == data.result) {
					saveCookie();
					$('body').append(loading);
					window.location.href = "main/index";
				} else if ("usererror" == data.result) {
					$("#loginname").tips({
						side: 1,
						msg: "用户名或密码有误",
						bg: '#0064c8',
						time: 2
					});
					/* $('#messagesss_pass').text("用户名或密码有误");
					$('#messagesss_pass').css({"margin-top":"10px"}); */
					$("#password").focus();
				} else if ("codeerror" == data.result) {
					$("#code").tips({
						side: 1,
						msg: "验证码输入有误",
						bg: '#FF5080',
						time: 2
					});
					$("#code").focus();
				} else {
					$("#loginname").tips({
						side: 1,
						msg: "缺少参数",
						bg: '#FF5080',
						time: 2
					});
					$("#loginname").focus();
				}
			}
		});
	}
}

$(document).ready(function () {
	changeCode();
	$("#codeImg").bind("click", changeCode);
});

$(document).keyup(function (event) {
	if (event.keyCode == 13) {
		$("#to-recover").trigger("click");
	}
});

function genTimestamp() {
	var time = new Date();
	return time.getTime();
}

function changeCode() {
	$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
}



function savePaw() {
	if (!$("#saveid").attr("checked")) {
		$.cookie('loginname', '', {
			expires: -1
		});
		$.cookie('password', '', {
			expires: -1
		});
		$("#loginname").val('');
		$("#password").val('');
	}
}

function saveCookie() {
	if ($("#saveid").attr("checked")) {
		$.cookie('loginname', $("#loginname").val(), {
			expires: 7
		});
		$.cookie('password', $("#password").val(), {
			expires: 7
		});
	}
}

function quxiao() {
	$("#loginname").val('');
	$("#password").val('');
}

jQuery(function () {
	var loginname = $.cookie('loginname');
	var password = $.cookie('password');
	if (typeof (loginname) != "undefined" &&
		typeof (password) != "undefined") {
		$("#loginname").val(loginname);
		$("#password").val(password);
		$("#saveid").attr("checked", true);
		$("#code").focus();
	}
});