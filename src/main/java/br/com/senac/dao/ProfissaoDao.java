package br.com.senac.dao;

import java.util.List;
import org.hibernate.*;
import br.com.senac.entidade.Profissao;

public interface ProfissaoDao extends BaseDao<Profissao, Long>{

	List<Profissao> pesquisarPorNome(String nome, Session sessao) throws HibernateException;

	List<Profissao> pesquisarTodo(Session sessao) throws HibernateException;
}
