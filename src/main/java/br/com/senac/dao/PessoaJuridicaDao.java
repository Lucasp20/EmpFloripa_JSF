package br.com.senac.dao;

import java.util.List;
import org.hibernate.*;

import br.com.senac.entidade.PessoaJuridica;

public interface PessoaJuridicaDao extends BaseDao<PessoaJuridica, Long> {

	List<PessoaJuridica> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
	PessoaJuridica pesquisarPorCnpj(String cnpj, Session sessao) throws HibernateException;
}
