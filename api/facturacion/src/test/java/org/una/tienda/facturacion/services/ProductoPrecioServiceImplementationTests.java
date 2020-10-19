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
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;

/**
 *
 * @author erikg
 */
@SpringBootTest
public class ProductoPrecioServiceImplementationTests {
    @Autowired
    private IProductoPrecioService productoPrecioService;

    ProductoPrecioDTO productoPrecioEjemplo;
       @BeforeEach
    public void setup() {
        productoPrecioEjemplo = new ProductoPrecioDTO() {
            {
                setDescuentoMaximo(1.0);
                setEstado(true);
            }
        };
    }
    
    @Test
    public void sePuedeCrearUnProductoPrecioCorrectamente() {
 
        productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);

        Optional<ProductoPrecioDTO> clienteEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());

        if (clienteEncontrado.isPresent()) {
            ProductoPrecioDTO cliente = clienteEncontrado.get();
            assertEquals(productoPrecioEjemplo.getId(), cliente.getId());

        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
         @Test
    public void sePuedeModificarUnProductoPrecioCorrectamente() {
 
       productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);
        
                ProductoPrecioDTO productoPrecioEjemplo2 = new ProductoPrecioDTO(); 
                productoPrecioEjemplo2.setDescuentoMaximo(0.5);
                productoPrecioEjemplo2.setEstado(false);

        Optional<ProductoPrecioDTO> productoPrecioEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());
        System.out.println(productoPrecioEjemplo.getId());
        
        
        
        if(productoPrecioEncontrado.isPresent()) {
            productoPrecioEjemplo = productoPrecioService.update(productoPrecioEjemplo.getId(),productoPrecioEjemplo2);
            
            assertEquals(productoPrecioEjemplo.getDescuentoMaximo(), productoPrecioEjemplo2.getDescuentoMaximo());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
     @Test
    public void sePuedeEliminarUnProductoPrecioCorrectamente() {
        
         productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);
         
        Optional<ProductoPrecioDTO> productoPrecioEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());

        if (productoPrecioEncontrado.isPresent()) {
            ProductoPrecioDTO productoPrecio = productoPrecioEncontrado.get();
            

            productoPrecioService.delete2(productoPrecioEncontrado.get().getId());
             
            assertEquals(productoPrecioEjemplo.getId(), productoPrecio.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    @AfterEach
    public void tearDown() {
        if (productoPrecioEjemplo != null) {
            productoPrecioService.delete(productoPrecioEjemplo.getId());
            productoPrecioEjemplo = null;
        }
    }
}
