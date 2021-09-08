package br.com.senac.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.*;
import org.junit.Test;

import br.com.senac.entidade.Profissao;
import util.UtilGerador;

public class ProfissaoDaoImplTest {

	private Profissao profissao;
	private ProfissaoDao profissaoDao;
	private Session sessao;

	public ProfissaoDaoImplTest() {
		profissaoDao = new ProfissaoDaoImpl();
	}

//	@Test
	public void testSalvar() {
		System.out.println("Salvar");
		profissao = new Profissao("Profissão: " + UtilGerador.gerarCaracter(18), UtilGerador.gerarCaracter(8));
		sessao = HibernateUtil.abrirSessao();
		profissaoDao.salvarOuAlterar(profissao, sessao);
		sessao.close();
		assertNotNull(profissao.getId());
	}

//	@Test
	public void testAlterar() {
		System.out.println("Alterar");
		buscarProfissaoBD();
		profissao.setDescricao(UtilGerador.gerarCaracter(18));
		sessao = HibernateUtil.abrirSessao();
		profissaoDao.salvarOuAlterar(profissao, sessao);
		sessao.close();

		sessao = HibernateUtil.abrirSessao();
		Profissao profisaoAlt = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
		sessao.close();
		assertEquals(profissao.getDescricao(), profissao.getDescricao());

	}

//	@Test
	public void testExcluir() {
		System.out.println("Excluir");
		buscarProfissaoBD();

		sessao = HibernateUtil.abrirSessao();
		profissaoDao.excluir(profissao, sessao);

		Profissao profissaoExc = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
		sessao.close();
		assertNull(profissaoExc);
	}

//	@Test
	public void testPesquisarPorID() {
		System.out.println("Pesquisar Por ID");
		buscarProfissaoBD();
		sessao = HibernateUtil.abrirSessao();
		Profissao profissaoId = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
		sessao.close();
		assertNotNull(profissaoId);
	}

//	@Test
	public void testPesquisarPorNome() {
		System.out.println("Pesquisar Por Nome");
		buscarProfissaoBD();

		int espaco = profissao.getNome().indexOf(" ");
		String nome = profissao.getNome().substring(0, espaco);
		sessao = HibernateUtil.abrirSessao();
		List<Profissao> profissoes = profissaoDao.pesquisarPorNome(profissao.getNome().substring(0, 4), sessao);
		sessao.close();
		assertTrue(profissoes.size() > 0);

	}

	public Profissao buscarProfissaoBD() {
		sessao = HibernateUtil.abrirSessao();
		Query consulta = sessao.createQuery("from Profissao");
		List<Profissao> profissoes = consulta.list();
		sessao.close();
		if (profissoes.isEmpty()) {
			testSalvar();
		} else {
			profissao = profissoes.get(0);
		}
		return profissao;
	}
}