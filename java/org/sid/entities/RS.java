package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RS implements Serializable {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long IdRS;
	private String nom;
	private String lien;
	private String photo;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@ManyToOne
	@JoinColumn(name = "ID_profil")
	private Profil profil;
	public RS(String nom, String lien, Profil profil) {
		super();
		this.nom = nom;
		this.lien = lien;
		this.profil = profil;
	}
	public RS() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Profil getProfil() {
		return profil;
	}
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	public Long getIdRS() {
		return IdRS;
	}
	public void setIdRS(Long idRS) {
		IdRS = idRS;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	} 
	

}
