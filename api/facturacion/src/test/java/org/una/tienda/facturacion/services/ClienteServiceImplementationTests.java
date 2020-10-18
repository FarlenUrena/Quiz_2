/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.ClienteDTO;

/**
 *
 * @author erikg
 */
@SpringBootTest
public class ClienteServiceImplementationTests {
    @Autowired
    private IClienteService clienteService;

    ClienteDTO clienteEjemplo;
    
    @BeforeEach
    public void setup() {
        clienteEjemplo = new ClienteDTO() {
            {
                setDireccion("Dirección.");
                setEmail("a@.com");
                setEstado(true);
            }
        };
    }
    
     @Test
    public void sePuedeCrearUnClienteCorrectamente() {
 
        clienteEjemplo = clienteService.create(clienteEjemplo);

        Optional<ClienteDTO> clienteEncontrado = clienteService.findById(clienteEjemplo.getId());

        if (clienteEncontrado.isPresent()) {
            ClienteDTO cliente = clienteEncontrado.get();
            assertEquals(clienteEjemplo.getId(), cliente.getId());

        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    @Test
    public void sePuedeModificarUnClienteCorrectamente() {
 
        String modificar = "Se modificó el cliente";
        clienteEjemplo = clienteService.create(clienteEjemplo);

        Optional<ClienteDTO> clienteEncontrado = clienteService.findById(clienteEjemplo.getId());
        if(clienteEncontrado.isPresent()&& (clienteEncontrado.get().getDireccion() == null ? clienteEjemplo.getDireccion() != null : !clienteEncontrado.get().getDireccion().equals(clienteEjemplo.getDireccion()))) {
            clienteEjemplo.setDireccion(modificar);
            ClienteDTO cliente = clienteEncontrado.get();

            System.out.println(clienteEncontrado.get().getDireccion());
            System.out.println(clienteEjemplo.getDireccion());

            assertEquals(clienteEjemplo.getId(), cliente.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    
    @AfterEach
    public void tearDown() {
        if (clienteEjemplo != null) {
            clienteService.delete(clienteEjemplo.getId());
            clienteEjemplo = null;
        }
    }
}
