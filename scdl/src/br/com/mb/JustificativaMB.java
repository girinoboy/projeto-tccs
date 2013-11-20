package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.JustificativaDAO;
import br.com.dto.JustificativaDTO;

@ManagedBean
@SessionScoped
public class JustificativaMB extends GenericoMB implements ModeloMB{

	private List<JustificativaDTO> listJustificativa;
	private JustificativaDAO justificativaDAO = new JustificativaDAO();
	private JustificativaDTO justificativaDTO = new JustificativaDTO();

	public static List<JustificativaDTO> justificativaDB;  

	static {  
		justificativaDB = new ArrayList<JustificativaDTO>();
		try {
			justificativaDB =  new ArrayList<JustificativaDTO>();

			justificativaDB.add(new JustificativaDTO(1,"Pre�o alto"));
			justificativaDB.add(new JustificativaDTO(2,"Livro cont�m erro gramatical"));
			justificativaDB.add(new JustificativaDTO(3,"Livro cont�m informa��es erradas"));
			justificativaDB.add(new JustificativaDTO(4,"Livro do concorrente � melhor"));
			justificativaDB.add(new JustificativaDTO(5,"Livro do concorrente � mais barato"));
			justificativaDB.add(new JustificativaDTO(6,"N�o est� adequado a ementa da institui��o"));
			justificativaDB.add(new JustificativaDTO(7,"O livro n�o me agradou"));
			justificativaDB.add(new JustificativaDTO(8,"Outros"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	public JustificativaMB() {
		try {
			listJustificativa = justificativaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		justificativaDAO.save(justificativaDTO);
		justificativaDTO = new JustificativaDTO();
		listJustificativa = justificativaDAO.list();
		addMessage("salvo");

	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(ActionEvent actionEvent)  {
		try {
			justificativaDAO.delete(justificativaDTO);

			listJustificativa = justificativaDAO.list();

		} catch (Exception e) {
			addMessage("Justificativa n�o exclu�da.");
			e.printStackTrace();
		}

	}

	public List<JustificativaDTO> getListJustificativa() {
		return listJustificativa;
	}

	public void setListJustificativa(List<JustificativaDTO> listJustificativa) {
		this.listJustificativa = listJustificativa;
	}


	public JustificativaDTO getJustificativaDTO() {
		return justificativaDTO;
	}


	public void setJustificativaDTO(JustificativaDTO justificativaDTO) {
		this.justificativaDTO = justificativaDTO;
	}

}
