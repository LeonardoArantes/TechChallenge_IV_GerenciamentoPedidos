package com.fiap.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private PedidoService pedidoService;    

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarPedidos() {
        return ResponseEntity.ok().body(pedidoService.buscarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> buscarPedido(@PathVariable long id) {
        return ResponseEntity.ok().body(pedidoService.buscarPedido(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> inserirPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoSalva = pedidoService.salvarPedido(pedidoDTO);

        return new ResponseEntity<>(pedidoSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPedido(@PathVariable long id) {
        try {
            pedidoService.excluirPedido(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Não foi possível excluir o pedido!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Pedido excluido com sucesso!", HttpStatus.OK);
    }

}
