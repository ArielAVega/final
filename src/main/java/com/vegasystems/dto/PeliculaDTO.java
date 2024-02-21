package com.vegasystems.dto;

import java.util.List;

import lombok.Data;

@Data
public class PeliculaDTO {
	private String titulo;
	private String urlWeb;
	private byte[] imagenPelicula;
	private String nombreImagen;
	private List<String> generos;

}
