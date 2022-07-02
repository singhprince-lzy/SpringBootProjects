package com.stackroute.newz.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.newz.model.News;


/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *                  
 * */
@Configuration
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. UserName 4. Password
	 */
	
	@Bean
	public DataSource getDataSource()
	{
//	BasicDataSource datasource=new BasicDataSource();
//	datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//	datasource.setUrl("jdbc:mysql://localhost:3306/firsthiber");
//	datasource.setUsername("root");
//		datasource.setPassword("Gagan@123");
//	return datasource;
//		
//	}
	
//Use this configuration while submitting solution in hobbes.
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" + System.getenv("MYSQL_DATABASE")
				+"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
		dataSource.setUsername(System.getenv("MYSQL_USER"));
		dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));
		return dataSource;

	}
	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
	 * class through which we get sessions and perform database operations.
	 */
	
	@Bean
	public LocalSessionFactoryBean getSessfact(DataSource datasour) throws Exception
	{
		
		
		LocalSessionFactoryBean sessfact=new LocalSessionFactoryBean();
		sessfact.setDataSource(datasour);
		Properties property=new Properties();
		property.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		property.put("hibernate.show_sql","true");
		property.put("hibernate.hbm2ddl.auto","update");
		sessfact.setHibernateProperties(property);
		sessfact.setAnnotatedClasses(News.class);
        sessfact.afterPropertiesSet();
        
		return sessfact;
		
	}
	
	
	
	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles
	 * transaction in Spring. The application that uses single hibernate session
	 * factory for database transaction has good choice to use
	 * HibernateTransactionManager. HibernateTransactionManager can work with plain
	 * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
	 * ensures data integrity.
	 */
	@Bean
	public HibernateTransactionManager getTransaction(SessionFactory sessfactory)
		{
	HibernateTransactionManager hibermanager=new HibernateTransactionManager();
	hibermanager.setSessionFactory(sessfactory);
	return hibermanager;
	
	}

	
}
