package com.fiap.pedido.service;

import com.fiap.pedido.dto.EnderecoDTO;
import com.fiap.pedido.entity.Endereco;
import com.fiap.pedido.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {  

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO inserirEndereco(EnderecoDTO EnderecoDTO) {
        Endereco endereco = toEndereco(EnderecoDTO);

        enderecoRepository.save(endereco);
    
        return toEnderecoDTO(endereco); 
    }
    public void updateEndereco(long id, Endereco novoEndereco) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        
        if (enderecoExistente.isPresent()) {
            Endereco endereco = enderecoExistente.get();

            endereco.setTipoEndereco(novoEndereco.getTipoEndereco());
            endereco.setCep(novoEndereco.getCep());
            endereco.setLogradouro(novoEndereco.getLogradouro());
            endereco.setComplemento(novoEndereco.getComplemento());
            endereco.setCidade(novoEndereco.getCidade());
            endereco.setUf(novoEndereco.getUf());
            endereco.setPais(novoEndereco.getPais());

            enderecoRepository.save(endereco);
        } else {
            throw new IllegalArgumentException("Não foi encontrado nenhum endereço com o id " + id);
        }
    }

    public void deleteEndereco(long id) {
        Optional<Endereco> EnderecoExistente = enderecoRepository.findById(id);

        if (!EnderecoExistente.isPresent()) {
            throw new IllegalArgumentException("Não foi encontrado nenhum endereço com o id " + id);
        }

        Endereco Endereco = EnderecoExistente.get();
        enderecoRepository.delete(Endereco);
    }

    public Endereco readEnderecoById(long id) {
        return enderecoRepository.findById(id).get();
    }

    public List<Endereco> readAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco readEnderecoByCep(String cep) {
        return enderecoRepository.findByCep(cep);        
    }

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

    public EnderecoDTO toEnderecoDTO(Endereco Endereco) {
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
