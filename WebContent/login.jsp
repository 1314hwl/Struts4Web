<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>struts 模拟登陆</title>
</head>
<script type="text/javascript">
	function check(form) {
		if (form.userName.value == null || form.userName.value == "") {
			alert("请输入用户名!")
			return;
		} else if (form.pass.value == null || form.pass.value == "") {
			alert("请输入密码!")
			return;
		} else {
			return true;
		}
	}
</script>
<font color="red"> <%
     if (request.getAttribute("err") != null) {
         out.println("系统发生错误:" + request.getAttribute("err") + "<br>");
     }
 %>
</font>

<body>
	<form id="struts_login" method="post" onsubmit="return check(this);"
		action="login">
		用户名：<input type="text" name="userName"><br /> <br>密
		&nbsp&nbsp码：<input type="password" name="pass"><br> <br>
		<input type="submit">&nbsp&nbsp<input type="reset">
	</form>
</body>
</html>