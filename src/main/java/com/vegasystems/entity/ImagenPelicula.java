package com.vegasystems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "imagenes")
public class ImagenPelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "img_id")
	private Integer id;
	@Column(name = "img_nombre_archivo",nullable = false,length = 100)
	private String nombreArchivo;
	@Lob
	@Column(name = "img_imagen", nullable = false, columnDefinition = "LONGBLOB")
	private byte[] imagen;
	@OneToOne(mappedBy = "imagenPelicula")
	private Pelicula pelicula;

}
