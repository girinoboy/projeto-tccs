package br.ucb.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.bean.UfBean;

public class UfDAO {
	private Connection con;
	
	public UfDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<UfBean> listar() throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM uf");
		ResultSet rs = stmt.executeQuery();
	    List<UfBean> ufs = new ArrayList<UfBean>();
	    while (rs.next()) {
	    	UfBean uf = new UfBean();
	    	uf.setId(rs.getLong("id"));
	    	uf.setSigla(rs.getString("sigla"));
		    ufs.add(uf);
		}
		rs.close();
		stmt.close();
		con.close();
		return ufs;
	}
	

}
