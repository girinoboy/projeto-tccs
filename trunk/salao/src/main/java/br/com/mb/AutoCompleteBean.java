package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.dao.ClienteDAO;
import br.com.dao.NotaFiscalDAO;
import br.com.model.Cliente;
import br.com.model.NotaFiscal;

@ManagedBean
public class AutoCompleteBean {
	
	ClienteDAO daoCliente = new ClienteDAO(Cliente.class);
	NotaFiscalDAO daoNotaFiscal = new NotaFiscalDAO(NotaFiscal.class);

	public AutoCompleteBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Cliente> completarCliente(String busca) throws Exception {
		List<Cliente> suggestions = new ArrayList<Cliente>();
		
		suggestions = daoCliente.autoComplete(busca);
//		cliente = new Cliente();
		return suggestions;
	}
	
	
	public List<NotaFiscal> completarNota(String busca) throws Exception {
		List<NotaFiscal> suggestions = new ArrayList<NotaFiscal>();
		
		suggestions = daoNotaFiscal.autoComplete(busca);
		return suggestions;
	}

}
