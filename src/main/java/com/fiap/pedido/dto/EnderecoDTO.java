package com.fiap.pedido.dto;

import com.fiap.pedido.entity.TipoEndereco;

public record EnderecoDTO(    
    long id,
    TipoEndereco tipoEndereco,  
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String cidade,
    String uf,
    String pais) 
{
    
}
