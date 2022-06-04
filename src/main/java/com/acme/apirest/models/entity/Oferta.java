package com.acme.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Entidad de JPA
@Table(name="ofertas")
public class Oferta implements Serializable {
    
    @Id 
    @GeneratedValue 
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(nullable = false) 
    private double precio;
    @Column(name = "dias_entrega", nullable = false) 
    private int diasEntrega;

    // Muchas ofertas están asociadas a un articulo
    @JsonIgnoreProperties(value={"ofertas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @JoinColumn(name = "articulo_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Articulo articulo;

    @JsonIgnoreProperties(value={"ofertas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @JoinColumn(name = "proveedor_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Proveedor proveedor;

    @Column(name="created_at")
    private Date createdAt = new Date();


    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDiasEntrega() {
        return this.diasEntrega;
    }

    public void setDiasEntrega(int diasEntrega) {
        this.diasEntrega = diasEntrega;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Articulo getArticulo() {
        return this.articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

	private static final long serialVersionUID = 1L;
}
