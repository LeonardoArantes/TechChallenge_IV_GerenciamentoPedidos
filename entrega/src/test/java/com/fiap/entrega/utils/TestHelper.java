package com.fiap.entrega.utils;

import com.fiap.entrega.dto.EnderecoDTO;
import com.fiap.entrega.entity.Endereco;
import com.fiap.entrega.entity.TipoEndereco;


public class TestHelper {

    public static Endereco criarEndereco() {
      return new Endereco(1L, 
                          TipoEndereco.DESTINATARIO,
                          "00000-000",
                          "rua almondega 9009",
                          "apto 9009",
                          "centro",
                          "sao paulo",
                          "sp",
                          "brasil");  
    }

    public static Endereco criarEndereco1() {
        return new Endereco(2L, 
                            TipoEndereco.REMETENTE,
                            "00000-000",
                            "rua almondega 9008",
                            "NA",
                            "centro",
                            "sao paulo",
                            "sp",
                            "brasil");  
      }
    /**
     * Converts an EnderecoDTO object to an Endereco object.
     *
     * @param  EnderecoDTO  the EnderecoDTO object to be converted
     * @return              the converted Endereco object
     */
    public Endereco toEndereco(EnderecoDTO EnderecoDTO) {
        return new Endereco(EnderecoDTO.id(),
                            EnderecoDTO.tipoEndereco(),
                            EnderecoDTO.cep(),
                            EnderecoDTO.logradouro(),
                            EnderecoDTO.complemento(),
                            EnderecoDTO.bairro(),
                            EnderecoDTO.cidade(),
                            EnderecoDTO.uf(),
                            EnderecoDTO.pais()); 
    }

    /**
     * Converts an Endereco object to an EnderecoDTO object.
     *
     * @param  Endereco  the Endereco object to be converted
     * @return           the converted EnderecoDTO object
     */
    public static EnderecoDTO toEnderecoDTO(Endereco Endereco) {
        return new EnderecoDTO(Endereco.getId(),
                                Endereco.getTipoEndereco(),
                                Endereco.getCep(),
                                Endereco.getLogradouro(),
                                Endereco.getComplemento(),
                                Endereco.getBairro(),
                                Endereco.getCidade(),
                                Endereco.getUf(),
                                Endereco.getPais());
    }
}
