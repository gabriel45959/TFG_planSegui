package plansegui.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import plansegui.configuration.menu.DefinirMenu;
import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.entities.Pedido;
import plansegui.hibernate.services.DetallePedidoService;
import plansegui.hibernate.services.PedidoService;
import plansegui.hibernate.services.UsuarioService;

@Controller
@RequestMapping("/fabrica")
public class FabricaController {

	private Log log = LogFactory.getLog(FabricaController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallePedidoService detallePedido;
	

	@RequestMapping(value = { "/completarPlanificacion" }, method = RequestMethod.GET)
	public ModelAndView completarPlanificacion() {
		ModelAndView model = new ModelAndView();

		List<Pedido> pedido = pedidoService.getPedido();

		log.info("FabricaController---------------------------------------------------------------------------------------completarPlanificacion "
				+ pedido.get(0).getEmpresa().getNombre());

		model.addObject("NombrePantalla", "Completar planificación");
		model.addObject(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole()));
		model.addObject("ListPedidos", pedido);
		model.addObject("idDetallePedido", new DetallePedido());

		model.setViewName("/fabrica/completarPlanificacion");
		return model;
	}

	@RequestMapping(value = { "/registrarPlanificacion/{id}" }, method = RequestMethod.GET)
	public ModelAndView registrarPlanificacion(@PathVariable("id") String id,
			Model modelPage) {
		ModelAndView model = new ModelAndView();
		log.info(id
				+ " FabricaController---------------------------------------------------------------------------------------registrarProblema ");

		modelPage.addAttribute("id_detalle_pedido", id);

		model.addObject("NombrePantalla", "Completar planificación");
		model.addObject(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole()));
		model.setViewName("/fabrica/registrarPlanificacion");

		return model;

	}
	
	@RequestMapping(value = { "/registrarProblema" }, method = RequestMethod.GET)
	public String registrarProblema(@ModelAttribute("id") String id,
			BindingResult result, Model modelPage, final RedirectAttributes redirectAttributes) {
		
		log.info(id
				+ " FabricaController---------------------------------------------------------------------------------------registrarProblema ");

		
		
		modelPage.addAttribute("detallePedido", detallePedido.getDetallePedido(new Long(id)));
		modelPage.addAttribute("NombrePantalla", "Registrar problema");
		modelPage.addAttribute(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole()));
		

		return "/fabrica/registrarProblema";

	}
	
}
