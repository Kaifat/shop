<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
	<head>
	    <title><tiles:getAsString name="title"/></title>
	    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
	    <link rel="stylesheet" href="${resourceContext}/layout.css">
	    <link rel="stylesheet" href="${resourceContext}/style.css">
	    <script type="text/javascript" src="${resourceContext}/jquery-3.2.1.min.js"></script>
	    
	</head>

    <body>
        <table>
	      <tr>
	        <td>
	          <tiles:insertAttribute name="header" />
	        </td>
	      </tr>
	      <tr>
	        <td>
	          <tiles:insertAttribute name="body" />
	        </td>
	      </tr>
	      <tr>
	        <td>
	          <tiles:insertAttribute name="footer" />
	        </td>
	      </tr>
	    </table>
	    
	    <script type="text/javascript" src="${resourceContext}/main.js"></script>
	    
  	</body>
</html>