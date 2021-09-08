package br.com.senac.entidade;

import java.io.Serializable;

import javax.persistence.*;


import org.hibernate.*;

@Entity
@Table(name = "cartao")
public class Cartao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 19)
	private String numero;
	@Column(nullable = false	)
	private String bandeira;
	@Column(nullable = false, length = 4)
	private String anoValidade;

	@ManyToOne
	@JoinColumn(name= "id_cliente")
	private Cliente cliente;
	
	public Cartao(String numero, String bandeira, String anoValidade) {
		super();
		this.numero = numero;
		this.bandeira = bandeira;
		this.anoValidade = anoValidade;
	}
	
	public Cartao() {
		super();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getAnoValidade() {
		return anoValidade;
	}

	public void setAnoValidade(String anoValidade) {
		this.anoValidade = anoValidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
