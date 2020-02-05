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
	Collegue findByMatricule(String matricule);

	@Query("Update Collegue set email=?1, url=?2 where matricule=?3")
	Collegue update(String email, String url, String matricule);

	boolean existsByMatricule(String matricule);

}
