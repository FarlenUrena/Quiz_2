/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;
import org.una.tienda.facturacion.entities.FacturaDetalle;
import org.una.tienda.facturacion.repositories.IFacturaDetalleRepository;
import org.una.tienda.facturacion.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@Service
public class FacturaDetalleServiceImplementation implements IFacturaDetalleService {
    
    @Autowired
    private IFacturaDetalleRepository facturaDetalleRepository;

    private Optional<FacturaDetalleDTO> oneToDto(Optional<FacturaDetalle> one) {
        if (one.isPresent()) {
            FacturaDetalleDTO FacturacionDetalleDTO = MapperUtils.DtoFromEntity(one.get(),   FacturaDetalleDTO.class);
            return Optional.ofNullable(FacturacionDetalleDTO);
        } else {
            return null;
        }
    }

  @Override
    @Transactional(readOnly = true)
    public Optional<FacturaDetalleDTO> findById(Long id) {
        return oneToDto(facturaDetalleRepository.findById(id));
    }

    @Override
    @Transactional
    public FacturaDetalleDTO create(FacturaDetalleDTO FacturaDetalleDTO) {
        FacturaDetalle facturaDetalle = MapperUtils.EntityFromDto(FacturaDetalleDTO, FacturaDetalle.class);
        facturaDetalle = facturaDetalleRepository.save(facturaDetalle);
        return MapperUtils.DtoFromEntity(facturaDetalle, FacturaDetalleDTO.class);
    }
    
   @Override
    @Transactional
    public void delete(Long id) {
        facturaDetalleRepository.deleteById(id);
    }
    
}
