package org.sid;

import java.util.List;
import java.util.Optional;

import javax.sound.midi.Soundbank;

import org.sid.dao.AdminRepository;
import org.sid.dao.EcoleRepository;
import org.sid.dao.FiliereRepository;
import org.sid.dao.LoginRepository;
import org.sid.dao.ProfilRepository;
import org.sid.dao.PromoRepository;
import org.sid.dao.RSRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Admin;
import org.sid.entities.Ecole;
import org.sid.entities.Filiere;
import org.sid.entities.Login;
import org.sid.entities.Profil;
import org.sid.entities.Promo;
import org.sid.entities.RS;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupePersonnelApplication  implements CommandLineRunner {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	ProfilRepository profileRepository;
	@Autowired
	EcoleRepository ecoleRepository;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	RSRepository rsRepository;
	@Autowired
	FiliereRepository filierRepository;
	@Autowired
	PromoRepository promoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GroupePersonnelApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
	/*Profil prf = profileRepository.findById((long) 1).get();
	
	System.out.println(prf.getDescription());
	Profil pr = profileRepository.findByDescription("%ELOUAHDANI");
	System.out.println(prf.getDescription());
	Utilisateur utl = utilisateurRepository.findByid_Profil((long ) 1);
	System.out.println(utl.getNom());
	System.out.println(utl.getPrenom());
	System.out.println(utl.getProfil().getTelephone());
	System.out.println(utl.getCompte().getCIN());
	System.out.println(utl.getFiliere().getNom());
	System.out.println(utl.getPromo().getDat_ent());
	List<RS> rs  =  rsRepository.findByid_Profil((long) 1);
	rs.forEach(e -> {
		System.out.println(e.getLien());
	});
	Filiere filiere = filierRepository.findByNom("GLSID");
	
			System.out.println(filiere.getNom());
		
			Utilisateur ut = utilisateurRepository.findById((long) 4).get();
			System.out.println(utl.getProfil().getTelephone());	 
			String[] date_ent=utl.getPromo().getDat_ent().toString().split("-"); 
			 String dt_ent=date_ent[0];
			 String[] date_sort=utl.getPromo().getDat_srt().toString().split("-"); 
			 String	  dt_srt=date_sort[0]; 
			 String promo=dt_ent + "-" +dt_srt;
			 System.out.println(promo);
				String[]  aniv=utl.getCompte().getDateNaissance().toString().split("-");
		        String[] s = aniv[2].split(" ");
		    	String date_aniv=aniv[1] + "-" +s[0];
		    	System.out.println(date_aniv);
		    	System.out.println(ut.getProfil().getIdProfil());
		    	List<RS> reseau_sociaux = rsRepository.findByid_Profil(ut.getProfil().getIdProfil());
		    	reseau_sociaux.forEach(e -> {
		    		System.out.println(e.getLien());
		    		
		    	});*/
	}	
    

}
