package plansegui.controller;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import plansegui.hibernate.entities.CompraMateriaPrima;
import plansegui.hibernate.entities.DetalleCompraMateriaPrima;
import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.services.CompraMateriaPrimaService;
import plansegui.hibernate.services.DetalleCompraMateriaPrimaService;
import plansegui.hibernate.services.DetallePedidoService;
import plansegui.hibernate.services.EstadoPedidoService;
import plansegui.hibernate.services.UsuarioService;

@Controller
@RequestMapping("/compra")
public class CompraController {

	
	private Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DetallePedidoService detallePedidoService; 
	
	@Autowired
	private CompraMateriaPrimaService compraMateriaPrima;
	
	@Autowired
	private DetalleCompraMateriaPrimaService detalleCompraMateriaPrimaService;
	
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	
	@Autowired
	private CompraMateriaPrimaService compraMateriaPrimaService;
	
	
	
	@RequestMapping(value = { "/registrarCompra" }, method = RequestMethod.GET)
	public ModelAndView registrarCompra() {
		
		ModelAndView model = new ModelAndView();
		
		List<DetallePedido> detallePedido=detallePedidoService.getdetallePedidoPorEstado(2);
		
		log.info("DepositoController -------------------------------------------------------------- registrarCompra "+detallePedido.size());
		model.addObject("ListDetallePedidos", detallePedido);
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole(),"COMPRA"));
		model.addObject("NombrePantalla","Registrar compra materia prima");
		model.setViewName("/compra/registrarCompra");
		
		return model;

	}
	
	/**
	 * iniccializo los obj para la vista
	 * @return
	 */
	@ModelAttribute("compraMateriaPrima")
	public CompraMateriaPrima getPedido(){
		CompraMateriaPrima compraMateriaPrima = new CompraMateriaPrima();
		compraMateriaPrima.addDetalleCompraMateriaPrima(new DetalleCompraMateriaPrima());
		compraMateriaPrima.setDetallePedido(new DetallePedido());
		return compraMateriaPrima;
	}
	
	/**
	 * Guardar los datos de la compra
	 * @param compraMateriaPrima
	 * @param result
	 * @param modelPagina
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = { "/registrarCompra" }, method = RequestMethod.POST)
	public String guardarCompra(@ModelAttribute("compraMateriaPrima") @Validated CompraMateriaPrima compraMateriaPrima,
			BindingResult result, Model modelPagina, final RedirectAttributes redirectAttributes) {
		
		int auxCuentaCompras=0;
		List<DetallePedido> detallePedido=detallePedidoService.getdetallePedidoPorEstado(2);
		
		log.info("CompraController -------------------------------------------------------------- guardarCompra nroFactura: "+compraMateriaPrima.getDetalleCompraMateriaPrima().get(0).getFechaLlegada()+" idDetallePedido: "+compraMateriaPrima.getDetallePedido().getId()+" idDetalleCompra: "+compraMateriaPrima.getDetalleCompraMateriaPrima().get(0).getId());
		
		
		
		modelPagina.addAttribute("ListDetallePedidos", detallePedido);
		modelPagina.addAttribute("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole(),"COMPRA"));
		modelPagina.addAttribute("NombrePantalla","Registrar compra materia prima");
		
		if(!result.hasErrors()){
			
			DetalleCompraMateriaPrima detalleCompraMateriaPrima=detalleCompraMateriaPrimaService.getDetalleCompraMateriaPrima(compraMateriaPrima.getDetalleCompraMateriaPrima().get(0).getId());
		    
			detalleCompraMateriaPrima.setFechaLlegada(compraMateriaPrima.getDetalleCompraMateriaPrima().get(0).getFechaLlegada());
			detalleCompraMateriaPrima.setNroFactura(compraMateriaPrima.getDetalleCompraMateriaPrima().get(0).getNroFactura());
			
			detalleCompraMateriaPrimaService.actualizarDetalleCompraMateriaPrima(detalleCompraMateriaPrima);

			detalleCompraMateriaPrima=detalleCompraMateriaPrimaService.getDetalleCompraMateriaPrima(compraMateriaPrima.getDetalleCompraMateriaPrima().get(0).getId());
			
			//valido si quedan materias primas del producto sin comprar
			   for(DetalleCompraMateriaPrima detCompraMat : detalleCompraMateriaPrima.getCompraMateriaPrima().getDetalleCompraMateriaPrima()){
			    	if(detCompraMat.getNroFactura() != null){
			    		log.info("CompraController -------------------------------------------------------------- guardarCompra nroFactura: "+detCompraMat.getNroFactura()+" Cantidad Materiales comprados: "+auxCuentaCompras+" cantidad Materia Prima pedida: "+detalleCompraMateriaPrima.getCompraMateriaPrima().getDetalleCompraMateriaPrima().size());
			    		auxCuentaCompras++;
			    	}
			    }
				
			   log.info("CompraController -------------------------------------------------------------- guardarCompra Cantidad Materiales comprados: "+auxCuentaCompras+" cantidad Materia Prima pedida: "+detalleCompraMateriaPrima.getCompraMateriaPrima().getDetalleCompraMateriaPrima().size());
			   //si todas las materias primas del producto fueron compradas cambio de estado al pedido
			   if(detalleCompraMateriaPrima.getCompraMateriaPrima().getDetalleCompraMateriaPrima().size()==auxCuentaCompras){
			    	for(DetallePedido detPed : detallePedido){
			    		log.info("CompraController ---------------------------"+detPed.getId()+" == "+compraMateriaPrima.getDetallePedido().getId()+"----------------------------------- guardarCompra" );
						if(detPed.getId()==compraMateriaPrima.getDetallePedido().getId()){
							detPed.setEstado(estadoPedidoService.getEstadoPedido(new Long(3)));
							detallePedidoService.actualizarDetallePedido(detPed);
						}
					}		
			    }
		
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Los datos fueron almacenados!!");
			
			return "redirect:/compra/registrarCompra";
			
		}else{
			modelPagina.addAttribute("modalopen", "false");
			modelPagina.addAttribute("css", "warning");
			modelPagina.addAttribute("msg", "Faltan ingresar datos!!");
		}
		
		
		return "/compra/registrarCompra";

	}
	
}
