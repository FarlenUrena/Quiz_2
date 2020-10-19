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
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;
import org.una.tienda.facturacion.entities.ProductoPrecio;
import org.una.tienda.facturacion.repositories.IProductoPrecioRepository;
import org.una.tienda.facturacion.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@Service
public class ProductoPrecioServiceImplementation implements IProductoPrecioService{
    @Autowired
    private IProductoPrecioRepository productoPrecioRepository;

    private Optional<ProductoPrecioDTO> oneToDto(Optional<ProductoPrecio> one) {
        if (one.isPresent()) {
            ProductoPrecioDTO ProductoDTO = MapperUtils.DtoFromEntity(one.get(),   ProductoPrecioDTO.class);
            return Optional.ofNullable(ProductoDTO);
        } else {
            return null;
        }
    }

   @Override
    @Transactional(readOnly = true)
    public Optional<ProductoPrecioDTO> findById(Long id) {
        return oneToDto(productoPrecioRepository.findById(id));
    }

    @Override
    @Transactional
    public ProductoPrecioDTO create(ProductoPrecioDTO ProductoDTO) {
        ProductoPrecio productoPrecio = MapperUtils.EntityFromDto(ProductoDTO, ProductoPrecio.class);
        productoPrecio = productoPrecioRepository.save(productoPrecio);
        return MapperUtils.DtoFromEntity(productoPrecio, ProductoPrecioDTO.class);
    }
    
   @Override
    @Transactional
    public void delete(Long id) {
        productoPrecioRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public ProductoPrecioDTO update(Long id, ProductoPrecioDTO productoPrecioDTO) {
    if(productoPrecioRepository.findById(id).isPresent()){
        ProductoPrecio productoPrecio;
        ProductoPrecio productoPrecioActualizado = MapperUtils.EntityFromDto(productoPrecioDTO, ProductoPrecio.class);
        productoPrecio = productoPrecioActualizado;
        productoPrecio = productoPrecioRepository.save(productoPrecio);
        return MapperUtils.DtoFromEntity(productoPrecio, ProductoPrecioDTO.class);
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete2(Long id) {
    oneToDto(productoPrecioRepository.findById(id));
    }
    
}
