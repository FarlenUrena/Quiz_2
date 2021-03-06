/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "ut_productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Producto  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100, name = "descripcion")
    private String descripcion;
    
    @Column
    private boolean estado;
    
    @Column(name = "fecha_modificacion", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    
    @Column(name = "impuesto")
    private double impuesto;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto") 
    private List<FacturaDetalle> facturaDetalle = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto") 
    private List<ProductoExistencia> productoExistencia = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto") 
    private List<ProductoPrecio> productoPrecio = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;
    
    @PrePersist
    public void prePersist() {
        estado=true;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }
}