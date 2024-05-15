package com.fiap.produto;

import lombok.Data;

@Data
public class RemoverEstoqueRequest{

    private Long idProduto;
    private int quantidade;

}
