<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>ATAR Calculator Main</title>
	<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
	<meta content="utf-8" http-equiv="encoding">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="stylemain.css" />
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

	<script src="jsmain.js"> </script>
	<script src="calc.js"> </script>
</head>

<body onload="calculator()" id="main">
	<div id="header">
		<br />
		<!--<h1>ATAR CALCULATOR</h1>-->
		<img id="title" src="img/ATARCalculator.png" alt="ATAR CALCULATOR" width="255" height="35" onclick="calculator()">
	</div>
	
	<div class="container-fluid" id="content_wrapper">
		<!-- content is the main display element, updatabed via ajax -->
		<div class="jumbotron" id="content">
		
		</div>
	</div>
	
	<div class="footer">
		<span id="footspan">
		<br />
			<a class="ajaxl" id="about" onclick="aboutDet()">&nbsp;About&nbsp;</a>|
			<a class="ajaxl" id="contact" onclick="contactDet()">Contact&nbsp;</a>
		</span>
	</div>
</body>

</html>