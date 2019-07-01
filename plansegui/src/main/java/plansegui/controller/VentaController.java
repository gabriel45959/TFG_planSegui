package plansegui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import plansegui.configuration.menu.DefinirMenu;
import plansegui.hibernate.entities.Pedido;
import plansegui.hibernate.services.EmpresaService;
import plansegui.hibernate.services.PedidoService;
import plansegui.hibernate.services.UsuarioService;


@Controller
@RequestMapping("/venta")
public class VentaController {

	private Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = { "/registrarPedido" }, method = RequestMethod.GET)
	public ModelAndView registrarPedido(){
		
		log.info("VentaController--------------------------------------------------------------------------registrarPedido");
		
		ModelAndView model = new ModelAndView();
		Pedido pedido = new Pedido();
		
		model.addObject("cargarPedido",pedido);
		model.addObject("listaEmpresa",empresaService.getEmpresa());
		model.addObject("NombrePantalla","Registrar Pedido");
		model.setViewName("/venta/registrarPedido");
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		return model;
	}
	
	@RequestMapping(value = { "/registrarPedido" }, method = RequestMethod.POST)
	public ModelAndView  crearPedido(@ModelAttribute("cargarPedido") @Validated Pedido usr,
			BindingResult result, Model model1, final RedirectAttributes redirectAttributes){
		ModelAndView model = new ModelAndView();
		
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		
		
		if(!result.hasErrors()){
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El usurario fue creado!!");
			//return "redirect:/admin/crearUsuario";
			model.setViewName("redirect:/admin/crearUsuario");
		}else{
			redirectAttributes.addFlashAttribute("modalopen", "false");
			redirectAttributes.addFlashAttribute("css", "warning");
			redirectAttributes.addFlashAttribute("msg", "Faltan datos!!");
			model.setViewName("/admin/crearUsuario");
		}
		
		
		return model;
		//return "/admin/crearUsuario";
	}
}
