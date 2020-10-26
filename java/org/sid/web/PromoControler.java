package org.sid.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.sid.dao.*;
import org.sid.entities.*;


@Controller
public class PromoControler {
	@Autowired
	ProfilRepository profileRepository;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired 
	RSRepository rsReppository;
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	FiliereRepository filiereRepository;
	@Autowired
	PromoRepository promoRepository;
	@Autowired
	private LoginRepository logindao;
	
	
	@GetMapping(path = "/user/promo")
    public String profil(Model model ,@RequestParam(name = "nom",defaultValue = "") String  nom,HttpServletRequest  req) {
		
    Filiere filiere     = filiereRepository.findByNom(nom);
    Principal p = req.getUserPrincipal();
    
    List<Promo> ps  =  promoRepository.findByid_Filiere(filiere.getIdFiliere());
    List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
    List<Utl> utls = new ArrayList<Utl>();
    

    utilisateurs.forEach(e->{
    	System.out.println(e.getNom());
    	System.out.println(e.getProfil().getPhoto());
    	String[] date_ent = e.getPromo().getDat_ent().toString().split("-");
		String dt_ent = date_ent[0];
		String[] date_sort = e.getPromo().getDat_srt().toString().split("-");
		String dt_srt = date_sort[0];
		String promo = dt_ent + "-" + dt_srt;
    Utl utl = new Utl(e.getIdUtl(), e.getNom(), e.getPrenom(), e.getProfil().getPhoto(),e.getFiliere().getNom(),promo);
    utls.add(utl);
    	System.out.println(e.getCompte().getCNE());
    	System.out.println(e.getProfil().getPhoto());
    });
	model.addAttribute("utilisateurs",utls);
	model.addAttribute("filiere", filiere);
	model.addAttribute("promos", ps);
	model.addAttribute("user",logindao.findByUsername(p.getName()).getUser());
	return "/user/Promo";

	
	}
	@GetMapping(path = "/user/filierePromo")
    public String profil(HttpServletRequest  req,Model model ,@RequestParam(name = "filiere",defaultValue = "") String  nom,@RequestParam(name = "promo",defaultValue = "") long  id_promo) {
		Filiere filiere     = filiereRepository.findByNom(nom);
	    Principal p = req.getUserPrincipal();
	    List<Promo> ps  =  promoRepository.findByid_Filiere(filiere.getIdFiliere());
	    List<Utilisateur> utls  =  utilisateurRepository.findByid_Promo(id_promo,nom);
	    List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
	    List<Utl> utlss = new ArrayList<Utl>();
	    

	    utilisateurs.forEach(e->{
	    	System.out.println(e.getNom());
	    	System.out.println(e.getProfil().getPhoto());
	    	String[] date_ent = e.getPromo().getDat_ent().toString().split("-");
			String dt_ent = date_ent[0];
			String[] date_sort = e.getPromo().getDat_srt().toString().split("-");
			String dt_srt = date_sort[0];
			String promo = dt_ent + "-" + dt_srt;
	    Utl utl = new Utl(e.getIdUtl(), e.getNom(), e.getPrenom(), e.getProfil().getPhoto(),e.getFiliere().getNom(),promo);
	    utlss.add(utl);
	    	System.out.println(e.getCompte().getCNE());
	    	System.out.println(e.getProfil().getPhoto());
	    });
		model.addAttribute("utilisateur",utlss);
	    model.addAttribute("user",logindao.findByUsername(p.getName()).getUser());
		model.addAttribute("filiere", filiere);
		model.addAttribute("promos", ps);
		model.addAttribute("utilisateurs", utls);

	return "/user/PromoEleve";

	
	}
}
