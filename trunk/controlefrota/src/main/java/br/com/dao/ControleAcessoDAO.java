package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.PerfilMenuDTO;
import br.com.dto.PermissaoMenuDTO;
import br.com.dto.UsuarioPerfilDTO;
import br.com.factory.HibernateUtility;

public class ControleAcessoDAO extends GenericoDAO<PerfilMenuDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	private Connection con;
	private final String COL_ID = "id";
	private final String COL_USUARIO = "usuarios_id";
	private final String COL_MENU =  "menus_id";*/
/*
	public List<PermissaoMenu> buscaPorUsuario(Usuario usuario) throws SQLException {
		List<PermissaoMenu> listaPermissao = new ArrayList<PermissaoMenu>();
		MenuDAO menuDAO = new MenuDAO();
		String query = "select * from permissoes where usuarios_id=?";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, usuario.getId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			PermissaoMenu permissao = new PermissaoMenu();
			permissao.setId(rs.getInt(COL_ID));
			permissao.setUsuario(usuario);
			permissao.setMenu(menuDAO.buscaPorId(rs.getInt(COL_MENU)));
			listaPermissao.add(permissao);
		}
		con.close();
		return listaPermissao;
	}*/
/*
	public PermissaoMenu buscaPorMenuUsuario(Menu menu, Usuario usuario) throws SQLException {
		PermissaoMenu permissao = new PermissaoMenu();
		String query = "select * from permissoes where usuarios_id=? and menus_id=?";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, usuario.getId());
		ps.setInt(2, menu.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			permissao.setId(rs.getInt(COL_ID));
			permissao.setMenu(menu);
			permissao.setUsuario(usuario);
		}
		con.close();
		return permissao;
	}*/
/*
	public void excluiPorUsuario(Usuario usuario) throws SQLException {
		String query = "delete from permissoes where usuarios_id=?";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, usuario.getId());
		ps.execute();
		con.close();
	}*/
/*
	public void salvar(PermissaoMenu permissao) throws SQLException {
		String query = "insert into permissoes(menus_id,usuarios_id) values(?,?)";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, permissao.getMenu().getId());
		ps.setInt(2, permissao.getUsuario().getId());
		ps.execute();
		con.close();
	}*/

	@SuppressWarnings("unchecked")
	public List<PermissaoMenuDTO> listByIdUsuario(int id) throws Exception {
		List<PermissaoMenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(PermissaoMenuDTO.class)
					.add(Restrictions.eq("usuario.id", id))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}


		return list;
	}

	public void deletePermissaoPorUsuario(int id) throws Exception {
		try {
			//Nome da classe e atributo
			String deleteQuery = "delete from Permissao where usuario.id = :usuariosId";  
			HibernateUtility.getSession().createQuery(deleteQuery)
			.setInteger("usuariosId", id)
			.executeUpdate();
			
			HibernateUtility.commitTransaction();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
	}
	
	public void deletePermissaoPorPerfil(Integer id) throws Exception {
		try {
			//Nome da classe e atributo
			String deleteQuery = "delete from PerfilMenuDTO where perfilDTO.id = :idPerfil";  
			HibernateUtility.getSession().createQuery(deleteQuery)
			.setLong("idPerfil", id)
			.executeUpdate();
			
			HibernateUtility.commitTransaction();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
	}

	public PermissaoMenuDTO buscaPorMenuUsuario(int menu, int usuario) throws Exception {
		PermissaoMenuDTO permissao;
		try {
			permissao = (PermissaoMenuDTO) HibernateUtility.getSession().createCriteria(PermissaoMenuDTO.class)
					.add(Restrictions.eq("usuarios_id", usuario))
					.add(Restrictions.eq("menus_id", menu))
					.uniqueResult();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}

		return permissao;

	}
	@SuppressWarnings("unchecked")
	public List<PerfilMenuDTO> listByIdPerfil(Integer id) throws Exception {
		List<PerfilMenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(PerfilMenuDTO.class)
					.add(Restrictions.eq("perfilDTO.id", id))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}


		return list;
	}

	public PermissaoMenuDTO buscaPorMenuPerfil(Integer menu, Integer perfil) throws Exception {
		PermissaoMenuDTO permissao;
		try {
			permissao = (PermissaoMenuDTO) HibernateUtility.getSession().createCriteria(PermissaoMenuDTO.class)
					.add(Restrictions.eq("perfilDTO.id", perfil))
					.add(Restrictions.eq("menuDTO.id", menu))
					.uniqueResult();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}

		return permissao;

	}

	@SuppressWarnings("unchecked")
	public List<PermissaoMenuDTO> listByIdsPerfil(List<UsuarioPerfilDTO> usuarioPeril) throws Exception {
		List<PermissaoMenuDTO> list;
		try {

			Criteria criteria = HibernateUtility.getSession().createCriteria(PermissaoMenuDTO.class);
			for (UsuarioPerfilDTO usuarioPerfil : usuarioPeril) {
				criteria.add(Restrictions.eq("perfil.id", usuarioPerfil.getPerfilDTO().getId()));
			}
			list = criteria.list();
			
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}


		return list;
	}

}

