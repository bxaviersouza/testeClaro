package com.brunoxavier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brunoxavier.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query("SELECT DISTINCT obj FROM Cliente obj WHERE obj.id = :idCliente")
	Cliente searchJPQL(@Param("idCliente") Integer idCliente);
	
	@Query(value="SELECT * FROM CLIENTE WHERE ID = :idCliente", nativeQuery=true)
	Cliente searchSQL(@Param("idCliente") Integer idCliente); 
}
