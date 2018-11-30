<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
<title>銘柄一覧画面</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<link href="css/boot.css" rel="stylesheet" type="text/css">
<link href="css/page.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- TODO:2-1 jsp:includeでヘッダー画面を読み込む --%>
	<jsp:include page="header.jsp"></jsp:include>
	<script src="js/b.js"></script>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/jque.js"></script>
	<script src="js/page.js"></script>
	<script src="paginaton.js"></script>
	<script src="pas.js"></script>
	
	<main>
	<h1>一覧</h1>

	<%-- リクエストスコープからBeanクラスの配列を取得 --%>
 	<% ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("item_bean"); %>
	<ul class="pageNav02">
	<form action="favServlet" method="post">
		<table class="table table-bordered shopping_table" id="paginate">
		<div id="Searchresult"><!-- ここにコンテンツを表示 --></div>
			<div id="hiddenresult" style="display: none;">
     		<div class="result">
     		 <input type="hidden" name="search_code" value="<%=request.getAttribute("code") %>"/> 
             <input type="hidden" name="search_name" value="<%=request.getAttribute("name") %>"/> 

			<thead class="thead-dark">
				<tr>
					<th>銘柄コード</th>
					<th>銘柄名</th>
					<th>市場</th>
					<th>業種</th>

					<th>お気に入り登録</th>
					<th>備考</th>
					
				</tr>
			</thead>
			<tbody>
			<ul class="result" id="result">

				<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
				<%
					for (ItemBean bean : itemList) {
				%>
				<tr>
					<%-- 銘柄コード --%>
					<td><a href=itemInfoServlet?code=<%=bean.getItemCode()%>><%=bean.getItemCode()%></td></a>
					<%-- 銘柄名 --%>
					<td><a href=itemInfoServlet?name=<%=bean.getItemName()%>><%=bean.getItemName()%></td></a>
					<%-- 市場 --%>
					<td class="int"><%=bean.getMarket()%></td>
					<%-- 業務 --%>
					<td class="int"><%=bean.getIndustry()%></td>
					<td><a href=favServlet?item_name=<%=bean.getItemName()%>><button data-condition=false class="favoButton">☆</button></td></a>
					<input type="hidden" name="item_name" value="<%=bean.getItemName() %>">
					<input type="hidden" name="data_condition" value="data-condition">
					<p style="color: red">${msg}</p>
					<%
						if (bean.getKanriSeiri() != null) {
					%>
					<td><font color=red>この銘柄は<%=bean.getKanriSeiri()%>です
					</font></td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</tbody>
			 </div>
			</div>
			</div>
			<div class="Pagination pagination"></div>
			<span id="prev">Preview</span>　<span id="pagination"></span>　<span id="next">Nextpage</span>
			</ul>
			<!-- ここにページネーションを表示 -->
		</table>
		<input class="back_button" onclick="history.back()" value="戻る">
	</form>
	</main>
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
<script type="text/javascript">
$(function(){
	
	
	
	$(".favoButton").click(function() {
		var button = this;
		//お気に入りボタンのdata-conditionで制御
		if($(this).data('condition') == false){
		  //お気に入り登録
		  //お気に入りボタンの色を黄色に
		  $(button).css('backgroundColor', '#FF0');
		  //お気に入りボタン状態の更新
		  $(button).data('condition',true);
		}
		else if($(this).data('condition') == true){

		  //お気に入り登録解除
		  //背景色を解除
		  $(button).css('backgroundColor', '');
		  //お気に入りボタン状態の更新
		  $(button).data('condition',false);
		}
	});	
})

</script>
</body>
</html>