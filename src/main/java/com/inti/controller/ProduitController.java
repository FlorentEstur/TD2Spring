package com.inti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Produit;
import com.inti.service.IProduitRepository;

@RestController
@RequestMapping("produit")
public class ProduitController {

	@Autowired
	IProduitRepository ipr;

	@GetMapping("/affichage")
	public String produitByNom(@RequestParam(name = "nom", defaultValue = "patate", required = true) String nom) {
		return nom;
	}

	@GetMapping("/prix")
	public Double produitByPrix(@RequestParam(name = "prix", defaultValue = "5", required = true) double prix) {
		return prix;
	}

	@GetMapping("/calcul")
	public List<String> prixProduitTTC(@RequestParam(name = "nom", defaultValue = "patate") String nom, @RequestParam (name ="prix", defaultValue = "5") double prix) {
		List<String> listeprixTTC = new ArrayList<>();
		double prixTTC = 1.8*prix;
		String prixTTCString = String.valueOf(prixTTC);
		listeprixTTC.add(prixTTCString);
		return listeprixTTC;

	}
}
