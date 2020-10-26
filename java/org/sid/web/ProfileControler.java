package org.sid.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.sid.dao.FiliereRepository;
import org.sid.dao.LoginRepository;
import org.sid.dao.ProfilRepository;
import org.sid.dao.RSRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Filiere;
import org.sid.entities.Login;
import org.sid.entities.Profil;
import org.sid.entities.RS;
import org.sid.entities.Utilisateur;
import org.sid.entities.Utl;

@Controller
public class ProfileControler {
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

	@GetMapping(path = "/user/profile")
	public String profil(Model model, @RequestParam(name = "id", defaultValue = "") Long id, HttpServletRequest req) {
		Utilisateur utl = utilisateurRepository.findById(id).get();
	    //System.out.println(utl.getProfil().getPhoto());
		Principal p = req.getUserPrincipal();
		model.addAttribute("user", loginRepository.findByUsername(p.getName()).getUser());
		Profil prf = utl.getProfil();

		String[] date_ent = utl.getPromo().getDat_ent().toString().split("-");
		String dt_ent = date_ent[0];
		String[] date_sort = utl.getPromo().getDat_srt().toString().split("-");
		String dt_srt = date_sort[0];
		String promo = dt_ent + "-" + dt_srt;

		String[] aniv = utl.getCompte().getDateNaissance().toString().split("-");
		String[] s = aniv[2].split(" ");
		String date_aniv = aniv[1] + "-" + s[0];

		List<RS> reseau_sociaux = rsReppository.findByid_Profil(prf.getIdProfil());
		List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
	    List<Utl> utls = new ArrayList<Utl>();
	    

	    utilisateurs.forEach(e->{
	    	System.out.println(e.getNom());
	    	System.out.println(e.getProfil().getPhoto());
	    	String[] date_entt = e.getPromo().getDat_ent().toString().split("-");
			String dt_entt = date_ent[0];
			String[] date_sortt = e.getPromo().getDat_srt().toString().split("-");
			String dt_srtt = date_sortt[0];
			String prommo = dt_entt + "-" + dt_srtt;
	    Utl utll = new Utl(e.getIdUtl(), e.getNom(), e.getPrenom(), e.getProfil().getPhoto(),e.getFiliere().getNom(),prommo);
	    utls.add(utll);
	    	System.out.println(e.getCompte().getCNE());
	    	System.out.println(e.getProfil().getPhoto());
	    });
		model.addAttribute("utilisateurs",utls);

		model.addAttribute("profile", prf);
		model.addAttribute("promo", promo);
		model.addAttribute("utilisateur", utl);
		model.addAttribute("aniv", date_aniv);
		model.addAttribute("rs_sociaux", reseau_sociaux);
		model.addAttribute("filieres", filiereRepository.findAll());

		return "/user/Profile";

	}
	@GetMapping(path = "/user/chat")
	public String chat(Model model, @RequestParam(name = "id", defaultValue = "") Long id, HttpServletRequest req) {
		Utilisateur utl = utilisateurRepository.findById(id).get();
	    
	    

	   
		model.addAttribute("utilisateur", utl);
	

		return "/user/index";

	}

	@GetMapping(path = "/user/editProfil")
	public String edit(Model model, Long id, HttpServletRequest req) {
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
		Profil prf = profileRepository.findById(id).get();
		Utilisateur utl = utilisateurRepository.findByid_Profil(prf.getIdProfil());
		Principal p = req.getUserPrincipal();
		String date_ent = utl.getPromo().getDat_ent().toString();
		System.out.println(date_ent);
		String dateSort = utl.getPromo().getDat_srt().toString();

		List<RS> reseau_sociaux = rsReppository.findByid_Profil(prf.getIdProfil());
		model.addAttribute("profil", prf);
		model.addAttribute("utilisateur", utl);
		model.addAttribute("user", loginRepository.findByUsername(p.getName()).getUser());

		model.addAttribute("date_ent", date_ent);
		model.addAttribute("dateSort", dateSort);

		model.addAttribute("rs_sociaux", reseau_sociaux);
		model.addAttribute("filieres", filiereRepository.findAll());

		return "/user/ProfileEdit";
	}

	@PostMapping("/user/saveProfil")
	public String sav(Model model, @Valid Profil profil, Long id, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "editProfil";
		profileRepository.save(profil);
		return "redirect:/user/editProfil?id=" + id;
	}

	@PostMapping("/user/saveCordonnee")
	public String savCord(Model model, @Valid Utilisateur utl, Long id, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "redirect:/editProfil?id=" + id;
		Profil prf = profileRepository.findById(utl.getProfil().getIdProfil()).get();
		Login lg = loginRepository.findById(utl.getLogin().getIdLogin()).get();
		Filiere flr = filiereRepository.findById(utl.getFiliere().getIdFiliere()).get();
		utl.setLogin(lg);
		utl.setFiliere(flr);
		utl.setProfil(prf);
		System.out.println(prf.getDescription());
		utilisateurRepository.save(utl);
		return "redirect:/user/editProfil?id=" + id;
	}

	@PostMapping("/user/saveRs")
	public String saveRs(Model model, @Valid RS e, Long id, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "redirect:/editProfil?id=" + id;

		Profil prf = profileRepository.findById(e.getProfil().getIdProfil()).get();
		e.setProfil(prf);
		rsReppository.save(e);

		return "redirect:/user/editProfil?id=" + id;
	}

}
