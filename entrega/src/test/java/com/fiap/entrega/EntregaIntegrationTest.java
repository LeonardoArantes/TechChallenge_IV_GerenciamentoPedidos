package com.fiap.entrega;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.entrega.dto.EntregaDTO;
import com.fiap.entrega.entity.Entrega;
import com.fiap.entrega.exceptions.ResourcesNotFoundException;
import com.fiap.entrega.repository.EntregaRepository;
import com.fiap.entrega.service.EntregaService;
import com.fiap.entrega.utils.TestHelper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class EntregaIntegrationTest {

    //public static Logger logger = org.slf4j.LoggerFactory.getLogger(EntregaIntegrationTest.class);
    @Autowired
    private EntregaRepository repository;

    @Autowired
    private EntregaService service;

    /**
     * Test case to verify if the table can be created.
     *
     * This test case checks if the table can be created by counting the number of records in the repository.
     * It asserts that the total number of records is greater than or equal to 0.
     *
     * @throws Exception if an error occurs while counting the records
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> {
            var totalRegistros = repository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

    /**
     * Test case to verify if a new delivery can be inserted.
     *
     * @throws ResourcesNotFoundException if the resources are not found
     */
    @Test
    void devePermitirInserirNovaEntrega() throws ResourcesNotFoundException {
        var novaEntrega = TestHelper.toEntregaDTO(TestHelper.criarEntrega());
        
        var novaEntregaSalva = service.inserirEntrega(novaEntrega);

        assertThat(novaEntregaSalva).isNotNull().isInstanceOf(EntregaDTO.class);
        assertThat(novaEntregaSalva.id()).isNotNull();
        assertThat(novaEntregaSalva.idCliente()).isNotNull();
        assertThat(novaEntregaSalva.codigoRastreio()).isNotNull();
        assertThat(novaEntregaSalva.statusEntrega()).isNotNull();
        assertThat(novaEntregaSalva.dataEnvio()).isNotNull();
        assertThat(novaEntregaSalva.dataPrevisaoEntrega()).isNotNull();
        assertThat(novaEntregaSalva.dataEntrega()).isNotNull();
        assertThat(novaEntregaSalva.Remetente()).isNotNull();
        assertThat(novaEntregaSalva.Destinatario()).isNotNull();
        assertThat(novaEntregaSalva.peso()).isNotNull();
        assertThat(novaEntregaSalva.valor()).isNotNull();
    }

    void devePermitirBuscarEntregaPorId() throws ResourcesNotFoundException {
        var entrega = TestHelper.criarEntrega();
        var entregaDTO = TestHelper.toEntregaDTO(entrega);

        Optional<Entrega> entregaEncontrada = service.buscarEntregaPorId(entrega.getId());

        //assertEquals(Optional.of(entregaEncontrada), entrega );
        //assertThat(entregaEncontrada).isNotNull();
        //assertThat(entregaEncontrada.getId()).isEqualTo(entregaSalva.getId());
    }

}
