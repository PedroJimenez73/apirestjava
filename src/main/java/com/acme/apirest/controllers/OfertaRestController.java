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

import com.acme.apirest.models.entity.Oferta;
import com.acme.apirest.models.services.IOfertaService;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class OfertaRestController {
	
	@Autowired
	private IOfertaService ofertaService;

    @GetMapping("/ofertas")
	public List<Oferta> index() {
		return ofertaService.findAll();
	}

	@GetMapping("/ofertas/{id}")
	public Oferta show(@PathVariable UUID id) {
		return ofertaService.findById(id);
	}

	@GetMapping("/ofertas/articulo/{articuloId}")
	public List<Oferta> showByArticle(@PathVariable String articuloId) {
		return ofertaService.findByArticuloId(articuloId);
	}

	@GetMapping("/ofertas/proveedor/{proveedorId}")
	public List<Oferta> showByProveedor(@PathVariable String proveedorId) {
		return ofertaService.findByProveedorId(proveedorId);
	}
	
	@PostMapping("/ofertas")
	@ResponseStatus(HttpStatus.CREATED)
    public Oferta create(@RequestBody Oferta oferta) {
        return ofertaService.save(oferta);
    }

	// public ResponseEntity<?> create(@RequestBody Oferta oferta) {
	// 	Oferta newOferta = null;
	// 	Map<String, Object> response = new HashMap<>();
	// 	try {
	// 		oferta.setCreatedAt(new Date());
	// 		newOferta = ofertaService.save(oferta);
	// 	} catch (DataAccessException e) {
	// 		if (e.getMostSpecificCause().getMessage().contains("Duplicate entry")) {
	// 			response.put("message", "Duplicate SKU code");
	// 			response.put("code", "validation-1010");
	// 			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	// 		} else {	
	// 			response.put("message", "Database server not available");
	// 			response.put("code", "db-2020");
	// 			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	// 		}
	// 	}
	// 	response.put("message", "Oferta was created succesfully");
	// 	response.put("oferta", newOferta);
	// 	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	// }
	
	@PutMapping("/ofertas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Oferta update(@RequestBody Oferta oferta, @PathVariable UUID id) {
		Oferta ofertaPrev = ofertaService.findById(id);
		ofertaPrev.setProveedor(oferta.getProveedor());
		// ofertaPrev.setArticulo(oferta.getArticulo());
		ofertaPrev.setPrecio(oferta.getPrecio());
		ofertaPrev.setDiasEntrega(oferta.getDiasEntrega());
		return ofertaService.save(ofertaPrev);
	}
	
	@DeleteMapping("/ofertas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		ofertaService.delete(id);
	}
	
}
