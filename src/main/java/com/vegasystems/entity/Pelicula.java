package com.vegasystems.entity;

import java.util.Set;

import lombok.Data;

@Data
public class Pelicula {
	private Integer id;
	private String titulo;
	private String urlWeb;
	private Set<Genero> generos;
	private ImagenPelicula imagen;

}
