package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import br.com.dto.AbstractDTO;

public interface ModeloMB {
	public List listaGenerica = new ArrayList<AbstractDTO>();
	
	public void atualiza(ActionEvent event) throws Exception;
	public void reset(ActionEvent event);
	public void add(ActionEvent actionEvent) throws Exception;
	public void edit(ActionEvent actionEvent) throws Exception;
	public void del(ActionEvent actionEvent) throws Exception;

}
