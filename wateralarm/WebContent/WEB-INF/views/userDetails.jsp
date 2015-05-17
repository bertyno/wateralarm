<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>Security Solution: User Details</title>
</head>

<body>
<div id="main_wrapper">

<h1>User Details</h1>

<table>
	<tr><td>
		<table>
			
			<tr>
				<td>Name:</td>
				<td>${user.name}</td>
			</tr>
			<tr>
				<td>Pass:</td>
				<td>${user.password}</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>Enabled:</td>
				<td>${user.enabled}</td>
			</tr>
		</table>
	</td></tr>
	<security:authorize ifAllGranted="ROLE_VIEWER">
		<tr><td>
			<table>
				<thead>
					<tr>
						<td>Plants:</td>
					</tr>
					<tr>
						<td>Name</td>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${user.plants}" var="plant">
						<tr>
							<td>${plant.name}</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</td></tr>
	</security:authorize>
</table>



 <security:authentication property="principal" var="principal" scope="page" /> 

<security:authorize access="hasRole('ROLE_EDITOR') or ${principal.username == user.name}" >
	<p><a href="editUser?entityId=${user.id}">Edit User (to add plants)</a></p>
</security:authorize>


<!--  Don't show logout unless someone is logged in -->

<c:if test="${principal != null}">
	<p><a href="<c:url value="/j_spring_security_logout"/>">Logout</a> (${principal.username})</p>
(${principal.username})
</c:if>
(${user.name})
<p>&#160;</p>   <!--  Force blank line at end of page -->

</div>
</body>

</html>
