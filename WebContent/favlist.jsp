<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.FavBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<%-- 購入履歴画面 --%>
<!DOCTYPE html>
<html>
<head>
<title>お気に入り一覧</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<link href="css/boot.css" rel="stylesheet" type="text/css">
<link href="css/page.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="errorSize">
		<jsp:include page="header.jsp" />
		<script src="js/b.js"></script>
		<script src="js/bo.js"></script>
		<script src="js/boo.js"></script>
		<script src="js/v.js"></script>
		<script src="js/va.js"></script>
		<script src="js/val.js"></script>
		<script src="js/page.js"></script>
		<script src="js/cconfirm.js"></script>

		<h1><jsp:getProperty name="login_user_bean" property="name" />さんのお気に入り一覧
		</h1>
		<%-- HistoryServletで付加したattribute: historyをリクエストスコープからBeanクラスのListを取得 --%>
		<%
			ArrayList<FavBean> favList = (ArrayList<FavBean>) request.getAttribute("favlist");
		%>
			<table class="table table-striped table-bordered shopping_table">
				<thead class="thead-dark">
					<tr>
						<th>商品名</th>
					

					</tr>
				</thead>
				<tbody>

					<%-- リクエストスコープから表示する値を取得 --%>
					<%
						for (FavBean bean : favList) {
					%>
					<tr>
						<%-- 商品ID --%>
						<%-- 商品名 --%>
						<td><a href=itemInfoServlet?name=<%=bean.getItem_name()%>><%=bean.getItem_name()%></td></a>
						<td><a onclick="return notice2();" href=favDelete?name=<%=bean.getItem_name()%>><button type="submit" class="btn btn-default btn-sm">取消</button></td></a>
					</tr>
					<%
						}
					%>
				</tbody>
				<div class="Pagination pagination"></div>
				<span id="prev">＜</span>　<span id="pagination"></span>　<span id="next">＞</span>
			</table>
		<input class="back_button" onclick="history.back()" value="戻る">
	</div>
	<script type="text/javascript">
  	$(function() {
  var page = 0;
  function draw() {
    $('#pagination').html(page + 1);
    $('tr').hide();
    $('tr:first,tr:gt(' + page * 10 + '):lt(10)').show();
  }
  $('#prev').click(function() {
    if (page > 0) {
      page--;
      draw();
    }
  });
  $('#next').click(function() {
    if (page < ($('tr').size() - 1) / 10 - 1) {
      page++;
      draw();
    }
  });
  draw();
});
</script>
</body>
</html>