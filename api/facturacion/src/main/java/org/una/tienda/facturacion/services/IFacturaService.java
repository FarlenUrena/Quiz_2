/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.List;
import java.util.Optional;
import org.una.tienda.facturacion.entities.Factura;

/**
 *
 * @author thony
 */
public interface IFacturaService {
    public Factura create(Factura factura);
    public Optional<Factura> update(Factura factura, Long id);
    public void delete(Long id);
    public void deleteAll();
    public Optional<Factura> findById(Long id);
    public Optional<List<Factura>> findAll();
}