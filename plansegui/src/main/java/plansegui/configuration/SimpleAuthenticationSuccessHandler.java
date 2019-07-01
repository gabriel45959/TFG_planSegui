package plansegui.configuration;

import java.io.IOException;
import java.util.Collection;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import plansegui.configuration.menu.DefinirMenu;

@Component
public class SimpleAuthenticationSuccessHandler  implements AuthenticationSuccessHandler  {

	
	private Log log = LogFactory.getLog(SimpleAuthenticationSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
		
	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0,
			HttpServletResponse arg1, Authentication arg2) throws IOException,
			ServletException {
		
		Collection<? extends GrantedAuthority> authorities = arg2.getAuthorities();
		authorities.forEach(authority -> {
			log.info(arg2.getName()+" SimpleAuthenticationSuccessHandler -------------------------------------------------------------- "+authority.getAuthority());
			
			DefinirMenu.USUARIO_CONECTADO=arg2.getName();
			
			if(authority.getAuthority().equals("VENTA")) {
				try {
					redirectStrategy.sendRedirect(arg0, arg1, "/venta/registrarPedido");
				} catch (Exception e) {
					log.error(arg2.getName()+" SimpleAuthenticationSuccessHandler --------------------------Error------------------------------------ "+e.getMessage());
					e.printStackTrace();
					
				}
			} else if(authority.getAuthority().equals("ADMIN")) {
				try {
					log.info("SimpleAuthenticationSuccessHandler -------------------------------------------------------------- /admin/administrar "+authority.getAuthority());
					redirectStrategy.sendRedirect(arg0, arg1, "/admin/administrar");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				log.info("SimpleAuthenticationSuccessHandler -------------------------------------------------------------- IllegalStateException ");
	            throw new IllegalStateException();
	        }
		});
		
		
	}

}
