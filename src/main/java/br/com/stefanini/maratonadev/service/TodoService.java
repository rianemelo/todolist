package br.com.stefanini.maratonadev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;

import br.com.maratonadev.dto.TodoDto;
import br.com.stefanini.maratonadev.dao.TodoDao;
import br.com.stefanini.maratonadev.model.Todo;
import br.com.stefanini.maratonadev.model.parser.TodoParser;

// usamos o DTO na service

@RequestScoped
public class TodoService {

	@Inject
	TodoDao dao;
	
	private void validar(Todo todo) {
		//validar regra de negocio
		if( dao.isNomeRepetido(todo.getNome()) ) {
			throw new NotFoundException(); //vai dar um 4040 se o nome já estiver na lista
		}
		//AGORA O HIBERNATE VALIDATOR CUIDA DISSO
//		if (todo.getNome() == null) {
//			throw new NotFoundException();
//		}
		
		

	}
	
	@Transactional(rollbackOn = Exception.class)
	public void inserir(@Valid TodoDto todoDto) { // se não for nem entra aqui
		//validação
		Todo todo = new Todo();
		todo = TodoParser.get().entidade(todoDto);
		validar(todo);
		todo.setDataCriacao(LocalDateTime.now());
		//chamada da dao
		dao.inserir(todo);
	}
	
	public List<TodoDto> listar() {
		return dao
				.listar()
				.stream()
				.map(TodoParser.get()::dto)
				.collect(Collectors.toList());
	}
	
	public void excluir(Long id) {
		if(dao.buscarPorId(id) == null) {
			throw new NotFoundException();
		}
		dao.excluir(id);
	}

	public TodoDto buscar(Long id) {
		
		return TodoParser.get().dto(buscarPorId(id));
	}
	
	private Todo buscarPorId(Long id) {
		Todo todo = dao.buscarPorId(id);
		
		if(todo == null) {
			throw new NotFoundException();
		}
		return todo;
	}

	@Transactional(rollbackOn = Exception.class)
	public void atualizar(@NotNull Long id, @Valid TodoDto dto) {
		Todo todo = TodoParser
				.get()
				.entidade(dto);
		Todo todoBanco = buscarPorId(id);
		todoBanco.setNome(todo.getNome());
	}

}
