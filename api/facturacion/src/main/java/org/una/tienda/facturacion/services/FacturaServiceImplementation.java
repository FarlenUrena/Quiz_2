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
import org.una.tienda.facturacion.dto.FacturaDTO;
import org.una.tienda.facturacion.entities.Factura;
import org.una.tienda.facturacion.repositories.IFacturaRepository;
import org.una.tienda.facturacion.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@Service
public class FacturaServiceImplementation implements IFacturaService {
    @Autowired
    private IFacturaRepository facturaRepository;

    private Optional<FacturaDTO> oneToDto(Optional<Factura> one) {
        if (one.isPresent()) {
            FacturaDTO FacturaDTO = MapperUtils.DtoFromEntity(one.get(),   FacturaDTO.class);
            return Optional.ofNullable(FacturaDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FacturaDTO> findById(Long id) {
        return oneToDto(facturaRepository.findById(id));
    }

    @Override
    @Transactional
    public FacturaDTO create(FacturaDTO FacturaDTO) {
        Factura factura = MapperUtils.EntityFromDto(FacturaDTO, Factura.class);
        factura = facturaRepository.save(factura);
        return MapperUtils.DtoFromEntity(factura, FacturaDTO.class);
    }
    
   @Override
    @Transactional
    public void delete(Long id) {
        facturaRepository.deleteById(id);
    }
    
}
