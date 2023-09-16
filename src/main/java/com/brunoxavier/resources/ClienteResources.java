package com.brunoxavier.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunoxavier.domain.Cliente;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResources {

	@GetMapping
	public List<Cliente> listar() {
		Cliente c1 = new Cliente(1, "Bruno");
		Cliente c2 = new Cliente(2, "Caio");
		
		List<Cliente> lista = new ArrayList<>();
		lista.add(c1);
		lista.add(c2);
		
		return lista;
	}
}
