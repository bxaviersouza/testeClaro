package com.brunoxavier;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brunoxavier.domain.Cliente;
import com.brunoxavier.domain.Endereco;
import com.brunoxavier.domain.enums.TipoCliente;
import com.brunoxavier.repositories.ClienteRepository;
import com.brunoxavier.repositories.EnderecoRepository;

@SpringBootApplication
public class TesteClaroApplication implements CommandLineRunner {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteClaroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente cli1 = new Cliente(null, "Bruno Xavier", "bruno@gmail.com", "01234567890", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("99994444","99995555"));
		Endereco e1 = new Endereco(null, "Rua um", "300", "Apto 1", "Jardim", "12345", cli1);
		Endereco e2 = new Endereco(null, "Rua dois", "100", "Apto 506", "Botafogo", "12345", cli1);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		Cliente cli2 = new Cliente(null, "Caio Marques");
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
