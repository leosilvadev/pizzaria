package br.com.leosilvadev.pizzaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.leosilvadev.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface PizzariaRepositorio extends CrudRepository<Pizzaria, Long> {

	@Query("SELECT p FROM Pizzaria p WHERE p.usuario.login = ?")
	public Pizzaria findOneByLogin(String login);
	
	
	@Query("SELECT p1 FROM Pizzaria p1 INNER JOIN p1.pizzas p2 WHERE p2.nome = ?")
	public List<Pizzaria> listarPizzariasPorNomePizza(String nomePizza);
	
}
