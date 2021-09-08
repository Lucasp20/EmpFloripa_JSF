package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

import br.com.senac.entidade.PessoaFisica;

public class PessoaFisicaDaoImpl extends BaseDaoImpl<PessoaFisica, Long> implements PessoaFisicaDao, Serializable {

	@Override
	public PessoaFisica pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (PessoaFisica) sessao.get(PessoaFisica.class, id);
	}

	@Override
	public List<PessoaFisica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from PessoaFisica pf left join fetch pf.cartoes where pf.nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public PessoaFisica pesquisarPorCpf(String cpf, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from PessoaFisica pf left join fetch pf.cartoes where cpf = :cpf");
		consulta.setParameter("cpf", cpf  );
		return (PessoaFisica) consulta.uniqueResult();
	}

}
