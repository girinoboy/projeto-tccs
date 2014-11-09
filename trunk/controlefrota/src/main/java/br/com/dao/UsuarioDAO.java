/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;
import br.com.utility.Constantes;

/**
 * @author marcleonio.medeiros
 *
 */
@SuppressWarnings("unchecked")
public class UsuarioDAO extends GenericoDAO<UsuarioDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}


	public UsuarioDTO verificaLoginSenha(UsuarioDTO usuario) throws HibernateException, Exception {
		try{
			usuario = (UsuarioDTO) HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
					//.setFetchMode("perfilDTO", FetchMode.JOIN)
					.add(Restrictions.eq("matricula", usuario.getUsuario()))
					.add(Restrictions.eq("senha", usuario.getSenha()))
					.uniqueResult();
		}catch(Exception e){
			throw e;
		}finally{
			HibernateUtility.closeSession();
			//			session.close();			
		}
		return usuario;

	}

	public void saveTheme(String theme, UsuarioDTO usuario) throws HibernateException, Exception {
		//Nome da classe e atributo
		String updateQuery = "UPDATE Usuario obj SET tema = :valor WHERE obj.id = :idUsuario";  
		session.createQuery(updateQuery)
		.setString("valor", theme)
		.setLong("idUsuario",usuario.getId())
		.executeUpdate();

		HibernateUtility.commitTransaction();

	}


	public void create(List<UsuarioDTO> listUser) throws HibernateException, Exception {

		for (UsuarioDTO usuario : listUser) { 
			HibernateUtility.getSession().save(usuario);
		}
	}



	public List<UsuarioDTO> listMotorista() throws HibernateException, Exception {
		return HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
				.add(Restrictions.eq("perfilDTO.id", Constantes.MOTORISTA))
				.list();
	}


}
