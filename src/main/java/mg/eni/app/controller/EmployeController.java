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

import mg.eni.app.entity.Employe;
import mg.eni.app.entity.Entreprise;
import mg.eni.app.entity.Travail;
import mg.eni.app.metiers.EmployeMetier;
import mg.eni.app.metiers.EntrepriseMetier;
import mg.eni.app.repository.EmployeRepository;
import mg.eni.app.repository.EntrepriseRepository;
import mg.eni.app.repository.TravailRepository;
import mg.eni.app.request.EmployeRequest;
import mg.eni.app.request.TravailEmployeRequest;

@RestController
@RequestMapping("/api/employe")
public class EmployeController {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private TravailRepository travailRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping
    public List<Employe> getAllEmployes() {
	return employeRepository.findAll();
    }

    @PostMapping
    public Travail create(@RequestBody @Valid TravailEmployeRequest req) {
	Entreprise entreprise = EntrepriseMetier.findById(entrepriseRepository, req.getNumEntreprise());
	EmployeMetier.exists(employeRepository, req.getNumEmploye());
	employeRepository.saveAndFlush(req.getEmploye());
	return EmployeMetier.create(travailRepository, req, entreprise);
    }

    @PutMapping("/{id}")
    public Employe updateEmploye(@PathVariable String id, @Valid @RequestBody EmployeRequest req) {

	Employe employe = EmployeMetier.findById(employeRepository, id);
	return EmployeMetier.saveEmploye(employeRepository, employe, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employe> deleteEntreprise(@PathVariable String id) {

	Employe employe = EmployeMetier.findById(employeRepository, id);
	employeRepository.delete(employe);
	return ResponseEntity.ok().build();
    }
}
