package mg.eni.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.eni.app.entity.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String>{

}
