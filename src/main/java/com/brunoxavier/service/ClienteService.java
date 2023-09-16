package com.brunoxavier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoxavier.domain.Cliente;
import com.brunoxavier.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> retorno = repo.findById(id);
		return retorno.orElse(null);
	}


}
