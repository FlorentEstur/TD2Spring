package com.inti.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "MagasinSpring")
@Data @NoArgsConstructor @AllArgsConstructor
public class Magasin {

	@Id
	private int id;
	@NonNull
	private String nom;
	private String adresse;
	private int cp;
	private String ville;
	@ManyToMany
	@JoinTable(name="Magasin_Produit",
	joinColumns = @JoinColumn(name="idProduit"),
		inverseJoinColumns = @JoinColumn(name="idMagasin"))
	protected List<Produit> listeProduit;
}
