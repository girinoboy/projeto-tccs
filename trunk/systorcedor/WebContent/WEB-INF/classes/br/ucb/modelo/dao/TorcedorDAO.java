package br.ucb.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.bean.TorcedorBean;
import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.enumerador.Uf;

public class TorcedorDAO {
	private Connection con;
	
	public TorcedorDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	
	public int incluir(TorcedorBean torcedor) throws SQLException {
		if (torcedor == null) return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO torcedor (nome, dtaNasc, estCivil, email, uf, idClube) values (?, ?, ?, ?, ?, ?)");
		stmt.setString(1, torcedor.getNome());
		java.sql.Date dta = new java.sql.Date(torcedor.getDtaNasc().getTime());
		stmt.setDate(2, dta);
		stmt.setString(3, (torcedor.getEstadoCivil()).toString());
		stmt.setString(4, torcedor.getEmail());
		stmt.setString(5, (torcedor.getUf()).toString());
		stmt.setLong(6, torcedor.getClube().getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}		

	public TorcedorBean consultar(Long id) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM torcedor WHERE id=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		TorcedorBean torcedor = null;
		if (rs.next()) {
			torcedor = new TorcedorBean();
		    torcedor.setId(rs.getLong("id"));
		    torcedor.setNome(rs.getString("nome"));
		    torcedor.setDtaNasc(rs.getDate("dtaNasc"));
		    torcedor.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		    torcedor.setEmail(rs.getString("email"));
		    torcedor.setUf(Uf.valueOf(rs.getString("uf")));
		    torcedor.setClube(new ClubeDAO().consultar(rs.getLong("idClube")));
		}
		rs.close();
		stmt.close();
		return torcedor;	
	}
	
	public List<TorcedorBean> listar() throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM torcedor");
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
		    torcedor.setClube(new ClubeDAO().consultar(rs.getLong("idClube")));
		    torcedores.add(torcedor);
		}
		rs.close();
		stmt.close();
		return torcedores;	
	}
	public List<TorcedorBean> listarFiltro(String filtro) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM torcedor WHERE nome like ?");
		stmt.setString(1, "%"+ filtro + "%");
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
		    torcedor.setClube(new ClubeDAO().consultar(rs.getLong("idClube")));
		    torcedores.add(torcedor);
		}
		rs.close();
		stmt.close();
		return torcedores;	
	}
	
	public int alterar(TorcedorBean torcedor) throws SQLException {
		if (torcedor == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("UPDATE torcedor SET nome=?, dtaNasc=?, estCivil=?, email=?, uf=?, idClube=? WHERE id=?");
		stmt.setString(1, torcedor.getNome());
		java.sql.Date dta = new java.sql.Date(torcedor.getDtaNasc().getTime());
		stmt.setDate(2, dta);
		stmt.setString(3, (torcedor.getEstadoCivil()).toString());
		stmt.setString(4, torcedor.getEmail());
		stmt.setString(5, (torcedor.getUf()).toString());
		stmt.setLong(6, torcedor.getClube().getId());
		stmt.setLong(7, torcedor.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}
	
	public int excluir(TorcedorBean torcedor) throws SQLException {
		if (torcedor == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("DELETE FROM torcedor WHERE id=?");
		stmt.setLong(1, torcedor.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

}