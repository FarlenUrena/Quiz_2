/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author thony
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString

public class ProductoPrecioDTO {
    private Long id; 
    private double descuentoMaximo;
    private double descuentoPromocional;
    private boolean estado; 
    private Date fechaModificacion;
    private Date fechaRegistro;  
    private double precioColones;
    private ProductoDTO producto;
}