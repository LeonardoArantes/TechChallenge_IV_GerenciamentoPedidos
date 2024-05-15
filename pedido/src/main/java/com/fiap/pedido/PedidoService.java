package com.fiap.pedido;

import java.util.List;
import java.util.Optional;

//import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    //private static final Logger logger = Logger.getLogger(PedidoService.class.getName());

    private final EstoquePedidoProducerProduto estoquePedidoProducerProduto;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.estoquePedidoProducerProduto = null;
    }
    
    // add
    public PedidoDTO salvarPedido(PedidoDTO pedidoDTO) {
        try {
            Pedido pedido = toEntity(pedidoDTO);

            pedidoDTO.itens().forEach(p -> this.estoquePedidoProducerProduto
                                               .baixarEstoque(new RemoverEstoqueRequest(p.getProdutoId(),p.getQuantidade())));
            
            // Salva o novo Modelo no repositório
            pedido = pedidoRepository.save(pedido);
    
            // Retorna o novo modelo
            return toDTO(pedido);
                
        } catch (Exception e) {
            throw new UnsupportedOperationException("Não há estoque"); // IllegalArgumentException
        }
    }

    // read all
    public List<Pedido> buscarPedidos() {
        return pedidoRepository.findAll();
    }

    // read por produto
    // public List<Pedido> buscarPedidosPorProduto(Long produtoId) {
    //     logger.info("Produto ID: " + produtoId);
    //     return pedidoRepository.findByPedidoId(produtoId);
    // }

    // read por cliente
    public List<Pedido> buscarPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    // read
    public Optional<Pedido> buscarPedido(long id) {
        return pedidoRepository.findById(id);
    }

    // delete
    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public PedidoDTO toDTO(Pedido pedido) {
        // Convertendo Pedido para PedidoDTO
        return new PedidoDTO(
                pedido.getId(),
                pedido.getCliente(),
                pedido.getItens(),
                pedido.getItens().size(), // Quantidade de itens
                pedido.getValorTotal(), // Valor total
                pedido.getDataCriacao());
        }

    public Pedido toEntity(PedidoDTO PedidoDTO) {
        // Convertendo PedidoDTO para Pedido
        Pedido Pedido = new Pedido();
        Pedido.setId(PedidoDTO.id());
        Pedido.setCliente(PedidoDTO.cliente());
        Pedido.setItens(PedidoDTO.itens());
        Pedido.setValorTotal(PedidoDTO.valorTotal());
        Pedido.setDataCriacao(PedidoDTO.dataCriacao());

        return Pedido;
    }
}
