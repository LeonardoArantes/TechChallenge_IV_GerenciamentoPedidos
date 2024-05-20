package com.fiap.produto.Service;

import com.fiap.produto.DTO.ProdutoDTO;
import com.fiap.produto.Exceptions.ProdutoNotFoundException;
import com.fiap.produto.Exceptions.ResourceNotFoundException;
import com.fiap.produto.Exceptions.ServiceException;
import com.fiap.produto.Model.Produto;
import com.fiap.produto.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    // add
    public ProdutoDTO salvarProduto(ProdutoDTO ProdutoDTO) {
       Produto Produto = toEntity(ProdutoDTO);

       // Salva o novo Modelo no repositório
       Produto = produtoRepository.save(Produto);

       // Retorna o novo modelo
       return toDTO(Produto);
    }

    public List<Produto> buscarProdutos() throws ResourceNotFoundException {
        List<Produto> produto = produtoRepository.findAll();
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado.");
        }
        return produto;
    }

    public Produto buscarProduto(long id) throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para este id: " + id));
        return produto;
    }

    // delete
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public String baixarEstoque(long id, int quantidade) {
        try {
            if (quantidade < 1) {
                // Quantidade deve ser maior que zero
                throw new ServiceException("Quantidade deve ser maior que zero");
            }

            Optional<Produto> produtoOpt = produtoRepository.findById(id);
            logger.info("---- id: " + id + " qtd: " + quantidade);

            if (produtoOpt.isEmpty()) {
                // Produto não encontrado
                throw new ProdutoNotFoundException("Produto não encontrado");
            }

            Produto produto = produtoOpt.get();
            int qtdDisponivel = produto.getQuantidade();

            if (qtdDisponivel < quantidade) {
                // Quantidade disponível insuficiente
                throw new ServiceException("Quantidade maior que a disponível");
            }

            int novaQuantidade = qtdDisponivel - quantidade;
            produto.setQuantidade(novaQuantidade);

            logger.info("---- nova qtd: " + novaQuantidade);

            // Atualiza o produto
            ProdutoDTO produtoDTO = this.toDTO(produto);

            this.salvarProduto(produtoDTO);

            // Retorna o produto atualizado
            return "Baixa efetuada com sucesso";
        } catch (ProdutoNotFoundException e) {
            logger.info("Erro ao baixar produto: Produto não encontrado " + e);
            throw e;
        } catch (ServiceException e) {
            logger.info("Erro ao baixar produto: Quantidade maior que a disponível " + e);
            throw e;
        } catch (Exception e) {
            logger.info("Erro ao baixar produto " + e);
            throw new ServiceException("Erro ao baixar produto", e);
        }
    }

    public ProdutoDTO toDTO(Produto Produto) {
        // Convertendo Produto para ProdutoDTO
        return new ProdutoDTO(
                Produto.getId(),
                Produto.getNome(),
                Produto.getPreco(),
                Produto.getQuantidade(),
                Produto.getDataAtualizacao());
    }

    public Produto toEntity(ProdutoDTO ProdutoDTO) {
        // Convertendo ProdutoDTO para Produto
        Produto Produto = new Produto();
        Produto.setId(ProdutoDTO.id());
        Produto.setNome(ProdutoDTO.nome());
        Produto.setPreco(ProdutoDTO.preco());
        Produto.setQuantidade(ProdutoDTO.quantidade());
        Produto.setDataAtualizacao(ProdutoDTO.dataAtualizacao());

        return Produto;
    }
}
