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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
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
	$('#checkbox-value').text($('#checkboxHabilitado').val());

	$("#checkboxHabilitado").on('change', function() {
		if ($(this).is(':checked')) {
			$(this).attr('value', 'true');
		} else {
			$(this).attr('value', 'false');
		}

		$('#checkbox-value').text($('#checkbox1').val());
	});
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove();
		});
	}, 2060);
</script>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link dropdown-toggle"
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
			<h2>Registar nuevo usuario</h2>
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
				<div class="panel-heading"></div>
				<form:form method="post" modelAttribute="cargarUsuario"
					action="/plansegui/admin/crearUsuario">
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input id="nombreUsuario" type="text" class="form-control"
								path="nombreUsuario" placeholder="Nombre Usuario" />
							<form:errors path="nombreUsuario" cssClass="error" />
							<br> <br>
							<form:input id="nombre" type="text" class="form-control"
								path="nombre" placeholder="Nombre de la persona" />
							<form:errors path="nombre" cssClass="error" />
							<br> <br>
							<form:input id="apellido" type="text" class="form-control"
								path="apellido" placeholder="Apellido de la persona" />
							<form:errors path="apellido" cssClass="error" />
							<br> <br>
							<form:checkbox id="checkboxHabilitado" path="habilitado"
								class="inline checkbox" label="¿Habilitado?" value="false" />
							<form:errors path="habilitado" cssClass="error" />
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
							<form:input id="clave" type="password" class="form-control"
								path="clave" placeholder="Clave" />
							<form:errors path="clave" cssClass="error" />
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon">Rol</span>
							<form:input id="rol" type="text" class="form-control"
								path="role[0].rol" placeholder="Rol del usuario" />
							<form:errors path="role[0].rol" cssClass="error" />
						</div>

					</div>
					<!-- fin body -->
					<div class="panel-footer">
						<button type="submit" class="btn btn-warning">Guardar</button>
					</div>
				</form:form>
			</div>
		</div>

	</div>


</body>
</html>