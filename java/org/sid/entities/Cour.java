package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Cour implements Serializable {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idCour;
	private String title;
	private String auteur;
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Column(name = "name")
    @JsonView(View.FileInfo.class)
	private String name;
    
    @Column(name = "mimetype")
	private String mimetype;
	
	@Lob
    @Column(name="pic")
    private byte[] pic;
	
	
	@ManyToOne
	@JoinColumn(name = "id_type")
	private TypeCour type;
	
	
	public Cour(String title, String auteur) {
		
		this.title = title;
		this.auteur = auteur;
	}
	public Cour() {
		
	}
	public Long getIdCour() {
		return idCour;
	}
	public void setIdCour(Long idCour) {
		this.idCour = idCour;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public Cour(String title,String auteur, TypeCour type) {
		super();
		this.title = title;
		this.auteur = auteur;
		this.type = type;
	}
	public TypeCour getType() {
		return type;
	}
	public void setType(TypeCour type) {
		this.type = type;
	}
	public Cour(String title, String auteur, String name, String mimetype, byte[] pic, TypeCour type) {
		super();
		this.title = title;
		this.auteur = auteur;
		this.name = name;
		this.mimetype = mimetype;
		this.pic = pic;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMimetype() {
		return mimetype;
	}
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public Cour(Long idCour, String title, String auteur, String image, String name, String mimetype, byte[] pic,
			TypeCour type) {
		super();
		this.idCour = idCour;
		this.title = title;
		this.auteur = auteur;
		this.image = image;
		this.name = name;
		this.mimetype = mimetype;
		this.pic = pic;
		this.type = type;
	}

	
	
	
	
	
	
}
