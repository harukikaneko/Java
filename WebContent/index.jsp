<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="login_user_bean" scope="session"
	class="login.LoginUserBean" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>サイト ログイン画面</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/vali.css" rel="stylesheet" type="text/css">
<link href="css/boot.css" rel="stylesheet" type="text/css">
</head>

<body>
	<main class="login_pane"> <script src="js/b.js"></script> <script
		src="js/bo.js"></script> <script src="js/boo.js"></script> <script
		src="js/v.js"></script> <script src="js/va.js"></script> <script
		src="js/val.js"></script> <script type="text/javascript">
			jQuery(document).ready(function() {
				jQuery("#loginID").validationEngine();
			});
		</script>
	<h1>ようこそ！</h1>
	<h2>長谷川ネット証券オンライントレーディングシステムへ</h2>

	<div class="container">
		<div class="wrapper">
			<form id="loginID" action="LoginServlet" method="post"
				class="form-signin">
				<h3 class="form-signin-heading">ログインIDとパスワードを入力して下さい</h3>
				<hr class="colorgraph">
				<br>
				<p style="color: red">${errMsg}</p>
				<%-- ログイン済みの場合はIDを表示 --%>
				<input
					class="form-control validate[required, custom[onlyLetterNumber]]"
					placeholder="Username" type="text" name="id" /> <input
					class="form-control validate[required]" placeholder="Password"
					type="password" name="pass" />

				<button class="btn btn-lg btn-primary btn-block" name="submit"
					value="ログイン" type="submit">ログイン</button>
		</div>
	</div>
	</form>
	<form action="FirstLogin.jsp" class="form-signin" method="post">
		<h3 class="form-signin-heading">初回ユーザーはこちら</h3>
		<hr class="colorgraph">
		<button class="btn btn-lg btn-danger btn-block" name="submit"
			value="初回ログイン" type="submit">初回ログイン</button>
	</form>
	</main>
</body>
</html>