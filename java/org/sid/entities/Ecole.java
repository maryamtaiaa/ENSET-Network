package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Ecole implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEcole;
	private String nom;
	private String nationalite;
	@OneToMany(mappedBy = "ecole",fetch = FetchType.LAZY)
	
	private List<Departement> departements; 
	
	public Ecole() {
		// TODO Auto-generated constructor stub
	}
	public Ecole(String nom, String nationalite) {
		super();
		this.nom = nom;
		this.nationalite = nationalite;
		departements = new ArrayList();
	}
	public Ecole(Long id, String nom, String nationalite) {
		super();
		this.idEcole = id;
		this.nom = nom;
		this.nationalite = nationalite;
	}
	public Long getId() {
		return idEcole;
	}
	public void setId(Long id) {
		this.idEcole = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	
	
}
