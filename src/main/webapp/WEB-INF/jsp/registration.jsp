<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <div class="row">
	  	<div class="col-12 offset-0 col-md-6 offset-md-3 col-lg-6 offset-lg-3">
 
        <h2 class="text-center">Registration</h2>
        <form:form method="POST" modelAttribute="user" class="form-horizontal">
             
                <div class="form-group">
                    <label for="firstName">First Name</label>
                        <form:input type="text" path="firstName" id="firstName" class="form-control" />
                        <div class="has-error">
                            <form:errors path="firstName" class="help-inline"/>
                        </div>
                </div>
     
                <div class="form-group">
                   <label for="lastName">Last Name</label>
                       <form:input type="text" path="lastName" id="lastName" class="form-control" />
                       <div class="has-error">
                           <form:errors path="lastName" class="help-inline"/>
                       </div>
                </div>            
			
                <div class="form-group">
                    <label for="login">Login *</label>
                        <form:input type="login" path="login" id="login" class="form-control" />
                        <div class="has-error">
                            <form:errors path="login" class="help-inline"/>
                        </div>
                </div>
			     
                <div class="form-group">
                    <label for="password">Password *</label>
                        <form:input type="password" path="password" id="password" class="form-control" />
                        <div class="has-error">
                            <form:errors path="password" class="help-inline"/>
                        </div>
                </div>

                <div class="form-group">
                    <label for="confirm_password">Confirm Password</label>
                        <input type="password" id="confirm_password" class="form-control" />
                 </div>
			     
                <div class="form-group">
                    <label for="email">Email</label>
                        <form:input type="text" path="email" id="email" class="form-control" />
                        <div class="has-error">
                            <form:errors path="email" class="help-inline"/>
                        </div>
            </div>
     
                <div class="form-group">
                	<input type="submit" value="Register" class="btn btn-primary disabled" 
                		disabled="disabled" id="registerButton" />
                </div>
        </form:form>
    </div>
     </div>
    