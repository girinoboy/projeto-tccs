package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.model.Item;

@ViewScoped
@ManagedBean
public class ItemBean extends GenericBean {

	private Item item = new Item();

	DAO<Item> dao = new DAO<Item>(Item.class);

	private List<Item> listItem = new ArrayList<Item>();

	private static final long serialVersionUID = 7253037847142472386L;

	public ItemBean() {
		listItem = dao.listaTodos();
	}

	public void guardarItem() {

		item.setValorUnitario(item.getProduto().getPreco());

		dao.adicionar(item);

		addMessage("Salvo!");
		listItem = dao.listaTodos();
		item = new Item();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getListItem() {
		return listItem;
	}

	public void setListItem(List<Item> listItem) {
		this.listItem = listItem;
	}

}
