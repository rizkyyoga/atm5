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

#confirmPin a {
	padding: 0px;
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
							Your Account Number</h1>
						<input type="text" id="userPinInput" class="form-control"
							maxlength="6" style="text-align: center;" required="true" />
						<div id="confirmPin">
							<a href="#"
								class="btn btn-block btn-lg btn-success text-uppercase">Confirm</a>
						</div>
						<div
							style="display: block; padding-top: 150px; padding-bottom: 25px">
							<span class="text-uppercase text-center"><b>Upload CSV
									file to upload data account</b></span>
							<form class="form-horizontal" method="POST"
								th:action="@{/upload}" enctype="multipart/form-data">
								<input id="singleFileUploadInput" type="file" name="file"
									class="form-control-file" accept=".csv"
									style="padding: 20px 0px" />
								<button type="submit"
									class="btn btn-block btn-lg btn-info text-uppercase">Submit</button>
							</form>
						</div>
						<span style="color: red;" th:utext="${message}"></span> <span
							style="color: green;" th:utext="${notif}"></span>
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
	<script th:inline="javascript">
		/*<![CDATA[*/
		    var context = [[@{/}]];
		/*]]>*/
		videojs.options.flash.swf = "js/video-js.swf";
		$(document).ready(function() {
			$("#confirmPin").click(function() {
				window.location.replace(context+"pin?an=" + $("#userPinInput").val());
			});
		});
	</script>
</body>
</html>