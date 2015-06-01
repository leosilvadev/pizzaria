package br.com.leosilvadev.pizzaria.modelo.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leosilvadev.pizzaria.modelo.entidades.Pizza;
import br.com.leosilvadev.pizzaria.modelo.entidades.Pizzaria;
import br.com.leosilvadev.pizzaria.modelo.repositorios.PizzaRepositorio;

@Service
public class ServicoPizza {

	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private PizzaRepositorio repositorio;

	public void salvar(Pizza pizza){
		pizza.setDono(servicoPizzaria.getPizzariaLogada());
		repositorio.save(pizza);
	}
	
	public Iterable<Pizza> listar(){
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findAllByDono(dono);
	}
	
	public Pizza buscar(long id){
		Pizzaria dono = servicoPizzaria.getPizzariaLogada();
		return repositorio.findByIdAndDono(id, dono);
	}
	
	public void remover(long id){
		Pizza pizza = this.buscar(id);
		if(pizza!=null) repositorio.delete(pizza);
	}

	public List<String> listarNomesPizzasDisponiveis() {
		List<Pizza> pizzas = repositorio.findAll();
		
		List<String> nomesPizzas = pizzas.stream().map((pizza)->{
			return pizza.getNome();
		}).sorted().collect(Collectors.toList());
		
		return nomesPizzas;
	}
	
}
