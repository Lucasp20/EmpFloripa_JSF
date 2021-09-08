package br.com.correntista.controle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.PessoaFisicaDao;
import br.com.senac.dao.PessoaFisicaDaoImpl;
import br.com.senac.entidade.PessoaFisica;

@ManagedBean(name = "clienteC")
public class ClienteControle {

	private PessoaFisica pessoaFisica;
	private PessoaFisicaDao pessoaFisicaDao;

	public String salvar() {
		
		pessoaFisicaDao = new PessoaFisicaDaoImpl();
		Session sessao = HibernateUtil.abrirSessao();
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		
		try {
			pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
			message = new FacesMessage("Salvo com sucesso");
		} catch (HibernateException e) {
			message = new FacesMessage("Erro ao Salvar");
		}finally {
			sessao.close();
			contexto.addMessage(null, message);
		}
		
		return "mostra";
	}

	public PessoaFisica getPessoaFisica() {
		if(pessoaFisica == null) {
			pessoaFisica = new PessoaFisica();
		
		}
		
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	
	
}