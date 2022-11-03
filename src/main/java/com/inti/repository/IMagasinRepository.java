package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inti.model.Magasin;

@Repository
@Transactional
public interface IMagasinRepository extends JpaRepository<Magasin, Integer> {

	//select * from magasin where nom =:nom;
	Magasin findByNom(String nom);
	
	//select * from magasin where cp =:cp and ville =:ville;
	Magasin findByCpAndVille(int cp, String ville);
	
	@Query(value = "select max(id) from magasin_spring", nativeQuery = true)
	int findMaxId();
	
	@Modifying
	@Query(value = "update magasin_spring set adresse=:adresse, cp=:cp  where id=:id", nativeQuery = true)
	void updateAdresseAndCpById(@Param("adresse") String adresse,@Param("cp") int cp,@Param("id") int id);
	
}
