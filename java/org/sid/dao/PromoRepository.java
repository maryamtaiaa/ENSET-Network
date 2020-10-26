package org.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.sid.entities.Promo;


public interface PromoRepository extends JpaRepository<Promo, Long>{
    @Query("select u from Promo u where u.filiere.idFiliere like :x")  
	public List<Promo> findByid_Filiere(@Param("x") Long id);

}
