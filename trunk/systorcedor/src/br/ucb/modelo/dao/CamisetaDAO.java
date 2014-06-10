package br.ucb.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.bean.CamisetaBean;

public class CamisetaDAO {
	private Connection con;

	public CamisetaDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	
	
	
	public int incluir(CamisetaBean camiseta) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		if (camiseta == null) return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO camiseta (nomeTime, dtCriacao, nomeCamiseta, descricao) values (?, ?, ?, ?)");
		stmt.setString(1, camiseta.getNomeTime());
		stmt.setInt(2, camiseta.getDtCriacao());
		stmt.setString(3, (camiseta.getNomeCamiseta()).toString());
		stmt.setString(4, camiseta.getDescricao());
		int retorno = stmt.executeUpdate();
		stmt.close();
		con.close();
		return retorno;
	}		

	public CamisetaBean consultar(Long id) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM camiseta WHERE id=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		CamisetaBean camiseta = null;
		if (rs.next()) {
			camiseta = new CamisetaBean();
		    camiseta.setId(rs.getLong("id"));
		    camiseta.setNomeTime(rs.getString("nomeTime"));
		    camiseta.setDtCriacao(rs.getInt("dtCriacao"));
		    camiseta.setNomeCamiseta(rs.getString("nomeCamiseta"));
		}
		rs.close();
		stmt.close();
		con.close();
		return camiseta;	
	}
	
	public List<CamisetaBean> listar() throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM camiseta");
		ResultSet rs = stmt.executeQuery();
	    List<CamisetaBean> camisetas = new ArrayList<CamisetaBean>();
	    while (rs.next()) {
		    CamisetaBean camiseta = new CamisetaBean();
		    camiseta.setId(rs.getLong("id"));
		    camiseta.setNomeTime(rs.getString("nomeTime"));
		    camiseta.setDtCriacao(rs.getInt("dtCriacao"));
		    camiseta.setNomeCamiseta(rs.getString("nomeCamiseta"));
		    camisetas.add(camiseta);
		}
		rs.close();
		stmt.close();
		con.close();
		return camisetas;	
	}
	
	public List<CamisetaBean> listarFiltro(String filtro) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM camiseta WHERE nomeTime like ?");
		stmt.setString(1, "%"+ filtro + "%");
		ResultSet rs = stmt.executeQuery();
	    List<CamisetaBean> camisetas = new ArrayList<CamisetaBean>();
	    while (rs.next()) {
		    CamisetaBean camiseta = new CamisetaBean();
		    camiseta.setId(rs.getLong("id"));
		    camiseta.setNomeCamiseta(rs.getString("nomeCamiseta"));
		    camiseta.setDtCriacao(rs.getInt("dtCriacao"));
		    camiseta.setNomeTime(rs.getString("nomeTime"));
		    camisetas.add(camiseta);
		}
		rs.close();
		stmt.close();
		con.close();
		return camisetas;	
	}
	
	public int alterar(CamisetaBean camiseta) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		if (camiseta == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("UPDATE camiseta SET nomeTime=?, dtCriacao=?, nomeCamiseta=?, descricao=? WHERE id=?");
		stmt.setString(1, camiseta.getNomeTime());
		stmt.setInt(2, camiseta.getDtCriacao());
		stmt.setString(3, (camiseta.getNomeCamiseta()).toString());
		stmt.setString(4, camiseta.getDescricao());
		stmt.setLong(5, camiseta.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		con.close();
		return retorno;
	}
	
	public int excluir(CamisetaBean camiseta) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		if (camiseta == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("DELETE FROM camiseta WHERE id=?");
		stmt.setLong(1, camiseta.getId());
		int retorno = stmt.executeUpdate();		
		stmt.close();
		con.close();
		return retorno;
	}

}
