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
<title>注文画面</title>
<link href="css/order.css" rel="stylesheet" type="text/css">
<link href="css/boot.css" rel="stylesheet" type="text/css">
<link href="css/vali.css" rel="stylesheet" type="text/css">
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<script src="js/b.js"></script>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/v.js"></script>
	<script src="js/va.js"></script>
	<script src="js/val.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			jQuery("#orderID").validationEngine();
		});
	</script>
	<main>
	<h1>注文</h1>
	<p style="color: red">${errMsg}</p>
	<p style="color: red">${errMsg2}</p>
	<%
		ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("item_bean");
	%> <%-- リクエストスコープからBeanクラスの配列を取得 --%>
	<form id="orderID" action="SellItemServlet">
		<table class="table_order table-bordered">
			<colgroup>
				<col class="w30per" />
				<col class="w70per" />
			</colgroup>
			<tbody>
				<%
					for (ItemBean bean : itemList) {
				%>
				<tr>
					<th class="ttip">銘柄コード</th>
					<td><p class="txt_r w60per status_left">
							<span class="tblFont"><%=bean.getItemCode()%></span>
						</p></td>
				</tr>
				<tr>
					<th class="ttip">銘柄名</th>
					<td><p class="txt_r w60per status_left">
							<span class="tblFont"><%=bean.getItemName()%></span>
						</p></td>
				</tr>
				<input type="hidden" name="code" value="<%=bean.getItemCode()%>" />
				<input type="hidden" name="name" value="<%=bean.getItemName()%>" />
				<tr>
					<th class="ttip"><a href="http/order.html" class="popup">注文条件</th></a>
					<td>
					<div class="form-group">
					<div class="form-inline">
							<label class="radio-inline text-center"><input type="radio" name="select" id="select0" value="成行" class="validate[required] ">成行:手数料1000円(税抜き)</label><br> <input type="hidden" name="price" value="<%=bean.getPrice()%>" class="calc" />&emsp;
							<label class="radio-inline text-center"><input type="radio" name="select" id="select1" value="指値" class="validate[required]">指値:</label> 
						    <label class="text-center"><input name="sashine" type="number" min="1" id="inputother" placeholder="金額" disabled="disabled" value="1" class="validate[required, custom[integer], min[1]]">円:手数料1500円(税抜き)</label>
					</div>
					</div>
					</td>
				</tr>
				<tr>
					<th class="ttip"><a href="http/select.html" class="popup">執行条件</th></a>
					<td><p class="txt_r w60per status_left">
							<span class="tblFont"><select name="choice"
								class="validate[required]">
									<option value="">項目を入力してください</option>
									<option value="無条件">無条件</option>
									<option value="寄付">寄付</option>
									<option value="引け">引け</option>
									<option value="指成">指成</option>
							</select><br></span>
						</p>
				</tr>
				<tr>
					<th class="ttip">売買単位</th>
					<td><p class="txt_r w60per status_left">
							<span class="tblFont"><%=bean.getUrikai()%>口<br> <input
								type="hidden" name="urikai" value="<%=bean.getUrikai()%>"
								class="q" price-date="<%=bean.getUrikai()%>" /> </span>
						</p>
				</tr>
				<tr>
					<th class="ttip"><a href="http/category.html" class="popup">預かり区分</th></a>
					<td><p class="txt_r w60per status_left">
							<span class="tbFont"><select name="category"
								class="validate[required]">
									<option value="">項目を入力してください</option>
									<option value="一般預かり">一般預かり</option>
									<option value="特定預かり">特定預かり</option>
							</select><br> </span>
				</tr>

				<tr>
					<th class="ttip"><a href="http/quantity.html" class="popup">数量（口）</th></a>
					<td><p class="txt_r w60per status_left">
							<span class="tblFont"><input type="number" name="quantity"
								min="1" value="1"
								class="validate[required,custom[integer],min[1]]">口<br></span>
						</p>
				</tr>
				<%
					}
				%>
			</tbody>
			</form>
			</main>
			<script
				src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
			<script type="text/javascript">
				$(function() {
					$('input[name="select"]:radio').change(function() {
						var radioval = $(this).val();
						if (radioval == "指値") {
							$('#inputother').removeAttr('disabled');
						} else {
							$('#inputother').attr('disabled', 'disabled');
						}
					});
				});
			</script>
			<script type="text/javascript">
				$(function() {
					$(".popup")
							.click(
									function() {
										// ウィンドウサイズ
										var ws = 940;
										var hs = 500;
										// 画面よりウィンドウサイズが大きい場合の縮小率
										var wr = 0.6;
										var hr = 0.6;
										if (ws > screen.availWidth) {
											ws = screen.availWidth * wr;
										}
										if (hs > screen.availHeight) {
											hs = screen.availHeight * hr;
										}
										// ウィンドウを中央寄せ
										var w = (screen.availWidth - ws) / 2;
										var h = (screen.availHeight - hs) / 2;
										window
												.open(this.href, "window", "width= " + ws + ",height=" + hs + ",left=" + w + ",top=" + h + ", location = no ,toolbar = no, menubar = no, status = no");
										return false;
									});
				});
			</script>
</body>

<table class="shopping_table">
<div class="my">
	<input class="common_button" onclick="history.back()" value="戻る">&emsp;
	<input class="common_button" type="submit" value="注文確定">
</div>
	</html>