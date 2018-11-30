<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.ItemBean"%>
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
<%-- 商品一覧画面 --%>
<!DOCTYPE html>

<html>
<head>
<title>取引詳細画面</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<link href="css/boot.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- TODO:2-1 jsp:includeでヘッダー画面を読み込む --%>
	<jsp:include page="header.jsp"></jsp:include>

	<h1>
		<jsp:getProperty name="login_user_bean" property="name" />さんの取引履歴詳細
	</h1>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/v.js"></script>
	<script src="js/va.js"></script>
	<script src="js/val.js"></script>
	<script src="js/cconfirm.js"></script>
	<main> <%-- リクエストスコープからBeanクラスの配列を取得 --%> <%
 	ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("item_bean");
 %> <%
 	ArrayList<HistoryBean> historyList = (ArrayList<HistoryBean>) request.getAttribute("history");
 %>
	<h3>取引履歴</h3>
	<form action="./Excel" method="post">
	<table class="table table-bordered shopping_table">
		<thead class="thead-dark">
			<tr>
				<th>商品名</th>
				<th>購入数</th>
				<th>購入合計</th>
				<th>注文価格</th>
				<th>執行条件</th>
				<th>注文番号</th>
				<th>注文日</th>
				<th>注文状態</th>
				<th>売買区分</th>
				<th>注文区分</th>

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
				<td><%=bean.getItemName()%></td>
				</a>
				<td class="int"><%=bean.getQuantity()%></td>
				<td><%=bean.getPurchased_total()%></td>
				<td><%=bean.getSelect()%></td>
				<td><%=bean.getChoice()%></td>
				<td><%=bean.getDateinfo()%></td>
				<td><%=bean.getDate()%></td>
				<td><%=bean.getOrderStatus()%></td>
				<td><%=bean.getSellBuy()%></td>
				<td><%=bean.getCategory() %></td>
				<input type="hidden" name="name" value="<%=bean.getItemName()%>" />
				<input type="hidden" name="dateinfo" value="<%=bean.getDateinfo()%>" />
				<input type="hidden" name="date" value="<%=bean.getDate()%>" />
				<input type="hidden" name="code" value="<%=bean.getItemId()%>" />
				<input type="hidden" name="quantity" value="<%=bean.getQuantity()%>" />
				<input type="hidden" name="price" value="<%=bean.getPurchased_total()%>" />
				<input type="hidden" name="status" value="<%=bean.getSellBuy()%>" />
				<td><a href=Excel><button type="submit" class="btn btn-primary btn-sm">出力</button></a></td>
				<td><a onclick="return notice();" href=cancelServlet?dateinfo=<%=bean.getDateinfo()%>><button type="button" class="btn btn-default btn-sm">取消</button></td></a>
				<td><a href=SellOrderServlet?name=<%=bean.getItemName()%>><button type="button" class="btn btn-default btn-sm">売り注文</button></td></a>
			</tr>
		</tbody>
	</table>
				
				<% } %>
	<h3>取引時の株価情報</h3>
	<table class="table table-bordered shopping_table">
		<thead class="thead-dark">
			<tr>
				<th>市場</th>
				<th>業種</th>
				<th>株価</th>
				<th>始値</th>
				<th>高値</th>
				<th>安値</th>
				<th>買い気配</th>
				<th>売り気配</th>
				<th>年初来の高値</th>
				<th>年初来の安値</th>
			</tr>
		</thead>
		<tbody>
			<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
			<%
				for (ItemBean bean : itemList) {
			%>
			<tr>
				<td><%=bean.getMarket()%></td>
				<td><%=bean.getIndustry()%></td>
				<td class="int"><%=bean.getPrice()%></td>
				<td class="int"><%=bean.getOpen()%></td>
				<td class="int"><%=bean.getHigh()%></td>
				<td class="int"><%=bean.getLow()%></td>
				<td class="int"><%=bean.getAsk()%></td>
				<td class="int"><%=bean.getBid()%></td>
				<td class="int"><%=bean.getYearly_high()%></td>
				<td class="int"><%=bean.getYearly_low()%></td>
				<input type="hidden" name="market" value="<%=bean.getMarket()%>" />
			</tr>

			<%
				}
			%>

			</tbody>
			</table>
			<h3>取引時の株価情報</h3>
			<table class="table table-bordered shopping_table">
				<thead class="thead-dark">
					<tr>
						<th>市場</th>
						<th>業種</th>
						<th>株価</th>
						<th>始値</th>
						<th>高値</th>
						<th>安値</th>
						<th>買い気配</th>
						<th>売り気配</th>
						<th>年初来の高値</th>
						<th>年初来の安値</th>
					</tr>
				</thead>
				<tbody>
				<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
				<%
					for (ItemBean bean : itemList) {
				%>
				<tr>
					<td><%=bean.getMarket()%></td>
					<td><%=bean.getIndustry()%></td>
					<td class="int"><%=bean.getPrice()%></td>
					<td class="int"><%=bean.getOpen()%></td>
					<td class="int"><%=bean.getHigh()%></td>
					<td class="int"><%=bean.getLow()%></td>
					<td class="int"><%=bean.getAsk()%></td>
					<td class="int"><%=bean.getBid()%></td>
					<td class="int"><%=bean.getYearly_high()%></td>
					<td class="int"><%=bean.getYearly_low()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<input class="back_button" onclick="history.back()" value="戻る">

	</main>
</body>
</html>