package com.brunoxavier.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.GenericValidator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoxavier.domain.Cliente;
import com.brunoxavier.domain.Endereco;
import com.brunoxavier.domain.enums.TipoCliente;
import com.brunoxavier.dto.ClienteDTO;
import com.brunoxavier.dto.ClienteNewDTO;
import com.brunoxavier.repositories.ClienteRepository;
import com.brunoxavier.repositories.EnderecoRepository;
import com.brunoxavier.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	//@Autowired
	private final ClienteRepository repo;
	//@Autowired
	private final EnderecoRepository enderecoRepository;
	
	public ClienteService(ClienteRepository repo, EnderecoRepository enderecoRepository) {
		this.repo = repo;
		this.enderecoRepository = enderecoRepository;
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> retorno = repo.findById(id);

		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente searchJPQL(Integer id) {
		return repo.searchJPQL(id);
	}
	
	public Cliente searchSQL(Integer id) {
		return repo.searchSQL(id);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()));
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		
		if(!GenericValidator.isBlankOrNull(objDto.getTelefone2())) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(!GenericValidator.isBlankOrNull(objDto.getTelefone3())) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	
}
