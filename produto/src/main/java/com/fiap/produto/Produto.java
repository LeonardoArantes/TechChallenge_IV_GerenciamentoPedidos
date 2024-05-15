package com.fiap.produto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name = "produto")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Produto {
   @Id
   @Column(unique = true)
   private Long id;

   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private int quantidade;    

   @Column(nullable = false)
   private Double preco;    

   //@Column(nullable = false)
   private LocalDate dataAtualizacao; 

}
