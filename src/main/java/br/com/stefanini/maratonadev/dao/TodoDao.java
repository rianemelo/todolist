package br.com.stefanini.maratonadev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.loader.plan.spi.QuerySpaceUidNotRegisteredException;

import br.com.stefanini.maratonadev.model.Todo;

@RequestScoped //a anotação de definição de escopo faz com que se torne um SERVICE
public class TodoDao {
	
	@PersistenceContext
	EntityManager em;
	
	
	@Transactional
	public void inserir(Todo todo) {
		String nomeSql = "INSERIR_TODO";
		Query query = em.createNamedQuery(nomeSql);
		
		query.setParameter("nome", todo.getNome());
		query.setParameter("dataCriacao", todo.getDataCriacao());
		
		query.executeUpdate();
		
	}
	
	public List<Todo> listar(){
		String nomeConsultaString = "CONSULTAR_TODO";
		List<Todo> listaRetorno;
		TypedQuery<Todo> query = em.createNamedQuery(nomeConsultaString, Todo.class);
		try {
			listaRetorno = query.getResultList();
		} catch (NoResultException e) {
			listaRetorno = new ArrayList(); 
		}
		return listaRetorno;
	}

	@Transactional
	public void excluir(Long id) {
		String nomeSql = "EXCLUIR_TODO";
		Query query = em.createNamedQuery(nomeSql);
		
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}
	
}
