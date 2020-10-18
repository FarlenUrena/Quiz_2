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
    public void sePuedeCrearUnProductoCorrectamente() {
 
        productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);

        Optional<ProductoPrecioDTO> productoPrecioEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());

        if (productoPrecioEncontrado.isPresent()) {
            ProductoPrecioDTO productoPrecio = productoPrecioEncontrado.get();
            assertEquals(productoPrecioEjemplo.getId(), productoPrecio.getId());

        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    @Test
    public void sePuedeModificarUnProductoCorrectamente() {
 
        String modificar = "Se modificó el productoPrecio";
        productoPrecioEjemplo = productoPrecioService.create(productoPrecioEjemplo);

        Optional<ProductoPrecioDTO> productoPrecioEncontrado = productoPrecioService.findById(productoPrecioEjemplo.getId());
        if(productoPrecioEncontrado.isPresent()&& productoPrecioEncontrado.get().getDescuentoMaximo() != productoPrecioEjemplo.getDescuentoMaximo()) {
            productoPrecioEjemplo.setDescuentoMaximo(1);
            ProductoPrecioDTO productoPrecio = productoPrecioEncontrado.get();

            System.out.println(productoPrecioEncontrado.get().getDescuentoMaximo());
            System.out.println(productoPrecioEjemplo.getDescuentoMaximo());

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
