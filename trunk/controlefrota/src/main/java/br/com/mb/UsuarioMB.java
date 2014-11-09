/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;
import br.com.ind.indCategoria;

/**
 * @author Marcle�nio
 *
 */
@ManagedBean
public class UsuarioMB extends GenericoMB<UsuarioDTO> implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<UsuarioDTO> listUsuario = new ArrayList<UsuarioDTO>();
	private List<indCategoria> listCategoria = new ArrayList<indCategoria>();
	private List<UsuarioDTO> listMotorista = new ArrayList<UsuarioDTO>();

	/**
	 * @throws Exception 
	 * 
	 */
	public UsuarioMB() throws Exception {
		listUsuario = usuarioDAO.list();
		listMotorista = usuarioDAO.listMotorista();
		listCategoria = Arrays.asList(indCategoria.values());
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(ActionEvent event) {
		usuarioDTO = new UsuarioDTO();
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		usuarioDTO.setUsuario(usuarioDTO.getMatricula());
		usuarioDAO.save(usuarioDTO);
		System.out.println(111);
		addMessage(rb.getString("successfullySaved"));
//		atualizaUserList(usuarioDTO);
		usuarioDTO = new UsuarioDTO();
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public List<UsuarioDTO> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<UsuarioDTO> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public List<indCategoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<indCategoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public List<UsuarioDTO> getListMotorista() {
		return listMotorista;
	}

	public void setListMotorista(List<UsuarioDTO> listMotorista) {
		this.listMotorista = listMotorista;
	}

}
