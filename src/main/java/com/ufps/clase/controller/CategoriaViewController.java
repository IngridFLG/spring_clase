package com.ufps.clase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.clase.entities.Categoria;
import com.ufps.clase.repository.CategoriaRepository;

@Controller
@RequestMapping("/categorias_view")
public class CategoriaViewController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Categoria> categorias = categoriaRepository.findAll();
		model.addAttribute("categorias", categorias);
		model.addAttribute("titulo2", "Listado de categorias");
		return "index";
	}

}
