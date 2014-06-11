package br.ucb.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.bean.ClubeBean;
import br.ucb.modelo.bean.TorcedorBean;
import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.enumerador.Uf;

public class ListClubesDAO {
	private Connection con;
	
	public ListClubesDAO() throws SQLException{
		//this.con = ConnectionFactory.getConnection();
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
		    clubes.add(clube);
		}
		rs.close();
		stmt.close();
		con.close();
		return clubes;	
	}
	
	public ClubeBean clube(Long id) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT id, nomeTime, dtCriacao, descricao FROM clube where id = ? ");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		ClubeBean clube = null;
		if (rs.next()) {
			clube = new ClubeBean();
		    clube.setId(rs.getLong("id"));
		    clube.setNomeTime(rs.getString("nomeTime"));
		    clube.setDtCriacao(rs.getInt("dtCriacao"));
		    clube.setDescricao(rs.getString("descricao"));
		}
		rs.close();
		stmt.close();
		con.close();
		return clube;	
	}
	
	public List<TorcedorBean> listarTorcedorDoClube(Long id) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT t.id, t.nome, t.dtaNasc, t.estCivil, t.email, t.uf FROM torcedor t, clube c WHERE t.idClube = c.id AND t.idClube = ?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		List<TorcedorBean> torcedores = new ArrayList<TorcedorBean>();
	    while (rs.next()) {
		    TorcedorBean torcedor = new TorcedorBean();
		    torcedor.setId(rs.getLong("id"));
		    torcedor.setNome(rs.getString("nome"));
		    torcedor.setDtaNasc(rs.getDate("dtaNasc"));
		    torcedor.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		    torcedor.setEmail(rs.getString("email"));
		    torcedor.setUf(Uf.valueOf(rs.getString("uf")));
		    torcedores.add(torcedor);
		}
		rs.close();
		stmt.close();
		con.close();
		return torcedores;	
	}
	
	public List<ClubeBean> listarFiltro(String filtro) throws SQLException {
		this.con = ConnectionFactory.getConnection();
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM clube WHERE nomeTime like ?");
		stmt.setString(1,"%"+ filtro + "%");
		ResultSet rs = stmt.executeQuery();
	    List<ClubeBean> clubes = new ArrayList<ClubeBean>();
	    while (rs.next()) {
		    ClubeBean clube = new ClubeBean();
		    clube.setId(rs.getLong("id"));
		    clube.setNomeTime(rs.getString("nomeTime"));
		    clubes.add(clube);
		}
		rs.close();
		stmt.close();
		con.close();
		return clubes;	
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
		}
		rs.close();
		stmt.close();
		con.close();
		return clube;	
	}
}
