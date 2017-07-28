package com.niit.backend.config;

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

import com.niit.backend.DAO.BlogCommentDAO;
import com.niit.backend.DAO.BlogDAO;
import com.niit.backend.DAO.CommentDAO;
import com.niit.backend.DAO.EventDAO;
import com.niit.backend.DAO.ForumDAO;
import com.niit.backend.DAO.FriendDAO;
import com.niit.backend.DAO.JobDAO;
import com.niit.backend.DAOImpl.BlogCommentDAOImpl;
import com.niit.backend.DAOImpl.BlogDAOImpl;
import com.niit.backend.DAOImpl.CommentDAOImpl;
import com.niit.backend.DAOImpl.EventDAOImpl;
import com.niit.backend.DAOImpl.ForumDAOImpl;
import com.niit.backend.DAOImpl.FriendDAOImpl;
import com.niit.backend.DAOImpl.JobDAOImpl;
import com.niit.backend.DAOImpl.UserDAO;
import com.niit.backend.DAOImpl.UserDAOImpl;
import com.niit.backend.model.Blog;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.Comment;
import com.niit.backend.model.Event;
import com.niit.backend.model.Forum;
import com.niit.backend.model.Friend;
import com.niit.backend.model.Job;
import com.niit.backend.model.User;
import com.niit.backend.model.Chat;

@Configuration
@ComponentScan("com.niit.backend")
@EnableTransactionManagement
public class ApplicationContextConfig {
	

	@Autowired
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		/* logger.debug("Starting of the method getOracleDataSource"); */
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		// dataSource.setUsername("asdfghj"); // Schema name
		dataSource.setUsername("COLLAB");
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
		sessionBuilder.addAnnotatedClass(BlogComment.class);
	//	sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Comment.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(User.class);
		
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

	/*@Autowired(required = true)
	@Bean(name = "chatDAO")
	public ChatDAO getChatDAO(SessionFactory sessionFactory) {
		return new ChatDAOImpl(sessionFactory);
	}*/

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
	
	@Autowired(required = true)
	@Bean(name = "commentDAO")
	public CommentDAO getCommentDAO(SessionFactory sessionFactory) {
		return new CommentDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "blogCommentDAO")
	public BlogCommentDAO getBlogCommentDAO(SessionFactory sessionFactory) {
		return new BlogCommentDAOImpl(sessionFactory);
	}
}