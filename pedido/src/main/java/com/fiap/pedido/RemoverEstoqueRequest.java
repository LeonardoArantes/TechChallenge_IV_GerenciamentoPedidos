package com.fiap.pedido;

public record RemoverEstoqueRequest(
    Long idProduto,
    int quantidade
){
}
