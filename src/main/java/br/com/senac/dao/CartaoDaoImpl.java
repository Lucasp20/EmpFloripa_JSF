package br.com.senac.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.senac.entidade.Cartao;

public class CartaoDaoImpl extends BaseDaoImpl<Cartao, Long> implements CartaoDao, Serializable{

	@Override
	public Cartao pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Cartao) sessao.get(Cartao.class, id);
	}

	@Override
	public Cartao pesquisarPorNumero(String numero, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Cartao where numero = :numero");
		consulta.setParameter("numero", numero);
		return (Cartao) consulta.uniqueResult();
	}

}
