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
<link th:href="@{/plugins/datatables/datatables.min.css}"
	rel="stylesheet">
<link
	th:href="@{/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css}"
	rel="stylesheet">

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
							<table
								class="table table-striped table-bordered table-hover table-checkable order-column"
								id="tableViewTransaction">
								<thead>
									<tr>
										<th class="hide"></th>
										<th>Date</th>
										<th>Type</th>
										<th>Amount</th>
										<th>Destination</th>
										<th>Reference</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<!-- 1/4 -->
					<div class="col-xs-3">
						<div class="tile">
							<a id="back" href="/transaction"
								class="btn btn-lg btn-inverse text-uppercase btn-padding"><span
								class="fui-arrow-left"></span> Back</a>
						</div>
						<div class="tile">
							<a id="cancelTransactin" href="/logout"
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
	<script type="text/javascript"
		th:src="@{/plugins/datatables/datatables.min.js}"></script>
	<script type="text/javascript"
		th:src="@{/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js}"></script>
	<script>
		videojs.options.flash.swf = "js/video-js.swf";
		$(document)
				.ready(
						function() {
							var table = $('#tableViewTransaction');
							table
									.dataTable({
										"dom" : 'rtip',
										scrollCollapse : true,
										"scrollX" : '100%',
										bAutoWidth : false,
										"language" : {
											"aria" : {
												"sortAscending" : ": activate to sort column ascending",
												"sortDescending" : ": activate to sort column descending"
											},
											"emptyTable" : "No data available in table",
											"info" : "Showing _START_ to _END_ of _TOTAL_ records",
											"infoEmpty" : "No records found",
											"infoFiltered" : "(filtered1 from _MAX_ total records)",
											"lengthMenu" : "Show _MENU_",
											"search" : "Search:",
											"zeroRecords" : "No matching records found",
											"paginate" : {
												"previous" : "Prev",
												"next" : "Next",
												"last" : "Last",
												"first" : "First"
											}
										},
										"bStateSave" : false,
										"lengthMenu" : [ [ 5, 15, 20, -1 ],
												[ 5, 15, 20, "All" ] ],
										"pageLength" : 5,
										"pagingType" : "bootstrap_full_number",
										"columnDefs" : [ {
											'orderable' : false,
											'targets' : [ 0, 1, 2, 3, 4, 5 ]
										}, {
											"searchable" : false,
											"targets" : [ 0, 1, 2, 3, 4, 5 ]
										} ],
										"processing" : true,
										"serverSide" : true,
										"ajax" : {
											"type" : "GET",
											"url" : "/getDataTransaction",
											"data" : function(data) {
												return "draw=" + data.draw
														+ "&length="
														+ data.length
														+ "&start="
														+ data.start;
											},
											"complete" : function(response) {
											}
										},
										createdRow : function(row, data, index) {
											$('td', row).eq(0).addClass('hide');
										},
										"drawCallback" : function(settings) {
											$('.fa-angle-right').html('>');
											$('.fa-angle-double-right').html('>>');
											$('.fa-angle-left').html('<');
											$('.fa-angle-double-left').html('<<');
										}
									});
						});
	</script>
</body>
</html>
