package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.senac.entidade.Perfil;


public class PerfilDaoImpl extends BaseDaoImpl<Perfil, Long> implements PerfilDao, Serializable {

	@Override
	public Perfil pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Perfil) sessao.get(Perfil.class, id);
	}

	@Override
	public List<Perfil> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Perfil where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public List<Perfil> pesquisarPorTodo(Session sessao) throws HibernateException {
		return sessao.createQuery("from Perfil").list();
	}

}
