package br.com.correntista.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.PerfilDao;
import br.com.senac.dao.PerfilDaoImpl;
import br.com.senac.entidade.Perfil;


@ManagedBean(name = "perfilC")
@ViewScoped
public class PerfilControle {

	private Perfil perfil;
	private PerfilDao perfilDao;
	private Session sessao;
	private List<Perfil> perfis;

	public PerfilControle() {
		perfilDao = new PerfilDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			perfis = perfilDao.pesquisarPorNome(perfil.getNome(), sessao);
			perfil.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar perfil por nome" + e.getMessage());
		} finally {
			sessao.close();
		}
	}

	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			perfilDao.salvarOuAlterar(perfil, sessao);
			perfil = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informação", "Erro ao salvar profissão"));
		} finally {
			sessao.close();
		}
	}

	public Perfil getPerfil() {
		if (perfil == null) {
			perfil = new Perfil();
		}
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getProfissoes() {
		return perfis;
	}
}
