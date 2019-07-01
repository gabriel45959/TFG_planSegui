package plansegui.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.commons.logging.LogFactory;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {
	    "plansegui.hibernate",
	    "plansegui.validator"
	})

public class AppConfig {

	private Log log = LogFactory.getLog(AppConfig.class);
	
	@Autowired
	  private Environment env; 

	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();
		log.info("AppConfig---++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++---sessionFactory");
		localSessionFactory.setDataSource(getDataSource());
		localSessionFactory.setPackagesToScan(new String[] {"plansegui.hibernate.entities"});
		localSessionFactory.setHibernateProperties(getHibernateProperties());
		return localSessionFactory;
	}
	
	  @Bean
	  public DataSource getDataSource() {
		DriverManagerDataSource  dataSource = new DriverManagerDataSource ();//lo cambie por hibernate
	    dataSource.setDriverClassName(env.getProperty("mysql.driver"));
	    dataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
	    dataSource.setUsername(env.getProperty("mysql.username"));
	    dataSource.setPassword(env.getProperty("mysql.password"));
	    return dataSource;
	  }

	private Properties getHibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.enable_lazy_load_no_trans", env.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
        return properties;
	}
	  
	 @Bean
	    public HibernateTransactionManager getTransactionManager() {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }  
	 
	 @Bean
	   public MessageSource messageSource() {
	      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	      source.setBasename("messages");
	      return source;
	   }
	
} 
