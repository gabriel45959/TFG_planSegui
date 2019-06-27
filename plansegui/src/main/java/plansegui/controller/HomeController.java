package plansegui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import plansegui.hibernate.services.UsuarioService;

@Controller
public class HomeController {
	
	private Log log = LogFactory.getLog(HomeController.class);
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView model = new ModelAndView();
		
		log.info("HomeController -------------------------------------------------------------- home");
		model.setViewName("/login");
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			log.info("HomeController -------------------------------------------------------------- "+"Contraseña incorrecta!");
			model.addObject("error", "Contraseña incorrecta!");
		}

		if (logout != null) {
			log.info("HomeController -------------------------------------------------------------- "+"Salio del sistema!!");
			model.addObject("msg", "Salio del sistema!.");
		}
		model.setViewName("login");

		return model;

	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		log.info("HomeController -------------------------------------------------------------- accesssDenied");
		model.setViewName("403");
		return model;

	}

}
