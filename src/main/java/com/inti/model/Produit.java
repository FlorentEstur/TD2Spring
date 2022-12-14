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
@Table(name = "ProduitSpring")
@Data @NoArgsConstructor @AllArgsConstructor
public class Produit {

	@Id
	private int id;
	@NonNull
	private String nom;
	private int reference;
	private double prixHT;
	private double poids;
	@ManyToMany
	@JoinTable(name="Magasin_Produit",
	joinColumns = @JoinColumn(name="idMagasin"),
		inverseJoinColumns = @JoinColumn(name="idProduit"))
	protected List<Magasin> listeMagasin;
}
