/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.Optional;
import org.una.tienda.facturacion.dto.ProductoDTO;

/**
 *
 * @author thony
 */
public interface IProductoService {
    public Optional<ProductoDTO> findById(Long id);
    public ProductoDTO create(ProductoDTO ProductoDTO);
    // public ProductoDTO update(ProductoDTO ProductoDTO, Long id);
    public void delete(Long id);
}