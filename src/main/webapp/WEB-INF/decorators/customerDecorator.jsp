<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>eShopping</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	 
	<!-- Bootstrap style -->
	<link id="callCss" rel="stylesheet"
		href="/eshopping/resources/themes/bootshop/bootstrap.min.css"
		media="screen" />
	<link href="/eshopping/resources/themes/css/base.css" rel="stylesheet"
		media="screen" />
		<!-- Bootstrap style responsive -->
	<link href="/eshopping/resources/themes/css/bootstrap-responsive.min.css"
		rel="stylesheet" />
	<link href="/eshopping/resources/themes/css/font-awesome.css"
		rel="stylesheet" type="text/css">
	<!-- Google-code-prettify -->
	<link
		href="/eshopping/resources/themes/js/google-code-prettify/prettify.css"
		rel="stylesheet" />
</head>
<body>
	<div>
		
		<div class="container">
			<div id="welcomeLine" class="row">
				<div class="span6">
					Welcome!<strong> <c:out value="${sessionScope.user.email} " /></strong>
				</div>
				<div class="span6">
					<div class="pull-right">
						<a
							href="/eshopping/registration/logout"><span
							class="btn btn-mini btn-primary">Logout </span> </a>

					</div>
				</div>
			</div>
			<div class="container">
				<!-- Navbar ================================================== -->
				<div id="logoArea" class="navbar">
				
					<div class="navbar-inner">
						<!-- Logo Image -->
						<a class="brand" href="/eshopping"><img
							src="/eshopping/resources/themes/images/logo.png" alt="eShopping" style="height:49px !important;" /><span style="color:#FED136; margin:0 3px 0 10px;">eShopping</a>
						
						<ul class="nav nav-pills pull-right">
							<li class=""><a href="normal.html">Home</a></li>
							<li class=""><a href="normal.html">Profile</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="container">
				<sitemesh:write property='body' />
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>


