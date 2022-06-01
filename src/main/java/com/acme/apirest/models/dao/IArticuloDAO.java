package com.acme.apirest.models.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.acme.apirest.models.entity.Articulo;

public interface IArticuloDAO extends CrudRepository<Articulo, UUID> {
    public List<Articulo> findByModeloStartsWith(String term);
}
    
