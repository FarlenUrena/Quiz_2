/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;


import java.util.Optional;
import org.una.tienda.facturacion.dto.FacturaDTO;

/**
 *
 * @author thony
 */
public interface IFacturaService {
   public Optional<FacturaDTO> findById(Long id);
    public FacturaDTO create(FacturaDTO FacturaoDTO);
    public void delete(Long id);
}