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
@RequestMapping("/compra")
public class CompraController {

	
	private Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = { "/registrarCompra" }, method = RequestMethod.GET)
	public ModelAndView registrarCompra() {
		
		ModelAndView model = new ModelAndView();

		log.info("DepositoController -------------------------------------------------------------- registrarCompra");
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		model.addObject("NombrePantalla","Registrar Compra materia prima");
		model.setViewName("/compra/registrarCompra");
		
		return model;

	}
}
