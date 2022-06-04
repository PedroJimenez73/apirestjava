package com.acme.apirest.models.services;

import com.acme.apirest.models.entity.Proveedor;
import java.util.List;
import java.util.UUID;

public interface IProveedorService {
    
    public List<Proveedor> findAll();
	
	public Proveedor findById(UUID id);

    public List<Proveedor> findByNombreStartsWith(String term);
	
	public Proveedor save(Proveedor proveedor);
	
	public void delete(UUID id);
}
