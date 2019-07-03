package plansegui.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import plansegui.configuration.menu.DefinirMenu;
import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.entities.Pedido;
import plansegui.hibernate.entities.ProblemaReportado;
import plansegui.hibernate.services.DetallePedidoService;
import plansegui.hibernate.services.EstadoPedidoService;
import plansegui.hibernate.services.PedidoService;
import plansegui.hibernate.services.ProblemaReportadoService;
import plansegui.hibernate.services.TipoProblemaService;
import plansegui.hibernate.services.UsuarioService;
import plansegui.validator.ProblemaReportadoValidator;

@Controller
@RequestMapping("/fabrica")
public class FabricaController {

	private Log log = LogFactory.getLog(FabricaController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallePedidoService detallePedidoService;
	
	@Autowired
	private TipoProblemaService tipoProblemaService;
	
	@Autowired
	private ProblemaReportadoService problemaReportadoService;
	
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	
	/*@Autowired
	private ProblemaReportadoValidator problemaReportadoValidator;
	
	@InitBinder
	protected void initBilder(WebDataBinder binder){
		
		binder.addValidators(problemaReportadoValidator);
		
	}*/
	
	
	

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
		
		if(id.isEmpty() || id == null || id==""){
			id="0";
		}
		
		log.info(id
				+ " FabricaController---------------------------------------------------------------------------------------registrarProblema ");

		
		modelPage.addAttribute("listTipoProblema", tipoProblemaService.getTipoProblema());
		modelPage.addAttribute("detallePedido", detallePedidoService.getDetallePedido(new Long(id)));
		modelPage.addAttribute("NombrePantalla", "Registrar problema");
		modelPage.addAttribute(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole()));
		

		return "/fabrica/registrarProblema";

	}
	

	/**
	 * iniccializo los obj para la vista
	 * @return
	 */
	@ModelAttribute("registrarProblema")
	public ProblemaReportado getProblemaReportado(){
		ProblemaReportado promRep=new ProblemaReportado();
		promRep.setEstado(false);
		promRep.setDetallePedido(new DetallePedido());
	   return promRep;	
	}
	
	@RequestMapping(value = { "/grabarProblema" }, method = RequestMethod.POST)
	public String grabarProblema(@ModelAttribute("registrarProblema")  @Validated ProblemaReportado problemaReportado,
			BindingResult result, Model modelPage, final RedirectAttributes redirectAttributes) {
		
		log.info( " FabricaController---------------------------------------------------------------------------------------grabarProblema ");

		
		
		modelPage.addAttribute("NombrePantalla", "Registrar problema");
		modelPage.addAttribute(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole()));
		


		if(!result.hasErrors()){
			
			problemaReportado.getDetallePedido().setEstado(estadoPedidoService.getEstadoPedido(new Long(6)));
			problemaReportado.setTipoProblema(tipoProblemaService.getTipoProblema(problemaReportado.getTipoProblema().getId()));
			
			problemaReportadoService.guardarProblemaReportado(problemaReportado);
			
			DetallePedido detPed = detallePedidoService.getDetallePedido(problemaReportado.getDetallePedido().getId());
			detPed.setEstado(estadoPedidoService.getEstadoPedido(new Long(6)));
			
			detallePedidoService.actualizarDetallePedido(detPed);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El problema fue registrado!!");
			return "redirect:/fabrica/registrarProblema";
		}else{
			modelPage.addAttribute("css", "warning");
			modelPage.addAttribute("msg", "Faltan ingresar datos!!");
		}
		
		return "/fabrica/registrarProblema";

	}
	
	
}
