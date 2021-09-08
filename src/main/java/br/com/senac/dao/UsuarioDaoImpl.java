package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

import br.com.senac.entidade.PessoaFisica;
import br.com.senac.entidade.Usuario;

public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Long> implements UsuarioDao, Serializable {

	@Override
	public Usuario pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Usuario) sessao.get(Usuario.class, id);
	}

	@Override
	public List<Usuario> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Usuario where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}
}
