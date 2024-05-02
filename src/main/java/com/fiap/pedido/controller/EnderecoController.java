package com.fiap.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.pedido.dto.EnderecoDTO;
import com.fiap.pedido.exceptions.ResourcesNotFoundException;
import com.fiap.pedido.service.EnderecoService;
import com.fiap.pedido.service.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService EnderecoService;


    //inserir endereço
    @PostMapping
    public ResponseEntity<EnderecoDTO> inserEndereco(@RequestBody EnderecoDTO EnderecoDTO) throws ResourcesNotFoundException {
        EnderecoDTO endereco = EnderecoService.inserirEndereco(EnderecoDTO);

        return new ResponseEntity<EnderecoDTO>(endereco, HttpStatus.CREATED);
    }
    //buscar endereço

    //listar todos os endereços

    //atualizar endereço

    //deletar endereço
}
