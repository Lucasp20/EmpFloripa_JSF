package br.com.senac.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class PessoaJuridica extends Cliente{
	

	@Column(nullable = false, length = 14, unique = true) 
    private String cnpj;
	
	@Column(nullable = false, unique = true) 
    private String incricao_estadual;
	
	@OneToMany(mappedBy = "pessoaJuridica", cascade = CascadeType.ALL)
	private List<Telefone> telefone;
	
	public PessoaJuridica(Long id, String nome, String email, String cnpj, String incricao_estadual) {
		super(id, nome, email);
		this.cnpj = cnpj;
		this.incricao_estadual = incricao_estadual;
	}
	
	public PessoaJuridica() {
		super();
		
	}	
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIncricao_estadual() {
		return incricao_estadual;
	}

	public void setIncricao_estadual(String incricao_estadual) {
		this.incricao_estadual = incricao_estadual;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
	
}
