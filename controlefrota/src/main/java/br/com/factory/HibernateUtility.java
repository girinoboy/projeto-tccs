package br.com.factory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



//@SuppressWarnings({ "rawtypes" })
public class HibernateUtility {

	private static SessionFactory sessionFactory;
	private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> transactionThread = new ThreadLocal<Transaction>();


	public static Session getSession() throws Exception {
		try{
			Session session = sessionThread.get();
			if ((session == null) || (!(session.isOpen()))) {
				session = sessionFactory.openSession();
				sessionThread.set(session);
			}
		}catch(Exception e){
			throw e;
		}
		return sessionThread.get();
	}

	public static void closeSession() {
		Session session = sessionThread.get();
		if ((session != null) && (session.isOpen())) {
			sessionThread.get().close();
			sessionThread.set(null);
		}
	}

	public static void beginTransaction() throws Exception {
		try{
			Transaction transaction = getSession().beginTransaction();
			transactionThread.set(transaction);
		}catch(Exception e){
			throw e;
		}
	}
	
	public static void flushSession() throws Exception {
		sessionThread.get().flush();  
    }  

	public static void commitTransaction() {
		Transaction transaction = transactionThread.get();
		if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
			transaction.commit();
			transactionThread.set(null);
		}
	}

	public static void rollbackTransaction() {
		Transaction transaction = transactionThread.get();
		if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
			transaction.rollback();
			transaction.begin();  
			//transactionThread.set(null);
		}
	}

	static {
		try {
			
			try {  
				Configuration configuration = new Configuration();
				//CADASTROS abaixo coloque todas classes que deseja ser modelo para criação do banco de dados
				for(Class<?> clazz : getClasses("br.com.dto")){
					configuration.addAnnotatedClass(clazz);
				}
				//configuration.addAnnotatedClass(NewView.class);
				sessionFactory =
				(configuration //configura as propiedades

//				.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect")//Mudar dialect do banco		
//				.setProperty("hibernate.connection.datasource", "java:/oracleDS")//DS da pasta deploy Jboss
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")// tipo de dialeto do banco
				.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
//				.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")// tipo de dialeto do banco
//				.setProperty("hibernate.connection.driver_class","com.hsqldb.jdbc.Driver")
				.setProperty("hibernate.max_fetch_depth","2")
//				.setProperty("hibernate.connection.datasource", "java:rdsDS")
//				.setProperty("hibernate.connection.datasource", "java:DefaultDS")
				
				.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/cf")
				.setProperty("hibernate.connection.username", "root")
				.setProperty("hibernate.connection.password", "root")

				.setProperty("hibernate.hbm2ddl.auto", "update")
				.setProperty("hibernate.c3p0.max_size", "10")
				.setProperty("hibernate.c3p0.min_size", "2")
				.setProperty("hibernate.c3p0.timeout", "5000")
				.setProperty("hibernate.c3p0.max_statements", "10")
				.setProperty("hibernate.c3p0.idle_test_period", "3000")
				.setProperty("hibernate.c3p0.acquire_increment", "2")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.use_outer_join", "true")
				.setProperty("hibernate.generate_statistics", "true")
				.setProperty("hibernate.use_sql_comments", "true")
				.setProperty("hibernate.format_sql", "true")
				//.setProperty("hibernate.order_updates", "true")
				.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider")
				.setProperty("hibernate.current_session_context_class", "thread")
				//.setProperty("hibernate.query.factory_class", "org.hibernate.hql.internal.classic.ClassicQueryTranslatorFactory")
				.setProperty("hibernate.validator.apply_to_ddl", "false")
				.setProperty("hibernate.validator.autoregister_listeners", "false")
				//inicio hibernate envers
				.setProperty("hibernate.ejb.event.post-insert","org.hibernate.ejb.event.EJB3PostInsertEventListener,org.hibernate.envers.event.AuditEventListener" )
				.setProperty("hibernate.ejb.event.post-update","org.hibernate.ejb.event.EJB3PostUpdateEventListener,org.hibernate.envers.event.AuditEventListener" )
				.setProperty("hibernate.ejb.event.post-delete","org.hibernate.ejb.event.EJB3PostDeleteEventListener,org.hibernate.envers.event.AuditEventListener" )
				.setProperty("hibernate.ejb.event.pre-collection-update","org.hibernate.envers.event.AuditEventListener" )
				.setProperty("hibernate.ejb.event.pre-collection-remove","org.hibernate.envers.event.AuditEventListener" )
				.setProperty("hibernate.ejb.event.post-collection-recreate","org.hibernate.envers.event.AuditEventListener" )
				
				.setProperty("hibernate.auditable","true")
				//fim hibernate envers
				)
				//.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory")
				//.setProperty("hibernate.default_schema", "dbo")
                //.addAnnotatedClass(UsuarioDTO.class)
				//.setProperty("hibernate.connection.autocommit", "true")
				.setProperty("hibernate.connection.pool_size", "1")
				.buildSessionFactory();
				/*
				Configuration config = new Configuration();//.configure();
				config.buildMappings();
				ServiceRegistry serviceRegistry =  new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry(); 
				SessionFactory factory = config.buildSessionFactory(serviceRegistry);*/
			} catch (Exception ex) {  
				//logger.fatal("Something happened here!!! 8-O", ex);
				ex.printStackTrace();
				throw new ExceptionInInitializerError(ex);  
			}  


		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("rawtypes")
	public static List<Class> getClasses(String pckgname) throws ClassNotFoundException {  
		List<Class> classes = new ArrayList<Class>();  
		ClassLoader cld = Thread.currentThread().getContextClassLoader();  
		String path = '/' + pckgname.replace('.', '/');  
		URL resource = cld.getResource(path);  
		File directory = new File(resource.getFile());  
		if (directory.exists()) {  
			String[] files = directory.list();  
			for (int i = 0; i < files.length; i++) {  
				if (files[i].endsWith(".class")) {  
					classes.add(Class.forName(pckgname + '.'  
							+ files[i].substring(0, files[i].length() - 6)));  
				}  
			}  
		}   
		return classes;  
	}  

}

