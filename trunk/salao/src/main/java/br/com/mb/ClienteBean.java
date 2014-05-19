package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dao.DAO;
import br.com.model.Cliente;

@RequestScoped
@ManagedBean
public class ClienteBean extends GenericBean{

	private static final long serialVersionUID = 214643162316805969L;
	
	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes;
		
	public void grava() {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		
		if (cliente.getId() == null) {			
			dao.adicionar(cliente);
		}else{
			dao.atualiza(cliente);
		}
		addMessage("Salvo!");
		clientes = dao.listaTodos();
		this.cliente = new Cliente();
	}
	
	public void remove(){
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		dao.remove(cliente);
		this.clientes = dao.listaTodos();
		addMessage("Removido");
	}
	
	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = new DAO<Cliente>(Cliente.class).listaTodos();
		}
		return clientes;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
