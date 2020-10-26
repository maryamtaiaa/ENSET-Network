package com.pi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pi.dao.FiliereRepository;
import com.pi.dao.ProfilRepository;
import com.pi.dao.PromoRepository;
import com.pi.dao.UtilisateurRepository;
import com.pi.entities.Filiere;
import com.pi.entities.Promo;

@SpringBootApplication
public class ProjectGroupApplication implements CommandLineRunner{
	
	@Autowired
	UtilisateurRepository  utilisateurRepository;
	@Autowired 
	ProfilRepository profileRepository;
	@Autowired 
	FiliereRepository filiereRepository;
	@Autowired
	PromoRepository promoRepository;
	public static void main(String[] args) {
		
		SpringApplication.run(ProjectGroupApplication.class, args);
		
		   
		  
	}
	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * List<Utilisateur> utls = utilisateurRepository.findByid_Promo((long)
		 * 1,"GLSID");
		 * 
		 * System.out.println("users"+utls); utls.forEach(e -> {
		 * System.out.println(e.getProfil().getIdPrf()); });
		 * 
		 * Profil prf = profileRepository.findById((long) 1).get();
		 * 
		 * System.out.println(prf.getDescription()); Profil pr =
		 * profileRepository.findByDescription("%ELOUAHDANI");
		 * System.out.println(prf.getDescription());
		 */
		/*
		 * Filiere filiere = filiereRepository.findByNom("GLSID"); List<Promo> ps =
		 * promoRepository.findByid_Filiere(filiere.getId()); ps.forEach(e -> {
		 * System.out.println(e.getId()); });
		 */
		 
		
	}

}
