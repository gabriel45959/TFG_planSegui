package plansegui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import plansegui.configuration.menu.DefinirMenu;
import plansegui.hibernate.services.UsuarioService;


@Controller
@RequestMapping("/venta")
public class VentaController {

	private Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = { "/registrarPedido" }, method = RequestMethod.GET)
	public ModelAndView registrarPedido(){
		
		log.info("VentaController--------------------------------------------------------------------------registrarPedido");
		
		ModelAndView model = new ModelAndView();
		model.addObject("NombrePantalla","Registrar Pedido");
		model.setViewName("/venta/registrarPedido");
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		return model;
	}
	
	
}
