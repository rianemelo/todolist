package br.com.stefanini.maratonadev.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


//H2: cached tables are the default type of regular tables. This means the number of rows is not limited by the main memory. 

@Entity //transforma a classe em classe especial: em uma entidade
@Table(name="todo") //se o nome da tabela é o mesmo nome da classe, podemos omitir a anatocao @Table
@NamedNativeQueries({
	@NamedNativeQuery(name="CONSULTAR_TODO", query = ""
			+ "SELECT id, nome, dataCriacao FROM todo", resultClass = Todo.class),
	@NamedNativeQuery(name="INSERIR_TODO", query = ""
			+ "INSERT INTO todo (id, nome, dataCriacao) values "
			+ "(:id, :nome, :dataCriacao)", resultClass = Todo.class),
})


public class Todo implements Serializable { //torna o objeto serializavel, caso eu queira mandá-lo no front sem o dto 
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // The IDENTITY strategy means that the JPA provider uses the database identity column to generate the primary key.
	private Long id;
	
	@Column(name="nome", length = 250, nullable = false)
	private String nome;
	
	@Column(name="dataCriacao", nullable = false)
	@CreationTimestamp
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
