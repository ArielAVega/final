package com.vegasystems.service.imp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vegasystems.dto.PeliculaDTO;
import com.vegasystems.dto.ResumenPeliculaDTO;
import com.vegasystems.dto.mapper.PeliculaMapper;
import com.vegasystems.entity.Pelicula;
import com.vegasystems.repository.IPeliculaRepository;
import com.vegasystems.service.IPeliculaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PeliculaServiceImp implements IPeliculaService{
	private PeliculaMapper peliculaMapper;
	private IPeliculaRepository peliculaRepository;
	
	@Override
	public ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		PeliculaDTO peliculaDTO = new PeliculaDTO();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			peliculaDTO = objectMapper.readValue(movie, PeliculaDTO.class);
			peliculaDTO.setImagenPelicula(archivoImagen.getBytes());
			
			Pelicula pelicula = peliculaMapper.pelicutaDTOtoPelicula(peliculaDTO);
			
			Pelicula peliculaRegistrada = peliculaRepository.save(pelicula);
			
			resumenPeliculaDTO = peliculaMapper.peliculaToResumenPeliculaDTO(peliculaRegistrada);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return resumenPeliculaDTO;
	}

	@Override
	public List<PeliculaDTO> buscarPorTitulo(String titulo) {
		List<Pelicula> peliculas = peliculaRepository.findByTituloContainingIgnoreCase(titulo);
		System.out.println("el repository se lleva a cabo");
		List<PeliculaDTO> peliculasDTO = peliculas.stream()
				.map(p->{
					PeliculaDTO peliculaDTO = peliculaMapper.peliculaToPeliculaDTO(p);
					return peliculaDTO;
				}).collect(Collectors.toList());
				
		return peliculasDTO;
	}

}
