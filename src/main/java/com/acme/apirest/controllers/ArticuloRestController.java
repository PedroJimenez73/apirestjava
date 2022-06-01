package com.acme.apirest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.apirest.models.entity.Articulo;
import com.acme.apirest.models.services.IArticuloService;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ArticuloRestController {
	
	@Autowired
	private IArticuloService articuloService;
	
	@GetMapping("/articulos")
	public List<Articulo> index() {
		return articuloService.findAll();
	}
	
	@GetMapping("/articulos/{id}")
	public Articulo show(@PathVariable UUID id) {
		return articuloService.findById(id);
	}

	@GetMapping("/articulos/search/{term}")
	public List<Articulo> show(@PathVariable String term) {
		return articuloService.findByModeloStartsWith(term);
	}
	
	@PostMapping("/articulos")
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Articulo articulo) {
		Articulo newArticulo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			articulo.setCreatedAt(new Date());
			newArticulo = articuloService.save(articulo);
		} catch (DataAccessException e) {
			if (e.getMostSpecificCause().getMessage().contains("Duplicate entry")) {
				response.put("message", "Duplicate SKU code");
				response.put("code", "validation-1010");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {	
				response.put("message", "Database server not available");
				response.put("code", "db-2020");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		response.put("message", "Article was created succesfully");
		response.put("articulo", newArticulo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/articulos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo update(@RequestBody Articulo articulo, @PathVariable UUID id) {
		Articulo articuloPrev = articuloService.findById(id);
		articuloPrev.setSku(articulo.getSku());
		articuloPrev.setMarca(articulo.getMarca());
		articuloPrev.setModelo(articulo.getModelo());
		articuloPrev.setDescripcion(articulo.getDescripcion());
		articuloPrev.setGenero(articulo.getGenero());
		return articuloService.save(articuloPrev);
	}
	
	@DeleteMapping("/articulos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		articuloService.delete(id);
	}
	

}
