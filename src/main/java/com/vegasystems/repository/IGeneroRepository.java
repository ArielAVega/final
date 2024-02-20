package com.vegasystems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vegasystems.entity.Genero;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer>{

}
