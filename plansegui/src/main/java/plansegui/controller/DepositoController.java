package plansegui.controller;

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
import plansegui.hibernate.entities.ReservaMateriaPrima;
import plansegui.hibernate.services.CompraMateriaPrimaService;
import plansegui.hibernate.services.DetallePedidoService;
import plansegui.hibernate.services.EstadoPedidoService;
import plansegui.hibernate.services.MateriaPrimaService;
import plansegui.hibernate.services.PedidoService;
import plansegui.hibernate.services.ReservaMateriaPrimaService;
import plansegui.hibernate.services.UsuarioService;

@Controller
@RequestMapping("/deposito")
public class DepositoController {

	private Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DetallePedidoService detallePedidoService;

	@Autowired
	private CompraMateriaPrimaService compraMateriaPrimaService;

	@Autowired
	private ReservaMateriaPrimaService reservaMateriaPrimaService;

	@Autowired
	private MateriaPrimaService materiaPrimaService;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EstadoPedidoService estadoPedidoService;

	@RequestMapping(value = { "/registrarIngresoMateriaPrima" }, method = RequestMethod.GET)
	public ModelAndView registrarIngresoMateriaPrima() {

		ModelAndView model = new ModelAndView();

		log.info("DepositoController -------------------------------------------------------------- registrarIngresoMateriaPrima");
		List<DetallePedido> detallePedido = detallePedidoService
				.getdetallePedidoPorEstado(3);

		model.addObject("ListDetallePedidos", detallePedido);
		model.addObject("MenuOpcionExtras", DefinirMenu.setItemMenu(
				usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO)
						.getRole(), "DEPOSITO"));
		model.addObject("NombrePantalla", "Registrar ingreso materia prima");
		model.setViewName("/deposito/registrarIngresoMateriaPrima");

		return model;

	}

	/**
	 * iniccializo los obj para la vista
	 * 
	 * @return
	 */
	@ModelAttribute("reservaMateriaPrima")
	public ReservaMateriaPrima getPedido() {
		ReservaMateriaPrima reservaMateriaPrima = new ReservaMateriaPrima();
		reservaMateriaPrima.setDetallePedido(new DetallePedido());
		return reservaMateriaPrima;
	}

	@RequestMapping(value = { "/registrarIngresoMateriaPrima" }, method = RequestMethod.POST)
	public String guararIngresoMateriaPrima(
			@ModelAttribute("reservaMateriaPrima") @Validated ReservaMateriaPrima reservaMateriaPrima,
			BindingResult result, Model modelPagina,
			final RedirectAttributes redirectAttributes) {

		log.info("DepositoController -------------------------------------------------------------- guararIngresoMateriaPrima fecha: "
				+ reservaMateriaPrima.getFechaIngreso());
		List<DetallePedido> listDetallePedido = detallePedidoService
				.getdetallePedidoPorEstado(3);

		log.info("DepositoController -------------------------------------------------------------- guararIngresoMateriaPrima "
				+ listDetallePedido.size());
		modelPagina.addAttribute("ListDetallePedidos", listDetallePedido);
		modelPagina.addAttribute("MenuOpcionExtras", DefinirMenu.setItemMenu(
				usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO)
						.getRole(), "DEPOSITO"));
		modelPagina.addAttribute("NombrePantalla",
				"Registrar ingreso materia prima");

		if (!result.hasErrors()) {
			
		
			
			DetallePedido detallePedido = detallePedidoService
					.getDetallePedido(reservaMateriaPrima.getDetallePedido()
							.getId());

			reservaMateriaPrima.setDetallePedido(detallePedido);
			reservaMateriaPrima.setMateriaPrima(materiaPrimaService
					.getMateriaPrima(reservaMateriaPrima.getMateriaPrima()
							.getId()));

			reservaMateriaPrimaService
					.guardarReservaMateriaPrima(reservaMateriaPrima);

			actualizarPedido(reservaMateriaPrima.getDetallePedido().getId());

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg",
					"Los datos fueron almacenados!!");

			return "redirect:/deposito/registrarIngresoMateriaPrima";

		} else {
			modelPagina.addAttribute("modalopen", "false");
			modelPagina.addAttribute("css", "warning");
			modelPagina.addAttribute("msg", "Faltan ingresar datos!!");
		}

		return "/deposito/registrarIngresoMateriaPrima";

	}

	protected void actualizarPedido(Long idDetallePedido) {

		DetallePedido detallePedido = detallePedidoService
				.getDetallePedido(idDetallePedido);
		log.info("DepositoController ---------------------"+detallePedido.getReservaMateriaPrima().size()+" == "+detallePedido.getCompraMateriaPrima().get(0).getDetalleCompraMateriaPrima().size()+"----------------------------------------- guararIngresoMateriaPrima ");
		if(detallePedido.getReservaMateriaPrima().size()>=detallePedido.getCompraMateriaPrima().get(0).getDetalleCompraMateriaPrima().size()){
			detallePedido.setEstado(estadoPedidoService.getEstadoPedido(new Long(4)));
			detallePedidoService.actualizarDetallePedido(detallePedido);
		}
			
	}
}
