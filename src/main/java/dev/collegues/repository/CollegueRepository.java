package dev.collegues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.collegues.entite.Collegue;

@Repository
public interface CollegueRepository extends JpaRepository<Collegue, Integer> {
	@Query("Select matricule from Collegue c where nom=?1")
	List<String> findByNom(String nom);

	boolean existsByNomAndPrenoms(String nom, String prenoms);

	@Query("Select c from Collegue c where matricule=?1")
	List<Collegue> findByMatricule(String matricule);

}
