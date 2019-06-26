package plansegui.controller;

import java.util.Collection;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();

		model.addObject("message", "This is default page!");

		if (hasRole("ADMIN")) {
			model.addObject("title", "rol: ADMIN");
			model.setViewName("/admin/admin");
		} else if (hasRole("USUARIO")) {
			model.addObject("title", "rol: USUARIO");
			model.setViewName("venta/cargarPedido");
		} else {
			model.setViewName("login");
		}

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

	private boolean hasRole(String role) {

		boolean hasRole = false;
		try {
			Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder
					.getContext().getAuthentication().getAuthorities();

			for (GrantedAuthority authority : authorities) {
				hasRole = authority.getAuthority().equals(role);
				if (hasRole) {
					break;
				}
			}
		} catch (NullPointerException e) {
			hasRole = false;
		}
		return hasRole;
	}
}
