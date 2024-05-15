package com.fiap.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "produtos", url = "http://localhost:8081/produtos")
public interface EstoquePedidoProducerProduto {

    @PutMapping(value = "/baixarEstoque/{id}/quantidade/{quantidade}")
    //@GetMapping("/{id}/quantidade/{quantidade}")
    void baixarEstoque(RemoverEstoqueRequest removerEstoqueRequest);

    Object baixarEstoque(Long produtoId, int quantidade);

    //Object baixarEstoque(RemoverEstoqueRequest removerEstoqueRequest);

}