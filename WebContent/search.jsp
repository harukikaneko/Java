<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<head>
<link href="css/boot.css" rel="stylesheet" type="text/css">
<link href="css/shopping.css" rel="stylesheet" type="text/css">
<link href="css/vali.css" rel="stylesheet" type="text/css">
<title>銘柄検索</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="resultSize">

	<script src="js/b.js"></script>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/v.js"></script>
	<script src="js/va.js"></script>
	<script src="js/val.js"></script>

	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#searchID").validationEngine();
		});
	</script>
	<p>検索したい銘柄名・コードを入力してください</p>
	<p style="color: red">${errMsg}</p>
	<form id="searchID" action="QueryListByNameServlet" method="post">
		<div class="input-group">
			<input id="req" type="text" style="width: 150px;"
				class="validate[required] form-control" name="search_name"
				placeholder="Search"> <span class="input-group-btn">
				<button class="btn btn-default btn-block" type="submit">検索</button>
			</span>
		</div>
	</form>
	<input class="back_button" onclick="history.back()" value="戻る">
	</div>
</body>
</html>