package com.acme.apirest.models.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.apirest.models.dao.IArticuloDAO;
import com.acme.apirest.models.entity.Articulo;

@Service
public class ArticuloService implements IArticuloService {

	@Autowired
	private IArticuloDAO articuloDAO;
	@Override
	@Transactional(readOnly=true)
	public List<Articulo> findAll() {

		return (List<Articulo>) articuloDAO.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Articulo findById(UUID id) {
		
		return articuloDAO.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Articulo> findByModeloStartsWith(String term) {
		return (List<Articulo>) articuloDAO.findByModeloStartsWith(term);
	}
	@Override
	@Transactional()
	public Articulo save(Articulo articulo) {
		
		return articuloDAO.save(articulo);
	}
	@Override
	@Transactional()
	public void delete(UUID id) {
		articuloDAO.deleteById(id);
	}

}
