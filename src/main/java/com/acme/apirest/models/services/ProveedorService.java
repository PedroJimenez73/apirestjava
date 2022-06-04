package com.acme.apirest.models.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.apirest.models.dao.IProveedorDAO;
import com.acme.apirest.models.entity.Proveedor;

@Service
public class ProveedorService implements IProveedorService {

	@Autowired
	private IProveedorDAO proveedorDAO;
	@Override
	@Transactional(readOnly=true)
	public List<Proveedor> findAll() {
		return (List<Proveedor>) proveedorDAO.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Proveedor findById(UUID id) {
		return proveedorDAO.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Proveedor> findByNombreStartsWith(String term) {
		return (List<Proveedor>) proveedorDAO.findByNombreStartsWith(term);
	}
	@Override
	@Transactional()
	public Proveedor save(Proveedor proveedor) {
		return proveedorDAO.save(proveedor);
	}
	@Override
	@Transactional()
	public void delete(UUID id) {
		proveedorDAO.deleteById(id);
	}

}
