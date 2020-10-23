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
	
	
//	@Transactional
//	public void inserir(Todo todo) {
//		String nomeSql = "INSERIR_TODO";
//		Query query = em.createNamedQuery(nomeSql);
//		
//		query.setParameter("nome", todo.getNome());
//		query.setParameter("dataCriacao", todo.getDataCriacao());
//		
//		query.executeUpdate();
//		
//	}
	
	@Transactional //Insere um TODO e retorna o ID criado
	public Long inserir(Todo todo) {
		String nomeSql = "INSERIR_TODO";
		inserirOuAtualizar(nomeSql, todo);		
		return todo.getId();
	}
	
	
	@Transactional
	public void atualizar(Todo todo) {
		String nomeSql = "ATUALIZAR_TODO";
		inserirOuAtualizar(nomeSql, todo);
	}
	
	
	@Transactional
	private void inserirOuAtualizar(String nomeSql, Todo todo) {
		Query query = em.createNamedQuery(nomeSql);
		
		query.setParameter("id", todo.getId());
		query.setParameter("nome", todo.getNome());
		query.setParameter("dataCriacao", todo.getDataCriacao());
		query.executeUpdate();
		
	}

	
	public List<Todo> listar(){
		String nomeSql = "CONSULTAR_TODO";
		List<Todo> listaRetorno;
		TypedQuery<Todo> query = em.createNamedQuery(nomeSql, Todo.class);
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

	
	public Boolean isNomeRepetido(String nome) {
		String nomeSql = "CONSULTAR_NOME_REPETIDO_TODO";
		Boolean nomeRepetido = Boolean.FALSE;
		
		TypedQuery<Todo> query = em
				.createNamedQuery(nomeSql, Todo.class);
		
		query.setParameter("nome", "%"+nome+"%"); //por causa do like na NamedNativeQuery
		
		nomeRepetido = query.getResultList().size() > 0;
		
		return nomeRepetido;
	}
	
	public Todo buscarPorId(Long id) {
		String nomeSql = "CONSULTAR_TODO_ID";
		Todo todo;
		TypedQuery<Todo> query = 
				em
				.createNamedQuery(nomeSql, Todo.class);
		query.setParameter("id", id);
		try {
			todo = query.getSingleResult();
		}catch(NoResultException e) {
			todo = null;
		}
		return todo;
	}

	
}
