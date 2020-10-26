package org.sid.dao;

import org.sid.entities.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;




public interface AdminRepository extends JpaRepository<Admin, Long>{
	//@Query("select a from Admin a where a.nom like :x or a.prenom like :x or a.login.username like :x")
	//public Page<Admin> chercherAdmin(@Param("x")String mc, Pageable pageable);
	public Page<Admin> findByNomContains(String mc, Pageable pageable);


}
