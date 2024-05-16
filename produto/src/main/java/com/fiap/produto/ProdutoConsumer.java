package com.fiap.produto;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConsumer {
    
    private final ProdutoService produtoService;

    public ProdutoConsumer(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @Bean(name = "baixa-estoque")
    Consumer<ProdutoRequest> consumer(){
        return pR -> 
            this.produtoService
                .baixarEstoque(pR.getIdProduto(), pR.getQuantidade());
    }
}
