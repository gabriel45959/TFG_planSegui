<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${NombrePantalla}</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/js/jquery_1.12.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap_3.3.7.min.js"></script>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"></a>
			</div>
			${MenuOpcionExtras}
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><span
						class="glyphicon glyphicon-user"></span> <c:if
							test="${pageContext.request.userPrincipal.name != null}">
					${pageContext.request.userPrincipal.name} </c:if> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:formSubmit()"> <span
								class="glyphicon glyphicon-log-in"></span> Salir
						</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<!-- CONTENIDOS -->
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2>Lista de usuarios</h2>
			<hr />
			<br /> <br />
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">lista de Usuarios</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>nombreUsuario</th>
							<th>nombre</th>
							<th>apellido</th>
						</tr>

						<!-- loop over and print our customers -->
						<c:forEach var="tempCustomer" items="${usuarios}">

							<!-- construct an "update" link with customer id -->
							<c:url var="updateLink" value="/customer/updateForm">
								<c:param name="customerId" value="${tempCustomer.nombreUsuario}" />
							</c:url>

							<!-- construct an "delete" link with customer id -->
							<c:url var="deleteLink" value="/customer/delete">
								<c:param name="customerId" value="${tempCustomer.nombreUsuario}" />
							</c:url>

							<tr>
								<td>${tempCustomer.nombreUsuario}</td>
								<td>${tempCustomer.nombre}</td>
								<td>${tempCustomer.apellido}</td>

								<td>
									<!-- display the update link --> <a href="${updateLink}">Update</a>
									| <a href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
								</td>

							</tr>

						</c:forEach>

					</table>

				</div>
			</div>
		</div>

	</div>

</body>
</html>