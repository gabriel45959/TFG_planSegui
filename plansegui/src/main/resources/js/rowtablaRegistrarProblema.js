$(document).ready(
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