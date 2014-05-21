package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.model.Cliente;

@ViewScoped
@ManagedBean
public class ClienteBean extends GenericBean{

	
	private static final long serialVersionUID = 8547551505625337061L;

	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes;
	
	public ClienteBean(){
		clientes = new DAO<Cliente>(Cliente.class).listaTodos();
		System.out.println(cliente);
	}
		
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
		addMessage("Removido");
		this.clientes = dao.listaTodos();
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
