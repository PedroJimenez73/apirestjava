package com.acme.apirest.models.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.acme.apirest.models.entity.Oferta;

public interface IOfertaDAO extends CrudRepository<Oferta, UUID> {
      @Query(value = "SELECT * FROM ofertas WHERE articulo_id = ?1", nativeQuery = true)
      public List<Oferta> findByArticuloId(String articuloId);
      @Query(value = "SELECT * FROM ofertas WHERE proveedor_id = ?1", nativeQuery = true)
      public List<Oferta> findByProveedorId(String proveedorId);
}
    
