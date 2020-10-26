package org.sid.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class TypeCour implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeCour;
	private String designation ;


	public TypeCour() {

	}
	public TypeCour(String designation) {

	this.designation = designation;
	}
	public Long getIdTypeCour() {
	return idTypeCour;
	}
	public void setIdTypeCour(Long idTypeCour) {
	this.idTypeCour = idTypeCour;
	}
	public String getDesignation() {
	return designation;
	}
	public void setDesignation(String designation) {
	this.designation = designation;
	}


}
