package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.*;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Profissao;

public class ProfissaoDaoImpl extends BaseDaoImpl<Profissao, Long> implements ProfissaoDao, Serializable{

	@Override
	public Profissao pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Profissao) sessao.get(Profissao.class, id);
	}

	@Override
	public List<Profissao> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Profissao where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public List<Profissao> pesquisarTodo(Session sessao) throws HibernateException {
		return sessao.createQuery("from Profissao order by nome").list();
	
	}

}
