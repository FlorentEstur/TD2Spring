package com.inti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Produit;
import com.inti.repository.IProduitRepository;

@RestController
@RequestMapping("produit")
public class ProduitController {

	@Autowired
	IProduitRepository ipr;

	@GetMapping("affichage")
	public String produitByNom(@RequestParam(name = "nom", defaultValue = "patate", required = true) String nom) {
		return nom;
	}

	@GetMapping("prix")
	public Double produitByPrix(@RequestParam(name = "prix", defaultValue = "5", required = true) double prix) {
		return prix;
	}

	@GetMapping("calcul")
	public List<String> prixProduitTTC(@RequestParam(name = "nom", defaultValue = "patate") String nom,
			@RequestParam(name = "prix", defaultValue = "5") double prix) {
		List<String> listeprixTTC = new ArrayList<>();
		double prixTTC = 1.8 * prix;
		String prixTTCString = String.valueOf(prixTTC);
		String prixHT = String.valueOf(prix);
		listeprixTTC.add(nom);
		listeprixTTC.add(prixHT);
		listeprixTTC.add(prixTTCString);
		return listeprixTTC;
	}

	@GetMapping("produits")
	public List<Produit> getProduits() {
		return ipr.findAll();
	}

	@PostMapping("saveprod")
	public boolean saveProduit(@RequestBody Produit prod) {
		if (prod != null) {
			ipr.save(prod);
			return true;
		}

		return false;
	}

	@GetMapping("get/{id}")
	public Produit getProduit(@PathVariable int id) {
		Produit p = null;
		try {
			p = ipr.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			p = null;
		}
		return p;
	}

	@DeleteMapping("delete/{id}")
	public boolean deleteProduit(@PathVariable int id) {
		if (id > 0) {
			ipr.deleteById(id);
			return true;
		}
		
		return false;
	}
}
