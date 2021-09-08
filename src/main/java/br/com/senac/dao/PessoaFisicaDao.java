package br.com.senac.dao;

import java.util.List;
import org.hibernate.*;
import br.com.senac.entidade.PessoaFisica;

public interface PessoaFisicaDao extends BaseDao<PessoaFisica, Long> {

	List<PessoaFisica> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
	PessoaFisica pesquisarPorCpf(String cpf, Session sessao) throws HibernateException;
}
