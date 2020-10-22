package br.com.stefanini.maratonadev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;


import br.com.stefanini.maratonadev.dao.TodoDao;
import br.com.stefanini.maratonadev.model.Todo;


@RequestScoped
public class TodoService {

	@Inject
	TodoDao dao;
	
	private void validar(Todo todo) {
		//validar regra de negocio
		if (todo.getNome() == null) {
			throw new NotFoundException();
		}
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void inserir(Todo todo) {
		//validação
		validar(todo);
		todo.setDataCriacao(LocalDateTime.now());
		//chamada da dao
		dao.inserir(todo);
	}
	
	public List<Todo> listar() {
		return dao.listar();
	}


}
