<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="login_user_bean" scope="session"
	class="login.LoginUserBean" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初回 ログイン画面</title>
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
	<h1>ようこそ！</h1>
	<h2>長谷川ネット証券オンライントレーディングシステムへ</h2>
	
	<div class="container">
		<div class="wrapper">
			<form id="loginID" action="LoginChangeSarvlet" method="post" class="form-signin">
				<h3 class="form-signin-heading">新しいパスワード/ログインIDを登録して下さい</h3>
				<hr class="colorgraph">
				<br>
				<p style="color: red">${errMsg}</p>
				<%-- ログイン済みの場合はIDを表示 --%>
				<p>ユーザー口座番号を入力して下さい
				<input class="form-control validate[required, custom[onlyLetterNumber]]" placeholder="AccountID" type="text" name="id" />
				<p>新しいユーザーIDを入力して下さい 
				<input class="form-control validate[required],custom[onlyLetterNumber]" placeholder="NewUserID" type="text" name="id2" />
				<p>新しいログインパスワードを入力して下さい
				<input class="form-control validate[required]" placeholder="NewPassword" type="password" name="pass" id="password1"/>
				<p>確認：もう一度入力して下さい
				<input class="form-control validate[required, equals[password1]]" placeholder="re:NewPassword" type="password" name="pass2" />

				<button class="btn btn-lg btn-primary btn-block" name="submit"
					value="登録" type="submit">登録</button>
		</div>
	</div>
	</form>
	</main>
</body>
</html>