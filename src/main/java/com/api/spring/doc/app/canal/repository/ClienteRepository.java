package com.api.spring.doc.app.canal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.spring.doc.app.canal.entity.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
