package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.DAO.BlogDAO;
import com.niit.collaboration.DAO.ChatDAO;
import com.niit.collaboration.DAO.EventDAO;
import com.niit.collaboration.DAO.ForumDAO;
import com.niit.collaboration.DAO.FriendDAO;
import com.niit.collaboration.DAO.JobDAO;
import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.DAOImpl.BlogDAOImpl;
import com.niit.collaboration.DAOImpl.ChatDAOImpl;
import com.niit.collaboration.DAOImpl.EventDAOImpl;
import com.niit.collaboration.DAOImpl.ForumDAOImpl;
import com.niit.collaboration.DAOImpl.FriendDAOImpl;
import com.niit.collaboration.DAOImpl.JobDAOImpl;
import com.niit.collaboration.DAOImpl.UserDAOImpl;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {
	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(ApplicationContextConfig.class);
	 */

	@Autowired
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		/* logger.debug("Starting of the method getOracleDataSource"); */
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		// dataSource.setUsername("asdfghj"); // Schema name
		dataSource.setUsername("Collaboration");
		dataSource.setPassword("system");

		/*
		 * logger.debug("Setting the data source :" +
		 * dataSource.getConnectionProperties());
		 */
		/* logger.debug("Ending of the method getOracleDataSource"); */
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		/* logger.debug("Starting of the method getSessionFactory"); */
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(User.class);
		// sessionBuilder.addAnnotatedClass(User.class);

		/* logger.debug("Ending of the method getSessionFactory"); */
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		/* logger.debug("Starting of the method getTransactionManager"); */
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		/* logger.debug("Ending of the method getTransactionManager"); */
		return transactionManager;
	}

	@Autowired(required = true)
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "eventDAO")
	public EventDAO getEventDAO(SessionFactory sessionFactory) {
		return new EventDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "forumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory) {
		return new ForumDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "chatDAO")
	public ChatDAO getChatDAO(SessionFactory sessionFactory) {
		return new ChatDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "friendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory) {
		return new FriendDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "jobDAO")
	public JobDAO getJobDAO(SessionFactory sessionFactory) {
		return new JobDAOImpl(sessionFactory);
	}

}