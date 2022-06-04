package com.acme.apirest.models.services;

import com.acme.apirest.models.entity.Oferta;
import java.util.List;
import java.util.UUID;

public interface IOfertaService {

    public List<Oferta> findAll();
	
    public Oferta findById(UUID id);

    public List<Oferta> findByArticuloId(String articuloId);
    public List<Oferta> findByProveedorId(String proveedorId);
	
	public Oferta save(Oferta oferta);
	
	public void delete(UUID id);
}
