package com.acme.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="articulos")
public class Articulo implements Serializable {
    
    @Id // Indica que es el identificador de la entidad
    @GeneratedValue // Indica que el valor del identificador es autogenerado
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(length = 4, unique = true, nullable = false) // Indica que el campo es de tipo String y es unico
    private String sku;
    @Column(nullable = false) // Indica que el campo es obligatorio
    private String marca;
    @Column(nullable = false)
    private String modelo;
	private String descripcion;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;
    @Column(name="created_at", nullable = false)
	// @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();

	@JsonIgnoreProperties(value={"articulo", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<Oferta> ofertas;

    public Articulo() {
        this.ofertas = new ArrayList<>();
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
	public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Genero getGenero() {
		return genero;
	}



	public void setGenero(Genero genero) {
		this.genero = genero;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	private static final long serialVersionUID = 1L;
}
