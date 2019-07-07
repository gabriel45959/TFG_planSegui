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
	
	
	@RequestMapping(value = { "/listarUsuarios" }, method = RequestMethod.GET)
	public ModelAndView administrar() {
		
		ModelAndView model = new ModelAndView();

		List<Usuario> usuarios = usuarioService.getUsuarios();
		log.info("AdminController -------------------------------------------------------------- "
				+ usuarios.size()+"-------------- role size: "+usuarios.get(0).getRole().size());
		model.addObject("usuarios", usuarios);
		model.addObject("menuActivo","active");
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole(),"ADMIN"));
		model.addObject("NombrePantalla","Listar usuarios");
		model.setViewName("/admin/listarUsuarios");
		
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
		model.addObject("NombrePantalla","Administrar Usuarios");
		model.addObject("usuarios", usuarios);
		model.addObject("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole(),"ADMIN"));
		
		model.setViewName("/admin/crearUsuario");
		
		Usuario usr= new Usuario();
		usr.addRole(new Role());
		
		model.addObject("cargarUsuario",usr);
		
		
		return model;

	}
	
	@RequestMapping(value = { "/crearUsuario" }, method = RequestMethod.POST)
	public String  crearUsuario(@ModelAttribute("cargarUsuario") @Validated Usuario usr,
			BindingResult result, Model model1, final RedirectAttributes redirectAttributes){

		redirectAttributes.addFlashAttribute("NombrePantalla","Crear Usuarios");
		
		BCryptPasswordEncoder encrypt=new BCryptPasswordEncoder();
		
		if(!result.hasErrors()){
			usr.getRole().get(0).setUsuario(usr);
			usr.setClave(encrypt.encode(usr.getClave()));
			usuarioService.guardarUsuario(usr);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El usurario fue creado!!");
			return "redirect:/admin/crearUsuario";
		}else{
			model1.addAttribute("modalopen", "false");
			model1.addAttribute("css", "warning");
			model1.addAttribute("msg", "Faltan ingresar datos!!");
			model1.addAttribute("MenuOpcionExtras",DefinirMenu.setItemMenu(usuarioService.getUsuario(DefinirMenu.USUARIO_CONECTADO).getRole(),"ADMIN"));
		}
		
		
		//return model;
		return "/admin/crearUsuario";
	}

}
