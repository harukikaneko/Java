<%-- login失敗 --%> <%@ page language="java" contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8"%> <%-- ログインエラー画面 --%>

<!DOCTYPE html>
<html>
  <head>
    <title>エラー画面</title>
    <link href="css/shopping.css" rel="stylesheet" type="text/css" />
    <link href="css/boot.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <jsp:include page="header.jsp"></jsp:include>

    <script src="js/b.js"></script>
    <script src="js/bo.js"></script>
    <script src="js/boo.js"></script>

    <main>
      <h1>セッションタイムアウト</h1>
      <p>セッションがきれました。ログインし直してください。</p>
      <form>
        <a class="loginFailed_button" href="index.jsp">ログイン画面ヘ戻る</a>
      </form>
    </main>
  </body>
</html>
