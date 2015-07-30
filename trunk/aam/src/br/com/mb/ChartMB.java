/**
 * 
 */
package br.com.mb;

/**
 * @author Marcleï¿½nio
 *
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;
//import org.primefaces.model.chart.BarChartSeries;












import br.com.dao.ChartDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.ResultadoAvaliacaoDTO;
import br.com.dto.UsuarioDTO;


@ManagedBean(name="chartBean")
@RequestScoped
public class ChartMB extends GenericoMB implements Serializable {  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private LineChartModel cNotaMediaGraduacao;
	private LineChartModel cMediaGeralAcademia;
	private LineChartModel cAvaliacaoMembros;
	private MeterGaugeChartModel meterGaugeModel2;
	private HorizontalBarChartModel horizontalBarModel;

	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private ChartDAO chartDAO = new ChartDAO();

	private Date dataInicial;
	private Date dataFinal;

	public ChartMB(){  
		cNotaMediaGraduacao();
		cMediaGeralAcademia();
		cAvaliacaoMembros();
		createMeterGaugeModels();
		createHorizontalBarModel();
	}

	public MeterGaugeChartModel getMeterGaugeModel2() {
		return meterGaugeModel2;
	}

	private MeterGaugeChartModel initMeterGaugeModel() {
		List<Number> intervals = new ArrayList<Number>(){{
			add(5);
			add(10);
			add(15);
			add(22);
		}};

		return new MeterGaugeChartModel(16, intervals);
	}

	private void createMeterGaugeModels() {
		//        meterGaugeModel1 = initMeterGaugeModel();
		//        meterGaugeModel1.setTitle("MeterGauge Chart");
		//        meterGaugeModel1.setGaugeLabel("km/h");

		meterGaugeModel2 = initMeterGaugeModel();
		meterGaugeModel2.setTitle("Total Geral Frequência Agrupado");
		meterGaugeModel2.setSeriesColors("cc6666,66cc66,E7E658,3299BB");
		meterGaugeModel2.setGaugeLabel("Otimo");
		meterGaugeModel2.setIntervalInnerRadius(1);
		//        meterGaugeModel2.setGaugeLabelPosition("bottom");
		meterGaugeModel2.setShowTickLabels(true);
		meterGaugeModel2.setLegendCols(2);
		meterGaugeModel2.setLegendRows(2);
		meterGaugeModel2.setLabelHeightAdjust(110);
//		meterGaugeModel2.setIntervalOuterRadius(100);
//		meterGaugeModel2.setMouseoverHighlight(true);
//		Number [] a = {5,10,15,22};
//		meterGaugeModel2.setIntervals(Arrays.asList(a));
//		meterGaugeModel2.setLegendPosition("ne");
//		meterGaugeModel2.setLegendPlacement(LegendPlacement.OUTSIDE);
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	private void createHorizontalBarModel() {
		horizontalBarModel = new HorizontalBarChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Frenquencia");
		boys.set("Graduação 1", 22);
		boys.set("Graduação 2", 5);
		boys.set("Graduação 3", 14);
		//        boys.set("2007", 55);
		//        boys.set("2008", 25);

		//        ChartSeries girls = new ChartSeries();
		//        girls.setLabel("Girls");
		//        girls.set("2004", 52);
		//        girls.set("2005", 60);
		//        girls.set("2006", 82);
		//        girls.set("2007", 35);
		//        girls.set("2008", 120);

		horizontalBarModel.addSeries(boys);
		//        horizontalBarModel.addSeries(girls);

		horizontalBarModel.setTitle("Frequência do Aluno");
		horizontalBarModel.setLegendPosition("e");
		horizontalBarModel.setStacked(true);

		Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Insuficiente Bom Regular Otimo");
		xAxis.setMin(0);
		xAxis.setMax(22);

		//        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
		//        yAxis.setLabel("Gender");        
	}

	public void handleSelect(SelectEvent event) {  

		try {
			usuarioDTO = (UsuarioDTO)event.getObject();

			//	addMessage("Selected:" + usuarioDTO.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizaAvaliacaoMembros() throws Exception {
		String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form1:idUsuario");
		if(idUsuario != null && !idUsuario.equals("")){
			usuarioDTO = usuarioDAO.getById(Integer.valueOf(idUsuario));
		}
		cAvaliacaoMembros(); 
	}

	public void atualizaNotaMediaGraduacao() throws Exception {
		cNotaMediaGraduacao(); 
	}
	public void atualizaMediaGeralAcademia() throws Exception {
		cMediaGeralAcademia(); 
	}

	@SuppressWarnings("rawtypes")
	private void cMediaGeralAcademia(){
		cMediaGeralAcademia = new LineChartModel();
		cMediaGeralAcademia.setAnimate(true);
		cMediaGeralAcademia.setLegendPosition("e");
		Axis yAxis = cMediaGeralAcademia.getAxis(AxisType.Y);
		yAxis.setMin(0);
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Notas");
		series1.setMarkerStyle("diamond");


		List<ResultadoAvaliacaoDTO> a = null;
		try {
			a = chartDAO.cMediaGeralAcademia(dataInicial,dataFinal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/yyyy");

		Iterator it = a.iterator();
		while(it.hasNext())  
		{  
			Object[] c = (Object[]) it.next();  
			System.out.println(c[0]);
			System.out.println(c[1]);

			//mes-avg(valor)
			series1.set(sdf.format(c[0]),(Double)c[1]); 

		}

		if(series1.getData().size() == 0){
			series1.set(0, 0);
		}

		cMediaGeralAcademia.addSeries(series1); 
	}

	private void cNotaMediaGraduacao(){
		cNotaMediaGraduacao = new LineChartModel();
		cNotaMediaGraduacao.setAnimate(true);
		cNotaMediaGraduacao.setLegendPosition("e");
		Axis yAxis = cNotaMediaGraduacao.getAxis(AxisType.Y);
		yAxis.setMin(0);
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Notas");
		series1.setMarkerStyle("diamond");


		List<ResultadoAvaliacaoDTO> a = null;
		try {
			a = chartDAO.cNotaMediaGraduacao(usuarioDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/yyyy");

		String dataAux = null;
		double resultp=0d,cont=1d;
		for (ResultadoAvaliacaoDTO ra : a) {
			if(!sdf.format(ra.getData()).equals(dataAux)){
				resultp = (ra.getLuta()+ra.getTecnica()+ra.getConhecimentos())/3;
				series1.set(sdf.format(ra.getData()), resultp/cont);
				cont++;
			}else{
				resultp += (ra.getLuta()+ra.getTecnica()+ra.getConhecimentos())/3;
				series1.set(sdf.format(ra.getData()), resultp/cont);
			}
			dataAux=sdf.format(ra.getData());
		}
		if(series1.getData().size() == 0){
			series1.set(0, 0);
		}

		cNotaMediaGraduacao.addSeries(series1); 
	}

	private void cAvaliacaoMembros() {

		String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:idUsuario");
		System.out.println(idUsuario);

		cAvaliacaoMembros = new LineChartModel();
		cAvaliacaoMembros.setAnimate(true);
		cAvaliacaoMembros.setLegendPosition("e");
		Axis yAxis = cAvaliacaoMembros.getAxis(AxisType.Y);
		yAxis.setMin(0);
		//        yAxis.setMax(10);

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Notas");
		series1.setMarkerStyle("diamond");

		List<ResultadoAvaliacaoDTO> a = null;
		try {
			if(idUsuario != null && !idUsuario.equals("")){
				usuarioDTO = usuarioDAO.getById(Integer.valueOf(idUsuario));
			}
			a = chartDAO.listByIdUsuario(usuarioDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/yyyy");

		for (ResultadoAvaliacaoDTO ra : a) {
			series1.set(sdf.format(ra.getData()), (ra.getLuta()+ra.getTecnica()+ra.getConhecimentos())/3);
		}

		if(series1.getData().size() == 0){
			series1.set(0, 0);
		}
		cAvaliacaoMembros.addSeries(series1);
	}  

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public LineChartModel getcNotaMediaGraduacao() {
		return cNotaMediaGraduacao;
	}

	public void setcNotaMediaGraduacao(LineChartModel cNotaMediaGraduacao) {
		this.cNotaMediaGraduacao = cNotaMediaGraduacao;
	}

	public CartesianChartModel getcMediaGeralAcademia() {
		return cMediaGeralAcademia;
	}

	public void setcMediaGeralAcademia(LineChartModel cMediaGeralAcademia) {
		this.cMediaGeralAcademia = cMediaGeralAcademia;
	}

	public LineChartModel getcAvaliacaoMembros() {
		return cAvaliacaoMembros;
	}

	public void setcAvaliacaoMembros(LineChartModel cAvaliacaoMembros) {
		this.cAvaliacaoMembros = cAvaliacaoMembros;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}  
}  

