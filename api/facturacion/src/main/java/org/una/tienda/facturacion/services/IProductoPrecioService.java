/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;


import java.util.Optional;
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;


/**
 *
 * @author thony
 */
public interface IProductoPrecioService {
    public Optional<ProductoPrecioDTO> findById(Long id);
    public ProductoPrecioDTO create(ProductoPrecioDTO ProductoDTO);
    public void delete(Long id);

   public ProductoPrecioDTO update(Long id,ProductoPrecioDTO productoPrecioDTO);

    public void delete2(Long id);
}