/**
 * 
 */
package br.com.mb;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import br.com.dao.FinanceiroDAO;
import br.com.dao.ParametroDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.FinanceiroDTO;
import br.com.dto.ParametroDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@RequestScoped
public class FinanceiroMB extends GenericoMB implements ModeloMB{

	private FinanceiroDTO financeiroDTO = new FinanceiroDTO();
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();
	private List<FinanceiroDTO> listFinanceiroDTO;
	private FinanceiroDTO selectedFinanceiroDTO = new FinanceiroDTO();

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	/**
	 * 
	 */
	public FinanceiroMB() {
		try {
			listFinanceiroDTO = financeiroDAO.list();

			atualizaMensalidade();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void atualizaMensalidade()throws Exception {
		ParametroDAO parametroDAO = new ParametroDAO();
		ParametroDTO p = parametroDAO.recuperaParametro("mensalidade");
		if(financeiroDTO==null){
			financeiroDTO = new FinanceiroDTO();
			financeiroDTO.setValorMensalidade(Double.valueOf(p.getValor()));
		}
		else if(financeiroDTO.getValorMensalidade() != null && financeiroDTO.getValorMensalidade() != Double.valueOf(p.getValor())){
			p.setValor(financeiroDTO.getValorMensalidade().toString());
			parametroDAO.save(p);
		}else{
			financeiroDTO.setValorMensalidade(Double.valueOf(p.getValor()));
		}

	}

	public void add(ActionEvent actionEvent) throws Exception {
		atualizaMensalidade();
		listFinanceiroDTO = financeiroDAO.list();
		addMessage("Operação realizada com sucesso!.");
		//financeiroDAO.save(financeiroDTO);

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public void onCellEdit(CellEditEvent event) throws Exception {
		//		Object oldValue = event.getOldValue();
		//		Object newValue = event.getNewValue();
		UsuarioDTO usuarioDTO = listFinanceiroDTO.get(event.getRowIndex()).getUsuarioDTO();
		//		usuarioDTO.setFinanceiroDTO(financeiroDTO);
		usuarioDTO.getFinanceiroDTO().setValorComDesconto(calculaDesconto(usuarioDTO));
		System.out.println(usuarioDTO.getFinanceiroDTO().getDataPagamento());
		System.out.println(usuarioDTO.getFinanceiroDTO().getDia());
		usuarioDAO.save(usuarioDTO);
		//		listFinanceiroDTO = financeiroDAO.list();
		atualizaMensalidade();
		//		if(newValue != null && !newValue.equals(oldValue)) {
		//			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);  
		//			FacesContext.getCurrentInstance().addMessage(null, msg);
		//		}
	}

	public Double calculaDesconto(UsuarioDTO usuarioDTO) throws HibernateException, Exception{
		ParametroDAO parametroDAO = new ParametroDAO();
		ParametroDTO p = parametroDAO.recuperaParametro("mensalidade");
		if(p != null && p.getValor() != null)
			return (Double.valueOf(p.getValor()) - Double.valueOf(p.getValor()) * usuarioDTO.getDesconto());
		else
			return(0d);
	}

	public void handleDateSelect(SelectEvent event) throws Exception {  

		listFinanceiroDTO = financeiroDAO.consultaPorMesAno(event.getObject());
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));  
	}  

	public FinanceiroDTO getFinanceiroDTO() {
		return financeiroDTO;
	}

	public void setFinanceiroDTO(FinanceiroDTO financeiroDTO) {
		this.financeiroDTO = financeiroDTO;
	}

	public List<FinanceiroDTO> getListFinanceiroDTO() {
		return listFinanceiroDTO;
	}

	public void setListFinanceiroDTO(List<FinanceiroDTO> listFinanceiroDTO) {
		this.listFinanceiroDTO = listFinanceiroDTO;
	}

	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub

	}

	public void reset(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	public FinanceiroDTO getSelectedFinanceiroDTO() {
		return selectedFinanceiroDTO;
	}

	public void setSelectedFinanceiroDTO(FinanceiroDTO selectedFinanceiroDTO) {
		this.selectedFinanceiroDTO = selectedFinanceiroDTO;
	}

}
