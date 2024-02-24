package com.vegasystems.service;

import java.util.List;

import com.vegasystems.dto.PeliculaDTO;

public interface IGeneroService {
	List<PeliculaDTO> obtenerPorGenero(String genero);

}
