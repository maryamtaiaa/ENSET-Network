package org.sid.dao;

import java.util.List;

import org.sid.entities.Cour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CourRepository extends JpaRepository<Cour, Long> {
	
	public Page<Cour> findByTitleContains(String mc, Pageable pageable);
	public Cour findByName(String name);
	@Query("select u from Cour u where u.type.idTypeCour like :x")  
	public List<Cour> findByid_TypeCour(@Param("x") Long id);


}
