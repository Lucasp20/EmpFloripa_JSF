package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

import br.com.senac.entidade.PessoaJuridica;


public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long> implements PessoaJuridicaDao, Serializable {

	@Override
	public PessoaJuridica pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (PessoaJuridica) sessao.get(PessoaJuridica.class, id);
	}

	@Override
	public List<PessoaJuridica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from PessoaJuridica pj join fetch pj.telefone t where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public PessoaJuridica pesquisarPorCnpj(String cnpj, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from PessoaJuridica pj where cnpj = :cnpj");
		consulta.setParameter("cnpj", cnpj);
		return (PessoaJuridica) consulta.uniqueResult();
	}

}
