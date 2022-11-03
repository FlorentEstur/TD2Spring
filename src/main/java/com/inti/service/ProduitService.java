package com.inti.service;

import java.util.List;


import com.inti.model.Produit;

public interface ProduitService {
	
	public List<Produit> getProduits();
	public void saveProduit(Produit prod);
	public Produit getProduit(int id);
	public void deleteProduit(Produit prod);

}
