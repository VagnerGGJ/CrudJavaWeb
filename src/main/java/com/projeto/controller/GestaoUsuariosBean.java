package com.projeto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

import com.projeto.model.Usuario;
import com.projeto.repository.Usuarios;
import com.projeto.service.CadastroUsuarioService;
import com.projeto.util.FacesMessages;

@Named
@ViewScoped
public class GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Inject
	private FacesMessages messages;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	private List<Usuario> listaUsuarios;

	private String termoPesquisa;

	private Usuario usuario;
	
	private BarChartModel barModel;
	
	@PostConstruct
	public void init() {
		createBarModel();
	}
	
	public void prepararNovoUsuario() {
		usuario = new Usuario();
	}

	public void salvar() {
		cadastroUsuarioService.salvar(usuario);

		atualizarRegistros();

		messages.info("Usuário salvo com sucesso!");

		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frm:usuariosDataTable");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frm:messages");
	}

	public void excluir() {
		cadastroUsuarioService.excluir(usuario);

		usuario = null;

		atualizarRegistros();

		messages.info("Usuário excluído com sucesso!");
	}

	public void pesquisar() {
		listaUsuarios = usuarios.pesquisar(termoPesquisa);

		if (listaUsuarios.isEmpty()) {
			messages.info("Nenhum registro para esse filtro.");
		}
	}

	public void todosUsuarios() {
		listaUsuarios = usuarios.todas();
	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todosUsuarios();
		}
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isUsuarioSelecionado() {
		return usuario != null;
	}

	 public void createBarModel() {
	        barModel = new BarChartModel();
	        ChartData data = new ChartData();

	        BarChartDataSet barDataSet = new BarChartDataSet();
	        barDataSet.setLabel("Usuários");

	        List<Number> values = new ArrayList<>();
	        values.add(usuarios.listarInativos().size());
	        values.add(usuarios.listarAtivos().size());
	        barDataSet.setData(values);

	        List<String> bgColor = new ArrayList<>();
	        bgColor.add("rgba(255, 99, 132, 0.2)");
	        bgColor.add("rgba(75, 192, 192, 0.2)");
	        barDataSet.setBackgroundColor(bgColor);

	        List<String> borderColor = new ArrayList<>();
	        borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(75, 192, 192)");
	        barDataSet.setBorderColor(borderColor);
	        barDataSet.setBorderWidth(1);

	        data.addChartDataSet(barDataSet);

	        List<String> labels = new ArrayList<>();
	        labels.add("Inativos");
	        labels.add("Ativos");
	        data.setLabels(labels);
	        barModel.setData(data);

	        //Options
	        BarChartOptions options = new BarChartOptions();
	        //options.setMaintainAspectRatio(false);
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        linearAxes.setOffset(true);
	        linearAxes.setBeginAtZero(true);
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        linearAxes.setTicks(ticks);
	        cScales.addYAxesData(linearAxes);
	        options.setScales(cScales);

	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Usuários do Sistema");
	        options.setTitle(title);

	        Legend legend = new Legend();
	        legend.setDisplay(true);
	        legend.setPosition("top");
	        LegendLabel legendLabels = new LegendLabel();
	        legendLabels.setFontStyle("italic");
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

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	 
	 
	
}
