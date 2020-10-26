package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
@Entity

public class Profil implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProfil;
	private String  telephone;
	private String description;
	private String photo;
	
	@OneToMany(mappedBy = "profil",fetch = FetchType.LAZY)
	private List<RS> rs; 
	public Profil(String telephone, String description, String photo) {
		super();
		this.telephone = telephone;
		this.description = description;
		this.photo = photo;
		
		rs= new ArrayList();
	}
	public Profil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdProfil() {
		return idProfil;
	}
	public void setIdProfil(Long idProfil) {
		this.idProfil = idProfil;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<RS> getRs() {
		return rs;
	}
	public void setRs(List<RS> rs) {
		this.rs = rs;
	}
	
	

}
