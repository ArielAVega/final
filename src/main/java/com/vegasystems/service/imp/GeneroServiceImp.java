package com.vegasystems.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vegasystems.dto.PeliculaDTO;
import com.vegasystems.dto.mapper.PeliculaMapper;
import com.vegasystems.entity.Genero;
import com.vegasystems.repository.IGeneroRepository;
import com.vegasystems.service.IGeneroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GeneroServiceImp implements IGeneroService{
	private IGeneroRepository generoRepository;
	private PeliculaMapper peliculaMapper;

	@Override
	public List<PeliculaDTO> obtenerPorGenero(String genero) {
		Genero generoBuscado = generoRepository.findByNombreGenero(genero);
		
		List<PeliculaDTO> peliculas = generoBuscado.getPeliculas().stream().map(
				p->{
					PeliculaDTO dto = peliculaMapper.peliculaToPeliculaDTO(p);
					return dto;
				}).collect(Collectors.toList());		
		return peliculas;
	}

}
