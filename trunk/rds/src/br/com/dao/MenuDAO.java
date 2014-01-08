package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.dto.MenuDTO;
import br.com.factory.HibernateUtility;

/**
 *
 * @author Marcleonio
 */
public class MenuDAO extends GenericoDAO<MenuDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Connection con;
	//String sql;
	/*
	private final String COL_ID = "id";
    private final String COL_DESCRICAO = "descricao";
    private final String COL_URL = "url";
    private final String COL_ICONE = "icone";
    private final String COL_INDICE = "indice";*/
/*
	public boolean verificaSubMenu(Menu menu) {
		sql = "select count(*) as total from tab_menus where tab_menus_id=?";
		con = ConnectionFactory.getConnection();
		int t = 0;
		try {
			PreparedStatement pstm;
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, menu.getId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				t = rs.getInt("total");
			}
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return (t > 0);
	}*/
/*
	public List<Menu> buscaPorMenu(Menu menu) {
		List<Menu> listaMenu = new ArrayList<Menu>();
		sql = "select * from tab_menus where tab_menus_id=?";
		con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, menu.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Menu m = new Menu();
				m.setId(rs.getInt("id"));
				m.setDescricao(rs.getString("descricao"));
				m.setIcone(rs.getString("icone"));
				m.setViewId(rs.getString("viewId"));
				Menu subMenu = new Menu();
				subMenu = buscaPorId(rs.getInt("tab_menus_id"));
				m.setMenu(subMenu);
				listaMenu.add(m);
			}
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaMenu;
	}*/
/*
	public Menu buscaPorId(int id) {
		sql = "select * from tab_menus where id=?";
		con = ConnectionFactory.getConnection();
		Menu menu = new Menu();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				menu.setId(rs.getInt("id"));
				menu.setDescricao(rs.getString("descricao"));
				menu.setIcone(rs.getString("icone"));
				menu.setViewId(rs.getString("viewId"));
				Menu subMenu = new Menu();
				subMenu = buscaPorId(rs.getInt("tab_menus_id"));
				menu.setMenu(subMenu);
			}
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return menu;
	}*/
/*
	public List<Menu> listaTodos() {
		List<Menu> listaMenu = new ArrayList<Menu>();
		sql = "select * from tab_menus";
		con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setDescricao(rs.getString("descricao"));
				menu.setIcone(rs.getString("icone"));
				menu.setViewId(rs.getString("viewId"));
				Menu subMenu = new Menu();
				subMenu = buscaPorId(rs.getInt("tab_menus_id"));
				menu.setMenu(subMenu);
				listaMenu.add(menu);
			}
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaMenu;
	}*/
	
	/*
	public List<Menu> listaTodosPermissao() throws SQLException {

    	List<Menu> listaMenu = new ArrayList<Menu>();
        String query = "select * from menus";
        con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Menu menu = new Menu();
            menu.setId(rs.getInt(COL_ID));
            menu.setDescricao(rs.getString(COL_DESCRICAO));
            menu.setUrl(rs.getString(COL_URL));
            menu.setIcone(rs.getString(COL_ICONE));
            menu.setIndice(rs.getString(COL_INDICE));
            listaMenu.add(menu);
        }
        con.close();
        return listaMenu;
    }*//*
    public Menu buscaPorIdPermissao(int id) throws SQLException {
        Menu menu = new Menu();
        String query = "select * from menus where id=?";
        con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            menu.setId(rs.getInt(COL_ID));
            menu.setDescricao(rs.getString(COL_DESCRICAO));
            menu.setUrl(rs.getString(COL_URL));
            menu.setIcone(rs.getString(COL_ICONE));
            menu.setIndice(rs.getString(COL_INDICE));
        }
        con.close();
        return menu;
    }*/

	@SuppressWarnings("unchecked")
	public List<MenuDTO> listCabecalho() throws Exception {
		List<MenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(MenuDTO.class)
					.add(Restrictions.isNull("menuDTO.id"))
					.add(Restrictions.eq("ativoInativo",true))
					.addOrder(Order.asc("dropIndex"))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuDTO> listByIdUsuario(Integer id) throws Exception {
		// select * from tab_menus where tab_menus_id=?
		List<MenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(MenuDTO.class)
					.add(Restrictions.eq("id",id))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<MenuDTO> listByIdSub(Integer id) throws Exception {
		// select * from tab_menus where tab_menus_id=?
		List<MenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(MenuDTO.class)
					.add(Restrictions.eq("menuDTO.id",id))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<MenuDTO> listInativos() throws Exception {
		List<MenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(MenuDTO.class)
					.add(Restrictions.or(Restrictions.eq("ativoInativo",false),Restrictions.isNull("ativoInativo")))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	public void saveOnDragDrop(MenuDTO menu) throws HibernateException, Exception {
		//Nome da classe e atributo
		String updateQuery = "UPDATE MenuDTO obj SET ativoInativo = :valor1, dropIndex= :valor2, menuDTO.id= :valor3 WHERE obj.id = :id";  
		try {
			HibernateUtility.getSession().createQuery(updateQuery)
			.setBoolean("valor1", menu.getAtivoInativo())
			.setInteger("valor2", menu.getDropIndex())
			.setParameter("valor3", menu.getMenuDTO() == null ? null : menu.getMenuDTO().getId())			
			.setInteger("id",menu.getId())
			.executeUpdate();
			HibernateUtility.commitTransaction();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
	}
}