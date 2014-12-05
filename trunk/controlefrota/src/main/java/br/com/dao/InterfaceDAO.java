package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface InterfaceDAO<T, ID extends Serializable> {
	
	public boolean inclui(T registro);

	public boolean exclui(T registro);

	public boolean altera(T registro);

	public Object consulta(ID id);
	
	public List<T> listaTudo();

	public List<T> listar(Criterion clausula[]);
	
	public List<T> listaLike(String nomeColuna , String Texto);
}
