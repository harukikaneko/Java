<%@ page language="java" contentType="text/html charset=UTF-8"%>
<jsp:useBean id="login_user_bean" scope="session"
    class="login.LoginUserBean" />



<%-- header --%>
<header>
<body>

	<%-- ログイン済みの場合はID を表示 --%>
	<p class="h">
	<img src="logo.png" align="left">
        ようこそ「<jsp:getProperty name="login_user_bean" property="name" />」さん！
        <%-- Getのクエリで購入履歴かログアウトか判断させる --%>
       <a href="myPage.jsp"><button type="button" class="btn btn-default">マイページ</button></a>
       <a href="LogoutServlet"><button type="button" class="btn btn-default">ログアウト</button></a>
    </p>

</body>
</header>



</body>
</header>