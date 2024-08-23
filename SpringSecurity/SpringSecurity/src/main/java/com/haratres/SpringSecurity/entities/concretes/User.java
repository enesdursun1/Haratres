package com.haratres.SpringSecurity.entities.concretes;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private int userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns = {
			@JoinColumn(name="userId",referencedColumnName = "userId")
	},inverseJoinColumns = {
			@JoinColumn(name="roleId",referencedColumnName = "roleId")
	})
	private List<Role> roles;

	@OneToOne(mappedBy = "user")
	private Cart cart;


	public User(String username,String password, List<Role> roles) {

		setUsername(username);
		setPassword(password);
		this.roles = roles;

	}

}
