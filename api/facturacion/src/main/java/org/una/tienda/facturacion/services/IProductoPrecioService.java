/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.List;
import java.util.Optional;
import org.una.tienda.facturacion.entities.ProductoPrecio;

/**
 *
 * @author thony
 */
public interface IProductoPrecioService {
    public ProductoPrecio create(ProductoPrecio productoPrecio);
    public Optional<ProductoPrecio> update(ProductoPrecio productoPrecio, Long id);
    public void delete(Long id);
    public void deleteAll();
    public Optional<ProductoPrecio> findById(Long id);
    public Optional<List<ProductoPrecio>> findAll();
}