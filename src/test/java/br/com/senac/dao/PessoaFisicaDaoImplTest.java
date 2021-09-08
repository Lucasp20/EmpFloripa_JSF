package br.com.senac.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.junit.Test;

import com.sun.xml.rpc.client.dii.webservice.WebService;

import br.com.correntista.webservice.WebServiceEndereco;
import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaFisica;
import br.com.senac.entidade.Profissao;
import util.UtilGerador;

public class PessoaFisicaDaoImplTest {

	private PessoaFisica pessoaFisica;
	private PessoaFisicaDao pessoaFisicaDao;
	private Session sessao;
	
	public PessoaFisicaDaoImplTest() {
		pessoaFisicaDao = new PessoaFisicaDaoImpl();
	}
	
//	@Test
	public void testSalvar() {
		System.out.println("Salvar");
		
		ProfissaoDaoImplTest profissaoDao = new ProfissaoDaoImplTest();
		Profissao profissao = profissaoDao.buscarProfissaoBD();
		
		pessoaFisica = new PessoaFisica(
					null,
					UtilGerador.gerarNome(),
					UtilGerador.gerarEmail(),
					UtilGerador.gerarCpf(),
					UtilGerador.gerarNumero(7), profissao);
		
		WebServiceEndereco webService = new WebServiceEndereco();
		Endereco endereco = webService.pesquisarCep("88115160");
		endereco.setNumero(UtilGerador.gerarNumero(3));
		endereco.setComplemento(UtilGerador.gerarCaracter(7));
					
		
		pessoaFisica.setEndereco(endereco);
		endereco.setCliente(pessoaFisica);	
		pessoaFisica.setProfissao(profissao);
		sessao = HibernateUtil.abrirSessao();
		
		pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
		sessao.close();
		assertNotNull(pessoaFisica.getId());
		assertNotNull(pessoaFisica.getEndereco().getId());
	
}
	
//	@Test
	public void testAlterar() {
		System.out.println("Alterar");
		buscarPessoaFisicaBD();
		pessoaFisica.setNome(UtilGerador.gerarNome());
		sessao = HibernateUtil.abrirSessao();
		pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
		sessao.close();
		
		sessao = HibernateUtil.abrirSessao();
		PessoaFisica pessoaFisicaAlt = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
		sessao.close();
		assertEquals(pessoaFisica.getNome(), pessoaFisicaAlt.getNome());
	}
	
//	@Test
	public void testExcluir() {
		System.out.println("excluir");
		buscarPessoaFisicaBD();
		sessao = HibernateUtil.abrirSessao();
		pessoaFisicaDao.excluir(pessoaFisica, sessao);
		
		PessoaFisica pessoaFisicaExc = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
		sessao.close();
		assertNull(pessoaFisicaExc);

	}
	
//	@Test
	public void testPesquisarPorNome() {
		System.out.println("Pesquisar Por Nome");
		buscarPessoaFisicaBD();
		sessao = HibernateUtil.abrirSessao();
		List<PessoaFisica> pessoas = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome().substring(0, 4), sessao);
		sessao.close();
		assertTrue(!pessoas.isEmpty());
	}
	
//	@Test
	public void testPesquisarPorCpf() {
		System.out.println("Pesquisar Por CPF");
		buscarPessoaFisicaBD();
		sessao = HibernateUtil.abrirSessao();
		PessoaFisica pessoaFisicaCPF = pessoaFisicaDao.pesquisarPorCpf(pessoaFisica.getCpf(), sessao);
		sessao.close();
		assertNotNull(pessoaFisicaCPF);
	}
	
	
	public void buscarPessoaFisicaBD() {
		sessao = HibernateUtil.abrirSessao();
		Query consulta = sessao.createQuery("from PessoaFisica pf left join fetch pf.cartoes");
		List<PessoaFisica> fisicas = consulta.list();
		sessao.close();
		if(fisicas.isEmpty()) {
			testSalvar();
		}else {
			pessoaFisica = fisicas.get(0);
		}
	}
}
