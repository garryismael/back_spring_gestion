package mg.eni.app.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.eni.app.entity.Entreprise;
import mg.eni.app.metiers.EntrepriseMetier;
import mg.eni.app.repository.EntrepriseRepository;
import mg.eni.app.request.EntrepriseRequest;

@RestController
@RequestMapping("/api/entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping
    public List<Entreprise> getAllEntreprises() {
	return entrepriseRepository.findAll();
    }

    @PostMapping
    public Entreprise create(@RequestBody @Valid Entreprise entreprise) {
	EntrepriseMetier.exist(entrepriseRepository, entreprise.getNumEntreprise());
	return entrepriseRepository.save(entreprise);
    }

    @PutMapping("/{id}")
    public Entreprise update(@PathVariable String id, @Valid @RequestBody EntrepriseRequest req) {
	Entreprise existingEntreprise = EntrepriseMetier.findById(entrepriseRepository, id);
	return EntrepriseMetier.saveEntreprise(entrepriseRepository, existingEntreprise, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entreprise> delete(@PathVariable String id) {

	Entreprise entreprise = EntrepriseMetier.findById(entrepriseRepository, id);
	entrepriseRepository.delete(entreprise);
	return ResponseEntity.ok().build();
    }
}
