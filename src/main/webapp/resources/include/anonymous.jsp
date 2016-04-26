<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="logoArea" class="navbar">
				<a id="smallScreen" data-target="#topMenu" data-toggle="collapse"
					class="btn btn-navbar"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<div class="navbar-inner">
					<!-- Logo Image -->

					<a class="brand" href="/eshopping"><img

                                                src="/eshopping/resources/themes/images/logo.png" alt="eShopping" style="height:49px !important;" /><span style="color:#FED136; margin:0 3px 0 10px;">eShopping</a>

					<form:form action="/eshopping/product/search" methodParam="query"
					 	class="form-inline navbar-search"
						method="POST">
						<input type="text" name="query" value="${query}" />
						<button type="submit" id="submitButton" class="btn btn-primary">Search</button>
					</form:form>
					<ul id="topMenu" class="nav pull-right">
						<li class=""><a href="/eshopping/signin">Sign in</a></li>
                                                <li class=""><a href="/eshopping/registration"> Register</a></li>
					</ul>
				</div>
			</div>
