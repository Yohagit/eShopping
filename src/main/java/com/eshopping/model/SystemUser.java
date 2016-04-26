package com.eshopping.model;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class SystemUser implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int userId;
	
	private String username;//email
	private String password;
	private String role;
	private int status;
	private String email;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passwordHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.password = (new HexBinaryAdapter()).marshal(md.digest(passwordHash.getBytes(Charset.forName("UTF-8"))));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SystemUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
