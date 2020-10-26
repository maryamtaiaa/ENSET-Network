package org.sid.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
@Entity
public class Compte implements Serializable {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int  idCompte;
	private String CIN;
	private String CNE;
	private String gmail;
	private Date dateNaissance;
	@Type(type = "org.hibernate.type.NumericBooleanType")	
	private boolean status ;
	
	public Compte(String cIN, String cNE, String gmail, Date dateNaissance) {
		super();
		CIN = cIN;
		CNE = cNE;
		this.gmail = gmail;
		status = false;	
		this.dateNaissance = dateNaissance;
	
	}
	public Compte(int id,String cIN, String cNE, String gmail, Date dateNaissance, Boolean status) {
		super();
		idCompte = id;
		CIN = cIN;
		CNE = cNE;
		this.gmail = gmail;
		status = false;
		this.dateNaissance = dateNaissance;
	
	}
	public Compte(String cIN, String cNE, String gmail, Date dateNaissance, Boolean status) {
		super();
	
		CIN = cIN;
		CNE = cNE;
		this.gmail = gmail;
		status = false;
		this.dateNaissance = dateNaissance;
	
	}
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public String getCNE() {
		return CNE;
	}
	public void setCNE(String cNE) {
		CNE = cNE;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	

}
