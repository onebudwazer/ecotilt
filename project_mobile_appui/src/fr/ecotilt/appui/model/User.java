package fr.ecotilt.appui.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
	
	public User() {
	}
	
	public User(String nameValue, String passwordValue) {
		this.name = nameValue;
		this.password = passwordValue;
	}
	
	public long getUserId() {
		return this.userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			if (this.name == other.getName()
			 && this.password == other.getPassword()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		User instanceUser = new User("Johan", "password");
		User instanceOther = new User("Johan1", "password");
		System.out.println(instanceOther.equals(instanceUser));
		// System.out.println(instanceUser.getName());
		// System.out.println(instanceUser.getPassword());
	}
	
}
