package com.fiap.entrega.dto;

import java.time.LocalDate;

import com.fiap.entrega.entity.Endereco;
import com.fiap.entrega.entity.StatusEntrega;

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
      double valor) {


}
