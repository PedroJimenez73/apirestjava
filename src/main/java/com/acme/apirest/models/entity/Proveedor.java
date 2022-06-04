package com.acme.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Entidad de JPA
@Table(name="proveedores")
public class Proveedor implements Serializable {
    
    @Id 
    @GeneratedValue 
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(length = 9, unique = true, nullable = false) 
    private String cif;
    @Column(nullable = false) 
    private String nombre;
    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false)
	private String localidad;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefono;
    @Column(name="created_at", nullable = false)
	@Temporal(TemporalType.DATE)
    private Date createdAt;

	@JsonIgnoreProperties(value={"proveedor", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Oferta> ofertas;

    public Proveedor() {
        this.ofertas = new ArrayList<>();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCif() {
        return this.cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // public List<Oferta> getOfertas() {
    //     return ofertas;
    // }

    // public void setOfertas(List<Oferta> ofertas) {
    //     this.ofertas = ofertas;
    // }

	private static final long serialVersionUID = 1L;
}
