package com.vegasystems.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private Long id;
	@Column(name = "usu_email",nullable = false, unique = true)
	private String email;
	@Column(name = "usu_password",nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles",
				joinColumns = {@JoinColumn(name="usuarios_usu_id")},
				inverseJoinColumns = {@JoinColumn(name="roles_rol_id")})
	private List<Rol> roles;

}
