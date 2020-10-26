package org.sid.entities;
import java.io.Serializable;
import java.sql.Date;
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
public class Promo implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dat_ent;
	private Date dat_srt;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_fil")
	private Filiere filiere;
	@JsonIgnore
	@OneToMany(mappedBy = "promo",fetch = FetchType.LAZY)
	List<Utilisateur> users ;
	public Promo(Long id,Date dat_ent, Date dat_srt)
	{
		this.id=id;
		this.dat_ent=dat_ent;
		this.dat_srt= dat_srt;
	}
	public Promo() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDat_ent() {
		return dat_ent;
	}
	public void setDat_ent(Date dat_ent) {
		this.dat_ent = dat_ent;
	}
	public Date getDat_srt() {
		return dat_srt;
	}
	public void setDat_srt(Date dat_srt) {
		this.dat_srt = dat_srt;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	

}
