 package org.sid.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sid.dao.*;
import  org.sid.entities.*;
@Controller
public class UserController {
	@Autowired
	private ArticleRepository articledao;
	@Autowired 
	private UtilisateurRepository userdao;
	@Autowired
	private LoginRepository logindao;
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	ProfilRepository profilRepository;
	@GetMapping("/user/articles")
	
	public String articles(Model model ,HttpServletRequest  req) {
		
	    List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
	    List<Utl> utls = new ArrayList<Utl>();
	    

	   utilisateurs.forEach(e->{
	    	//System.out.println(e.getNom());
	    	//System.out.println(e.getProfil().getPhoto());
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
	   
		Article article = new Article();
	    Principal p = req.getUserPrincipal();
		article.setUser(logindao.findByUsername(p.getName()).getUser());
		model.addAttribute("article", article);
		model.addAttribute("articles",  articledao.findAll());
		model.addAttribute("filieres", filiereRepository.findAll());

		model.addAttribute("user", logindao.findByUsername(p.getName()).getUser());
		return "/user/ArticlePage";
	}
	@GetMapping("/user/home")
	public String userHome(Model model ,HttpServletRequest  req) {
		List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
	    List<Utl> utls = new ArrayList<Utl>();
	    

	   utilisateurs.forEach(e->{
	    	//System.out.println(e.getNom());
	    	//System.out.println(e.getProfil().getPhoto());
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
		 Principal p = req.getUserPrincipal();
		 model.addAttribute("user", logindao.findByUsername(p.getName()).getUser());
		return "index2";
	}
	
	@GetMapping("/user/articles/ArticleForm")
	public String formArticles(Model model,HttpServletRequest  req) {
		Article article = new Article();
		 Principal p = req.getUserPrincipal();
			article.setUser(logindao.findByUsername(p.getName()).getUser());
				
		model.addAttribute("article", article);
		return "/user/FormArticle";
	}
	
	@RequestMapping(value = "/user/articles/save", method = RequestMethod.POST)
	public String saveArticle(Model model,@Valid Article article, BindingResult bindingResult,HttpServletRequest  req) {
		if (bindingResult.hasErrors())
			return "FormArticle";
		 Principal p = req.getUserPrincipal();
		 article.setUser(logindao.findByUsername(p.getName()).getUser());
		articledao.save(article);
		return "redirect:/user/articles";
	}
	
	@GetMapping("/user/articles/delete")
	public String deleteArticle(Model model, Long id) {
		articledao.deleteById(id);
		return "redirect:/user/articles";
	}
	
	@GetMapping("/user/articles/signaler")
	public String signalerArticle(Model model, Long id) {
		Article article=articledao.findById(id).get();
		article.setSignale(article.getSignale()+1);
		articledao.save(article);
		if(article.getSignale()==6) {
			articledao.deleteById(id);
			return "redirect:/user/articles";
		}
		else {
		return "redirect:/user/articles";
		}
	}
	@GetMapping(path = "/user/mynetwork")
    public String profil(HttpServletRequest  req,Model model) {
		
	    List<Utilisateur> utls  =  utilisateurRepository.findAll();
	    List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
	    List<Utl> utlss = new ArrayList<Utl>();
	    
	    Principal p = req.getUserPrincipal();
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
		model.addAttribute("filieres", filiereRepository.findAll());
		model.addAttribute("user", logindao.findByUsername(p.getName()).getUser());
		model.addAttribute("utilisateurs", utls);

	return "/user/resaux";

	
	}

}
