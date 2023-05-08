package com.ufps.clase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufps.clase.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
