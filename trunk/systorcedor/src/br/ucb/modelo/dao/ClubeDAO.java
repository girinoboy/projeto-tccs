package br.ucb.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.bean.ClubeBean;
import br.ucb.modelo.enumerador.Uf;

public class ClubeDAO {
	private Connection con;
	
	public ClubeDAO() throws SQLException {
		//this.con = ConnectionFactory.getConnection();
	}
	
	public int incluir(ClubeBean clube) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		if (clube == null) return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO clube (nomeTime, dtCriacao, uf, descricao) values (?, ?, ?, ?)");
		stmt.setString(1, clube.getNomeTime());
		stmt.setInt(2, clube.getDtCriacao());
		stmt.setString(3, (clube.getUf()).toString());
		stmt.setString(4, clube.getDescricao());
		int retorno = stmt.executeUpdate();
		stmt.close();
		con.close();
		return retorno;
	}		

	public ClubeBean consultar(Long id) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM clube WHERE id=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		ClubeBean clube = null;
		if (rs.next()) {
			clube = new ClubeBean();
		    clube.setId(rs.getLong("id"));
		    clube.setNomeTime(rs.getString("nomeTime"));
		    clube.setDtCriacao(rs.getInt("dtCriacao"));
		    clube.setUf(Uf.valueOf(rs.getString("uf")));
		}
		rs.close();
		stmt.close();
		con.close();
		return clube;	
	}
	
	public List<ClubeBean> listar() throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM clube");
		ResultSet rs = stmt.executeQuery();
	    List<ClubeBean> clubes = new ArrayList<ClubeBean>();
	    while (rs.next()) {
		    ClubeBean clube = new ClubeBean();
		    clube.setId(rs.getLong("id"));
		    clube.setNomeTime(rs.getString("nomeTime"));
		    clube.setDtCriacao(rs.getInt("dtCriacao"));
		    clube.setUf(Uf.valueOf(rs.getString("uf")));
		    clubes.add(clube);
		}
		rs.close();
		stmt.close();
		con.close();
		return clubes;	
	}
	
	public List<ClubeBean> listarFiltro(String filtro) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM clube WHERE nomeTime like ?");
		stmt.setString(1, "%"+ filtro + "%");
		ResultSet rs = stmt.executeQuery();
	    List<ClubeBean> clubes = new ArrayList<ClubeBean>();
	    while (rs.next()) {
		    ClubeBean clube = new ClubeBean();
		    clube.setId(rs.getLong("id"));
		    clube.setNomeTime(rs.getString("nomeTime"));
		    clube.setDtCriacao(rs.getInt("dtCriacao"));
		    clube.setUf(Uf.valueOf(rs.getString("uf")));
		    clubes.add(clube);
		}
		rs.close();
		stmt.close();
		con.close();
		return clubes;	
	}
	
	public int alterar(ClubeBean clube) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		if (clube == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("UPDATE clube SET nomeTime=?, dtCriacao=?, uf=?, descricao=? WHERE id=?");
		stmt.setString(1, clube.getNomeTime());
		stmt.setInt(2, clube.getDtCriacao());
		stmt.setString(3, (clube.getUf()).toString());
		stmt.setString(4, clube.getDescricao());
		stmt.setLong(5, clube.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		con.close();
		return retorno;
	}
	
	public int excluir(ClubeBean clube) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		if (clube == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("DELETE FROM clube WHERE id=?");
		stmt.setLong(1, clube.getId());
		int retorno = stmt.executeUpdate();		
		stmt.close();
		con.close();
		return retorno;
	}

}