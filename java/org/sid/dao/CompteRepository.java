package org.sid.dao;

import org.sid.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface CompteRepository extends JpaRepository<Compte, Long>{
	@Query("SELECT c FROM Compte c WHERE c.CIN = ?1 and c.CNE = ?2")
	Compte findCompteByCINandCNE(String cin, String cne);
}
