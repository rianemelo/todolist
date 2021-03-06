package br.com.stefanini.maratonadev.model.parser;

import br.com.maratonadev.dto.TodoDto;
import br.com.stefanini.maratonadev.model.Todo;

public class TodoParser {
	
	public static TodoParser get() { //pq vamos usar lambdas
		return new TodoParser();
	}
	
	public Todo entidade(TodoDto dto) {
		Todo entidade = new Todo();
		
		entidade.setId(dto.getId());
		entidade.setNome(dto.getNome());
		entidade.setDataCriacao(dto.getDataCriacao());
		
		return entidade;
	}
	
	public TodoDto dto(Todo entidade) {
		TodoDto dto = new TodoDto();
		
		dto.setId(entidade.getId());
		dto.setNome(entidade.getNome());
		dto.setDataCriacao(entidade.getDataCriacao());
		return dto;
		
	}
	


}

