	package org.sid.service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.sid.dao.CompteRepository;
import org.sid.dao.FiliereRepository;
import org.sid.dao.PromoRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Compte;
import org.sid.entities.Filiere;
import org.sid.entities.Login;
import org.sid.entities.Profil;
import org.sid.entities.Promo;
import org.sid.entities.Roles;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

@Service
public class StudentService {
	private String defaultImg = "default.png";
	@Autowired
	private FiliereRepository fr;
	@Autowired
	private PromoRepository pr;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private CompteRepository compteRepository;
	public void createAccounts(String csvFile,Long idPromo,Long idFiliere) {
		CSVReader reader ;
		String [] nextLine;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			nextLine = reader.readNext();
			//format csv
			//cin,cne,nom,prenom,email,number,address,dateNaissance
			while ((nextLine = reader.readNext()) != null) {
			   // nextLine[] is an array of values from the line
				
				  Login login = createLogin(nextLine[2], nextLine[3]); 
				  Profil profil =   createProfile(nextLine[5]); String stringDate = nextLine[7]; SimpleDateFormat
				  f = new SimpleDateFormat("dd-MM-yyyy"); 
					
					  Compte compte = createCompte(nextLine[0], nextLine[1], new
					  Date(f.parse(stringDate).getTime()), nextLine[4]);
					  
					
						  Filiere filiere = fr.findById(idFiliere).get();
						  Promo promo = pr.findById(idFiliere).get(); 
						  Utilisateur user = createUser(nextLine[2],
						  nextLine[3],nextLine[6],login,profil,promo,filiere,compte);
						  login.setUser(user);
						  login.setActive(true);
						  ur.save(user);
						 
					 
				 
				System.out.println(nextLine[0]+nextLine[1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Login createLogin(String nom,String prenom) {
		Login l = new Login();
		l.setPwd(generateRandomPassword());
		l.setUsername(prenom+"." + nom +"@ENSET");
		l.setRole(new Roles("USER"));
		
		return l;
	}
	public Profil createProfile(String number) {
		Profil p = new Profil();
		p.setPhoto(defaultImg);
		p.setTelephone(number);
		return p;
	}
	public Compte createCompte(String cin,String cne, Date dateNaissance,String email) {
		Compte p = new Compte(0,cin, cne, email, dateNaissance, false);
		System.out.println(p.getStatus());
		return p;
	}
	public Utilisateur createUser(String nom,String prenom,String address,Login login,Profil profil,Promo promo,Filiere filiere,Compte compte) {
		Utilisateur user = new Utilisateur();
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setAdresse(address);
		user.setLogin(login);
		user.setProfil(profil);
		user.setPromo(promo);
		user.setFiliere(filiere);
		user.setCompte(compte);
		return user;
	}
	public String generateRandomPassword() {

		List rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1),new CharacterRule(EnglishCharacterData.Special, 1));

		PasswordGenerator generator = new PasswordGenerator();
		String password = generator.generatePassword(8, rules);
		return password;
	}
}
