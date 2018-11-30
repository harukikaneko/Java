<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.HistoryBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="login_user_bean" scope="session"
	class="login.LoginUserBean" />
<% if (session == null){
	session.setMaxInactiveInterval(600);
} %>
<%
	if(session.isNew()) {
		response.sendRedirect(request.getContextPath() + "/loginFailed.jsp");
		return;
	}
%>
<%-- 購入履歴画面 --%>
<!DOCTYPE html>
<html>
<head>

<title>注文約定一覧（購入履歴)</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<link href="css/boot.css" rel="stylesheet" type="text/css">
</head>

<body>
	<jsp:include page="header.jsp" />
	<script src="bo.js"></script>
	<script src="boo.js"></script>
	<script src="v.js"></script>
	<script src="va.js"></script>
	<script src="val.js"></script>

	<h1><jsp:getProperty name="login_user_bean" property="name" />さんの注文約定一覧（取引履歴）
	</h1>
	<%-- ShoppingServletで付加したattribute: historyをリクエストスコープからBeanクラスのListを取得 --%>
	<h3><jsp:getProperty name="login_user_bean" property="name" />さんの取引履歴はございません。</h3>
	<form action="myPage.jsp" method="post">

		<input class="back_button" type="submit" value="戻る">

	</form>
	</div>
</body>
</html>