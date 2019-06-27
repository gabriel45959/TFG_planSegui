package plansegui.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"plansegui.controller"})
public class WebConfig implements WebMvcConfigurer{

	/*@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
	}*/
	
	 @Bean
	    public InternalResourceViewResolver resolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setViewClass(JstlView.class);
	        resolver.setPrefix("/WEB-INF/views/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	    }
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
	        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
	        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/");
	        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
	        registry.addResourceHandler("/style/**").addResourceLocations("classpath:/style/");
	        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/fonts/");
	        registry.addResourceHandler("/resources/**")
	                .addResourceLocations("classpath:/resources/"); 
    }
}
