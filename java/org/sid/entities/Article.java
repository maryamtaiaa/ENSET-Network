package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idArticle;
	private String title;
	private String description;
	private int signale ;
	@ManyToOne
	@JoinColumn(name = "ID_user")
	private Utilisateur user;
	public Article(String title, String description, int signale, Utilisateur user) {
		super();
		this.title = title;
		this.description = description;
		this.signale = signale;
		this.user = user;
	}
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSignale() {
		return signale;
	}
	public void setSignale(int signale) {
		this.signale = signale;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	
	

}
