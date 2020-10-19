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
                setCantidad(2);
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
 
       facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
        
                FacturaDetalleDTO facturaDetalleEjemplo2 = new FacturaDetalleDTO(); 
                facturaDetalleEjemplo2.setCantidad(3);
                facturaDetalleEjemplo2.setDescuentoFinal(0.20);
                facturaDetalleEjemplo2.setEstado(false);

        Optional<FacturaDetalleDTO> facturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());
        System.out.println(facturaDetalleEjemplo.getId());
        
        
        
        if(facturaDetalleEncontrado.isPresent()) {
            facturaDetalleEjemplo = facturaDetalleService.update(facturaDetalleEjemplo.getId(),facturaDetalleEjemplo2);
            
            assertEquals(facturaDetalleEjemplo.getCantidad(), facturaDetalleEjemplo2.getCantidad());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
     @Test
    public void sePuedeEliminarUnFacturaDetalleCorrectamente() {
        
         facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
         
        Optional<FacturaDetalleDTO> facturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());

        if (facturaDetalleEncontrado.isPresent()) {
            FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrado.get();
            
//          facturaDetalleService.delete(facturaDetalleEjemplo.getId());
            facturaDetalleService.delete2(facturaDetalleEncontrado.get().getId());
             
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
