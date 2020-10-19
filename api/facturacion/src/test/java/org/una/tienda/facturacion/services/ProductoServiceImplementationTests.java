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
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.ProductoDTO;

/**
 *
 * @author thony
 */
@SpringBootTest
public class ProductoServiceImplementationTests  {

    @Autowired
    private IProductoService productoService;

    ProductoDTO productoEjemplo;

    @BeforeEach
    public void setup() {
        productoEjemplo = new ProductoDTO() {
            {
                setDescripcion("Producto de ejemplo.");
                setImpuesto(0.10);
            }
        };
    }

    @Test
    public void sePuedeCrearUnProductoCorrectamente() {
 
        productoEjemplo = productoService.create(productoEjemplo);

        Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());

        if (productoEncontrado.isPresent()) {
            ProductoDTO producto = productoEncontrado.get();
            assertEquals(productoEjemplo.getId(), producto.getId());

        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    @Test
    public void sePuedeModificarUnProductoCorrectamente() {
        
         productoEjemplo = productoService.create(productoEjemplo);
        
                ProductoDTO productoEjemplo2 = new ProductoDTO(); 
                productoEjemplo2.setDescripcion("Producto actualizado.");
                productoEjemplo2.setImpuesto(0.20);

        Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
        System.out.println(productoEjemplo.getId());
        
        
        
        if(productoEncontrado.isPresent()) {
            productoEjemplo = productoService.update(productoEjemplo.getId(),productoEjemplo2);

            assertEquals(productoEjemplo.getDescripcion(), productoEjemplo2.getDescripcion());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    
    @Test
    public void sePuedeEliminarUnProductoCorrectamente() {
        
         productoEjemplo = productoService.create(productoEjemplo);
         
        Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());

        if (productoEncontrado.isPresent()) {
            ProductoDTO producto = productoEncontrado.get();
            
//            productoService.delete(productoEjemplo.getId());
            productoService.delete2(productoEncontrado.get().getId());
             
            assertEquals(productoEjemplo.getId(), producto.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    

    @AfterEach
    public void tearDown() {
        if (productoEjemplo != null) {
            productoService.delete(productoEjemplo.getId());
            productoEjemplo = null;
        }
    }
    
}