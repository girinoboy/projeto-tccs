package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bo.ClienteBO;
import br.com.model.Cliente;

@ManagedBean
@SessionScoped
public class CadastrarCliente {

	private ClienteBO clienteBO = new ClienteBO();

	// private Cliente cliente = new Cliente();

	public void adicionar() {

		clienteBO.gravar();

	}

	public ClienteBO getClienteBO() {
		return clienteBO;
	}

	public void setClienteBO(ClienteBO clienteBO) {
		this.clienteBO = clienteBO;
	}
}
