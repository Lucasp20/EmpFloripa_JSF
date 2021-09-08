package br.com.senac.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.*;
import org.junit.Test;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaFisica;
import br.com.senac.entidade.PessoaJuridica;
import util.UtilGerador;

public class PessoaJuridicaDaoImplTest {

	private PessoaJuridica pessoaJuridica;
	private PessoaJuridicaDao pessoaJuridicaDao;
	private Session sessao;
	
	public PessoaJuridicaDaoImplTest() {
		pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
	}

//	@Test
	public void testSalvar() {
		System.out.println("Salvar");
		pessoaJuridica = new PessoaJuridica(
				null, 
				UtilGerador.gerarNome(),
				UtilGerador.gerarEmail(),
				UtilGerador.gerarCnpj(),
				UtilGerador.gerarCaracter(10)
				);
		
		Endereco endereco = new Endereco (
				UtilGerador.gerarLogradouro(),
				"Centro",
				UtilGerador.gerarNumero(4),
				UtilGerador.gerarCidade(),
				"Santa Catarina",
				"Casa",
				UtilGerador.gerarCep()
			);
		
		pessoaJuridica.setEndereco(endereco);
		endereco.setCliente(pessoaJuridica);
		sessao = HibernateUtil.abrirSessao();
		
		pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
		sessao.close();
		assertNotNull(pessoaJuridica.getId());
		assertNotNull(endereco.getId());
	}
	
//	@Test
	public void testAlterar() {
		System.out.println("Alterar");
	
	}
	
//	@Test
	public void testExcluir() {
		System.out.println("Excluir");
	
	}
	
//	@Test
	public void testPesquisarPorId() {
		System.out.println("Pesquisar Por ID");
	
	}
//	@Test
	public void testPesquisarPorNome() {
		System.out.println("Pesquisar Por Nome");
	
	}
//	@Test
	public void testPesquisarPorCNPJ() {
		System.out.println("Pesquisar Por CNPJ");
	
	}

	
	public void buscarPessoaJuridicaBD() {
		sessao = HibernateUtil.abrirSessao();
		Query consulta = sessao.createQuery("from PessoaJuridica pj left join fetch pj.cartoes");
		List<PessoaJuridica> juridicas = consulta.list();
		sessao.close();
		if(juridicas.isEmpty()) {
			testSalvar();
		}else {
			pessoaJuridica = juridicas.get(0);
		}
	}
}
