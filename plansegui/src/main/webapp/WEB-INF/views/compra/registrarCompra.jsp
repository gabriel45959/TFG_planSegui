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

	<nav
		class="navbar navbar-expand-sm bg-secondary navbar-dark  nav-pills">
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
	<div class="continer">
		<div class="col-md-offset-1 col-md-10">
			<h2>Registrar compra materia prima</h2>

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

					<table id="ListaPedidos" class="table table-striped table-hover"
						style="border-collapse: collapse;">

						<thead>
							<tr>
								<th>
									<div class="input-group " style="width: 160px">
										<label>Nro. de Pedido</label> <input type="text" id="busqueda"
											onkeyup="funcionBuscar()" placeholder="Buscar pedido...">
									</div>
								</th>
								<th>Cliente</th>
								<th>Materia Prima</th>
								<th>Cantidad en Kg</th>
								<th>Estado del pedido</th>
								<th>Fecha de arribo</th>
								<th>Número de factura</th>
								<th>Guardar Compra</th>
							</tr>
						</thead>

						<tbody>
							<tr data-toggle="collapse" class="accordion-toggle">

								<c:forEach var="detallePedidoPag" items="${ListDetallePedidos}">
									<c:forEach var="compraMateriaPrimaPage"
										items="${detallePedidoPag.getCompraMateriaPrima()}">
										<c:forEach var="detalleCompraMateriaPrimaPage"
											items="${compraMateriaPrimaPage.getDetalleCompraMateriaPrima()}">
											<tr>
												<td>${detallePedidoPag.getId()}</td>
												<td>${detallePedidoPag.getPedido().getEmpresa().getNombre()}</td>
												<td>${detalleCompraMateriaPrimaPage.getMateriaPrima().nombre}</td>
												<td>${detalleCompraMateriaPrimaPage.cantidad}</td>
												<td><div
														class="bg-${detallePedidoPag.getEstado().valorVisual}">${detallePedidoPag.getEstado().nombre}</div></td>
												<form:form method="post" modelAttribute="compraMateriaPrima"
													action="/plansegui/compra/registrarCompra">
													<td><c:choose>
															<c:when
																test="${detalleCompraMateriaPrimaPage.fechaLlegada != null}">
																<form:input id="fechaLlegada" type="date"
																	path="fechaLlegada" name="fechaLlegada"
																	class="form-control"
																	value="${detalleCompraMateriaPrimaPage.fechaLlegada}" />
															</c:when>
															<c:otherwise>
																<form:input id="fechaLlegada" type="date"
																	name="fechaLlegada" class="form-control"
																	path="detalleCompraMateriaPrima[0].fechaLlegada" />
															</c:otherwise>
														</c:choose></td>

													<td><form:input id="nroFactura" type="text"
															class="form-control"
															path="detalleCompraMateriaPrima[0].nroFactura"
															value="${detalleCompraMateriaPrimaPage.nroFactura}" /> <form:errors
															path="nroFactura" cssClass="error" /></td>

													<form:hidden path="detalleCompraMateriaPrima[0].id"
														value="${detalleCompraMateriaPrimaPage.id}" />

													<form:hidden path="detallePedido.id"
														value="${detallePedidoPag.id}" />

													<td><button type="submit" class="btn btn-warning">Guardar</button></td>
												</form:form>
											</tr>

										</c:forEach>
									</c:forEach>
								</c:forEach>

							</tr>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</body>
</html>