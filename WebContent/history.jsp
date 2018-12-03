<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.HistoryBean"%>
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
<title>注文約定一覧（購入履歴)</title>
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

		<h1><jsp:getProperty name="login_user_bean" property="name" />さんの取引履歴一覧
		</h1>
		<%-- HistoryServletで付加したattribute: historyをリクエストスコープからBeanクラスのListを取得 --%>
		<%
			ArrayList<HistoryBean> historyList = (ArrayList<HistoryBean>) request.getAttribute("history");
		%>
		<form action="HistoryInfoServlet">
			<table class="table table-striped table-bordered shopping_table">
				<thead class="thead-dark">
					<tr>
						<th>商品名</th>
						<th>購入数</th>
						<th>購入合計</th>
						<th>注文価格</th>
						<th>執行条件</th>
						<th>注文番号</th>
						<th>取引日</th>
						<th>注文状態</th>
						<th>売買区分</th>


					</tr>
				</thead>
				<tbody>


					<%-- リクエストスコープから表示する値を取得 --%>
					<%
						for (HistoryBean bean : historyList) {
					%>
					<tr>
						<%-- 商品ID --%>
						<%-- 商品名 --%>
						<td><a href=HistoryInfoServlet?name=<%=bean.getItemName()%>><%=bean.getItemName()%></td></a>
						<td class="int"><%=bean.getQuantity()%></td>
						<td><%=bean.getPurchased_total()%></td>
						<td><%=bean.getSelect()%></td>
						<td><%=bean.getChoice()%></td>
						<td><%=bean.getDateinfo()%></td>
						<td><%=bean.getDate()%></td>
						<td><%=bean.getOrderStatus()%></td>
						<td><%=bean.getSellBuy()%></td>
						<input type="hidden" name="name" value="<%=bean.getItemName() %>">
					</tr>
					<%
						}
					%>
				</tbody>
				<div class="Pagination pagination"></div>
				<span id="prev">＜</span>　<span id="pagination"></span>　<span id="next">＞</span>
			</table>
		</form>
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