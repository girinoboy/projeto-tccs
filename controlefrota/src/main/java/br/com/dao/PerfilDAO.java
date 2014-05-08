package br.com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.PerfilDTO;
import br.com.dto.UsuarioDTO;
import br.com.dto.UsuarioPerfilDTO;
import br.com.factory.HibernateUtility;

public class PerfilDAO extends GenericoDAO<PerfilDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioPerfilDAO usuarioPerfilDAO;

	public List<PerfilDTO> listPerfisRestantes(UsuarioDTO usuario) throws Exception {
		usuarioPerfilDAO = new UsuarioPerfilDAO();
		try {
			List<UsuarioPerfilDTO> list2 = usuarioPerfilDAO.listPorUsuario(usuario.getId());

			List<Integer> ids = new ArrayList<Integer>();  
			ids.add(0);
			for (UsuarioPerfilDTO usuarioPerfil : list2) {
				ids.add(usuarioPerfil.getPerfilDTO().getId());
			}

			@SuppressWarnings("unchecked")
			List<PerfilDTO> list = HibernateUtility.getSession().createCriteria(PerfilDTO.class)
			.add(Restrictions.not(Restrictions.in("id", ids )))
			.list();
			//HibernateUtility.closeSession();
			return (List<PerfilDTO>) list;
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			HibernateUtility.closeSession();
		}


	}

	public List<PerfilDTO> listPerfisUsuario(UsuarioDTO usuario) throws Exception {
		usuarioPerfilDAO = new UsuarioPerfilDAO();
		try {
			List<UsuarioPerfilDTO> list2 = usuarioPerfilDAO.listPorUsuario(usuario.getId());

			List<Integer> ids = new ArrayList<Integer>();  
			for (UsuarioPerfilDTO usuarioPerfil : list2) {
				ids.add(usuarioPerfil.getPerfilDTO().getId());
			}

			@SuppressWarnings("unchecked")
			List<PerfilDTO> list = HibernateUtility.getSession().createCriteria(PerfilDTO.class)
			.add(Restrictions.in("id", ids ))
			.list();
			//HibernateUtility.closeSession();
			return (List<PerfilDTO>) list;
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			HibernateUtility.closeSession();
		}
	}

}
