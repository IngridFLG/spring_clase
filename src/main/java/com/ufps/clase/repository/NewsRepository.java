package com.ufps.clase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufps.clase.entities.News;

public interface NewsRepository extends JpaRepository<News, Integer> {

}
