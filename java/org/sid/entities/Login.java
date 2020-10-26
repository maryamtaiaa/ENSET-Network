package org.sid.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Login implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLogin;
	private String username;
	private String pwd;
	private boolean active;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user", referencedColumnName = "idUtl")
	private Utilisateur user;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_admin", referencedColumnName = "idAdmin")
	private Admin admin;
	@ManyToOne
	@JoinColumn(name = "role")
	private Roles role;
	public Login() {
	
	}


	public Roles getRole() {
		return role;
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public void setRole(Roles role) {
		this.role = role;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Login(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}


	public Long getIdLogin() {
		return idLogin;
	}


	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
