package org.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.sid.entities.RS;


public interface RSRepository extends JpaRepository<RS, Long>{
	 @Query("select r from RS r where r.profil.idProfil like :x")  
		public List<RS>findByid_Profil(@Param("x") Long id);

}
