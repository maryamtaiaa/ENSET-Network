package org.sid.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.sid.entities.Filiere;



public interface FiliereRepository extends JpaRepository<Filiere, Long>{
	public Page<Filiere> findByNomContains(String mc, Pageable pageable);
	 @Query("select r from Filiere r where r.nom like :x")  
		public Filiere findByNom(@Param("x") String id);
}
