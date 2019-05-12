package com.lernia.spring.registration.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "registereduser")
@DynamicUpdate
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;

	@NotNull
	@Column(name = "personal_number", unique = true)
	private long personalNumber;

	@NotNull
	@Column(name = "user_name", unique = true)
	private String userName;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Column(name = "address")
	private String address;

	@NotNull
	@Email
	@Column(name = "email", unique = true)
	private String email;

	@Min(value = 100000000)
	@NotNull
	@Column(name = "phone")
	private Long phone;

	@NotNull
	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private Boolean active;
	
	@NotNull
	@Column(name = "balance")
	private int balance;

	@Column(name = "rating")
	private int rating;

	@Column(name = "roles")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Override
	public String toString() {
		return "User [id=" + user_id + ", personalNumber=" + personalNumber + ", userName=" + userName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", address=" + address + ", email=" + email + ", phone="
				+ phone + ", password=" + password + ", active=" + active + ", rating=" + rating + ", roles=" + roles
				+ "]";
	}

	public User(@NotNull long personalNumber,
			@NotNull String userName, @NotNull String firstName, @NotNull String lastName, @NotNull String address,
			@NotNull @Email String email, @Min(1000000000) @NotNull Long phone, @NotNull String password) {
		super();
		this.personalNumber = personalNumber;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

}
