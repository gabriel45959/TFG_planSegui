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
	<form:form method="post"  modelAttribute="cargarPedido"
		action="/plansegui/venta/registrarPedido">
		<script type="text/javascript">	$(document)
		.ready(
				function() {
					var counter = 0;

					$("#addrow")
							.on(
									"click",
									function() {
										var newRow = $("<tr>");
										var cols = "";
										var aux2='['+counter+']';
										
										cols += '<td><form:select id="producto" path="detallePedido[0].producto.id" name="producto' + counter + '" class="form-control"><form:option value="0">Seleccione...</form:option><form:options items="${listaProducto}" itemValue="id" itemLabel="nombre"/></form:select></td>'.replace("[0]",aux2);
										cols += '<td><form:input type="text" path="detallePedido[0].cantidad" class="form-control" name="cantidad' + counter + '"/></td>'.replace("[0]",aux2);
										cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Eliminar"></td>';
										newRow.append(cols);
										$("table.order-list")
												.append(newRow);
										counter++;
									});

					$("table.order-list").on("click", ".ibtnDel",
							function(event) {
								$(this).closest("tr").remove();
								counter -= 1
							});

				});

function calculateRow(row) {
	var price = +row.find('input[name^="price"]').val();

}

function calculateGrandTotal() {
	var grandTotal = 0;
	$("table.order-list").find('input[name^="price"]').each(function() {
		grandTotal += +$(this).val();
	});
	$("#grandtotal").text(grandTotal.toFixed(2));
}

</script>
		<div class="container">
			<div class="col-md-offset-1 col-md-10">
				<h2>Registrar un pedido de fabricación</h2>
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
						<div class="col-sm-12">
							<div class="row align-items-center">
								<div class="col">
									<div class="form-group">
										<label for="empresa">Empresa:</label> 
										<form:select id="empresa" path="empresa.id" 
											name="empresa" class="form-control" >
											<form:option value="0">Seleccione...</form:option>
											<form:options items="${listaEmpresa}" itemValue="id" itemLabel="nombre"/>
										</form:select>
										<form:errors path="empresa.id" cssClass="error" />
									</div>
								</div> 
								<div class="col">
									<div class="form-group">
										<label for="nroFactura">Numero de factura:</label>
										<form:input id="nroFactura" type="text" class="form-control" path="nroFactura" />
										<form:errors path="nroFactura" cssClass="error" />
									</div>
								</div>

								<div class="col">
									<div class="form-group">
										<label for="fechaEntrega">Fecha de entrega:</label> 
										<form:input
											id="fechaEntrega" type="date" name="fechaEntrega" path="fechaEntrega"
											class="form-control" />
									</div>
								</div>

							</div>
							<div class="row align-items-center">
								<div class="col">
									<div class="form-group">
										<label for="observaciones">Observacion:</label>
										<textarea class="form-control" rows="5" id="observaciones"
											name="observaciones" class="form-control" ></textarea>
											<form:errors path="observaciones" cssClass="error" />
									</div>

								</div>
							</div>
							<hr />
							<div class="row align-items-center">
								<div class="col">
									<button type="button" class="btn btn-info " id="addrow" />
									Agregar un producto
									</button>
								</div>
							</div>
							<br>
							<div class="row align-items-center">
								<div class="col">
									<div >
										<table class=" table order-list">
											<thead>
												<tr>
													<th >Nombre del producto</th>
													<th >Cantidad en Kg</th>
													<th>Eliminar</th>
												</tr>
											</thead>

										</table>
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