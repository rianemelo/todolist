package br.com.maratonadev.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


//regras de visualização
public class TodoDto implements Serializable{
	 
	private Long id;
	
	@NotNull (message = "Nome Obrigatorio!") //aparece no console log, quando o nome é nulo
	@NotBlank (message = "Nao e permitido nome nulo")
	@Length (min = 3, max = 250, message = "min de 3 e max de 250 caracteres!")
	private String nome;
	
	@JsonbDateFormat("dd/MM/yyy HH:mm")
	private LocalDateTime dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	


}
