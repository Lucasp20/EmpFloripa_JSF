package br.com.senac.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class PessoaFisica extends Cliente{
	
	@Column(nullable = false, length = 14, unique = true) 
    private String cpf;
	@Column(nullable = false, unique = true) 
    private String rg;
	
	@ManyToOne
	@JoinColumn(name = "id_profissao")
	private Profissao profissao;
	
	public PessoaFisica(Long id, String nome, String email, String cpf, String rg, Profissao profissao) {
		super(id, nome, email);
		this.cpf = cpf;
		this.rg = rg;
		this.profissao = profissao;
	}
	
	public PessoaFisica() {
		super();
		
	}
		
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	
}
