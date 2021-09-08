package br.com.correntista.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import com.sun.xml.rpc.client.dii.webservice.WebService;

import br.com.correntista.webservice.WebServiceEndereco;
import br.com.senac.dao.*;
import br.com.senac.entidade.*;
import util.UtilGerador;

@ManagedBean(name = "pessoaJuridicaC")
@ViewScoped
public class PessoaJuridicaControle {

	private PessoaJuridica pessoaJuridica;
	private Endereco endereco;
	private Telefone telefone;
	private PessoaJuridicaDao pessoaJuridicaDao;
	private Session sessao;
	private List<PessoaJuridica> pessoasJuridicas;
	private DataModel<PessoaJuridica> modelPessoasJuridicas;
	private List<Telefone> telefones;
	private int aba;

	public PessoaJuridicaControle() {
		pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			pessoasJuridicas = pessoaJuridicaDao.pesquisarPorNome(pessoaJuridica.getNome(), sessao);
			modelPessoasJuridicas = new ListDataModel<>(pessoasJuridicas);
			pessoaJuridica.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pessoa juridica por nome" + e.getMessage());
		} finally {
			sessao.close();
		}

	}
	
	public void buscarCep() {
		WebServiceEndereco webservice = new WebServiceEndereco();
		endereco = webservice.pesquisarCep(endereco.getCep());
		if(endereco.getLogradouro() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não existe nenhum cep com esse valor", null));
		}
	}

	/* mudar aba para novo e carrega o comboBox */
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getTitle().equals("Novo")) {
	}
}

	public void onTabClose(TabCloseEvent event) {
	}
	
	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			endereco.setCliente(pessoaJuridica);
			pessoaJuridica.setEndereco(endereco);
			
			pessoaJuridica.setTelefone(telefones);
			
			pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
			pessoaJuridica = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa Juridica Salvo com Sucesso", null));
			modelPessoasJuridicas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Pessoa Juridica", null));
		} finally {
			sessao.close();
		}
	
	}

	public void adicionarTelefoneLista() {
		if(telefones == null) {
			telefones = new ArrayList<>();
		}
		telefones.add(telefone);
		telefone.setPessoaJuridica(pessoaJuridica);
		telefone = new Telefone();
	}
	
	public void excluir() {
		pessoaJuridica = modelPessoasJuridicas.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			pessoaJuridicaDao.excluir(pessoaJuridica, sessao);
			pessoaJuridica = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa Juridica excluido com Sucesso", null));
			modelPessoasJuridicas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Juridica Pessoa Fisica", ""));
		} finally {
			sessao.close();
		}
	}

	public void prepararAlterar() {
		pessoaJuridica = modelPessoasJuridicas.getRowData();
		endereco = pessoaJuridica.getEndereco();
		aba = 1;
	}

	public PessoaJuridica getPessoaJuridica() {
		if (pessoaJuridica == null) {
			pessoaJuridica = new PessoaJuridica();
		}
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public DataModel<PessoaJuridica> getModelPessoasJuridicas() {
		return modelPessoasJuridicas;
	}

	public int getAba() {
		return aba;
	}

	public Endereco getEndereco() {
		if(endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		if(telefone == null) {
			telefone = new Telefone();
		}
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}	
	
}
