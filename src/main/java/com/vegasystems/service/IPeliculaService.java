package com.vegasystems.service;

import org.springframework.web.multipart.MultipartFile;

import com.vegasystems.dto.ResumenPeliculaDTO;

public interface IPeliculaService {
	ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen);

}
