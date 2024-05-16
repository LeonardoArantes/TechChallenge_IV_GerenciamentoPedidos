package com.fiap.pedido;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(
    long id,
    Cliente cliente,
    List<Item> itens,
    int quantidade,
    Double valorTotal,
    LocalDate dataCriacao
) {
    public void validate() {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do pedido deve ser maior que zero.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente do pedido não pode ser nulo.");
        }

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens do pedido não pode ser nula ou vazia.");
        }

        for (Item item : itens) {
            if (item == null) {
                throw new IllegalArgumentException("Item do pedido não pode ser nulo.");
            }
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade do pedido deve ser maior que zero.");
        }

        if (valorTotal < 0) {
            throw new IllegalArgumentException("Valor total do pedido não pode ser negativo.");
        }

        if (dataCriacao == null || dataCriacao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de criação do pedido inválida.");
        }
    }
}
