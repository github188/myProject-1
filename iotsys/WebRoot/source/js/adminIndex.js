 
function fn_newPass() {
	var loginPasswd = $("#loginPasswd").val();
	if(checkPwdText()){
		baseAjax(false,"user/userAction_updatePassword.do",updatePwd,{"loginPasswd":loginPasswd});
	} 
	function updatePwd(data){
		if(data.code=="000000"){
			bootbox.alert("修改密码成功！将重新登陆",function(){
				location.href="login.jsp"; 
			});
		}
	}
}

/**
 * 验证
 * @returns {Boolean}
 */
function checkPwdText() {
	var addCheck = true;
	//原始密码
	var ysPasswd = $("#ysPasswd").val();
	var loginPasswd = $("#loginPasswd").val();
	if (ysPasswd == null || ysPasswd == "") {
		$("#ysPasswd").next().html("原始密码未输入!");
		addCheck = false;
	}else{
		baseAjax(false,"user/userAction_findOldPwd.do",findOldPwd,{"ysPasswd" : ysPasswd});
	}
	function findOldPwd(data){
		// 判断原始密码
		if(data.code == "000002"){
			$("#ysPasswd").next().html(data.msg);
			addCheck = false;
		}else{
			$("#ysPasswd").next().html("*");
		}
	}
	// 判断登陆的密码
	if (loginPasswd == null || loginPasswd == "") {
		$("#loginPasswd").next().html("密码不能为空");
		addCheck = false;
	} else {
		var reg = /^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|:;,.<>/——+|-]){6,20}$/;
		if (reg.test(loginPasswd) == false) {
			$("#loginPasswd").next().html("6-20位(数字,字母,字符)");
			addCheck = false;
		} else {
			baseAjax(false,"user/userAction_findOldPwd.do",judgePwd,{"loginPasswd" : loginPasswd});
		}
	}
	function judgePwd(domain){
		// 判断原始密码
		if(domain.code == "000001"){
			$("#loginPasswd").next().html(domain.msg);
			addCheck = false;
		}else{
			$("#loginPasswd").next().html("*");
		}
	}
	// 确认密码
	var newPasswd = $("#newPasswd").val();
	if (newPasswd == null || newPasswd == "") {
		$("#newPasswd").next().html("请再次输入密码");
		addCheck = false;
	}
	else{
		if (newPasswd != loginPasswd) {
			$("#newPasswd").next().html("两次密码不一致");
			addCheck = false;
		} else {
			$("#newPasswd").next().html("*");
		}
		
	}

	return addCheck;
}
