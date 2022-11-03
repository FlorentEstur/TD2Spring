package com.inti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inti.model.Produit;
import com.inti.repository.IProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService {

	@Autowired
	IProduitRepository ipr;

	@Override
	@RequestMapping("/liste")
	public List<Produit> getProduits() {
		return null;

	}

	@Override
	@RequestMapping("/saveprod")
	public void saveProduit(@RequestBody Produit prod) {
		ipr.save(prod);
	}

	@Override
	@RequestMapping("/getprod")
	public Produit getProduit(@PathVariable int id) {
		return ipr.findById(id).get();

	}

	@Override
	@RequestMapping("/delete")
	public void deleteProduit(@RequestBody Produit prod) {
		ipr.delete(prod);
	}

}
