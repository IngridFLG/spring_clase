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
import org.springframework.web.bind.annotation.RequestParam;
import com.ufps.clase.entities.News;
import com.ufps.clase.repository.NewsRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;

	@GetMapping("/list")
    public String listNews(Model model) {
        List<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "Listanews";
    }

	@GetMapping("/agregar")
    public String mostrarFormulario(News news) {
        return "agregarnoticias";
    }
	
	@PostMapping("/agregar")
    public String procesarFormulario(@Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            return "agregarnoticias";
        }
        newsRepository.save(news);

        return "redirect:/news/list";
    }

	@PostMapping("/editar")
    public String editarNews(@RequestParam("id") Integer id, @ModelAttribute("news") @Valid News news, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "agregarnoticias";
        }
        News newsnuevo = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Noticia no encontrada con id: " + id));
        if (newsnuevo != null) {
        	newsnuevo.setTitulo(news.getTitulo());
        	newsnuevo.setDesarrollo(news.getDesarrollo());
        	newsnuevo.setFecha(news.getFecha());
        	newsnuevo.setCategoria(news.getCategoria());
        	newsnuevo.setUrl(news.getUrl());
        	newsnuevo.setResumen(news.getResumen());
	        newsRepository.save(newsnuevo);
	    }
        return "redirect:/news/list";
    }

	@GetMapping("/eliminar/{id}")
    public String eliminarNews(@RequestParam("id") Integer id, @ModelAttribute("news") @Valid News news,
            BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            return "redirect:/news/list";
        }
    	
    	newsRepository.deleteById(id);
    	
    	return "redirect:/news/list";
    }

}
