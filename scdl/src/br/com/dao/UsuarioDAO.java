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

/**
 * @author marcleonio.medeiros
 *
 */
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
			usuario = (UsuarioDTO) session.createCriteria(UsuarioDTO.class)
					.add(Restrictions.eq("usuario", usuario.getUsuario()))
					.add(Restrictions.eq("senha", usuario.getSenha()))
					.uniqueResult();
		}catch(Exception e){
			throw e;
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
			session.save(usuario);
		}
	}


}
