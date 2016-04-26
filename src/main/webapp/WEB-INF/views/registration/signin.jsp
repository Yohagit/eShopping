<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>


<div class="container">
	<h2>Sign in</h2>
</div>
<div class="container-fluid" style="padding: 0">
	<div class="row-fluid">
		<div class="span5" style="padding-right: 10px;">
			<!--Sidebar content-->

			<div>
				<!--Login-->
				<div class="col-lg-5 col-md-5 col-sm-5">
					<form:form action="/eshopping/registration/signin" method="POST"
						modelAttribute="user" class="login-form" novalidate="novalidate">
						
						<c:if test="${ login_error }">
							<label class="error" style="font-weight: bold; color:red">User name or password are incorrect</label>
						</c:if>
						<div class="form-group "
							style="padding-top: 10px; padding-bottom: 10px;">
							<label for="log-email2" style="font-weight: bold;">Email</label>
							<form:input  class="form-control" type="text" id="inputEmail" path="email"
									placeholder="Enter your email" required=""
								style="width: 100%"/>
							
						</div>
						<div class="form-group group"
							style="padding-top: 10px; padding-bottom: 10px;">
							<label for="log-password2" style="font-weight: bold;">Password</label>
							<form:input type="password" class="form-control" id="inputPassword" path="password"
									placeholder="Enter your password" required=""
								style="width: 100%" />
						</div>
						<input class="btn btn-primary" type="submit" value="Login">
					</form:form>
				</div>

			</div>
		</div>
		
	</div>
</div>
