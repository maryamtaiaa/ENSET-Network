package org.sid.web;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sid.dao.CourRepository;
import org.sid.dao.LoginRepository;
import org.sid.dao.TypeCourRepisitory;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Cour;

import org.sid.entities.TypeCour;
import org.sid.entities.Utilisateur;
import org.sid.entities.Utl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourControler {
   @Autowired
	TypeCourRepisitory typeCourRepisitory;
   @Autowired
   CourRepository courRepository;
   @Autowired
   UtilisateurRepository utilisateurRepository;
  @Autowired
	private LoginRepository logindao;
	@GetMapping(path = "/user/cour")
    public String edit(Model model, HttpServletRequest  req) {
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
		List<TypeCour> crs   = typeCourRepisitory.findAll();
		
		List<Cour> cours = courRepository.findAll();
		List<String> images = new ArrayList<String>();
		

		cours.forEach(e -> {
        	if(e.getTitle().contains("JEE"))
        	{
        		images.add("jee.jpg");
        	}
          });
	   
		 Principal p = req.getUserPrincipal();
		 model.addAttribute("user", logindao.findByUsername(p.getName()).getUser());
		model.addAttribute("cours",cours);
		model.addAttribute("crs",cours);
		model.addAttribute("coursType",crs);
		model.addAttribute("images",images);
		
		return "/user/PageCour";
    }
	@GetMapping(path = "/user/downloadFile")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(name = "cour",defaultValue = "") long  id) {
	     Cour cour = courRepository.findById(id).get();
	     return ResponseEntity.ok()
	    		 .contentType(MediaType.parseMediaType(cour.getMimetype()))
	    		 .header(HttpHeaders.CONTENT_DISPOSITION,"attachement:filename=\""+cour.getName()+"\"")
	    		 .body(new ByteArrayResource(cour.getPic()));

		
		
		
    }
	@GetMapping(path = "/user/CourRecherche")
    public String courRecherche(HttpServletRequest  req,Model model,@RequestParam(name = "id") long  id) {
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
		List<TypeCour> crs   = typeCourRepisitory.findAll();
		
		List<Cour> cours = courRepository.findByid_TypeCour(id);
		List<Cour> courss = courRepository.findAll();
		List<String> images = new ArrayList<String>();
		 Principal p = req.getUserPrincipal();
		 model.addAttribute("user", logindao.findByUsername(p.getName()).getUser());
		cours.forEach(e -> {
        	if(e.getTitle().contains("JEE"))
        	{
        		images.add("jee.jpg");
        	}
          });	
	   
	    	
		model.addAttribute("cours",cours);
		model.addAttribute("crs",courss);
		model.addAttribute("coursType",crs);
		model.addAttribute("images",images);
		
		return "/user/PageCour";
    }
	@GetMapping(path = "/user/CourSearsh")
    public String CourSearsh(HttpServletRequest  req,Model model,@RequestParam(name = "id") long  id) {
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
		List<TypeCour> crs   = typeCourRepisitory.findAll();
		
		Cour cours = courRepository.findById(id).get();
		List<Cour> courss = courRepository.findAll();
		List<String> images = new ArrayList<String>();
		 Principal p = req.getUserPrincipal();
		 model.addAttribute("user", logindao.findByUsername(p.getName()).getUser());
		
	   
	    	
		model.addAttribute("cours",cours);
		model.addAttribute("crs",courss);
		model.addAttribute("coursType",crs);
		model.addAttribute("images",images);
		
		return "/user/PageCour";
    }
	
	
}


