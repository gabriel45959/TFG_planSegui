<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${NombrePantalla}</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap_3.4.1.min.css">
<script src="${pageContext.request.contextPath}/js/jquery_3.4.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap_4.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper_1.14.7.min.js"></script>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}

	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove();
		});
	}, 1060);
</script>
</head>
<body>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark nav-pills">
		<ul class="navbar-nav ">
			<li class="nav-item active"><a class="nav-link dropdown-toggle "
				id="navbardrop" data-toggle="dropdown"> <span
					class="glyphicon glyphicon-user"></span> <c:if
						test="${pageContext.request.userPrincipal.name != null}">
					${pageContext.request.userPrincipal.name} </c:if>
			</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="javascript:formSubmit()"
						class="glyphicon glyphicon-log-in">Salir</a>
				</div></li>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
					${MenuOpcionExtras} </c:if>
		</ul>
	</nav>


	<!-- CONTENIDOS -->

	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2>Lista de usuarios</h2>
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<hr />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title"></div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>Nombre de usuario</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Roles</th>
							<th>¿Habilitado?</th>
						</tr>

						<!-- loop para mostrar el usuario -->
						<c:forEach var="usuario" items="${usuarios}">

							<tr>
								<td>${usuario.nombreUsuario}</td>
								<td>${usuario.nombre}</td>
								<td>${usuario.apellido}</td>
								<td><c:forEach var="usuarioRole"
										items="${usuario.getRole()}">
											${usuarioRole.rol}<br>
									</c:forEach></td>
								<td>${usuario.habilitado?"SI":"NO"}</td>
							</tr>

						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
</html>