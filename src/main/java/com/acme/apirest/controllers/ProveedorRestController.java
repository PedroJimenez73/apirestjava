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

import com.acme.apirest.models.entity.Proveedor;
import com.acme.apirest.models.services.IProveedorService;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProveedorRestController {
	
	@Autowired
	private IProveedorService proveedorService;
	
	@GetMapping("/proveedores")
	public List<Proveedor> index() {
		return proveedorService.findAll();
	}
	
	@GetMapping("/proveedores/{id}")
	public Proveedor show(@PathVariable UUID id) {
		return proveedorService.findById(id);
	}

	@GetMapping("/proveedores/search/{term}")
	public List<Proveedor> show(@PathVariable String term) {
		return proveedorService.findByNombreStartsWith(term);
	}
	
	@PostMapping("/proveedores")
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Proveedor proveedor) {
		Proveedor newProveedor = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedor.setCreatedAt(new Date());
			newProveedor = proveedorService.save(proveedor);
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
		response.put("message", "Proveedor was created succesfully");
		response.put("proveedor", newProveedor);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/proveedores/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Proveedor update(@RequestBody Proveedor proveedor, @PathVariable UUID id) {
		Proveedor proveedorPrev = proveedorService.findById(id);
		proveedorPrev.setCif(proveedor.getCif());
		proveedorPrev.setNombre(proveedor.getNombre());
		proveedorPrev.setDireccion(proveedor.getDireccion());
		proveedorPrev.setLocalidad(proveedor.getLocalidad());
		proveedorPrev.setEmail(proveedor.getEmail());
		proveedorPrev.setTelefono(proveedor.getTelefono());
		return proveedorService.save(proveedorPrev);
	}
	
	@DeleteMapping("/proveedores/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		proveedorService.delete(id);
	}
	

}
