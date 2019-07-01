package plansegui.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		
	  @Autowired
	  private DataSource dataSource;
	  
	
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	    auth.jdbcAuthentication().dataSource(dataSource)
	        .usersByUsernameQuery("select nombre_usuario, clave, habilitado"
	            + " from usuario where nombre_usuario=?")
	        .authoritiesByUsernameQuery("select nombre_usuario, id_rol "
	            + "from role where nombre_usuario=?")
	        .passwordEncoder(new BCryptPasswordEncoder());
	    
	  }
	  
	
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  
		 
		  http.authorizeRequests().antMatchers("/js/**",
	                "/css/**",
	                "/img/**","/style/**","/fonts/**").permitAll()
		  .and().authorizeRequests()
		  	.antMatchers("/login").anonymous()
		  	.antMatchers("/venta/**").hasAnyAuthority("VENTA","ADMIN")
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
			.and().formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check").usernameParameter("username").passwordParameter("password").successHandler(new SimpleAuthenticationSuccessHandler())
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login").clearAuthentication(true);
		  

		
	  }
	  
	 
}
