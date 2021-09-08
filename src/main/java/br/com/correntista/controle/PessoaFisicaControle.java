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

@ManagedBean(name = "pessoaFisicaC")
@ViewScoped
public class PessoaFisicaControle {

	private PessoaFisica pessoaFisica;
	private Endereco endereco;
	private Profissao profissao;
	private PessoaFisicaDao pessoaFisicaDao;
	private Session sessao;
	private List<PessoaFisica> pessoasFisicas;
	private List<SelectItem> comboProfissoes;
	private DataModel<PessoaFisica> modelPessoasFisicas;
	private int aba;

	public PessoaFisicaControle() {
		pessoaFisicaDao = new PessoaFisicaDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			pessoasFisicas = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome(), sessao);
			modelPessoasFisicas = new ListDataModel<>(pessoasFisicas);
			pessoaFisica.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pessoa fisica por nome" + e.getMessage());
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

	public void carregarComboProfissao() {
		sessao = HibernateUtil.abrirSessao();
		ProfissaoDao profissaoDao = new ProfissaoDaoImpl();
		try {
			List<Profissao> profissoes = profissaoDao.pesquisarTodo(sessao);
			comboProfissoes = new ArrayList<>();
			for (Profissao prof : profissoes) {
				comboProfissoes.add(new SelectItem(prof.getId(), prof.getNome()));
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar combobox profissões" + e.getMessage());
		} finally {
			sessao.close();
		}
	}

	/* mudar aba para novo e carrega o comboBox */
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getTitle().equals("Novo"));
			carregarComboProfissao();
	}

	public void onTabClose(TabCloseEvent event) {
	}
	
	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			pessoaFisica.setProfissao(profissao);
			endereco.setCliente(pessoaFisica);
			pessoaFisica.setEndereco(endereco);
			pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
			pessoaFisica = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa Fisica Salvo com Sucesso", null));
			modelPessoasFisicas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informação", "Erro ao salvar Pessoa Fisica"));
		} finally {
			sessao.close();
		}
	
	}

	public void excluir() {
		pessoaFisica = modelPessoasFisicas.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			pessoaFisicaDao.excluir(pessoaFisica, sessao);
			pessoaFisica = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa Fisica excluido com Sucesso", null));
			modelPessoasFisicas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Pessoa Fisica", ""));
		} finally {
			sessao.close();
		}
	}

	public void prepararAlterar() {
		pessoaFisica = modelPessoasFisicas.getRowData();
		endereco = pessoaFisica.getEndereco();
		profissao = pessoaFisica.getProfissao();
		aba = 1;
	}

	public PessoaFisica getPessoaFisica() {
		if (pessoaFisica == null) {
			pessoaFisica = new PessoaFisica();
		}
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public DataModel<PessoaFisica> getModelPessoasFisicas() {
		return modelPessoasFisicas;
	}
	
	public List<SelectItem> getComboProfissoes() {
		return comboProfissoes;
	}

	public int getAba() {
		return aba;
	}

	public Profissao getProfissao() {
		if(profissao == null) {
			profissao = new Profissao();
		}
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
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
	
}
