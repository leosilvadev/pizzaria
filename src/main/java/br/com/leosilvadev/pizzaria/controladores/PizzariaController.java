package br.com.leosilvadev.pizzaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.leosilvadev.pizzaria.modelo.servicos.ServicoPizza;
import br.com.leosilvadev.pizzaria.modelo.servicos.ServicoPizzaria;

@Controller
@RequestMapping("/pizzarias")
public class PizzariaController {
	
	@Autowired private ServicoPizzaria servicoPizzaria;
	@Autowired private ServicoPizza servicoPizza;

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("nomesPizzas", servicoPizza.listarNomesPizzasDisponiveis());
		return "cliente/busca_pizzarias";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pizza/{nomePizza}")
	public String index(@PathVariable String nomePizza, Model model){
		model.addAttribute("pizzarias", servicoPizzaria.listarPizzariasQueContem(nomePizza));
		return "cliente/tabela_pizzarias";
	}
	
}
