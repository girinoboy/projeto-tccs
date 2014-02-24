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
			usuario = (UsuarioDTO) HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
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
		String updateQuery = "UPDATE UsuarioDTO obj SET tema = :valor WHERE obj.id = :idUsuario";  
		HibernateUtility.getSession().createQuery(updateQuery)
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


	public boolean validaLogin(UsuarioDTO usuarioDTO) throws Exception{
		UsuarioDTO usuario = (UsuarioDTO) HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
				.add(Restrictions.eq("usuario", usuarioDTO.getUsuario()))
				.add(Restrictions.ne("id", usuarioDTO.getId() == null ? 0:usuarioDTO.getId()))
				.uniqueResult();
		if(usuario == null)
			return false;
		else
			return true;
	}


	public boolean validaCPF(UsuarioDTO usuarioDTO) throws Exception{
		UsuarioDTO usuario = (UsuarioDTO) HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
				.add(Restrictions.eq("cpf", usuarioDTO.getCpf()))
				.add(Restrictions.ne("id", usuarioDTO.getId() == null ? 0:usuarioDTO.getId()))
				.uniqueResult();
		if(usuario == null)
			return false;
		else
			return true;
	}


	public void exclusaoLogica(UsuarioDTO u) throws HibernateException, Exception {
		String updateQuery = "UPDATE UsuarioDTO obj SET excluido = :valor WHERE obj.id = :idUsuario";  
		HibernateUtility.getSession().createQuery(updateQuery)
		.setBoolean("valor", u.getExcluido())
		.setLong("idUsuario",u.getId())
		.executeUpdate();

		HibernateUtility.commitTransaction();
		
	}


	@SuppressWarnings("unchecked")
	public List<UsuarioDTO> listExclusaoLogica(Boolean excluido) throws HibernateException, Exception {
		List<UsuarioDTO> result = HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
				.add(Restrictions.or( 
						Restrictions.or(
						Restrictions.isNull("excluido"),
						Restrictions.eq("id", 1)),//1 = administrador
						Restrictions.eq("excluido", excluido)
						))
						.list();
		return result;
	}


}
