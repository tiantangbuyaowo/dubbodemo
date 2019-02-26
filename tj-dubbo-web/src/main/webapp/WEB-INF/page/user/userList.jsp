<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://kuyun/jst/c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
	用户列表

	<table border="1">
		<form action="addNewUser.do" method="post">
			<tr>
				<td>用户名:<input type="text" name="name">
				</td>
				<td>密码:<input type="text" name="password"></td>
				<td><select name="addType">
						<option value="0">mq</option>
						<option value="1">tcc</option>
				</select> <input type="submit" value="提交"></td>
			</tr>
		</form>
		<tr>
			<td>id</td>
			<td>用户名</td>
			<td>密码</td>
		</tr>
		<c:forEach var="user" items="${userPage.list}">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.password}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>