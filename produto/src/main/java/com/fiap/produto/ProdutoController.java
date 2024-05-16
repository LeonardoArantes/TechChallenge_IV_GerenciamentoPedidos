package com.fiap.produto;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private ProdutoService produtoService;    

       @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarProdutos() {
        return ResponseEntity.ok().body(produtoService.buscarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable Long id) {
        try {
            ProdutoDTO produtoDTO = produtoService.buscarProduto(id);
            return ResponseEntity.ok(produtoDTO);
        } catch (ProdutoNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> inserirProduto(@RequestBody ProdutoDTO produtoDTO) {
        // Chama o método validate() para validar o ProdutoDTO
        produtoDTO.validate();
        ProdutoDTO produtoSalva = produtoService.salvarProduto(produtoDTO);

        return new ResponseEntity<>(produtoSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable long id) {
        try {
            produtoService.excluirProduto(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Não foi possível excluir o produto!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Produto excluido com sucesso!", HttpStatus.OK);
    }

    // @PostMapping
    // public ResponseEntity<String> baixarEstoque(@RequestBody RemoverEstoqueRequest removerEstoqueRequest) {
    //     String msg;
    //     try {
    //         msg = produtoService.baixarEstoque(removerEstoqueRequest.getIdProduto(), removerEstoqueRequest.getQuantidade());
    //     } catch (ProdutoNotFoundException e) {
    //         return new ResponseEntity<>("Não foi possível baixar o produto!", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    //     return new ResponseEntity<>(msg, HttpStatus.OK);
    // }     
    
    @PutMapping("/{id}/quantidade/{quantidade}")
    public ResponseEntity<String> baixarEstoque(@PathVariable long id, @PathVariable int quantidade) {
        String msg;
        try {
            msg = produtoService.baixarEstoque(id, quantidade);
        } catch (ProdutoNotFoundException e) {
            return new ResponseEntity<>("Não foi possível baixar o produto!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    } 

    @PostMapping("/upload/file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Arquivo vazio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        InputStream inputStream = file.getInputStream();
        try (Scanner leitor = new Scanner(inputStream)) {
            //StringBuilder result = new StringBuilder();
            int j = 0;
    
            // percorre todo o arquivo
            while (leitor.hasNext()) {
                // recebe cada linha do arquivo
                String linhasDoArquivo = leitor.nextLine();
    
                // separa os campos entre as vírgulas de cada linha
                String[] valoresEntreVirgulas = linhasDoArquivo.split(";");
                Long id = Long.parseLong(valoresEntreVirgulas[0]);
                Optional<Produto> produtoOPT = produtoService.buscarProdutoOpt(id);
                
                // Se o produto não existir, crie um novo; caso contrário, atualize o existente
                Produto produto = produtoOPT.orElseGet(() -> new Produto());
    
                produto.setId(id);
                produto.setNome(valoresEntreVirgulas[1]);
                produto.setQuantidade(Integer.parseInt(valoresEntreVirgulas[2]));
                produto.setPreco(Double.parseDouble(valoresEntreVirgulas[3]));
                produto.setDataAtualizacao(LocalDate.now());
    
                ProdutoDTO produtoDTO = produtoService.toDTO(produto);
                produtoService.salvarProduto(produtoDTO);
                j++;
            }
            return new ResponseEntity<>("Upload realizado com sucesso! " + j + " produtos atualizados!", HttpStatus.OK);
        }
    }
}
