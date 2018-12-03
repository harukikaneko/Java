<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="login_user_bean" scope="session"
	class="login.LoginUserBean" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>完了</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/vali.css" rel="stylesheet" type="text/css">
<link href="css/boot.css" rel="stylesheet" type="text/css">
</head>

<body>
	<main class="login_pane">
	<script src="js/b.js"></script>
	<script src="js/bo.js"></script>
	<script src="js/boo.js"></script>
	<script src="js/v.js"></script>
	<script src="js/va.js"></script>
	<script src="js/val.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#loginID").validationEngine();
		});
	</script>
	<h1>登録が完了しました</h1>
	<h2>長谷川ネット証券オンライントレーディングシステムへ</h2>
	
	<div class="container">
		<div class="wrapper">
			<form id="loginID" action="LoginChangeSarvlet" method="post" class="form-signin">
				<h3 class="form-signin-heading">ログインして取引を開始しましょう！</h3>
				<hr class="colorgraph">
				<br>
				<button class="btn btn-lg btn-primary btn-block" name="submit"
					value="ログイン画面へ" type="submit">ログイン画面へ</button>
		</div>
	</div>
	</form>
	</main>
</body>
</html>