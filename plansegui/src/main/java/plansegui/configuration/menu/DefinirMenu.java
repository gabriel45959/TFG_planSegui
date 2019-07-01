package plansegui.configuration.menu;

import java.util.Iterator;



import java.util.List;
import plansegui.hibernate.entities.Role;


public  class DefinirMenu {
	
	public static String USUARIO_CONECTADO;
	
	
	public static String setItemMenu(List<Role> role){
				
		String menuItem="";
		for (Iterator<Role> iterator = role.iterator(); iterator.hasNext();) {
			
			Role type = (Role) iterator.next();
			
			switch (type.getRol()) {
			case "ADMIN": 
				
				menuItem+="<li class=\"nav-item\"><a class=\"nav-link\" href=\"/plansegui/admin/crearUsuario/\">Registrar nuevo Usuario</a></li>"
							//+"<li><a data-toggle=\"modal\" href=\"/plansegui/admin/crearUsuario/\"></span>Registrar nuevo Usuario</a></li>"
						+ "<li class=\"nav-item\"><a class=\"nav-link\" href=\"/plansegui/admin/administrar/\">Listar usuarios</a></li>";
						//+ "<li><a data-toggle=\"modal\" href=\"/plansegui/admin/administrar/\"></span>Listar usuarios</a></li>";
								
				continue;
			case "VENTA":
				
				menuItem+="<li class=\"nav-item\"><a class=\"nav-link\" href=\"/plansegui/venta/registrarPedido\">Registrar un pedido</a></li>";
				//"<li><a data-toggle=\"modal\" href=\"/plansegui/venta/registrarPedido\"></span>Registrar un pedido</a></li>";
				continue;

			default:
				break;
			}
		}  	
		
	 return menuItem;	
	}
	
}
