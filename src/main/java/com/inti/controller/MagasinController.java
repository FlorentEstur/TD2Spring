package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Magasin;
import com.inti.model.Produit;
import com.inti.repository.IMagasinRepository;
import com.inti.repository.IProduitRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("magasin")
@Slf4j
public class MagasinController {

	@Autowired
	IMagasinRepository imr;
	@Autowired
	IProduitRepository ipr;

	@GetMapping("magasins")
	public List<Magasin> getMagasins() {
		log.info("Liste de tous les magasins :");
		return imr.findAll();
	}

	@PostMapping("savemag")
	public boolean saveMagasin(@RequestBody Magasin mag) {
		if (mag != null) {
			log.info("L'objet magasin " + mag + " a pu être enregistré.");
			imr.save(mag);
			return true;
		}
		log.error("L'objet magasin " + mag + " n'a pas été sauvegardé !");
		return false;
	}

	@GetMapping("get/{id}")
	public Magasin getMagasin(@PathVariable int id) {
		Magasin m = null;
		try {
			log.info("Magasin, id : " + id + " récupéré");
			m = imr.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("L'id : " + id + " n'existe pas dans la BDD !");
			m = null;
		}
		return m;
	}

	@DeleteMapping("delete/{id}")
	public boolean deleteMagasin(@PathVariable int id) {
		int maxID = imr.findMaxId();
		log.info("maxId :" + maxID);
		if (id > 0 && id <= maxID) {
			log.info("Suppression de l'objet magasin, id : " + id);
			imr.deleteById(id);
			return true;
		}

		log.error("L'objet magasin, id : " + id + " n'a pas été supprimé !");
		return false;
	}

	@PutMapping("update/{id}")
	public Magasin updateMagasin(@RequestBody Magasin nouveauMagasin, @PathVariable int id) {
		return imr.findById(id).map(magasin -> {
			magasin.setNom(nouveauMagasin.getNom());
			magasin.setAdresse(nouveauMagasin.getAdresse());
			magasin.setCp(nouveauMagasin.getCp());
			magasin.setVille(nouveauMagasin.getVille());
			return imr.save(magasin);
		}).orElseGet(() -> {
			return imr.save(nouveauMagasin);
		});
	}

	@PostMapping("associerProduits/{id}")
	public boolean associerProduitsToMagasin(@PathVariable int id) {
		try {
			Magasin magasin = imr.findById(id).get();

			List<Produit> listeProduit = ipr.findAll();

			magasin.setListeProduit(listeProduit);

			imr.save(magasin);
			log.info("réussite");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("erreur");

		}

		return false;
	}

	@PostMapping("associerProduit/{id}")
	public boolean associerProduitToMagasin(@PathVariable int id, @RequestBody Produit produit) {
		try {
			Magasin magasin = imr.findById(id).get();

			List<Produit> listeProduit = ipr.findAll();
			listeProduit.add(produit);

			magasin.setListeProduit(listeProduit);

			imr.save(magasin);
			log.info("réussite");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("erreur");

		}

		return false;
	}

	@GetMapping("byName/{nom}")
	public Magasin getMagasinByNom(@PathVariable String nom) {
		return imr.findByNom(nom);
	}

	@GetMapping("byCpAndVille")
	public Magasin getMagasinByCpAndVille(@RequestParam(name = "cp") int cp,
			@RequestParam(name = "ville") String ville) {
		return imr.findByCpAndVille(cp, ville);
	}
	
	@GetMapping("updateAdresseAndCp")
	public void updateMagasinByAdresseAndCp(@RequestParam(name="adresse") String adresse,@RequestParam(name="cp") int cp, @RequestParam(name="id") int id) {
		imr.updateAdresseAndCpById(adresse, cp, id);
	}
}
