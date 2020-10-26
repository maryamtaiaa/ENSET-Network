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
public class Departement implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDep;
	private String intitule;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_ecole")
	private Ecole ecole;
	@OneToMany(mappedBy = "departement",fetch = FetchType.LAZY)
	private List<Filiere> filieres;
	public Departement(String intitule, Ecole ecole) {
		super();
		this.intitule = intitule;
		this.ecole = ecole;
		filieres = new ArrayList();
	}
	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdDep() {
		return idDep;
	}
	public void setIdDep(Long idDep) {
		this.idDep = idDep;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public Ecole getEcole() {
		return ecole;
	}
	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}
	public List<Filiere> getFilieres() {
		return filieres;
	}
	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}
	
	

}
