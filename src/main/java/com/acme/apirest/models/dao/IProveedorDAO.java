package com.acme.apirest.models.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.acme.apirest.models.entity.Proveedor;

public interface IProveedorDAO extends CrudRepository<Proveedor, UUID> {
    public List<Proveedor> findByNombreStartsWith(String term);
}
    
