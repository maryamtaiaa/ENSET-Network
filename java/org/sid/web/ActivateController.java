package org.sid.web;

import org.sid.entities.ActivateLogin;
import org.sid.dao.CompteRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Compte;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ActivateController {
	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private UtilisateurRepository ur;
	
	@GetMapping("/activate")
	public String openForm(Model model) {
		model.addAttribute("details", new ActivateLogin());
		return "activate";
	}
	@PostMapping("/activate")
	public String getData(@ModelAttribute ActivateLogin details) {
		SimpleMailMessage msg = new SimpleMailMessage();
		Compte compte = compteRepository.findCompteByCINandCNE(details.getCin(), details.getCne());
		compte.setStatus(true);
		compteRepository.save(compte);
		System.out.println(compte.getIdCompte());
		Utilisateur user = ur.findByIdCompte(compte.getIdCompte());
		msg.setTo(compte.getGmail());
		msg.setSubject("Activation mail");
		msg.setText("Hello youre username is : " + user.getLogin().getUsername() +"\n"+
		"your password is : "+ user.getLogin().getPwd());
		javaMailSender.send(msg);
		//System.out.println(details.getCin()+ " " +  details.getCne());
		//System.out.println(compteRepository.findCompteByCINandCNE(details.getCin(), details.getCne()).getGmail());
		return "index";
	}
}
