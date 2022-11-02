package com.inti.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
}
