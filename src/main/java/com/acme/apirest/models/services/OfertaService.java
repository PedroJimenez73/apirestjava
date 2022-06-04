package com.acme.apirest.models.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.apirest.models.dao.IOfertaDAO;
import com.acme.apirest.models.entity.Oferta;

@Service
public class OfertaService implements IOfertaService {

	@Autowired
	private IOfertaDAO ofertaDAO;
	@Override
	@Transactional(readOnly=true)
	public List<Oferta> findAll() {
		return (List<Oferta>) ofertaDAO.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Oferta findById(UUID id) {
		return ofertaDAO.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Oferta> findByArticuloId(String articuloId) {
		return ofertaDAO.findByArticuloId(articuloId);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Oferta> findByProveedorId(String proveedorId) {
		return ofertaDAO.findByProveedorId(proveedorId);
	}
	@Override
	@Transactional()
	public Oferta save(Oferta oferta) {
		return ofertaDAO.save(oferta);
	}
	@Override
	@Transactional()
	public void delete(UUID id) {
		ofertaDAO.deleteById(id);
	}

}
