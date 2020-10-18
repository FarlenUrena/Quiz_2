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
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;

/**
 *
 * @author erikg
 */
@SpringBootTest
public class FacturaDetalleServiceImplementationTests {
    @Autowired
    private IFacturaDetalleService facturaDetalleService;

    FacturaDetalleDTO facturaDetalleEjemplo;
    
    @BeforeEach
    public void setup() {
        facturaDetalleEjemplo = new FacturaDetalleDTO() {
            {
                setCantidad(0.10);
                setDescuentoFinal(0.10);
                setEstado(true);
                
            }
        };
    }
     @Test
    public void sePuedeCrearUnFacturaDetalleCorrectamente() {
        facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
        Optional<FacturaDetalleDTO> facturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());
        if (facturaDetalleEncontrado.isPresent()) {
            FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrado.get();
            assertEquals(facturaDetalleEjemplo.getId(), facturaDetalle.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    @Test
    public void sePuedeModificarUnFacturaDetalleCorrectamente() {
 
        String modificar = "Se modificó la facturaDetalle";
        facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);

        Optional<FacturaDetalleDTO> facturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());
        if(facturaDetalleEncontrado.isPresent()&& facturaDetalleEncontrado.get().getCantidad() != facturaDetalleEjemplo.getCantidad()) {
            facturaDetalleEjemplo.setCantidad(1);
            FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrado.get();

            System.out.println(facturaDetalleEncontrado.get().getCantidad());
            System.out.println(facturaDetalleEjemplo.getCantidad());

            assertEquals(facturaDetalleEjemplo.getId(), facturaDetalle.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    @AfterEach
    public void tearDown() {
        if (facturaDetalleEjemplo != null) {
            facturaDetalleService.delete(facturaDetalleEjemplo.getId());
            facturaDetalleEjemplo = null;
        }
    }
}
