<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="login_user_bean" scope="session"
	class="login.LoginUserBean" />
<%
	if (session == null) {
		session.setMaxInactiveInterval(600);
	}
%>
<%
	if (session.isNew()) {
		response.sendRedirect(request.getContextPath() + "/loginFailed.jsp");
		return;
	}
%>
<html>
<head>
<link href="css/boot.css" rel="stylesheet" type="text/css">
<title>マイページ</title>
</head>
<body>
<script src="js/b.js"></script>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/v.js"></script>
	<script src="js/va.js"></script>
	<script src="js/val.js"></script>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- ログイン済みの場合はID を表示 --%>
	
	<div class="my">
	<br>
	<br>
		<h3><b>「<jsp:getProperty name="login_user_bean" property="name" />」さんのマイページです。</b></h3>
		<h5>(ID:<jsp:getProperty name="login_user_bean" property="id" />)</h5>
		<p class="my">
			<%-- Getのクエリで購入履歴かログアウトか判断させる --%>
			<br>
			<a class="common_button" href="search.jsp">銘柄検索</a>&emsp;

			<a class="common_button" href="HistoryServlet?submit=history">購入履歴</a>&emsp;
			<a class="common_button" href="favlistServlet?submit=fav">お気に入</a><br>

		</p>
	</div>
</body>
</html>