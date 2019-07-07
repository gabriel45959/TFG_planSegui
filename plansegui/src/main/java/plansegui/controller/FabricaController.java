package plansegui.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import plansegui.configuration.menu.DefinirMenu;
import plansegui.hibernate.entities.CompraMateriaPrima;
import plansegui.hibernate.entities.DetalleCompraMateriaPrima;
import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.entities.Ingrediente;
import plansegui.hibernate.entities.Inventario;
import plansegui.hibernate.entities.MateriaPrima;
import plansegui.hibernate.entities.Pedido;
import plansegui.hibernate.entities.ProblemaReportado;
import plansegui.hibernate.entities.Producto;
import plansegui.hibernate.entities.ReservaMateriaPrima;
import plansegui.hibernate.services.CompraMateriaPrimaService;
import plansegui.hibernate.services.DetalleCompraMateriaPrimaService;
import plansegui.hibernate.services.DetallePedidoService;
import plansegui.hibernate.services.EstadoPedidoService;
import plansegui.hibernate.services.InventarioService;
import plansegui.hibernate.services.PedidoService;
import plansegui.hibernate.services.ProblemaReportadoService;
import plansegui.hibernate.services.ReservaMateriaPrimaService;
import plansegui.hibernate.services.TipoProblemaService;
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
	private DetallePedidoService detallePedidoService;

	@Autowired
	private TipoProblemaService tipoProblemaService;

	@Autowired
	private ProblemaReportadoService problemaReportadoService;

	@Autowired
	private EstadoPedidoService estadoPedidoService;

	@Autowired
	private InventarioService inventarioService;

	@Autowired
	private CompraMateriaPrimaService compraMateriaPrimaService;
	
	@Autowired
	private DetalleCompraMateriaPrimaService detalleCompraMateriaPrimaService;

	@Autowired
	private ReservaMateriaPrimaService reservaMateriaPrimaService;

	
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
						DefinirMenu.USUARIO_CONECTADO).getRole(),"FABRICA"));
		model.addObject("ListPedidos", pedido);
		model.addObject("idDetallePedido", new DetallePedido());

		model.setViewName("/fabrica/completarPlanificacion");
		return model;
	}

	@RequestMapping(value = { "/registrarPlanificacion/{id}" }, method = RequestMethod.GET)
	public ModelAndView registrarPlanificacion(@PathVariable("id") String id,
			Model modelPage) {
		ModelAndView model = new ModelAndView();
		int cantidadNecesitada = 0;
		DetalleCompraMateriaPrima detComMat;
		//CompraMateriaPrima compra;
		ReservaMateriaPrima reservaMateriaPrima;
		List<Ingrediente> noEstaInventario;
		List<CompraMateriaPrima> comMat = new ArrayList<CompraMateriaPrima>();
		
		
		log.info("Id pedido: " + id
				+ " FabricaController-----------registrarPlanificacion ");

		modelPage.addAttribute("id_detalle_pedido", id);

		DetallePedido detPed = detallePedidoService.getDetallePedido(new Long(
				id));
		comMat.add(new CompraMateriaPrima());
		comMat.get(0).setDetallePedido(detPed);
		detPed.setCompraMateriaPrima(comMat);
		List<Inventario> inv = getInventarioDelProducto(
				inventarioService.getInventario(), detPed.getProducto());

		if (inv.size() == 0) {// no existe en inventario
			log.info("tamaño del inventario: "
					+ inv.size()
					+ " FabricaController-----------NO existe en el inventario-------------registrarPlanificacion ");
			comMat = pedirMateriales(detPed, model,comMat);
			
		} else {// existe inventario
			log.info("tamaño del inventario: "
					+ inv.size()
					+ " FabricaController-----------Existe en el inventario----------------registrarPlanificacion ");
			//compra = new CompraMateriaPrima();
			

			for (Inventario inventario : inv) {
				detComMat = new DetalleCompraMateriaPrima();
				for (Ingrediente ingre : detPed.getProducto().getIngredientes()) {
					log.info("cantidad ingredientes: "+detPed.getProducto().getIngredientes().size()
							+ " " + ingre.getMateriaPrima().getId() + "=="
							+ inventario.getMateriaPrima().getId() + " "
							+ " cantidad de elementos en el inventario: "+inv.size());
					if (ingre.getMateriaPrima().getId() == inventario.getMateriaPrima().getId()) {

						if (ingre.getMateriaPrima().isKgOCantidad()) {// /kg
							cantidadNecesitada = ((detPed.getCantidad() * ingre
									.getPorcentaje()) / 100);
						} else {// cantidad
							cantidadNecesitada = (ingre.getPorcentaje() * detPed
									.getCantidad()) / 100;
						}
						detComMat.setCantidad(cantidadNecesitada);

						if (cantidadNecesitada <= inventario.getCantidad()) {// reservo
							
							reservaMateriaPrima = new ReservaMateriaPrima();

							reservaMateriaPrima.setCantidad(cantidadNecesitada);
							reservaMateriaPrima.setDetallePedido(detPed);
							reservaMateriaPrima.setMateriaPrima(ingre
									.getMateriaPrima());

							inventario.setCantidad(inventario.getCantidad()
									- cantidadNecesitada);

							reservaMateriaPrimaService
									.guardarReservaMateriaPrima(reservaMateriaPrima);
														
							inventarioService.guardarInventario(inventario);
							
							log.info("inventario id: "+inventario.getId()+"cantidad inventario: "+inventario.getCantidad()+" Materia Prima: "
									+ ingre.getMateriaPrima().getNombre()
									+ " FabricaController----------------------------tengo debo reservar-------------------------registrarPlanificacion ");

						}// comprar
						else {
							log.info("Materia Prima: "
									+ ingre.getMateriaPrima().getNombre()
									+ " FabricaController----------------------------tengo pero no alcanza debo comprar----------------registrarPlanificacion ");
							
							detComMat.setCantidad(cantidadNecesitada);
							detComMat.setCompraMateriaPrima(detPed.getCompraMateriaPrima().get(0));
							detComMat.setMateriaPrima(ingre.getMateriaPrima());
							detPed.getCompraMateriaPrima().get(0).addDetalleCompraMateriaPrima(detComMat);
							compraMateriaPrimaService.guardarCompraMateriaPrima(detPed.getCompraMateriaPrima().get(0));
							detPed.setEstado(estadoPedidoService
									.getEstadoPedido(new Long(2)));

						}

					}

				}

			}
			noEstaInventario = getNoEstanEnInventario(inv, detPed.getProducto()
					.getIngredientes());
			if (noEstaInventario.size() > 0) {// no esta en el inventario debo
												// comprar
				for (Ingrediente noInv : noEstaInventario) {
					detComMat = new DetalleCompraMateriaPrima();

					if (noInv.getMateriaPrima().isKgOCantidad()) {// /kg
						cantidadNecesitada = ((detPed.getCantidad() * noInv
								.getPorcentaje()) / 100);
					} else {// cantidad
						cantidadNecesitada = ( detPed.getCantidad() / noInv.getPorcentaje());
					}
					detComMat.setCantidad(cantidadNecesitada);
					detComMat.setCompraMateriaPrima(detPed.getCompraMateriaPrima().get(0));
					detComMat.setMateriaPrima(noInv.getMateriaPrima());
					detPed.getCompraMateriaPrima().get(0).addDetalleCompraMateriaPrima(detComMat);
					
					compraMateriaPrimaService.guardarCompraMateriaPrima(detPed.getCompraMateriaPrima().get(0));
					detPed.setEstado(estadoPedidoService
							.getEstadoPedido(new Long(2)));
					log.info("Materia Prima: "
							+ noInv.getMateriaPrima().getNombre()
							+ " FabricaController----------------------------no esta en el inventario debo comprar----------------registrarPlanificacion ");
				}
			}
			//compraMateriaPrimaService.guardarCompraMateriaPrima(detPed.getCompraMateriaPrima().get(0));
			detallePedidoService.actualizarDetallePedido(detPed);
			log.info("Id pedido: "
					+ id
					+ " FabricaController-------fin--------------registrarPlanificacion ");
		}

		model.addObject("NombrePantalla", "Completar planificación");
		model.addObject(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole(),"FABRICA"));
		model.setViewName("/fabrica/registrarPlanificacion");

		return model;

	}

	/**
	 * materia prima que no esta en el inventario
	 * 
	 * @param invTotal
	 * @param ingredientes
	 * @return
	 */
	private List<Ingrediente> getNoEstanEnInventario(List<Inventario> invTotal,
			List<Ingrediente> ingredientes) {
		List<Ingrediente> noInv = new ArrayList<Ingrediente>();

		for (Ingrediente ingre : ingredientes) {
			for (Inventario inv : invTotal) {
				if (inv.getMateriaPrima().getId() == ingre.getMateriaPrima().getId()) {
					continue;
				} else {
					log.info(" FabricaController-------noInv--------------getNoEstanEnInventario materiaPrima: "+ingre.getMateriaPrima().getNombre());
					noInv.add(ingre);
				}

			}

		}
		return noInv;
	}

	/**
	 * Solicitud de compra de materia prima cuando todos los ingredientes del
	 * producto no existe en el inventario
	 * 
	 * @param detPed
	 * @param model
	 */
	private List<CompraMateriaPrima> pedirMateriales(DetallePedido detPed, ModelAndView model,List<CompraMateriaPrima> comMat) {
		DetalleCompraMateriaPrima detComMat;
		CompraMateriaPrima compra = new CompraMateriaPrima();
		MateriaPrima matPrima;
		
		String msgCompraMateriaPrima = "Se informo que se debe comprar meteria prima";

		detPed.setEstado(estadoPedidoService.getEstadoPedido(new Long(2)));

		log.info(detPed.getEstado().getId()
				+ " FabricaController----------------------------ingredientes: "
				+ detPed.getProducto().getIngredientes().size()
				+ "--------------------------pedirMateriales ");
		for (int i = 0; i < detPed.getProducto().getIngredientes().size(); i++) {

			detComMat = new DetalleCompraMateriaPrima();

			matPrima = detPed.getProducto().getIngredientes().get(i)
					.getMateriaPrima();

			if (matPrima.isKgOCantidad()) {// /kg
				detComMat
						.setCantidad((detPed.getCantidad() * detPed
								.getProducto().getIngredientes().get(i)
								.getPorcentaje()) / 100);
			} else {// cantidad
				detComMat.setCantidad((detPed.getCantidad() / detPed.getProducto().getIngredientes()
						.get(i).getPorcentaje()));
			}
			compra.setDetallePedido(detPed);

			detComMat.setCompraMateriaPrima(compra);
			detComMat.setMateriaPrima(matPrima);
			compra.addDetalleCompraMateriaPrima(detComMat);
			comMat.add(compra);
			compra.setDetallePedido(detPed);
			compraMateriaPrimaService.guardarCompraMateriaPrima(compra);

		}
		detPed.setCompraMateriaPrima(comMat);
		detallePedidoService.actualizarDetallePedido(detPed);

		model.addObject("msgCompraMateriaPrima", msgCompraMateriaPrima);
		return comMat;
	}

	@RequestMapping(value = { "/registrarProblema/{id}" }, method = RequestMethod.GET)
	public ModelAndView registrarProblema(@PathVariable("id") String id,
			Model modelPage) {

		ModelAndView model = new ModelAndView();

		if (id.isEmpty() || id == null || id == "") {
			id = "0";
		}

		log.info(id
				+ " FabricaController---------------------------------------------------------------------------------------registrarProblema ");

		model.addObject("listTipoProblema",
				tipoProblemaService.getTipoProblema());
		model.addObject("detallePedido",
				detallePedidoService.getDetallePedido(new Long(id)));
		model.addObject("NombrePantalla", "Registrar problema");
		model.addObject(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole(),"FABRICA"));

		model.setViewName("/fabrica/registrarProblema");

		return model;
	}

	/**
	 * iniccializo los obj para la vista
	 * 
	 * @return
	 */
	@ModelAttribute("registrarProblema")
	public ProblemaReportado getProblemaReportado() {
		ProblemaReportado promRep = new ProblemaReportado();
		promRep.setEstado(false);
		promRep.setDetallePedido(new DetallePedido());
		return promRep;
	}

	@RequestMapping(value = { "/grabarProblema" }, method = RequestMethod.POST)
	public String grabarProblema(
			@ModelAttribute("registrarProblema") @Validated ProblemaReportado problemaReportado,
			BindingResult result, Model modelPage,
			final RedirectAttributes redirectAttributes) {

		log.info(" FabricaController---------------------------------------------------------------------------------------grabarProblema ");

		modelPage.addAttribute("NombrePantalla", "Registrar problema");
		modelPage.addAttribute(
				"MenuOpcionExtras",
				DefinirMenu.setItemMenu(usuarioService.getUsuario(
						DefinirMenu.USUARIO_CONECTADO).getRole(),"FABRICA"));

		if (!result.hasErrors()) {

			problemaReportado.getDetallePedido().setEstado(
					estadoPedidoService.getEstadoPedido(new Long(6)));
			problemaReportado.setTipoProblema(tipoProblemaService
					.getTipoProblema(problemaReportado.getTipoProblema()
							.getId()));

			problemaReportadoService
					.guardarProblemaReportado(problemaReportado);

			DetallePedido detPed = detallePedidoService
					.getDetallePedido(problemaReportado.getDetallePedido()
							.getId());
			detPed.setEstado(estadoPedidoService.getEstadoPedido(new Long(6)));

			detallePedidoService.actualizarDetallePedido(detPed);

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg",
					"El problema fue registrado!!");
			return "redirect:/fabrica/registrarProblema";
		} else {
			modelPage.addAttribute("css", "warning");
			modelPage.addAttribute("msg", "Faltan ingresar datos!!");
		}

		return "/fabrica/registrarProblema";

	}

	protected List<Inventario> getInventarioDelProducto(List<Inventario> inv,
			Producto producto) {
		List<Inventario> invenProducto = new ArrayList<Inventario>();

		for (Inventario pasandoInv : inv) {
			if (pasandoInv.getMateriaPrima().getId() == producto.getId()) {
				invenProducto.add(pasandoInv);
			}
		}

		return invenProducto;
	}

}
