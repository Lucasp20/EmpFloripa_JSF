package br.com.senac.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidade.Cartao;

public interface CartaoDao extends BaseDao<Cartao, Long>{

	Cartao pesquisarPorNumero(String numero, Session sessao) throws HibernateException;
		
}
