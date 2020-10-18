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
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;
import org.una.tienda.facturacion.entities.ProductoExistencia;
import org.una.tienda.facturacion.repositories.IProductoExistenciaRepository;
import org.una.tienda.facturacion.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@Service
public class ProductoExistenciaServiceImplementation  implements IProductoExistenciaService{
    @Autowired
    private IProductoExistenciaRepository productoRepository;

    private Optional<ProductoExistenciaDTO> oneToDto(Optional<ProductoExistencia> one) {
        if (one.isPresent()) {
            ProductoExistenciaDTO ProductoDTO = MapperUtils.DtoFromEntity(one.get(),   ProductoExistenciaDTO.class);
            return Optional.ofNullable(ProductoDTO);
        } else {
            return null;
        }
    }

   @Override
    @Transactional(readOnly = true)
    public Optional<ProductoExistenciaDTO> findById(Long id) {
        return oneToDto(productoRepository.findById(id));
    }

    @Override
    @Transactional
    public ProductoExistenciaDTO create(ProductoExistenciaDTO ProductoDTO) {
        ProductoExistencia productoExistencia = MapperUtils.EntityFromDto(ProductoDTO, ProductoExistencia.class);
        productoExistencia = productoRepository.save(productoExistencia);
        return MapperUtils.DtoFromEntity(productoExistencia, ProductoExistenciaDTO.class);
    }
    
   @Override
    @Transactional
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
    
}
