package org.sid.rest;

import java.util.List;
import java.util.Optional;

import org.sid.dao.DepartementRepository;
import org.sid.entities.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departement")
public class DepartementRest {
	@Autowired
	private DepartementRepository dr;
	
	@GetMapping
	public List<Departement> findAll(){
		return dr.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Departement> getOne(@PathVariable Long id) {
		return dr.findById(id);
	}

}
