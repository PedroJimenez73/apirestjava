package com.acme.apirest.models.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.acme.apirest.models.entity.Articulo;

public interface IArticuloDAO extends CrudRepository<Articulo, UUID> {
    @Query(value = "SELECT * FROM articulos WHERE marca LIKE :marcaTerm%", nativeQuery = true)
    public List<Articulo> searchArticuloByMarca(String marcaTerm);
    @Query(value = "SELECT * FROM articulos WHERE modelo LIKE :modeloTerm%", nativeQuery = true)
    public List<Articulo> searchArticuloByModelo(String modeloTerm);
    @Query(value = "SELECT * FROM articulos WHERE marca LIKE :marcaTerm% AND modelo LIKE :modeloTerm%", nativeQuery = true)
    public List<Articulo> searchArticuloByMarcaAndModelo(String marcaTerm, String modeloTerm);
}
    
