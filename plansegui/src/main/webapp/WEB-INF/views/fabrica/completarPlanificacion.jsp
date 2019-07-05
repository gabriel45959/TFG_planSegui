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
	function myFunction() {
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("busqueda");
		filter = input.value.toUpperCase();
		table = document.getElementById("ListaPedidos");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			if (td) {
				txtValue = td.textContent || td.innerText;
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
</script>
<style type="text/css">
.error {
	color: red;
}

#busqueda {
	width: 100%;
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

	<div class="continer">
		<div class="col-md-offset-1 col-md-10">
			<h2>Completar Planificación de pedidos</h2>

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
									<div class="input-group">
										<label>Nro. de Pedido</label> <input type="text" id="busqueda"
											onkeyup="myFunction()" placeholder="Buscar pedido...">
									</div>
								</th>
								<th>Cliente</th>
								<th>Producto</th>
								<th>Cantidad en Kg</th>
								<th>Estado del pedido</th>
								<th>Fecha de entrega</th>
								<th>Planificar</th>
								<th>Registrar problema</th>
							</tr>
						</thead>

						<tbody>
							<!-- loop para mostrar el usuario -->
							<c:forEach var="pedidos" items="${ListPedidos}">
								<tr data-toggle="collapse" data-target="#demo${pedidos.getId()}"
									class="accordion-toggle">

									<c:forEach var="detallePedido"
										items="${pedidos.getDetallePedido()}">
										<tr>
											<td>${detallePedido.getId()}</td>
											<td>${pedidos.getEmpresa().getNombre()}</td>
											<td>${detallePedido.getProducto().nombre}</td>
											<td>${detallePedido.getCantidad()}</td>
											<td><div class="bg-${detallePedido.getEstado().valorVisual}">${detallePedido.getEstado().nombre}</div></td>
											<td>${pedidos.getFechaEntrega()}</td>
											<td><a
												href='/plansegui/fabrica/registrarPlanificacion/${detallePedido.getId()}'><span
													class="glyphicon glyphicon-calendar"></span></a></td>
											<td><a
												href='/plansegui/fabrica/registrarProblema/${detallePedido.getId()}'><span
													class="glyphicon glyphicon-bullhorn"></span></a></td>
										</tr>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>


</body>
</html>