<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

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

<style type="text/css" id="enject"></style>
</head>
<body>
	<div>
		<div class="container">
			<div id="welcomeLine" class="row">
				<div class="span6">
					Welcome <strong> <c:out value="${user.email }" /></strong>!
				</div>
				<div class="span6">
					<div class="pull-right">
						<a href="/eshopping/product/cart"><span></span></a> </a> <a
							href="/eshopping/product/cart"><span class="btn btn-mini btn-primary"><i
								class="icon-shopping-cart icon-white"></i>${size} Items in your
								cart </span> </a>
					</div>
				</div>
			</div>
			<!-- Menu bar============================ -->
			<c:if test="${status}">
				<jsp:include page="/resources/include/loggedin.jsp" />
			</c:if>
			<c:if test="${!status}">
				<jsp:include page="/resources/include/anonymous.jsp" />
			</c:if>
			
			
			<!-- Side bar ======================================= -->
			<div>
				<div class="container">
					
					<div class="row">

						<!-- End of side bar========================================= -->
						<sitemesh:write property='body' />
					</div>

					<script
						src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
					<script
						src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
				</div>
			</div>
		</div>
	</div>

</body>