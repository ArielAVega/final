package com.vegasystems.dto.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vegasystems.dto.PeliculaDTO;
import com.vegasystems.dto.ResumenPeliculaDTO;
import com.vegasystems.entity.Genero;
import com.vegasystems.entity.ImagenPelicula;
import com.vegasystems.entity.Pelicula;
import com.vegasystems.repository.IGeneroRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PeliculaMapper {
	private IGeneroRepository generoRepository;
	
	public Pelicula pelicutaDTOtoPelicula(PeliculaDTO peliculaDTO) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(peliculaDTO.getTitulo());
		pelicula.setUrlWeb(peliculaDTO.getUrlWeb());
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo(peliculaDTO.getNombreImagen());
		imagenPelicula.setImagen(peliculaDTO.getImagenPelicula());
		pelicula.setImagenPelicula(imagenPelicula);
		Set<Genero> generos = new HashSet<>();
		for(String g: peliculaDTO.getGeneros()) {
			generos.add(generoRepository.findByNombreGenero(g));
		}
		pelicula.setGeneros(generos);
		return pelicula;
	}
	
	public ResumenPeliculaDTO peliculaToResumenPeliculaDTO(Pelicula pelicula) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		resumenPeliculaDTO.setNombreImagen(pelicula.getImagenPelicula().getNombreArchivo());
		String generosString = pelicula.getGeneros().stream().map(
							g->g.getNombreGenero()).collect(Collectors.joining(" - "));
		
		resumenPeliculaDTO.setStringGeneros(generosString);
		resumenPeliculaDTO.setTitulo(pelicula.getTitulo());
		resumenPeliculaDTO.setUrlWeb(pelicula.getUrlWeb());
		return resumenPeliculaDTO;
	}
	
}
