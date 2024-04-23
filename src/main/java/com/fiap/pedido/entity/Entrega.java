package com.fiap.pedido.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="entrega")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Entrega {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private long idCliente;

    private String codigoRastreio;

    private StatusEntrega statusEntrega; ;

    private LocalDate dataEnvio;

    private LocalDate dataPrevisaoEntrega;

    private LocalDate dataEntrega;

    private Endereco Remetente;

    private Endereco Destinatario;

    private double peso;

    private BigDecimal valor;

}
