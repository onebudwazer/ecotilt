package fr.ecotilt.appui.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.ecotilt.appui.util.UserManager;

@Entity
@Table(name = "USER")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private long	userId;
	
	@Column(name = "USER_NAME")
	private String	name;
	
	@Column(name = "USER_PASSWORD")
	private String	password;
	
	@Column(name="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	
	public User() {
	}
	
	public User(String nameValue, String passwordValue) {
		this.name = nameValue.toLowerCase();
		this.password = passwordValue;
		this.date = new Date();
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return this.userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			if (this.name.equals(other.getName())
			 && this.password.equals(other.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "User{" + "name=" + name +
				       ", password=" + password + 
				       ", date=" + date + '}';
	}
	
	public static void main(String[] args) {
		User instanceUser = new User("Johan", UserManager.getInstance().hashPassword("p@ssword").toString());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		User instanceOther = new User("johan",  UserManager.getInstance().hashPassword("p@ssword").toString());
		
		System.out.println(instanceOther.equals(instanceUser));
		System.out.println(instanceUser);
		System.out.println(instanceOther);
		// System.out.println(instanceUser.getName());
		// System.out.println(instanceUser.getPassword());
	}
	
}
