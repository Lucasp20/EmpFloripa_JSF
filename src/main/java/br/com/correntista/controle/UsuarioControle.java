package br.com.correntista.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.hibernate.*;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import br.com.senac.dao.*;
import br.com.senac.entidade.Perfil;
import br.com.senac.entidade.Usuario;
import util.UtilGerador;

@ManagedBean(name = "usuarioC")
@ViewScoped
public class UsuarioControle {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private Session sessao;
	private Perfil perfil;
	private List<Usuario> usuarios;
	private List<SelectItem> comboPerfis;
	private DataModel<Usuario> modelUsuarios;
	private int aba;

	public UsuarioControle() {
		usuarioDao = new UsuarioDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			usuarios = usuarioDao.pesquisarPorNome(usuario.getNome(), sessao);
			modelUsuarios = new ListDataModel<>(usuarios);
			usuario.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar usuário por nome" + e.getMessage());
		} finally {
			sessao.close();
		}
	}

	public void carregarComboPerfil() {
		sessao = HibernateUtil.abrirSessao();
		PerfilDao perfilDao = new PerfilDaoImpl();
		try {
			List<Perfil> perfis = perfilDao.pesquisarPorTodo(sessao);
			comboPerfis = new ArrayList<>();
			for (Perfil perfi : perfis) {
				comboPerfis.add(new SelectItem(perfi.getId(), perfi.getNome()));
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar combobox perfil" + e.getMessage());
		} finally {
			sessao.close();
		}
	}

	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getTitle().equals("Novo"));
			carregarComboPerfil();
	}

	public void onTabClose(TabCloseEvent event) {
	}

	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			usuario.setSenha(UtilGerador.gerarNumero(5));
			usuario.setPerfil(perfil);
			usuarioDao.salvarOuAlterar(usuario, sessao);
			usuario = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Salvo com Sucesso", null));
			modelUsuarios = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informação", "Erro ao salvar usuário"));
		} finally {
			sessao.close();
		}
	}

	public void excluir() {
		usuario = modelUsuarios.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			usuarioDao.excluir(usuario, sessao);
			usuario = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário excluido com Sucesso", null));
			modelUsuarios = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Usuário", ""));
		} finally {
			sessao.close();
		}
	}

	public void prepararAlterar() {
		usuario = modelUsuarios.getRowData();
		aba = 1;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario profissao) {
		this.usuario = profissao;
	}

	public DataModel<Usuario> getModelUsuarios() {
		return modelUsuarios;
	}

	public int getAba() {
		return aba;
	}

	public List<SelectItem> getComboPerfis() {
		return comboPerfis;
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

}
