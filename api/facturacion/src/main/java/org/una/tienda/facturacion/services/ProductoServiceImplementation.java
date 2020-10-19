/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;


import org.una.tienda.facturacion.dto.ProductoDTO;
import org.una.tienda.facturacion.entities.Producto;
import org.una.tienda.facturacion.repositories.IProductoRepository;
import org.una.tienda.facturacion.utils.MapperUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author thony
 */
@Service
public class ProductoServiceImplementation implements IProductoService {
    
    @Autowired
    private IProductoRepository productoRepository;

    private Optional<ProductoDTO> oneToDto(Optional<Producto> one) {
        if (one.isPresent()) {
            ProductoDTO ProductoDTO = MapperUtils.DtoFromEntity(one.get(),   ProductoDTO.class);
            return Optional.ofNullable(ProductoDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> findById(Long id) {
        return oneToDto(productoRepository.findById(id));
    }

    @Override
    @Transactional
    public ProductoDTO create(ProductoDTO ProductoDTO) {
        Producto producto = MapperUtils.EntityFromDto(ProductoDTO, Producto.class);
        producto = productoRepository.save(producto);
        return MapperUtils.DtoFromEntity(producto, ProductoDTO.class);
    }
    
   @Override
    @Transactional
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductoDTO update(Long id,ProductoDTO productoDTO) {
        
        if(productoRepository.findById(id).isPresent()){
        Producto producto;
        Producto productoActualizado = MapperUtils.EntityFromDto(productoDTO, Producto.class);
        producto = productoActualizado;
        producto = productoRepository.save(producto);
        return MapperUtils.DtoFromEntity(producto, ProductoDTO.class);
        }else {
            return null;
        }
    
    }

    @Override
    @Transactional
    public void delete2(Long id) {
    oneToDto(productoRepository.findById(id));
    }
}