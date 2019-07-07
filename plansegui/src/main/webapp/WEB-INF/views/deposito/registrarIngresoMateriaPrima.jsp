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
	<!-- CONTENIDOS -->
	<div class="continer">
		<div class="col-md-offset-1 col-md-10">
			<h2>Registrar ingreso materia prima</h2>

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
								<th>Materia prima</th>
								<th>Cantidad</th>
								<th>Estado del pedido</th>
								<th>Número de factura</th>
								<th>Fecha de ingreso</th>
								<th>Ingreso materia prima</th>
							</tr>
						</thead>

						<tbody>
							<tr data-toggle="collapse" class="accordion-toggle">

								<c:forEach var="detallePedido" items="${ListDetallePedidos}">
									<c:forEach var="compraMateriaPrima" items="${detallePedido.getCompraMateriaPrima()}">
									<c:forEach var="detalleCompraMateriaPrima" items="${compraMateriaPrima.getDetalleCompraMateriaPrima()}">
										<tr>
											<td>${detallePedido.getId()}</td>
											<td>${detallePedido.getPedido().getEmpresa().getNombre()}</td>
											<td>${detalleCompraMateriaPrima.getMateriaPrima().nombre}</td>
											<td>${detalleCompraMateriaPrima.cantidad}</td>

											<td><div
													class="bg-${detallePedido.getEstado().valorVisual}">${detallePedido.getEstado().nombre}</div></td>
											<td>${compraMateriaPrima.nroFactura}</td>
											<td><input id="fechaEntrega" type="date"
												name="fechaEntrega" class="form-control" /></td>


											<td><a class="btn btn-primary"
												href='/plansegui/fabrica/registrarPlanificacion/${detallePedido.getId()}'>Guardar</a></td>
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