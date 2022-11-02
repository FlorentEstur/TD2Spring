package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Produit;
import com.inti.service.IProduitRepository;

@RestController
@RequestMapping("produit")
public class ProduitController {

	@Autowired
	IProduitRepository ipr;
	
	@GetMapping("/affichage")
	public Produit produitById(@PathVariable int id)
	{
		return ipr.findById(id).get();
	}
}
