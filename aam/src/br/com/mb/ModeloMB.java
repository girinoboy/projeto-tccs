package br.com.mb;

import javax.faces.event.ActionEvent;

public interface ModeloMB {
	
	public void atualiza(ActionEvent event) throws Exception;
	public void reset(ActionEvent event);
	public void add(ActionEvent actionEvent) throws Exception;
	public void edit(ActionEvent actionEvent) throws Exception;
	public void del(ActionEvent actionEvent) throws Exception;

}
