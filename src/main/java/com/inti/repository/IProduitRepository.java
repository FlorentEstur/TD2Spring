package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Produit;

@Repository
public interface IProduitRepository extends JpaRepository<Produit, Integer> {

}
