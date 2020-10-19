/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.services;

import java.util.Optional;
import org.una.tienda.facturacion.dto.ClienteDTO;

/**
 *
 * @author thony
 */
public interface IClienteService {
 public Optional<ClienteDTO> findById(Long id);
    public ClienteDTO create(ClienteDTO ClienteDTO);
    public void delete(Long id);

    public ClienteDTO update(Long id,ClienteDTO productoDTO);

    public void delete2(Long id);
}