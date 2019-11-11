<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>ATM System Options: Account Details</title>
<!-- Loading Bootstrap -->
<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">

<!-- Loading Flat UI -->
<link th:href="@{/css/flat-ui.css}" rel="stylesheet">
<link th:href="@{/css/demo.css}" rel="stylesheet">
<link th:href="@{/css/custom.css}" rel="stylesheet">

<link rel="shortcut icon" th:href="@{/img/favicon.ico}">
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
				<h1 class="demo-section-title text-uppercase text-center">Account
					Details</h1>
				<h6 class="text-center" th:text="${accountNumber}"></h6>
				<br />
				<div class="row">
					<!-- 3/4 -->
					<!-- Balance -->
					<div class="col-xs-9">
						<div class="col-xs-12">
							<div class="tile">
								<h4 class="text-uppercase balance">
									<span>Destination</span><span style="color: black;"
										th:text="${destination}"></span>
								</h4>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="tile">
								<h4 class="text-uppercase balance">
									<span>Amount</span>$<span style="color: black;"
										th:text="${amount}"></span>
								</h4>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="tile">
								<h4 class="text-uppercase balance">
									<span>Reference</span><span style="color: black;"
										th:text="${reference}"></span>
								</h4>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="tile">
								<h4 class="text-uppercase balance">
									<span>Balance</span>$<span style="color: black;"
										th:text="${balance}"></span>
								</h4>
							</div>
						</div>
					</div>
					<!-- 1/4 -->
					<div class="col-xs-3">
						<div class="tile">
							<a id="back" th:href="@{/transaction}"
								class="btn btn-lg btn-inverse text-uppercase btn-padding"><span
								class="fui-arrow-left"></span> Back</a>
						</div>
						<div class="tile">
							<a id="cancelTransactin" th:href="@{/logout}"
								class="btn btn-lg btn-danger text-uppercase btn-padding"><span
								class="fui-cross"></span> Exit</a>
						</div>
					</div>
				</div>
				<!-- // END OPTIONS-->
			</div>
			<!--  // END column-->
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
