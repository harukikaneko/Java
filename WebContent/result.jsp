<%@ page language="java" contentType="text/html; charset=UTF-8" %> <% if
(session == null){ session.setMaxInactiveInterval(600); } %> <%
if(session.isNew()) { response.sendRedirect(request.getContextPath() +
"/loginFailed.jsp"); return; } %> <%-- 購入結果画面 --%>
<!DOCTYPE html>
<html>
  <head>
    <title>購入結果</title>
    <link href="css/shopping.css" rel="stylesheet" type="text/css" />
    <link href="css/boot.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <jsp:include page="header.jsp" />

    <script src="js/b.js"></script>
    <script src="js/bo.js"></script>
    <script src="js/boo.js"></script>

    <div class="resultSize">
      <h1>購入結果</h1>
      <p>購入しました</p>
      <form action="./ResultServlet" method="post">
        <table class="table table-bordered shopping_table">
          <thead class="thead-dark">
            <tr>
              <th>購入日/注文番号</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <%= session.getAttribute("date")%> / <%=
                session.getAttribute("dateinfo") %>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
  </body>
</html>
