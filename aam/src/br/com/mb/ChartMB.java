/**
 * 
 */
package br.com.mb;

/**
 * @author Marcle�nio
 *
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


	private CartesianChartModel cNotaMediaGraduacao;
	private CartesianChartModel cMediaGeralAcademia;
	private CartesianChartModel cAvaliacaoMembros;
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
            add(20);
            add(50);
            add(120);
            add(220);
        }};
         
        return new MeterGaugeChartModel(140, intervals);
    }
	
	private void createMeterGaugeModels() {
//        meterGaugeModel1 = initMeterGaugeModel();
//        meterGaugeModel1.setTitle("MeterGauge Chart");
//        meterGaugeModel1.setGaugeLabel("km/h");
         
        meterGaugeModel2 = initMeterGaugeModel();
        meterGaugeModel2.setTitle("Custom Options");
        meterGaugeModel2.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
        meterGaugeModel2.setGaugeLabel("km/h");
        meterGaugeModel2.setGaugeLabelPosition("bottom");
        meterGaugeModel2.setShowTickLabels(false);
        meterGaugeModel2.setLabelHeightAdjust(110);
        meterGaugeModel2.setIntervalOuterRadius(100);
    }
	
	public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
	
	private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");        
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
		cMediaGeralAcademia = new CartesianChartModel();
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
		cNotaMediaGraduacao = new CartesianChartModel();
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

		cAvaliacaoMembros = new CartesianChartModel();


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

	public CartesianChartModel getcNotaMediaGraduacao() {
		return cNotaMediaGraduacao;
	}

	public void setcNotaMediaGraduacao(CartesianChartModel cNotaMediaGraduacao) {
		this.cNotaMediaGraduacao = cNotaMediaGraduacao;
	}

	public CartesianChartModel getcMediaGeralAcademia() {
		return cMediaGeralAcademia;
	}

	public void setcMediaGeralAcademia(CartesianChartModel cMediaGeralAcademia) {
		this.cMediaGeralAcademia = cMediaGeralAcademia;
	}

	public CartesianChartModel getcAvaliacaoMembros() {
		return cAvaliacaoMembros;
	}

	public void setcAvaliacaoMembros(CartesianChartModel cAvaliacaoMembros) {
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

