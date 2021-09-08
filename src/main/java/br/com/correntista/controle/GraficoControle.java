package br.com.correntista.controle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

@ManagedBean(name="graficoC")
public class GraficoControle {
	
	private BarChartModel barModel;

	@PostConstruct
    public void inicializar() {
		createBarModel();
	}
	
		public void createBarModel() {
	        barModel = new BarChartModel();
	        ChartData data = new ChartData();

	        BarChartDataSet barDataSet = new BarChartDataSet();
	        barDataSet.setLabel("Ranking de alunos");

	        List<Number> values = new ArrayList<>();
	        values.add(65);
	        values.add(59);
	        values.add(80);
	        values.add(81);
	        values.add(56);
	        values.add(55);
	        values.add(40);
	        barDataSet.setData(values);

	        List<String> bgColor = new ArrayList<>();
	        bgColor.add("rgba(255, 20, 100, 0.2)");
	        bgColor.add("rgba(255, 10, 64, 0.2)");
	        bgColor.add("rgba(255, 205, 86, 0.2)");
	        bgColor.add("rgba(200, 100, 192, 0.2)");
	        bgColor.add("rgba(124,252,0,0.4)");
	        bgColor.add("rgba(153, 102, 255, 0.2)");
	        bgColor.add("rgba(0,0,139, 0.2)");
	        barDataSet.setBackgroundColor(bgColor);

	        List<String> borderColor = new ArrayList<>();
	        borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(255, 159, 64)");
	        borderColor.add("rgb(255, 205, 86)");
	        borderColor.add("rgb(75, 192, 192)");
	        borderColor.add("rgb(54, 162, 235)");
	        borderColor.add("rgb(153, 102, 255)");
	        borderColor.add("rgb(153, 102, 255)");
	        barDataSet.setBorderColor(borderColor);
	        barDataSet.setBorderWidth(1);

	        data.addChartDataSet(barDataSet);

	        List<String> labels = new ArrayList<>();
	        labels.add("Lucas");
	        labels.add("Leonardo");
	        labels.add("Bruna");
	        labels.add("Maria");
	        labels.add("Rosa");
	        labels.add("Creuza");
	        labels.add("Jurema");
	        data.setLabels(labels);
	        barModel.setData(data);

	        //Options
	        BarChartOptions options = new BarChartOptions();
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        linearAxes.setOffset(true);
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        ticks.setBeginAtZero(true);
	        linearAxes.setTicks(ticks);
	        cScales.addYAxesData(linearAxes);
	        options.setScales(cScales);

	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Bar Chart");
	        options.setTitle(title);

	        Legend legend = new Legend();
	        legend.setDisplay(true);
	        legend.setPosition("top");
	        LegendLabel legendLabels = new LegendLabel();
	        legendLabels.setFontStyle("bold");
	        legendLabels.setFontColor("#2980B9");
	        legendLabels.setFontSize(24);
	        legend.setLabels(legendLabels);
	        options.setLegend(legend);

	        // disable animation
	        Animation animation = new Animation();
	        animation.setDuration(0);
	        options.setAnimation(animation);

	        barModel.setOptions(options);
	    
	}
	
	/* getters e setters */
	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}	
	
}