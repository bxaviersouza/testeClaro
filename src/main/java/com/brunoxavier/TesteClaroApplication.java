package com.brunoxavier;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brunoxavier.domain.Cliente;
import com.brunoxavier.repositories.ClienteRepository;

@SpringBootApplication
public class TesteClaroApplication implements CommandLineRunner {

	@Autowired
	ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteClaroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente c1 = new Cliente(null, "Bruno Xavier");
		Cliente c2 = new Cliente(null, "Caio Marques");
		
		clienteRepository.saveAll(Arrays.asList(c1, c2));
	}

}
