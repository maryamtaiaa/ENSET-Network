package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Filiere  implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFiliere;
	private String nom;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_departement")
	private Departement departement ;
	@OneToMany(mappedBy = "filiere",fetch = FetchType.LAZY)
	private List<Promo> promos;
	public List<Promo> getPromos() {
		return promos;
	}
	public void setPromos(List<Promo> promos) {
		this.promos = promos;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "filiere",fetch = FetchType.LAZY)
	List<Utilisateur> users ;
	public Filiere() {
		// TODO Auto-generated constructor stub
	}
	public Filiere(Long id, String nom) {
		this.idFiliere = id;
		this.nom = nom;
	}
	
	
	public Filiere(String nom, Departement idDepartement) {
		this.nom = nom;
		this.departement = idDepartement;
		users = new ArrayList();
	}
	public Long getIdFiliere() {
		return idFiliere;
	}
	public void setIdFiliere(Long idFiliere) {
		this.idFiliere = idFiliere;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	public List<Utilisateur> getUsers() {
		return users;
	}
	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}


	
	
}
