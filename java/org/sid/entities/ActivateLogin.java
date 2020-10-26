package org.sid.entities;

import java.io.Serializable;

public class ActivateLogin implements Serializable{
	private String cin;
	private String cne;
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	

}
