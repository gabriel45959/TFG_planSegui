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
	<form:form method="post" modelAttribute="registrarProblema"
		action="/plansegui/fabrica/grabarProblema">

		<div class="container">
			<div class="col-md-offset-1 col-md-10">
				<h2>Registar nuevo Problema</h2>
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
					<div class="panel-body">
						<div class="col-sm-12">
							<div class="row align-items-center">
								<div class="col">
									<div class="form-group">
										<label for="embresa">Empresa:</label> <input id="empresa"
											type="text" class="form-control"
											value="${detallePedido.pedido.empresa.nombre}"
											disabled="disabled" />

									</div>
								</div>
								<div class="col">
									<div class="form-group">
										<label for="producto">Producto:</label> <input id="producto"
											type="text" class="form-control"
											value="${detallePedido.producto.nombre}" disabled="disabled" />
									</div>
								</div>

								<div class="col">
									<div class="form-group">
										<label for="cantidad">Cantidad:</label> <input id="cantidad"
											type="text" class="form-control"
											value="${detallePedido.cantidad}" disabled="disabled" />

									</div>
								</div>
								<div class="col">
									<div class="form-group">
										<label for="estado">Estado:</label> <input id="estado"
											type="text" class="form-control"
											value="${detallePedido.estado.nombre}" disabled="disabled" />

									</div>
								</div>
							</div>

							<hr />
							<!-- detallePedido -->
							<form:input type="hidden" path="detallePedido.id" value="${detallePedido.id}"/>
							<div class="row align-items-center">
								<div class="col">
									<div class="form-group">
										<label for="tipoProblema">Tipo de Problema:</label>
										<form:select id="tipoProblema" path="tipoProblema.id"
											name="tipoProblema" class="form-control">
											<form:option value="0">Seleccione...</form:option>
											<form:options items="${listTipoProblema}" itemValue="id"
												itemLabel="nombre" />
										</form:select>

									</div> 
								</div>
								<div class="col">
									<div class="form-group">
										<label for="fechaResolucion">Fecha de Resoluciòn:</label>
										<form:input id="fechaResolucion" type="date"
											name="fechaResolucion" path="fechaResolucion"
											class="form-control" />
									</div>
								</div>
							</div>
							<br>
							<div class="row align-items-center">
								<div class="col">
								 <label for="observaciones">Observaciones:</label>
										<textarea class="form-control" rows="5" id="observaciones"
											name="observaciones" class="form-control"></textarea>
										<form:errors path="observaciones" cssClass="error" />
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