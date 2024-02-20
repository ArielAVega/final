package com.vegasystems.entity;

import java.util.Set;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "peliculas")
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pel_id")
	private Integer id;
	@Column(name = "pel_titulo",nullable = false,length = 100)
	private String titulo;
	@Column(name = "pel_url_web",nullable = false)
	private String urlWeb;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
	@JoinTable(name = "peliculas_generos",
				joinColumns = {@JoinColumn(name="peliculas_pel_id")},
				inverseJoinColumns = {@JoinColumn(name="generos_gen_id")})
	private Set<Genero> generos;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pel_imagen_id",referencedColumnName = "img_id",nullable = false)
	private ImagenPelicula imagenPelicula;

}
