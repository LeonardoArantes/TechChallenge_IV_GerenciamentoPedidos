package com.fiap.pedido;

public record ItemDTO(
    long id,
    Long produtoId,    
    int quantidade
) {
    
}
