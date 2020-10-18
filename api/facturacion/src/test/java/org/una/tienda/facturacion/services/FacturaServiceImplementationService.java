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
import org.una.tienda.facturacion.dto.FacturaDTO;

/**
 *
 * @author erikg
 */
@SpringBootTest
public class FacturaServiceImplementationService {
    @Autowired
    private IFacturaService facturaService;

    FacturaDTO facturaEjemplo;
  
        @BeforeEach
    public void setup() {
        facturaEjemplo = new FacturaDTO() {
            {
                setCaja(1);
                setDescuentoGeneral(0.10);
                setEstado(true);
            }
        };
    }
     @Test
    public void sePuedeCrearUnFacturaCorrectamente() {
 
        facturaEjemplo = facturaService.create(facturaEjemplo);

        Optional<FacturaDTO> facturaEncontrado = facturaService.findById(facturaEjemplo.getId());

        if (facturaEncontrado.isPresent()) {
            FacturaDTO factura = facturaEncontrado.get();
            assertEquals(facturaEjemplo.getId(), factura.getId());

        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    @Test
    public void sePuedeModificarUnFacturaCorrectamente() {
 
        String modificar = "Se modificó la factura";
        facturaEjemplo = facturaService.create(facturaEjemplo);

        Optional<FacturaDTO> facturaEncontrado = facturaService.findById(facturaEjemplo.getId());
        if(facturaEncontrado.isPresent()&& facturaEncontrado.get().getCaja() != facturaEjemplo.getCaja()) {
            facturaEjemplo.setCaja(1);
            FacturaDTO factura = facturaEncontrado.get();

            System.out.println(facturaEncontrado.get().getCaja());
            System.out.println(facturaEjemplo.getCaja());

            assertEquals(facturaEjemplo.getId(), factura.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    @AfterEach
    public void tearDown() {
        if (facturaEjemplo != null) {
            facturaService.delete(facturaEjemplo.getId());
            facturaEjemplo = null;
        }
    }
}
