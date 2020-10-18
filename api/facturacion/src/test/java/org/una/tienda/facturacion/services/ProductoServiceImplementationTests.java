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
 
        String modificar = "Se modificó el producto";
        productoEjemplo = productoService.create(productoEjemplo);

        Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
        if(productoEncontrado.isPresent()&& productoEncontrado.get().getDescripcion() != productoEjemplo.getDescripcion()) {
            productoEjemplo.setDescripcion(modificar);
            ProductoDTO producto = productoEncontrado.get();

            System.out.println(productoEncontrado.get().getDescripcion());
            System.out.println(productoEjemplo.getDescripcion());

            assertEquals(productoEjemplo.getId(), producto.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    /*
    @Test
    public void sePuedeEliminarUnProductoCorrectamente() {
 
        productoEjemplo = productoService.delete(productoEjemplo.getId());

        Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());

        if (productoEncontrado.isPresent()) {
            ProductoDTO producto = productoEncontrado.get();
            assertEquals(productoEjemplo.getId(), producto.getId());

        } else {
            fail("No se encontró la información en la BD");
        }
    }
    */

    @AfterEach
    public void tearDown() {
        if (productoEjemplo != null) {
            productoService.delete(productoEjemplo.getId());
            productoEjemplo = null;
        }
    }
    
}