package br.com.fiap.msclientes.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import br.com.fiap.msclientes.Model.Endereco;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cliente")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cliente {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "O nome não pode estar vazio.")
    @Size(min = 10, max = 50, message = "O nome deve ter entre 10 e 50 caracteres")
    private String nome;

    @Column(nullable = false)
    @NotEmpty(message = "O e-mail não pode estar vazio.")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "O CPF não pode estar vazio.")
    private String cpf;

    @Column(nullable = false)
    @NotNull(message = "O nascimento não pode estar vazio.")
    private LocalDate nascimento;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
}
