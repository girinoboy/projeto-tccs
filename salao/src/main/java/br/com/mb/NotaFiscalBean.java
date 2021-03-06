package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import br.com.dao.DAO;
import br.com.model.Cliente;
import br.com.model.Item;
import br.com.model.NotaFiscal;

@RequestScoped
@ManagedBean
public class NotaFiscalBean extends GenericBean {

	private static final long serialVersionUID = 5439862683505400576L;

	private Item item = new Item();
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Cliente cliente = new Cliente();
	DAO<Item> dao = new DAO<Item>(Item.class);
	private List<Item> listItem = new ArrayList<Item>();

	private List<NotaFiscal> notas;

	public NotaFiscalBean() {
		listItem = dao.listaTodos();
	}

	public void grava() {

		// dao.adicionar(notaFiscal);
		//
		// this.notaFiscal = dao.buscaPorId(notaFiscal.getId());

	}

	public void guardarItem() {

		item.setValorUnitario(item.getProduto().getPreco());

		dao.adicionar(item);

		addMessage("Salvo!");
		listItem = dao.listaTodos();
		item = new Item();
	}

	public void adiciona() {
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		dao.adicionar(notaFiscal);
		
		this.notaFiscal = new NotaFiscal();
		this.item = new Item();
		addMessage("Dados salvos na Nota Fiscal!");
		//notas = dao.listaTodos();
		this.notaFiscal = new NotaFiscal();
	}
	
	public void handleSelect(SelectEvent event) {  

		try {
			notaFiscal = (NotaFiscal)event.getObject();

			addMessage("Selected:" + notaFiscal.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public List<Item> getListItem() {
		return listItem;
	}

	public void setListItem(List<Item> listItem) {
		this.listItem = listItem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<NotaFiscal> getNotas() {
		return notas;
	}

	public void setNotas(List<NotaFiscal> notas) {
		this.notas = notas;
	}
}
