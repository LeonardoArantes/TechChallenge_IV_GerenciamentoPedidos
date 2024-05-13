package com.fiap.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fiap.pedido.entity.Endereco;
import com.fiap.pedido.entity.StatusEntrega;

public record EntregaDTO(long id,
      long idCliente,
      String codigoRastreio,
      StatusEntrega statusEntrega, 
      LocalDate dataEnvio,
      LocalDate dataPrevisaoEntrega,
      LocalDate dataEntrega,
      Endereco Remetente,
      Endereco Destinatario,
      double peso,
      BigDecimal valor) {


}
