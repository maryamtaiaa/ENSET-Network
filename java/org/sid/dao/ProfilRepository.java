package org.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.sid.entities.Profil;



public interface ProfilRepository extends JpaRepository<Profil, Long>{
	
	 @Query("select p from Profil p where p.description like :x")   
		public Profil findByDescription(@Param("x") String mc);

}
