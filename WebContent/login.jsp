<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">

	<!-- JQuery -->
	<script src="library/jquery/jquery-3.2.1.min.js"></script>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="library/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="library/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	<!-- Roboto google font -->
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">

	<!-- My library -->
	<link rel="stylesheet" href="style/style.css">
	<script src="js/script.js"></script>

	<!-- Favicon -->
	<link href="image/favicon.ico" rel="shortcut icon" >

	<title>Mergen Travel</title>
</head>
<body>

	<div class="container">
	  <div class="row">
	      <div id="flip-container">
	            <div id="flip-card">
	                <div id="signin" class="face">
	                	<jsp:include page="loginMain.jsp" /> 
	                </div>
	                <div class="back face">
	                    <div id="signup" class="hide">
	                        <jsp:include page="register.jsp" />
	                        <a href="#" class="backToSignin">Back to login.</a>
	                      </div>
	                </div>
	            </div>
	        </div> <!-- /container -->
	  </div>
	</div>

	<footer>
	  <div class="container-fluid">
	 	<p>2017 &copy; All right reserved. Developed By Erdenezaya</p>
	  </div>
	</footer>
</body>
</html>