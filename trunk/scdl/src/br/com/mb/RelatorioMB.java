package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.com.dao.EscolaLivroDAO;
import br.com.dto.EscolaLivroDTO;
import br.com.dto.LivroDTO;

@ManagedBean
@SessionScoped
public class RelatorioMB {
	
	private List<EscolaLivroDTO> historicoLivroAdotado;
	
	private List<EscolaLivroDTO> listQtdAlunoLivro =new ArrayList<>();
	
	private EscolaLivroDAO escolaLivroDAO = new EscolaLivroDAO();
	
	private LivroDTO livroDTO = new LivroDTO();
	

	public RelatorioMB() {
		populaHistoricoLivroAdotado();
		listQtdAlunoLivro = new ArrayList<>();
		
	}

	private void populaHistoricoLivroAdotado() {
		
		try {
			historicoLivroAdotado = escolaLivroDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void populaQtdAlunoLivro(){
		
		//listQtdAlunoLivro = new ArrayList<EscolaLivroDTO>();
		
		String consulta = "select l.id,l.nome,e.nome,sum(el.quantidade_aluno) from escola_livro el"+
							" inner join livro l on l.id=el.livro_id"+
							" inner join editora e on e.id=l.editora_id"+
							" where l.id="+livroDTO.getId()+
							" group by  l.id,l.nome,e.nome";
		try {
			listQtdAlunoLivro.addAll(escolaLivroDAO.consultaSQLQuery(consulta));
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




	public List<EscolaLivroDTO> getListQtdAlunoLivro() {
		return listQtdAlunoLivro;
	}




	public void setListQtdAlunoLivro(List<EscolaLivroDTO> listQtdAlunoLivro) {
		this.listQtdAlunoLivro = listQtdAlunoLivro;
	}

	public LivroDTO getLivroDTO() {
		return livroDTO;
	}

	public void setLivroDTO(LivroDTO livroDTO) {
		this.livroDTO = livroDTO;
	}



	

}
