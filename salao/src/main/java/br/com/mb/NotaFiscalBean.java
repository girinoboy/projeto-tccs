package br.com.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.model.Item;
import br.com.model.NotaFiscal;
import br.com.model.Produto;

@ViewScoped
@ManagedBean
public class NotaFiscalBean implements Serializable {

	private static final long serialVersionUID = 5439862683505400576L;

	private Item item = new Item();
	private Long idProduto;
	private NotaFiscal notaFiscal = new NotaFiscal();

	public void grava() {
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		dao.adicionar(notaFiscal);

		this.notaFiscal = new NotaFiscal();

	}

	public void guardarItem() {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);

		Produto produto = dao.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());

		notaFiscal.getItens().add(item);
		item.setNotafiscal(notaFiscal);

		item = new Item();
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Item getItem() {
		return item;
	}

}
