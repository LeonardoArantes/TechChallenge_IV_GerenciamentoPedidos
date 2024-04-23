package com.fiap.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.pedido.entity.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    
}
