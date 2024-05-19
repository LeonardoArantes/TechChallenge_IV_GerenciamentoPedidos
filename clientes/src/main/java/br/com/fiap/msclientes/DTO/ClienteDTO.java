package br.com.fiap.msclientes.DTO;

import java.time.LocalDate;

import br.com.fiap.msclientes.Model.Endereco;

public record ClienteDTO(
    long id,
    String nome,
    String email,
    String cpf,
    LocalDate nascimento,
    Endereco endereco) {
}
