<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% if (session == null){
	session.setMaxInactiveInterval(600);
} %>
<%
	if(session.isNew()) {
		response.sendRedirect(request.getContextPath() + "/loginFailed.jsp");
		return;
	}
%>
<%-- 商品一覧画面 --%>
<!DOCTYPE html>

<html>
    <head>
        <title>銘柄一覧画面</title>
		<link href="css/shopping.css" rel="stylesheet" type="text/css" />
		<link href="css/boot.css" rel="stylesheet" type="text/css">    
	</head>
    <body>
        <%-- TODO:2-1 jsp:includeでヘッダー画面を読み込む --%> 
        <jsp:include page="header.jsp"></jsp:include>

		<script src="js/b.js"></script>
		<script src="js/bo.js"></script>
		<script src="js/boo.js"></script>


            <main>
                <h1>銘柄一覧</h1>
                <h3>該当する項目はございません。</h3>
                <br>
                <a class="back_button" onclick="history.back()">戻る</a>

            </form>
        </main>

    </body>
</html>