<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>

<div class="container">

</div>
<div class="container-fluid" style="padding: 0">
    <div class="row-fluid">
<h5>Thank you for your interest in our online store. Please continue registration:</h5>
        <div class="span5"
             style="border-left: 1px solid #033833; padding-left: 30px;">
            
            <springForm:form action="/eshopping/registration/register" method="POST"
                             modelAttribute="reg_user" class="login-form" novalidate="novalidate">
                          
                <div id="V" >   

                    <div class="form-group group"
                         style="padding-top: 10px; padding-bottom: 10px;">
                        <label for="rf-email" style="font-weight: bold;">Email</label> 
                        <form:input  class="form-control" type="text"  path="email"
                                     placeholder="Enter email" required=""
                                     style="width: 100%"/>
                        <form:errors path="email" cssClass="alert alert-danger" />
                    </div>
                    <div class="form-group group"
                         style="padding-top: 10px; padding-bottom: 10px;">
                        <label for="rf-password" style="font-weight: bold;">Password</label>
                        <form:input  class="form-control" type="password" path="password"
                                     placeholder="Enter password" required=""
                                     style="width: 100%"/>
                        <form:errors path="password" cssClass="alert alert-danger" />
                    </div>
                    <div class="form-group group"
                         style="padding-top: 10px; padding-bottom: 10px;">
                        <label for="rf-password-repeat" style="font-weight: bold;">Repeat
                            password</label> 
                            <form:input  class="form-control" type="password" path="confirmPassword"
                                         placeholder="Repeat password" required=""
                                         style="width: 100%"/>
                            <form:errors path="confirmPassword" cssClass="alert alert-danger" />
                    </div>

<div id="RBB">

                    <label class="radio-inline"  for="rd_vendor" >  
                        <form:radiobutton path="role" value="vendor" id="rd_vendor" /> I want to register as a vendor
                    </label> 
                </div>
                <script>
                    document.getElementById("rd_vendor").checked = true;
                </script>
                <script>
                    document.getElementById("RBB").style.visibility = "hidden";
                </script>
                    <input id="NO" class="btn btn-primary" type="submit" value="Register a Vendor" />
                </div> 
            </springForm:form>  
            </div>
        </div>
    </div>

