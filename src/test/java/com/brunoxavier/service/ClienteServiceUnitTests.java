package com.brunoxavier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.brunoxavier.domain.Cliente;
import com.brunoxavier.domain.Endereco;
import com.brunoxavier.domain.enums.TipoCliente;
import com.brunoxavier.repositories.ClienteRepository;
import com.brunoxavier.repositories.EnderecoRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceUnitTests {

	@Spy
	@InjectMocks
	private ClienteService service;
	
	@Mock
	private ClienteRepository repo;
	
	@Mock
	private EnderecoRepository enderecoRepo;
	
	@Test
	public void insert() {
		Cliente cli = obterCliente();
		
		when(repo.save(cli)).thenReturn(cli);
		when(enderecoRepo.saveAll(cli.getEnderecos())).thenReturn(cli.getEnderecos());
		
		Cliente retornoCli = service.insert(cli);
		assertNotNull(retornoCli);
		assertEquals(cli, retornoCli);
	}
	
	@Test
	public void update() {
		Cliente cli = obterCliente();
		cli.setId(1);
		
		when(repo.findById(cli.getId())).thenReturn(Optional.ofNullable(cli));
		when(repo.save(cli)).thenReturn(cli);
		
		Cliente retornoCli = service.update(cli);
		assertNotNull(retornoCli);
		assertEquals(cli, retornoCli);
	}
	
	@Test
	public void delete() {
		Cliente cli = obterCliente();
		cli.setId(1);
		
		when(repo.findById(cli.getId())).thenReturn(Optional.ofNullable(cli));
		doNothing().when(repo).deleteById(cli.getId());
		
		service.delete(cli.getId());
		verify(service,times(1)).delete(cli.getId());
	}
	
	@Test
	public void find() {
		Cliente cli = obterCliente();
		cli.setId(1);
		
		when(repo.findById(cli.getId())).thenReturn(Optional.ofNullable(cli));
		
		service.find(cli.getId());
	}
	
	private Cliente obterCliente() {
		Cliente cli1 = new Cliente(null, "Bruno Xavier", "bruno@gmail.com", "01234567890", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("99994444","99995555"));
		Endereco e1 = new Endereco(null, "Rua um", "300", "Apto 1", "Jardim", "12345", cli1);
		Endereco e2 = new Endereco(null, "Rua dois", "100", "Apto 506", "Botafogo", "12345", cli1);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		return cli1;
	}
	
}
