package com.fiap.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> { 
        // Método para buscar pedidos por produto
        //@Query("SELECT p FROM Pedido p JOIN p.itens i WHERE i.produto.id = :produtoId")
        //List<Pedido> findByPedidoId(Long produtoId);
    
        // Método para buscar pedidos por cliente
        List<Pedido> findByClienteId(Long clienteId);

        List<Pedido> findByItensProdutoId(Long produtoId);
}
