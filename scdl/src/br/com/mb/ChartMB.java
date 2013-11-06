/**
 * 
 */
package br.com.mb;

/**
 * @author Marcle�nio
 *
 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
//import org.primefaces.model.chart.BarChartSeries;


import br.com.dao.ChartDAO;
import br.com.dto.CidadeDTO;
import br.com.dto.EscolaDTO;
import br.com.dto.MetaDTO;
import br.com.dto.UsuarioDTO;


@ManagedBean(name="chartBean")
@RequestScoped
public class ChartMB implements Serializable {  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartesianChartModel linearModel;  

	private CartesianChartModel categoryModel;  

	private PieChartModel livePieModel;

	private CartesianChartModel combinedModel; 
	
	private CartesianChartModel desempenhoDivulgador;
	
	private Double previsao = 31D;
	
	private ChartDAO chartDAO = new ChartDAO();
	
	private UsuarioDTO divulgadorDTO = new UsuarioDTO();

	public ChartMB(){  
		//createLinearModel();
		createCategoryModel();
		createLivePieModel();
		createCombinedModel();
		
		desempenhoDivulgador(new UsuarioDTO());
	}

	public void atualizaGrafico() {
		createLinearModel(); 
	}
	
	public void atualizaDesempenhoDivulgador(){
		desempenhoDivulgador(divulgadorDTO);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void desempenhoDivulgador(UsuarioDTO divulgadorDTO){
		desempenhoDivulgador = new CartesianChartModel();
		
		ChartSeries series1 = new ChartSeries();
		series1.setLabel("Meta");
		ChartSeries series2 = new ChartSeries();
		series2.setLabel("Atingido");

		List<MetaDTO> a = null;
		try {
			
			a = chartDAO.metaByIdDivulgador(divulgadorDTO);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		//SimpleDateFormat sdf = new SimpleDateFormat("MMMM/yyyy");

		Iterator it = a.iterator();
		while(it.hasNext())
		{
			Object[] c = (Object[]) it.next();
			System.out.println(c[0]);//id_cidade
			System.out.println(c[1]);//nome_cidade
			System.out.println(c[2]);//Atingido

			//mes-avg(valor)
			series1.set(c[1],(Double)c[2]);
			MetaDTO d = chartDAO.metaByIdCidade(new CidadeDTO((Integer)c[0],(String)c[1]));
			series1.set(d.getCidadeDTO().getNome(), d.getValor());
		}

		if(series1.getData().size() == 0){
			series1.set(0, 0);
		}
		if(series2.getData().size() == 0){
			series2.set(0, 0);
		}
		
		desempenhoDivulgador.addSeries(series1);
		desempenhoDivulgador.addSeries(series2);
	}

	private void createLinearModel() {  

		linearModel = new CartesianChartModel();  
		
		String tabela = "new_view";
		String colunaX = "dia";
		String colunaY = "venda";
		//Double previsao = null;

		List<EscolaDTO> a = null;
		try {
			a = chartDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LineChartSeries series1 = new LineChartSeries();  
		series1.setLabel("Real");  
		series1.setMarkerStyle("diamond");
		for (EscolaDTO newView : a) {
			System.out.println(newView);
			//series1.set(newView.getDia().intValue(), newView.getVenda());
		}

		LineChartSeries series2 = new LineChartSeries();

		series2.setLabel("Previs�o");  
		
//		for (NewView newView : a) {
//			series2.set(newView.getCusto().intValue(), newView.getQuantidade());
//		}
		series2.set(previsao.intValue(), chartDAO.minerar(tabela, colunaX, colunaY, previsao));
		
		linearModel.addSeries(series1);  
		linearModel.addSeries(series2);  
	}  

	private void createCategoryModel() {  
		categoryModel = new CartesianChartModel();  

		ChartSeries boys = new ChartSeries();  
		boys.setLabel("Produto A");  

		boys.set("2004", 120);  
		boys.set("2005", 100);  
		boys.set("2006", 44);  
		boys.set("2007", 150);  
		boys.set("2008", 25);  

		ChartSeries girls = new ChartSeries();  
		girls.setLabel("Produto B");  

		girls.set("2004", 52);  
		girls.set("2005", 60);  
		girls.set("2006", 110);  
		girls.set("2007", 135);  
		girls.set("2008", 120);  

		categoryModel.addSeries(boys);  
		categoryModel.addSeries(girls);  
	}

	public PieChartModel getLivePieModel() {  
		int random1 = (int)(Math.random() * 1000);  
		int random2 = (int)(Math.random() * 1000);  

		livePieModel.getData().put("Candidate 1", random1);  
		livePieModel.getData().put("Candidate 2", random2);  

		return livePieModel;  
	}  

	private void createLivePieModel() {  
		livePieModel = new PieChartModel();  

		livePieModel.set("Candidate 1", 540);  
		livePieModel.set("Candidate 2", 325);  
	}  

	private void createCombinedModel() {  
		combinedModel = new CartesianChartModel();  

		ChartSeries boys = new ChartSeries();   
		boys.setLabel("Boys");  

		boys.set("2004", 120);  
		boys.set("2005", 100);  
		boys.set("2006", 44);  
		boys.set("2007", 150);  
		boys.set("2008", 25);  

		LineChartSeries girls = new LineChartSeries();  
		girls.setLabel("Girls");  

		girls.set("2004", 52);  
		girls.set("2005", 60);  
		girls.set("2006", 110);  
		girls.set("2007", 135);  
		girls.set("2008", 120);  

		combinedModel.addSeries(boys);  
		combinedModel.addSeries(girls);  
	}  

	/**
	 * @param linearModel the linearModel to set
	 */
	public void setLinearModel(CartesianChartModel linearModel) {
		this.linearModel = linearModel;
	}

	/**
	 * @param categoryModel the categoryModel to set
	 */
	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	/**
	 * @param livePieModel the livePieModel to set
	 */
	public void setLivePieModel(PieChartModel livePieModel) {
		this.livePieModel = livePieModel;
	}

	/**
	 * @return the combinedModel
	 */
	public CartesianChartModel getCombinedModel() {
		return combinedModel;
	}

	/**
	 * @param combinedModel the combinedModel to set
	 */
	public void setCombinedModel(CartesianChartModel combinedModel) {
		this.combinedModel = combinedModel;
	}

	/**
	 * @return the previsao
	 */
	public Double getPrevisao() {
		return previsao;
	}

	/**
	 * @param previsao the previsao to set
	 */
	public void setPrevisao(Double previsao) {
		this.previsao = previsao;
	}

	public CartesianChartModel getDesempenhoDivulgador() {
		return desempenhoDivulgador;
	}

	public void setDesempenhoDivulgador(CartesianChartModel desempenhoDivulgador) {
		this.desempenhoDivulgador = desempenhoDivulgador;
	}

	public UsuarioDTO getDivulgadorDTO() {
		return divulgadorDTO;
	}

	public void setDivulgadorDTO(UsuarioDTO divulgadorDTO) {
		this.divulgadorDTO = divulgadorDTO;
	}
	
	public CartesianChartModel getLinearModel() {
		return linearModel;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}
}  
