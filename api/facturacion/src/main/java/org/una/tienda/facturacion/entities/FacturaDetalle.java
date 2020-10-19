/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author thony
 */

@Entity
@Table(name = "ut_facturas_detalles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FacturaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cantidad")
    private double cantidad;
    
    @Column(name = "descuento_final")
    private double descuentoFinal;
    
    @Column
    private boolean estado;
    
    @Column(name = "fecha_modificacion", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    
    @ManyToOne 
    @JoinColumn(name = "facturas_id")
    private Factura factura;
    
    @ManyToOne 
    @JoinColumn(name = "productos_id")
    private Producto producto;
    
    private static final long serialVersionUID = 1L;
    
    @PrePersist
    public void prePersist() {
        estado = true;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }
}