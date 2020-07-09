package com.subg.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken {

	 private static final int EXPIRATION = 60 * 24;
	  
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	  
	    private String token;
	    
	    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
	    @JoinColumn(nullable = false, name = "userId")
	    private Users users;
	    
	    private Date expiryDate;


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Users getUsers() {
			return users;
		}

		public void setUsers(Users users) {
			this.users = users;
		}

		public Date getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}

		public static int getExpiration() {
			return EXPIRATION;
		}
	    
		   public void setExpiryDate(int minutes){
		        Calendar now = Calendar.getInstance();
		        now.add(Calendar.MINUTE, minutes);
		        this.expiryDate = now.getTime();
		    }

		    public boolean isExpired() {
		        return new Date().after(this.expiryDate);
		    }
	    
}
