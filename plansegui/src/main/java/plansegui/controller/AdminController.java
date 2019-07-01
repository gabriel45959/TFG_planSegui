package plansegui.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import plansegui.hibernate.entities.Role;
import plansegui.hibernate.entities.Usuario;
import plansegui.hibernate.services.UsuarioService;
import plansegui.validator.RegistrarUsuarioValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
	


	private Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RegistrarUsuarioValidator registrarUsuarioValidator;
	
	@InitBinder
	protected void initBilder(WebDataBinder binder){
		binder.addValidators(registrarUsuarioValidator);
	}
	
	
	@RequestMapping(value = { "/administrar" }, method = RequestMethod.GET)
	public ModelAndView administrar() {
		
		ModelAndView model = new ModelAndView();

		model.addObject("message", "This is default page!");

		List<Usuario> usuarios = usuarioService.getUsuarios();
		log.info("AdminController -------------------------------------------------------------- "
				+ usuarios.size()+"-------------- role size: "+usuarios.get(0).getRole().size());
		model.addObject("TituloVentana", "rol: ADMIN");
		model.addObject("NombrePantalla","Administrar Usuarios");
		model.addObject("usuarios", usuarios);
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		
		model.setViewName("/admin/admin");
		
		Usuario usr= new Usuario();
		usr.addRole(new Role());
		
		model.addObject("cargarUsuario",usr);
		
		
		return model;

	}
	
	@RequestMapping(value = { "/crearUsuario" }, method = RequestMethod.GET)
	public ModelAndView crearUsuario() {
		
		ModelAndView model = new ModelAndView();

		List<Usuario> usuarios = usuarioService.getUsuarios();
		log.info("AdminController -------------------------------------------------------------- "
				+ usuarios.size()+"-------------- role size: "+usuarios.get(0).getRole().size());
		model.addObject("TituloVentana", "rol: ADMIN");
		model.addObject("NombrePantalla","Administrar Usuarios");
		model.addObject("usuarios", usuarios);
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		
		model.setViewName("/admin/crearUsuario");
		
		Usuario usr= new Usuario();
		usr.addRole(new Role());
		
		model.addObject("cargarUsuario",usr);
		
		
		return model;

	}
	
	@RequestMapping(value = { "/crearUsuario" }, method = RequestMethod.POST)
	public ModelAndView  crearUsuario(@ModelAttribute("cargarUsuario") @Validated Usuario usr,
			BindingResult result, Model model1, final RedirectAttributes redirectAttributes){
		ModelAndView model = new ModelAndView();
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole()));
		model.setViewName("/admin/crearUsuario");
		BCryptPasswordEncoder encrypt=new BCryptPasswordEncoder();
		
		if(!result.hasErrors()){
			usr.getRole().get(0).setUsuario(usr);
			usr.setClave(encrypt.encode(usr.getClave()));
			usuarioService.guardarUsuario(usr);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El usurario fue creado!!");
			//return "redirect:/admin/crearUsuario";
		}else{
			redirectAttributes.addFlashAttribute("modalopen", "false");
			redirectAttributes.addFlashAttribute("css", "warning");
			redirectAttributes.addFlashAttribute("msg", "Faltan datos!!");
		}
		
		
		return model;
		//return "/admin/crearUsuario";
	}

}
