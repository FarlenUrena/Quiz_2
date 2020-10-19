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
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;

/**
 *
 * @author erikg
 */
@SpringBootTest
public class ProductoExistenciaServiceImplementationTests {
    @Autowired
    private IProductoExistenciaService productoExistenciaService;

    ProductoExistenciaDTO productoExistenciaEjemplo;
    
       @BeforeEach
    public void setup() {
        productoExistenciaEjemplo = new ProductoExistenciaDTO() {
            {
                setCantidad(1);
                setEstado(true);
            }
        };
    }
     @Test
    public void sePuedeCrearUnProductoExistenciaCorrectamente() {
 
        productoExistenciaEjemplo = productoExistenciaService.create(productoExistenciaEjemplo);

        Optional<ProductoExistenciaDTO> productoEncontrado = productoExistenciaService.findById(productoExistenciaEjemplo.getId());

        if (productoEncontrado.isPresent()) {
            ProductoExistenciaDTO producto = productoEncontrado.get();
            assertEquals(productoExistenciaEjemplo.getId(), producto.getId());

        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    @Test
    public void sePuedeModificarUnProductoExistenciaCorrectamente() {
 
       productoExistenciaEjemplo = productoExistenciaService.create(productoExistenciaEjemplo);
        
                ProductoExistenciaDTO productoExistenciaEjemplo2 = new ProductoExistenciaDTO(); 
                productoExistenciaEjemplo2.setCantidad(4);
                productoExistenciaEjemplo2.setEstado(false);

        Optional<ProductoExistenciaDTO> productoExistenciaEncontrado = productoExistenciaService.findById(productoExistenciaEjemplo.getId());
        System.out.println(productoExistenciaEjemplo.getId());
        
        
        
        if(productoExistenciaEncontrado.isPresent()) {
            productoExistenciaEjemplo = productoExistenciaService.update(productoExistenciaEjemplo.getId(),productoExistenciaEjemplo2);
            
            assertEquals(productoExistenciaEjemplo.getCantidad(), productoExistenciaEjemplo2.getCantidad());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
     @Test
    public void sePuedeEliminarUnProductoExistenciaCorrectamente() {
        
         productoExistenciaEjemplo = productoExistenciaService.create(productoExistenciaEjemplo);
         
        Optional<ProductoExistenciaDTO> productoExistenciaEncontrado = productoExistenciaService.findById(productoExistenciaEjemplo.getId());

        if (productoExistenciaEncontrado.isPresent()) {
            ProductoExistenciaDTO productoExistencia = productoExistenciaEncontrado.get();
            
//          productoExistenciaService.delete(productoExistenciaEjemplo.getId());
            productoExistenciaService.delete2(productoExistenciaEncontrado.get().getId());
             
            assertEquals(productoExistenciaEjemplo.getId(), productoExistencia.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    @AfterEach
    public void tearDown() {
        if (productoExistenciaEjemplo != null) {
            productoExistenciaService.delete(productoExistenciaEjemplo.getId());
            productoExistenciaEjemplo = null;
        }
    }
}
