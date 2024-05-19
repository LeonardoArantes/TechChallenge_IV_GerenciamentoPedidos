package br.com.fiap.msclientes.Model;

import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "endereco")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Endereco {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "O nome da rua não pode estar vazio.")
    @Size(min = 10, max = 50, message = "O nome da rua deve ter entre 10 e 50 caracteres")
    private String rua;

    @Column(nullable = false)
    @NotEmpty(message = "O número não pode estar vazio.")
    private String numero;

    @Column(nullable = false)
    @NotEmpty(message = "O CEP não pode estar vazio.")
    private String cep;

    @Column(nullable = false)
    @NotEmpty(message = "O complemento não pode estar vazio.")
    private String complemento;
}
