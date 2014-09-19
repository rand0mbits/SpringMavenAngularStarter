<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/favicon.ico" />" />
	<title>MY PROJECT - LOGIN</title>
	<style>
		html, body {
			margin: 0;
		}
		.errorblock {
			color: #ff0000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
		body {
			background-color: #F5F5F5;
			/* background-image: url(resources/images/login-bg.png); */
			background-repeat: no-repeat;
		}
		#floater	{
			float:left; height:50%; margin-bottom:-150px;
		}
		#content {
			clear:both; 
			height:250px;
			min-width: 620px;
			position:relative;
			background-image: url(resources/images/stripe.png);
		}
		#login-form	{
			width: 343px;
			height: 300px;
			padding-left: 265px;
			padding-top: 45px;
			margin: 0 auto;
			background-image: url(resources/images/logo.png);
			background-repeat: no-repeat;
			position: relative;
		}
		
		#username-input-wrapper, #password-input-wrapper {
			border: solid 1px #333;
			border-radius: 3px;
			box-shadow: .0em .1em rgba(255, 255, 255, 0.40);
			width: 340px;
			margin-bottom: 12px;
			background-color: white;
			background-position: 5px;
			background-repeat: no-repeat;
		}
		
		input.unstyled {
			border: 0;
			outline: none;
			margin: 0;
			padding: 4px;
			width: 311px;
		}
		
		.icon {
			float: left;
			margin: 3px 3px 3px 5px;
			width: 19px;
			height: 19px;
			background-image: url(resources/images/input-icons.png);
		}
		
		
		#password-input-wrapper .icon {
			background-position: 19px;
		}
		
		input:-webkit-autofill {
		    -webkit-box-shadow: 0 0 0 1000px white inset;
		}
		
		#login-buttons {
			position: absolute;
			bottom: 105px;
			right: 5px;
		}
		
		#login-buttons button {
			background-image: url("resources/images/login-buttons.png");
			border: none;
			background-color: transparent;
			height: 64px;
			width: 112px;
			cursor: pointer;
		}
		
		#login-wnba {
			background-position: -116px 0;
		}
		#login-dnba {
			background-position: -232px 0;
		}
	</style>
	<script>
		// if there's more than 1 title element, then the login screen
		// has been loaded inside another page, likely because of a timeout
		if (document.getElementsByTagName('title').length > 1) {
			// reload the page so that the login page is loaded on its own
			location.reload();
		}
		function setLeague(league) {
			document.getElementById('login-league-input').value = league;
		}
	</script>
</head>
<body onload='document.f.j_username.focus();'>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} <br />
		</div>
	</c:if>
 
 	<div id="floater"></div>
 	<div id="content">
 		<form name='f' action="<c:url value='j_spring_security_check' />" method='POST' id="login-form">
	 		<input type="hidden" name="loginLeague" id="login-league-input" value="NBA"/>
			<div id="username-input-wrapper"><label class="icon" for="username-input"></label><input type='text' name='j_username' value='' class="unstyled" id="username-input" placeholder="Username"></div>
			<div id="password-input-wrapper"><label class="icon" for="password-input"></label><input type='password' name='j_password' class="unstyled" id="password-input" placeholder="Password" /></div>

			<div id="login-buttons">
				<button id="login-nba" onclick="setLeague('NBA')" title="Login to NBA"></button>
				<button id="login-wnba" onclick="setLeague('WNBA')" title="Login to WNBA"></button>
				<button id="login-dnba" onclick="setLeague('DLG')" title="Login to D-League"></button>
			</div>
		</form>
 	</div>
	
</body>
</html>