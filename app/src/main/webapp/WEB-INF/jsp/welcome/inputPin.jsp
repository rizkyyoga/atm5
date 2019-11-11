<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>ATM System Login: Input Pin</title>
<!-- Loading Bootstrap -->
<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">

<!-- Loading Flat UI -->
<link th:href="@{/css/flat-ui.css}" rel="stylesheet">
<link th:href="@{/css/demo.css}" rel="stylesheet">
<link th:href="@{/css/custom.css}" rel="stylesheet">

<link rel="shortcut icon" th:href="@{/img/favicon.ico}">
<style>
#confirmPin {
	display: block;
	float: left;
	margin-top: 1rem;
	margin-left: 0px;
	width: 100%;
}

#confirmPin button {
	padding: 0px;
	text-align: center;
	font-size: 5rem;
}
</style>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="dist/js/vendor/html5shiv.js"></script>
      <script src="dist/js/vendor/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="demo-headline" style="padding: 0px;">
			<h1 class="demo-logo">
				ATM <small>24 Hour Syariah Saving</small>
			</h1>
		</div>
		<!-- /demo-headline -->
		<div class="row">
			<div class="col-xs-12" id="screenView">
				<!-- Place <h1></h1> below -->

				<!-- Replace Video with Animated Gif or Animated SVG -->
				<div class="row">
					<div class="col-xs-8 text-center"
						style="border-right: solid 3px #CCC">
						<h1 class="demo-section-title text-uppercase text-center">Input
							Your PIN</h1>
						<form class="form-horizontal" method="POST" th:action="@{/login}">
							<input name="accountNumber" type="hidden"
								th:value="${ accountNumber }"> <input type="password"
								id="userPinInput" name="pin" class="form-control" maxlength="6"
								style="text-align: center;" required />
							<div id="confirmPin">
								<button type="submit"
									class="btn btn-block btn-lg btn-success text-uppercase">Confirm</button>
							</div>
						</form>
						<span style="color: red;" th:utext="${message}"></span>
						<!-- // Pin Pad -->
					</div>
					<div class="col-xs-4">
						<div style="margin-top: 8rem;">
							<img th:src="@{/img/CardInsertedGreenLight.png}"
								style="width: 16em;" />
						</div>
					</div>
					<!-- /.col-xs-4 -->
				</div>
				<!-- // END login screen  -->
			</div>
		</div>
		<!-- // END row-->
	</div>
	<!-- /container -->
	<script type="text/javascript" th:src="@{/js/jquery.min.js}"></script>
	<script type="text/javascript" th:src="@{/js/flat-ui.min.js}"></script>
	<script type="text/javascript" th:src="@{/js/video.js}"></script>
	<script type="text/javascript" th:src="@{/js/application.js}"></script>
	<script>
		videojs.options.flash.swf = "js/video-js.swf";
		$(document).ready(function() {

		});
	</script>
</body>
</html>