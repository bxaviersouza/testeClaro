package com.brunoxavier.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResources {

	@GetMapping
	public String listar() {
		return "REST está funcionando";
	}
}
