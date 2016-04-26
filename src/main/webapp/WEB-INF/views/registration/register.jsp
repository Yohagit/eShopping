<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>

<div class="container">

</div>
<div class="container-fluid" style="padding: 0">
    <div class="row-fluid">

        <div class="span5"
             style="border-left: 1px solid #033833; padding-left: 30px;">
            <h2>Customer Registration</h2>
            <div class="col-lg-7 col-md-7 col-sm-7" style="">
                <springForm:form action="/eshopping/registration/register" method="POST"
                                 modelAttribute="reg_user" class="login-form" novalidate="novalidate">

                    <div class="form-group group"
                         style="padding-top: 10px; padding-bottom: 10px;">
                        <label for="rf-email" style="font-weight: bold;">Email</label> 
                        <form:input  class="form-control" type="text" id="inputEmail2" path="email"
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

                    <input class="btn btn-primary" type="submit" value="Register a Customer" />
                    <fieldset>

                        <div id="RB">

                            <label class="radio-inline" for="rd_customer" > 
                                <form:radiobutton path="role" value="customer" id="rd_customer" /> I want to register as a customer
                            </label> <br />

                            <label class="radio-inline"  for="rd_vendor">  
                                <form:radiobutton path="role" value="vendor" id="rd_vendor" /> I want to register as a vendor
                            </label> <br />
                        </div>

                        <script>
                          document.getElementById("RB").style.visibility = "hidden";
                        </script>
                    </fieldset>

                </springForm:form>
            </div>
        </div>

        <div class="span5"
             style="border-left: 1px solid #033833; padding-left: 30px;">
            <h2>Vendor Registration</h2>
            <springForm:form action="/eshopping/USRegCO" method="POST"
                             modelAttribute="reg_user" class="login-form" novalidate="novalidate">
                <div class="form-group group"
                     style="padding-top: 10px; padding-bottom: 10px;">
                    <label for="rf-regNo" style="font-weight: bold;">Registration Number</label> 
                                       
                    <form:input  class="form-control" type="text" id="regNo" path="regNo"
                                 placeholder="Enter company name" autocomplete="off" maxlength="4"
                                            pattern="\d{4}" title="Register Number" 
                                 style="width: 100%"/>
                    
                    <form:errors path="regNo" cssClass="alert alert-danger" />
                </div>

                <div class="form-group group"
                     style="padding-top: 10px; padding-bottom: 10px;">
                    <label for="rf-cmpName" style="font-weight: bold;">Company Name</label> 
                    <form:input  class="form-control" type="text" id="cmpName" path="cmpName"
                                 placeholder="Enter company name" autocomplete="off" maxlength="20"
                                             title="Company Name" 
                                 style="width: 100%"/>
                    
                    
                    <form:errors path="cmpName" cssClass="alert alert-danger" />
                </div>
                <input class="btn btn-primary" type="submit" value="Test vendor validity" />
                </springForm:form>
                <springForm:form action="/eshopping/registration/register" method="POST"
                             modelAttribute="reg_user" class="login-form" novalidate="novalidate">

            </springForm:form>       
        </div>
    </div>
</div>
