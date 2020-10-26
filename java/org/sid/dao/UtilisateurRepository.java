 package org.sid.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.sid.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	public Page<Utilisateur> findByNomContains(String mc, Pageable pageable);
	 @Query("select u from Utilisateur u where u.profil.idProfil like :x")  
		public Utilisateur findByid_Profil(@Param("x") Long id);
	     @Query("select u from Utilisateur u where u.promo.id like :x and u.filiere.nom like :y")  
	 	public List<Utilisateur> findByid_Promo(@Param("x") Long id,@Param("y") String nom);
	     @Query("select u from Utilisateur u where u.filiere.nom like :y")  
		 	public List<Utilisateur> findByFiliere(@Param("y") String nom);
	     @Query("select u from Utilisateur u where u.compte.idCompte = :x")  
	   	public Utilisateur findByIdCompte(@Param("x") int id);
}
