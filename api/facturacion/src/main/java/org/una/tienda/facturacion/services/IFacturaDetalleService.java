/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.List;
import java.util.Optional;
import org.una.tienda.facturacion.entities.FacturaDetalle;

/**
 *
 * @author thony
 */
public interface IFacturaDetalleService {
    public FacturaDetalle create(FacturaDetalle facturaDetalle);
    public Optional<FacturaDetalle> update(FacturaDetalle facturaDetalle, Long id);
    public void delete(Long id);
    public void deleteAll();
    public Optional<FacturaDetalle> findById(Long id);
    public Optional<List<FacturaDetalle>> findAll();
}