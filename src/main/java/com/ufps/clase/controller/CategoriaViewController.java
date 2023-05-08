package com.ufps.clase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.clase.entities.Categoria;
import com.ufps.clase.repository.CategoriaRepository;

import jakarta.validation.Valid;

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
	
    @GetMapping("/registrar")
    public String mostrarFormulario(Categoria categoria) {
        return "registrar";
    }

    @PostMapping("/registrar")
    public String procesarFormulario(@Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registrar";
        }

        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        model.addAttribute("categoria", categoriaGuardada);

        return "redirect:/categorias_view/listar";
    }
    
    @PostMapping("/editar")
    public String editarCategoria(@ModelAttribute("categoria") @Valid Categoria categoria,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            return "registrar";
        }
        // Lógica para actualizar la categoría en la base de datos
        model.addAttribute("mensaje", "La categoría ha sido actualizada exitosamente.");
        return "redirect:/categorias_view/listar";
    }

}


