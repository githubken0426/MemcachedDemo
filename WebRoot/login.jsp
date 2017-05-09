<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="<%=path%>/resources/css/login.css">
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.min.js"></script>
	<title>城市停车信息诱导云平台系统</title>
<script>
	function subAction(){
		var userName=$("#userName").val();
		var pwd=$("#pwd").val();
		if((userName==undefined || userName=="" || userName==null)){
			alert("用户名不能为空!");
			return false;
		}
		if(pwd==undefined || pwd=="" || pwd==null){
			alert("密码不能为空!");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_form">
				<form id="loginAction"action="user_login.action" method="post" onsubmit="return subAction();">
					<div id="login_tip">
						后台登录
					</div>
					<div><input type="text" id="userName"class="username" name="user.userName"/></div>
					<div><input type="password" id="pwd" class="pwd" name="user.password"/></div>
					<div id="btn_area">
						<input type="submit" id="sub_btn" value="登&nbsp;&nbsp;录">
						&nbsp;&nbsp;
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="login_bottom">
		大连盖特尔信息科技有限公司版权所有
	</div>
</body>
</html>