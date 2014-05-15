package br.com.bo;

import br.com.dao.ClienteDAO;
import br.com.model.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();
	private Cliente cliente = new Cliente();

	public ClienteBO() {

	}

	public void gravar() {
		dao.adicionar(cliente);
		cliente = new Cliente();

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
