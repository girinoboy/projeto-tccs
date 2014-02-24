/**
 * 
 */
package br.com.mb;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
import br.com.dto.RelatorioGestaoMensalDTO;
import br.com.dto.StatusGestaoDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@SessionScoped
public class FinanceiroMB extends GenericoMB implements ModeloMB{

	private static final long serialVersionUID = 5253517323316896638L;
	private FinanceiroDTO financeiroDTO = new FinanceiroDTO();
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();
	private List<FinanceiroDTO> listFinanceiroDTO;
	private FinanceiroDTO selectedFinanceiroDTO = new FinanceiroDTO();

	private List<RelatorioGestaoMensalDTO> listRelatorioGestaoMensalDTO = new ArrayList<RelatorioGestaoMensalDTO>();

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private RelatorioGestaoMensalDTO relatorioGestaoMensalDTO = new RelatorioGestaoMensalDTO();
	/**
	 * 
	 */
	public FinanceiroMB() {
		try {
			//listFinanceiroDTO = financeiroDAO.list();

			atualizaMensalidade();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void atualizaMensalidade()throws Exception {
		listFinanceiroDTO = financeiroDAO.consultaPorMesAno(financeiroDTO.getDataPagamento());
//		Calendar c = Calendar.getInstance(); 
//		if(financeiroDTO==null && financeiroDTO.getDataPagamento() ==null){
//			listFinanceiroDTO = financeiroDAO.consultaPorMesAno(new Date());
//			c.setTime(new Date());
//			for (FinanceiroDTO f : listFinanceiroDTO) {
//				if(!f.getMes().equals(c.get(Calendar.MONTH))){
//					f.setId(null);
//					f.setSituacao(false);
////					f.setValorComDesconto(0d);
//				}
//			}
//		}
//		else{
//			listFinanceiroDTO = financeiroDAO.consultaPorMesAno(financeiroDTO.getDataPagamento());
//			c.setTime(financeiroDTO.getDataPagamento());
//			for (FinanceiroDTO f : listFinanceiroDTO) {
//				if(!f.getMes().equals(c.get(Calendar.MONTH))){
//					f.setId(null);
//					f.setSituacao(false);
////					f.setValorComDesconto(0d);
//				}
//			}
//		}

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
		if(getAdm()){
			atualizaMensalidade();
			//listFinanceiroDTO = financeiroDAO.consultaPorMesAno(new Date());
			addMessage("Operação realizada com sucesso!.");
			//financeiroDAO.save(financeiroDTO);
		}

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public void onCellEdit(CellEditEvent event) throws Exception {
		if(getAdm()){
			//Object oldValue = event.getOldValue();
			//Object newValue = event.getNewValue();
			System.out.println(financeiroDTO.getDataPagamento());
			UsuarioDTO usuarioDTO = listFinanceiroDTO.get(event.getRowIndex()).getUsuarioDTO();
			//usuarioDTO.setFinanceiroDTO(financeiroDTO);
			usuarioDTO.setFinanceiroDTO(listFinanceiroDTO.get(event.getRowIndex()));
			usuarioDTO.getFinanceiroDTO().setValorComDesconto(calculaDesconto(usuarioDTO));
			System.out.println(usuarioDTO.getFinanceiroDTO().getDataPagamento());
			System.out.println(usuarioDTO.getFinanceiroDTO().getDia());
			usuarioDTO.getFinanceiroDTO().setDataPagamento(financeiroDTO.getDataPagamento());
			financeiroDAO.save(usuarioDTO.getFinanceiroDTO());
//			usuarioDAO.save(usuarioDTO);
			//listFinanceiroDTO = financeiroDAO.list();
			atualizaMensalidade();
			//if(newValue != null && !newValue.equals(oldValue)) {
			//	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);  
			//	FacesContext.getCurrentInstance().addMessage(null, msg);
			//}
		}
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

//		Calendar c = Calendar.getInstance(); 
//		Date a = (Date)event.getObject();
		listFinanceiroDTO = financeiroDAO.consultaPorMesAno(event.getObject());
//		c.setTime(financeiroDTO.getDataPagamento());
//		for (FinanceiroDTO f : listFinanceiroDTO) {
//			if(!f.getMes().equals(c.get(Calendar.MONTH))){
//				f.setId(null);
//				f.setSituacao(false);
////				f.setValorComDesconto(0d);
//			}
//		}

		FacesContext facesContext = FacesContext.getCurrentInstance();  
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));  
	} 

	public void a(ActionEvent actionEvent) throws Exception{
		List<?> a = financeiroDAO.listRelatorioGestaoMensal(relatorioGestaoMensalDTO);
		RelatorioGestaoMensalDTO  rel =null;
		Iterator it = a.iterator();
		String mesAno = null,mesAnoAux=null;
		while(it.hasNext())  
		{
			Object[] c = (Object[]) it.next();  
			System.out.println(c[0]);
			System.out.println(c[1]);
			mesAno = c[6]+"/"+c[7];
			if(!mesAno.equals(mesAnoAux)){
				rel =  new RelatorioGestaoMensalDTO(c[6],c[7]);
				Integer membrosComPendencia = c[4] == null ? 0:((BigInteger) c[4]).intValue();
				Integer membrosAtivos = c[1] == null ? 0:((BigInteger) c[1]).intValue();
				Integer membrosSemPendencia = c[3] == null ? 0:((BigInteger) c[3]).intValue();
				Integer totalMembros = c[0] == null ? 0:((BigInteger) c[0]).intValue();
				Integer membrosInativos = c[2] == null ? 0:((BigInteger) c[2]).intValue();
				Integer totalArrecadado = c[5] == null ? 0:((Double) c[5]).intValue();
				rel.getStatusGestaoDTO().add(new StatusGestaoDTO(membrosAtivos, membrosInativos, membrosSemPendencia, membrosComPendencia, totalMembros, totalArrecadado));
				listRelatorioGestaoMensalDTO.add(rel);
				
			}
//			else{
//				Integer membrosComPendencia = c[4] == null ? 0:((BigInteger) c[4]).intValue();
//				Integer membrosAtivos = c[1] == null ? 0:((BigInteger) c[1]).intValue();
//				Integer membrosSemPendencia = c[3] == null ? 0:((BigInteger) c[3]).intValue();
//				Integer totalMembros = c[0] == null ? 0:((BigInteger) c[0]).intValue();
//				Integer membrosInativos = c[2] == null ? 0:((BigInteger) c[2]).intValue();
//				Integer totalArrecadado = c[5] == null ? 0:((Double) c[5]).intValue();
//				rel.getStatusGestaoDTO().add(new StatusGestaoDTO(membrosAtivos, membrosInativos, membrosSemPendencia, membrosComPendencia, totalMembros, totalArrecadado));
////				listRelatorioGestaoMensalDTO.add(rel);
//			}
			mesAnoAux=mesAno;
//			b =  new RelatorioGestaoMensalDTO(c[6],c[7]);
//			Integer membrosComPendencia = ((BigInteger) c[4]).intValue();
//			Integer membrosAtivos = c[1] == null ? 0:((BigInteger) c[1]).intValue();
//			Integer membrosSemPendencia = ((BigInteger) c[3]).intValue();
//			Integer totalMembros = ((BigInteger) c[0]).intValue();
//			Integer membrosInativos = ((BigInteger) c[2]).intValue();
//			Integer totalArrecadado = ((Double) c[5]).intValue();
//			b.getStatusGestaoDTO().add(new StatusGestaoDTO(membrosAtivos, membrosInativos, membrosSemPendencia, membrosComPendencia, totalMembros, totalArrecadado));
//			listRelatorioGestaoMensalDTO.add(b);

		}


		addMessage("Teste: "+a.size()); 
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

	public List<RelatorioGestaoMensalDTO> getListRelatorioGestaoMensalDTO() {
		return listRelatorioGestaoMensalDTO;
	}

	public void setListRelatorioGestaoMensalDTO(
			List<RelatorioGestaoMensalDTO> listRelatorioGestaoMensalDTO) {
		this.listRelatorioGestaoMensalDTO = listRelatorioGestaoMensalDTO;
	}

	public RelatorioGestaoMensalDTO getRelatorioGestaoMensalDTO() {
		return relatorioGestaoMensalDTO;
	}

	public void setRelatorioGestaoMensalDTO(
			RelatorioGestaoMensalDTO relatorioGestaoMensalDTO) {
		this.relatorioGestaoMensalDTO = relatorioGestaoMensalDTO;
	}

}
