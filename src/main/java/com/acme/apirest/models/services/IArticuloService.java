package com.acme.apirest.models.services;

import com.acme.apirest.models.entity.Articulo;
import java.util.List;
import java.util.UUID;

public interface IArticuloService {
    public List<Articulo> findAll();
	
	public Articulo findById(UUID id);

    public List<Articulo> findByModeloStartsWith(String term);
	
	public Articulo save(Articulo articulo);
	
	public void delete(UUID id);
}
