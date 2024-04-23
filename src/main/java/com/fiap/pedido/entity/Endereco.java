package com.fiap.pedido.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name ="endereco")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Endereco {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * REMETENTE ou DESTINATARIO
     */
    private TipoEndereco tipoEndereco;  

    private String cep;

    private String logradouro;
    
    private String complemento;
    
    private String bairro;

    private String cidade;
    
    private String uf;
    
    private String pais;
}
