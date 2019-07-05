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
public class SimpleAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private Log log = LogFactory
			.getLog(SimpleAuthenticationSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0,
			HttpServletResponse arg1, Authentication arg2) throws IOException,
			ServletException {

		Collection<? extends GrantedAuthority> authorities = arg2
				.getAuthorities();
		authorities
				.forEach(authority -> {

					String pagina = "";
					DefinirMenu.USUARIO_CONECTADO = arg2.getName();

					switch (authority.getAuthority()) {
					case "ADMIN":
						pagina = "/admin/listarUsuarios";
						break;
					case "VENTA":
						pagina = "/venta/registrarPedido";
						break;
					case "FABRICA":
						pagina = "/fabrica/completarPlanificacion";
						break;
					case "DEPOSITO":
						pagina = "/deposito/registrarIngresoMateriaPrima";
						break;
					case "COMPRA":
						pagina = "/compra/registrarCompra";
						break;
					default:
						break;
					}

					try {
						redirectStrategy.sendRedirect(arg0, arg1, pagina);
					} catch (Exception e) {
						log.error(arg2.getName()
								+ " SimpleAuthenticationSuccessHandler --------------------------Error------------------------------------ "
								+ e.getMessage());
						e.printStackTrace();
					}
				});

	}

}
