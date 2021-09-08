package br.com.senac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidade.Perfil;

public interface PerfilDao extends BaseDao<Perfil, Long>{

	List<Perfil> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
	List<Perfil> pesquisarPorTodo(Session sessao) throws HibernateException;
}
