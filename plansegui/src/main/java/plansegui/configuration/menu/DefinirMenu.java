package plansegui.configuration.menu;


import java.util.List;
import plansegui.hibernate.entities.Role;

public class DefinirMenu {

	public static String USUARIO_CONECTADO;

	public static String setItemMenu(List<Role> role,String RolPantalla) {

		String menuItem = "";
		for (Role rol : role) {
			
			switch (rol.getRol()) {
			case "ADMIN":

				menuItem += "<li class=\"nav-item dropdown \">"
						+ "<a class=\"nav-link dropdown-toggle MenuADMIN \" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Administrar</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/admin/crearUsuario\">Registrar nuevo Usuario</a>"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/admin/listarUsuarios\">Listar usuarios</a>"
						+ "</div></div></li>"
						+ "<li class=\"nav-item dropdown \">"
						+ "<a class=\"nav-link dropdown-toggle MenuVENTA\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Ventas</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/venta/registrarPedido\">Registrar un pedido</a>"
						+ "</div></div></li>"
						+ "<li class=\"nav-item dropdown \">"
						+ "<a class=\"nav-link dropdown-toggle MenuFABRICA\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Fabricación</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/fabrica/completarPlanificacion\">Completar planificación</a>"
						+ "</div></div></li>"
						+ "<li class=\"nav-item dropdown \">"
						+ "<a class=\"nav-link dropdown-toggle MenuDEPOSITO\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Deposito</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/deposito/registrarIngresoMateriaPrima\">Registar Ingreso Materia Prima</a>"
						+ "</div></div></li>"
						+ "<li class=\"nav-item dropdown \">"
						+ "<a class=\"nav-link dropdown-toggle MenuCOMPRA\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Compra</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/compra/registrarCompra\">Registrar compra materia prima</a>"
						+ "</div></div></li>";
				continue;
			case "VENTA":
				menuItem += "<li class=\"nav-item dropdown MenuVENTA\">"
						+ "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Ventas</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/venta/registrarPedido\">Registrar un pedido</a>"
						+ "</div></div></li>";
				continue;
			case "FABRICA":
				menuItem += "<li class=\"nav-item dropdown MenuFABRICA\">"
						+ "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Fabricación</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/fabrica/completarPlanificacion\">Completar planificación</a>"
						+ "</div></div></li>";
				continue;
			case "DEPOSITO":
				menuItem += "<li class=\"nav-item dropdown MenuDEPOSITO\">"
						+ "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Deposito</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/deposito/registrarIngresoMateriaPrima\">Registar Ingreso Materia Prima</a>"
						+ "</div></div></li>";
				continue;
			case "COMPRA":
				menuItem += "<li class=\"nav-item dropdown MenuCOMPRA\">"
						+ "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Compra</a>"

						+ " <div class=\"dropdown-menu\">"
						+ "<a class=\"dropdown-item\" href=\"/plansegui/compra/registrarCompra\">Compra</a>"
						+ "</div></div></li>";
				continue;
			default:
				break;
			}

			
		}

		return menuItem.replaceAll("Menu"+RolPantalla, "active");
	}

}
