package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.dao.EscolaLivroDAO;
import br.com.dto.EscolaLivroDTO;

@ManagedBean
public class RelatorioMB {
	
	private List<EscolaLivroDTO> historicoLivroAdotado;
	

	public RelatorioMB() {
		populaHistoricoLivroAdotado();
	}
	
	
	

	private void populaHistoricoLivroAdotado() {
		EscolaLivroDAO escolaLivroDAO = new EscolaLivroDAO();
		try {
			historicoLivroAdotado = escolaLivroDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}




	public List<EscolaLivroDTO> getHistoricoLivroAdotado() {
		return historicoLivroAdotado;
	}

	public void setHistoricoLivroAdotado(List<EscolaLivroDTO> historicoLivroAdotado) {
		this.historicoLivroAdotado = historicoLivroAdotado;
	}

}
