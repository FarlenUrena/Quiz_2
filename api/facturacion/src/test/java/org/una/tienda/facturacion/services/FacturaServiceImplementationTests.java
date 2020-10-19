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
public class FacturaServiceImplementationTests {
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
 
        facturaEjemplo = facturaService.create(facturaEjemplo);
        
                FacturaDTO facturaEjemplo2 = new FacturaDTO(); 
                facturaEjemplo2.setCaja(3);
                facturaEjemplo2.setDescuentoGeneral(0.15);
                facturaEjemplo2.setEstado(false);

        Optional<FacturaDTO> facturaEncontrado = facturaService.findById(facturaEjemplo.getId());
        System.out.println(facturaEjemplo.getId());
        
        
        
        if(facturaEncontrado.isPresent()) {
            facturaEjemplo = facturaService.update(facturaEjemplo.getId(),facturaEjemplo2);
            
            assertEquals(facturaEjemplo.getCaja(), facturaEjemplo2.getCaja());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    @Test
    public void sePuedeEliminarUnFacturaCorrectamente() {
        
         facturaEjemplo = facturaService.create(facturaEjemplo);
         
        Optional<FacturaDTO> facturaEncontrado = facturaService.findById(facturaEjemplo.getId());

        if (facturaEncontrado.isPresent()) {
            FacturaDTO factura = facturaEncontrado.get();
            
//          facturaService.delete(facturaEjemplo.getId());
            facturaService.delete2(facturaEncontrado.get().getId());
             
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
