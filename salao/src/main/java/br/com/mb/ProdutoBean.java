package br.com.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.model.Produto;

@ViewScoped
@ManagedBean
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2615041034654321741L;

	private Produto produto = new Produto();

	private List<Produto> produtos;
	
	public void grava() {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		
		if (produto.getId() == null) {
			dao.adicionar(produto);		
		}else{
			dao.atualiza(produto);
		}
		
		produtos = dao.listaTodos();
		this.produto = new Produto();
	}
	
	public void remove(Produto produto){
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	
	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando Produtos...");
			produtos = new DAO<Produto>(Produto.class).listaTodos();
		}
		return produtos;
	}

	public Produto getProduto() {
		return produto;
	}

}
