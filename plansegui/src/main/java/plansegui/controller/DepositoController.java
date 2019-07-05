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
@RequestMapping("/deposito")
public class DepositoController {

	private Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = { "/registrarIngresoMateriaPrima" }, method = RequestMethod.GET)
	public ModelAndView registrarIngresoMateriaPrima() {
		
		ModelAndView model = new ModelAndView();

		log.info("DepositoController -------------------------------------------------------------- registrarIngresoMateriaPrima");
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		model.addObject("NombrePantalla","Registrar ingreso materia prima");
		model.setViewName("/deposito/registrarIngresoMateriaPrima");
		
		return model;

	}
	
	
	
}
