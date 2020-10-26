package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreType
public class Utilisateur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUtl;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_compte", referencedColumnName = "idCompte")
	private Compte compte;
	private String nom;
	private String prenom;
	private String adresse;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_login", referencedColumnName = "idLogin")
	private Login login;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Article> articles;
	@ManyToOne
	@JoinColumn(name = "ID_filiere")
	private Filiere filiere;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_profil", referencedColumnName = "idProfil")
	private Profil profil;
	@ManyToOne
	@JoinColumn(name = "id_promo")
	private Promo promo;


	public Utilisateur(String nom, String prenom, String adresse) {
		super();
	
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}

	public Utilisateur() {

	}

	public Utilisateur(String nom, String prenom, Login login, Compte compte, Filiere filiere, Profil profil,String adresse, Promo p) {

		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.compte = compte;
		this.articles = new ArrayList();
		this.filiere = filiere;
		this.profil = profil;
		this.promo = p;
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Long getIdUtl() {
		return idUtl;
	}

	public void setIdUtl(Long idUtl) {
		this.idUtl = idUtl;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}

}
