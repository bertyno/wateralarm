<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>Security Solution: Edit User Details</title>
</head>

<body>
<div id="main_wrapper">

<h1>Edit User Details</h1>

<form:form commandName="user">
	<table>
		<tr>
			<td>Name:</td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name"/></td>
		</tr>
		<tr>
			<td>Pass:</td>
			<td><form:input path="password"/></td>
			<td><form:errors path="password"/></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input path="email"/></td>
			<td><form:errors path="email"/></td>
		</tr>
		<tr>
		<c:if test="${user.id != null}">
			<td colspan="3"><input type="submit" value="Update User"/></td>
		</c:if>
		
		<c:if test="${user.id == null}">
			<td colspan="3"><input type="submit" value="New User"/></td>
		</c:if>
		</tr>
	</table>
</form:form>




<c:if test="${user.id != null}">
<form:form commandName="plant" action="newPlant">
	<table>
		<tr>
			<td>Name:</td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name"/></td>
		</tr>
		
		<tr>
			<td colspan="3"><input type="submit" value="New Plant"/></td>
		</tr>
	</table>
</form:form>
</c:if>

<!--  Don't show logout unless someone is logged in -->
<security:authentication property="principal" var="principal" scope="page" />

<c:if test="${principal != 'anonymousUser'} ">
	<p><a href="<c:url value="/j_spring_security_logout"/>">Logout</a> (${principal.username})</p>
</c:if>

</div>
</body>

</html>
