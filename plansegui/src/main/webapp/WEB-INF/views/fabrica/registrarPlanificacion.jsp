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

	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark nav-pills">
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
	<form:form method="post" modelAttribute="planificar"
		action="/plansegui/fabrica/registrarPlanificacion">

		<div class="container">
			<div class="col-md-offset-1 col-md-10">
				<h2>Registrar Planifiacion</h2>
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
						<div class="col-md-12">
							<div class="row">

								<div class="col-md-4">
									<div class="form-group">
										<label>Empresa:</label> <input type="text"
											value="${detallePedido.getPedido().getEmpresa().nombre}"
											disabled="disabled" class="form-control" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="nroFactura">Producto:</label> <input type="text"
											value="${detallePedido.getProducto().nombre}"
											disabled="disabled" class="form-control" />
									</div>

								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Fecha de entrega:</label> <input type="text"
											value="${detallePedido.getPedido().fechaEntrega}"
											disabled="disabled" class="form-control" />
									</div>
								</div>
							</div>
							<hr>
							<div class="row align-items-center">
								<div class="col">
									<div class="form-group">
										<label for="observaciones">Planificar:</label>
										<div class="row mt-2">

											<div class="col-md-6">
												<div class="form-group">
													<label>Fecha estimada de inicio:</label> <form:input type="date" path="fechaInicioEstimada"
														value="${fechaPlanificacion.getFechaInicioEstimada()}"
													    class="form-control" />
												</div>
											</div>
											<form:hidden path="detallePedido.id" value="${detallePedido.getId()}"/>
											<div class="col-md-6">
												<div class="form-group">
													<label for="nroFactura">Fecha estimada de fin:</label> <form:input
														type="date" value="${fechaPlanificacion.getFechaEntregaEstimada()}"
														path="fechaEntregaEstimada"
														 class="form-control" />
												</div>

											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!-- fin body -->
					<div class="panel-footer">
						<button type="submit" class="btn btn-warning">Guardar</button>
					</div>

				</div>

			</div>

		</div>
	</form:form>
</body>
</html>