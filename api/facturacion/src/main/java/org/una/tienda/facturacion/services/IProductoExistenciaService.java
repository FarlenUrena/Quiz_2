/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.List;
import java.util.Optional;
import org.una.tienda.facturacion.entities.ProductoExistencia;

/**
 *
 * @author thony
 */
public interface IProductoExistenciaService {
    public ProductoExistencia create(ProductoExistencia productoExistencia);
    public Optional<ProductoExistencia> update(ProductoExistencia productoExistencia, Long id);
    public void delete(Long id);
    public void deleteAll();
    public Optional<ProductoExistencia> findById(Long id);
    public Optional<List<ProductoExistencia>> findAll();
}