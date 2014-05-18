package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dao.DAO;
import br.com.model.Cliente;

@RequestScoped
@ManagedBean
public class CadastroCliente extends GenericBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 214643162316805969L;
	private Cliente cliente = new Cliente();

	public void grava() {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		dao.adicionar(cliente);
		this.cliente = new Cliente();
		addMessage("Salvo!");
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
