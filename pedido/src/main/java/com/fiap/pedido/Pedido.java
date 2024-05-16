package com.fiap.pedido;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name = "pedido")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Pedido {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY) // Indica que muitos pedidos podem pertencer a um cliente
   @JoinColumn(name = "cliente_id", nullable = false) // Define a coluna que representa o relacionamento
   private Cliente cliente;   

   @OneToMany(mappedBy = "pedido") // Define o nome do campo na classe Item que representa o relacionamento
   private List<Item> itens;  

   @Column(nullable = false)
   private int quantidade; 

   @Column(nullable = false)
   private Double valorTotal;    

   @Column(nullable = false)
   private LocalDate dataCriacao; 

}
