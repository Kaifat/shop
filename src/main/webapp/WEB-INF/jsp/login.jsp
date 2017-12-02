<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
	    <form:form modelAttribute="loginForm">
	         <form:input path="email" />
	         <form:input type="password" path="password" />
	         <input type="submit" class="btn btn-success" value="login" />
	     </form:form>
	  
    </body>
</html>