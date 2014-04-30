/**
 * 
 */
package br.com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * @author Joelson
 *
 */
public class JPAUtil {

	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("projeto");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
