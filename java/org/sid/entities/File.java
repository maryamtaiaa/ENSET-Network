package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class File implements Serializable {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idFile;
	private String fileName;
    private byte[] data;
	public File(String fileName, byte[] data) {
	
		this.fileName = fileName;
		this.data = data;
	}
    
	public File() {
		
		
	}

	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
    

}
