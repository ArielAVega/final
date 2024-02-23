package com.vegasystems.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vegasystems.dto.PeliculaDTO;
import com.vegasystems.dto.ResumenPeliculaDTO;

public interface IPeliculaService {
	ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen);
	List<PeliculaDTO> buscarPorTitulo(String titulo);
}
