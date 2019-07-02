package plansegui.controller;

import java.util.Iterator;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import plansegui.configuration.menu.DefinirMenu;
import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.entities.Empresa;
import plansegui.hibernate.entities.EstadoPedido;
import plansegui.hibernate.entities.Pedido;
import plansegui.hibernate.entities.Producto;
import plansegui.hibernate.services.EmpresaService;
import plansegui.hibernate.services.EstadoPedidoService;
import plansegui.hibernate.services.PedidoService;
import plansegui.hibernate.services.ProductoService;
import plansegui.hibernate.services.UsuarioService;
import plansegui.validator.RegistrarPedidoValidator;


@Controller
@RequestMapping("/venta")
public class VentaController {

	private Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EstadoPedidoService estadoService;
	
	@Autowired
	private RegistrarPedidoValidator registrarPedidoValidator;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.addValidators(registrarPedidoValidator);
	}
	
	
	
	@RequestMapping(value = { "/registrarPedido" }, method = RequestMethod.GET)
	public ModelAndView registrarPedido(){
		
		log.info("VentaController--------------------------------------------------------------------------registrarPedido");
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("listaEmpresa",empresaService.getEmpresa());
		model.addObject("listaProducto",productoService.getProducto());
		model.addObject("NombrePantalla","Registrar Pedido");
		model.setViewName("/venta/registrarPedido");
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		return model;
	}
	
	/**
	 * iniccializo los obj para la vista
	 * @return
	 */
	@ModelAttribute("cargarPedido")
	public Pedido getPedido(){
		Pedido pedido = new Pedido();
		pedido.setEmpresa(new Empresa());
		DetallePedido detallePedido=new DetallePedido();
		detallePedido.setProducto(new Producto());
		pedido.addDetallePedido(detallePedido);
		
		return pedido;
	}
	
	
	@RequestMapping(value = { "/registrarPedido" }, method = RequestMethod.POST)
	public String  crearPedido(@ModelAttribute("cargarPedido") @Validated Pedido pedido,
			BindingResult result, Model model1, final RedirectAttributes redirectAttributes){
		
		log.info("VentaController------------------------------------desde la pagina--------------------------------------crearPedido empresa: "+pedido.getEmpresa().getId()+" "+pedido.getEmpresa().getNombre()+" factura: "+pedido.getNroFactura());
					
		
		
		if(!result.hasErrors()){

			pedidoService.guardarPedido(completarDatosDetallePedido(pedido));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El pedido fue creado!!");
			
			return "redirect:/venta/registrarPedido";
			
		}else{
			model1.addAttribute("modalopen", "false");
			model1.addAttribute("css", "warning");
			model1.addAttribute("msg", "Faltan ingresar datos!!");
			model1.addAttribute("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		}
		
		
		return "/venta/registrarPedido";
	}
	
	protected Pedido completarDatosDetallePedido(Pedido pedido){
		
		pedido.setEmpresa(empresaService.buscarEmpresa(pedido.getEmpresa().getId()));

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		
		pedido.setFechaCreacion(date);
		
		pedido.setEmpresa(empresaService.buscarEmpresa(pedido.getEmpresa().getId()));
		
		EstadoPedido estadoPedido =estadoService.getEstadoPedido(new Long(1));
		
		for(Iterator<DetallePedido> i = pedido.getDetallePedido().iterator(); i.hasNext();){
			DetallePedido det = i.next();
			
			det.setPedido(pedido);
			det.setProducto(productoService.getProducto(det.getProducto().getId()));
			det.setEstado(estadoPedido);
			
		}

		
		
		return pedido;
		
	}
	
}
