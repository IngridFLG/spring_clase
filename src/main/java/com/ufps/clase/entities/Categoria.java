package com.ufps.clase.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorianews")
public class Categoria {
	
	@Id
	@SequenceGenerator(name="categorianews_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categorianews_id_seq")
	private Integer id;
	
	private String descripcion;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<News> news;

}
