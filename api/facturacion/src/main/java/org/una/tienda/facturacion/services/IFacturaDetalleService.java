/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;


import java.util.Optional;
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;


/**
 *
 * @author thony
 */
public interface IFacturaDetalleService {
public Optional<FacturaDetalleDTO> findById(Long id);
    public FacturaDetalleDTO create(FacturaDetalleDTO FacturaDetalleDTO);
    public void delete(Long id);
}