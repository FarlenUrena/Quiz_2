/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.ClienteDTO;
import org.una.tienda.facturacion.dto.FacturaDTO;
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;
import org.una.tienda.facturacion.dto.ProductoDTO;
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;
import org.una.tienda.facturacion.exceptions.ProductoConDescuentoMayorAlPermitidoException;

/**
 *
 * @author erikg
 */
@SpringBootTest
public class FacturaDetalleServiceImplementationTests {
    @Autowired
    private IFacturaService facturaService;
    
    @Autowired
    private IFacturaDetalleService facturaDetalleService;
    
    @Autowired
    private IProductoService productoService; 
    
    @Autowired
    private IProductoExistenciaService productoExistenciaService;
    
    @Autowired
    private IProductoPrecioService productoPrecioService;
    
    @Autowired
    private IClienteService clienteService;
    
    FacturaDTO facturaPrueba;
    
    FacturaDetalleDTO facturaDetalleEjemplo;
    
    FacturaDetalleDTO facturaDetallePruebaConExtraDescuento;
    
    ProductoDTO productoPrueba;
    
    ProductoExistenciaDTO productoExistenciaPrueba;
    
    ProductoPrecioDTO productoPrecioPrueba;
    
    ClienteDTO clientePrueba;
    
    private void initDataForSeEvitaFacturarUnProductoConDescuentoMayorAlPermitido() {
        productoPrueba = new ProductoDTO() {
            {
                setDescripcion("Producto de ejemplo.");
                setImpuesto(0.10);
            }
        };
        
        assertThrows(ProductoConDescuentoMayorAlPermitidoException.class, () -> {
        productoPrueba = productoService.create(productoPrueba);
        });        

        productoExistenciaPrueba = new ProductoExistenciaDTO() {
            {
                setProducto(productoPrueba);
                setCantidad(1);
            }
        };
        
        assertThrows(ProductoConDescuentoMayorAlPermitidoException.class, () -> {
        productoExistenciaPrueba = productoExistenciaService.create(productoExistenciaPrueba);
        });
        
        productoPrecioPrueba = new ProductoPrecioDTO() {
            {
                setProducto(productoPrueba);
                setPrecioColones(1000);
                setDescuentoMaximo(10);
                setDescuentoPromocional(2);
            }
        };
        
        assertThrows(ProductoConDescuentoMayorAlPermitidoException.class, () -> {
        productoPrecioPrueba = productoPrecioService.create(productoPrecioPrueba);
        });

        clientePrueba = new ClienteDTO() {
            {
                setNombre("ClienteDePrueba");
            }
        };
        
        assertThrows(ProductoConDescuentoMayorAlPermitidoException.class, () -> {
        clientePrueba = clienteService.create(clientePrueba);
        });

        facturaPrueba = new FacturaDTO() {
            {
                setCaja(991);
                setCliente(clientePrueba);
            }
        };
        
        assertThrows(ProductoConDescuentoMayorAlPermitidoException.class, () -> {
        facturaPrueba = facturaService.create(facturaPrueba);
        });

        facturaDetallePruebaConExtraDescuento = new FacturaDetalleDTO() {
            {
                setCantidad(1);
                setProducto(productoPrueba);
                setFactura(facturaPrueba);
                setDescuentoFinal(productoPrecioPrueba.getDescuentoMaximo() + 1);
            }
        };
    }
    
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
    public void sePuedeCrearUnFacturaDetalleCorrectamente() throws ProductoConDescuentoMayorAlPermitidoException {
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
    public void sePuedeEliminarUnFacturaDetalleCorrectamente() throws ProductoConDescuentoMayorAlPermitidoException {
        facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
                 
        Optional<FacturaDetalleDTO> facturaDetalleEncontrado = facturaDetalleService.findById(facturaDetalleEjemplo.getId());

        if (facturaDetalleEncontrado.isPresent()) {
            FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrado.get();
            
            // facturaDetalleService.delete(facturaDetalleEjemplo.getId());
            facturaDetalleService.delete2(facturaDetalleEncontrado.get().getId());
             
            assertEquals(facturaDetalleEjemplo.getId(), facturaDetalle.getId());
        } else {
            fail("No se encontró la información en la BD");
        }
    }
    
    /*
    @Test
    public void seEvitaFacturarUnProductoConDescuentoMayorAlPermitido() throws ProductoConDescuentoMayorAlPermitidoException {
        initDataForSeEvitaFacturarUnProductoConDescuentoMayorAlPermitido();
        
        facturaDetalleService.create(facturaDetallePruebaConExtraDescuento);
    }
    */
    
    @AfterEach
    public void tearDown() {
        if (facturaDetalleEjemplo != null) {
            facturaDetalleService.delete(facturaDetalleEjemplo.getId());
            facturaDetalleEjemplo = null;
        }
    }
}