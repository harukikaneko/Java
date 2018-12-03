<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="shopping.weeklyStockBean"%>
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
<title>銘柄詳細情報画面</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<link href="css/boot.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- TODO:2-1 jsp:includeでヘッダー画面を読み込む --%>
	<jsp:include page="header.jsp"></jsp:include>
	<script src="js/b.js"></script>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/chart.js"></script>

	<main> <%-- リクエストスコープからBeanクラスの配列を取得 --%> <%
 	ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("item_bean");
 %> <%
 	ArrayList<weeklyStockBean> wsbList = (ArrayList<weeklyStockBean>) request.getAttribute("ws_bean");
 %> <font color=red>${errMsg}</font>
	<form action="orderServlet">

	<div>
	<table class= "table table-bordered shopping_table">
        <thead class= "thead-dark">
        	<tr>
					<th>銘柄コード</th>
					<th>銘柄名</th>
					<th>市場</th>
					<th>業種</th>
					<th>株価</th>
					<th>始値</th>
					<th>高値</th>
					<th>安値</th>
					<th>買い<br>気配</th>
					<th>売り<br>気配</th>
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
					<%-- 銘柄コード --%>
					<td><%=bean.getItemCode()%></td>
					<%-- 銘柄名 --%>
					<td><%=bean.getItemName()%></td>
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
		</table>
		<input class="common_button" type="submit" value="注文">
					
					<input type="hidden" name="code" value="<%=bean.getItemCode()%>" />

					<%
						for (ItemBean bean : itemList) {
					%>

					<tr>
						<%-- 銘柄コード --%>
						<td><%=bean.getItemCode()%></td>
						<%-- 銘柄名 --%>
						<td><%=bean.getItemName()%></td>
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
			</table>
			<input class="common_button" type="submit" value="注文"> <input
				type="hidden" name="code" value="<%=bean.getItemCode()%>" />
			<%
				}
			%>
		</div>
		<canvas id="myChart" width="200" height="50"></canvas>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.min.js"></script>
		<script type="text/javascript">
		//項目用の配列を定義		
				<%for (weeklyStockBean bean : wsbList) {%>			
			var array01 = ["1日前","2日前","3日前","4日前","5日前","6日前","7日前"];

			var array02 = [];
			var d1 = '<%=bean.getOneDayAgo()%>';
			var d2 = '<%=bean.getTwoDaysAgo()%>';
			var d3 = '<%=bean.getThreeDaysAgo()%>';
			var d4 = '<%=bean.getFourDaysAgo()%>';
			var d5 = '<%=bean.getFiveDaysAgo()%>';
			var d6 = '<%=bean.getSixDaysAgo()%>';
			var d7 = '<%=bean.getSevenDaysAgo()%>';

			<%}%>			
			var newLength = array02.push(d1,d2,d3,d4,d5,d6,d7);


			var ctx = document.getElementById('myChart').getContext('2d');
			var myChart = new Chart(ctx, {
			  type: 'line',
			  data: {
			    labels: array01,
			    datasets: [{
			      label: '一週間の株価変動',
			      data: array02,
			      backgroundColor: "rgba(153,255,51,0.4)",

			   
			    }]

			  },
			  options: {
				  
				  	      scales: {
				  
				  	          //縦軸の設定
				  
				  	          yAxes: [{
				  
				  	              ticks: {
				  
				  	                  //最小値を0にする
				  
				  	                  beginAtZero: true
				  
				  	              }
				  	          }]
				  	      }
				  	  }
			});
		</script>

		<p>過去１週間の株価</p>
		<table class="table table-bordered shopping_table" id="table">
			<thead class="thead-dark">

				<tr>
					<th><span>１日前</span></th>
					<th><span>２日前</span></th>
					<th><span>３日前</span></th>
					<th><span>４日前</span></th>
					<th><span>５日前</span></th>
					<th><span>６日前</span></th>
					<th><span>７日前</span></th>
				</tr>
			</thead>
			<tbody>

				<div>
					<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
					<%
						for (weeklyStockBean bean : wsbList) {
					%>
					<tr>
						<%-- 一週間分の株価 --%>
						<td><span><%=bean.getOneDayAgo()%></span></td>
						<td><span><%=bean.getTwoDaysAgo()%></span></td>
						<td><span><%=bean.getThreeDaysAgo()%></span></td>
						<td><span><%=bean.getFourDaysAgo()%></span></td>
						<td><span><%=bean.getFiveDaysAgo()%></span></td>
						<td><span><%=bean.getSixDaysAgo()%></span></td>
						<td><span><%=bean.getSevenDaysAgo()%></span></td>
					</tr>
					<%
						}
					%>
				</div>

			</tbody>
		</table>
		</tbody>
		</table>
		<input class="back_button" onclick="history.back()" value="戻る">
	</form>
	</main>
</body>