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
import mg.eni.app.entity.TravailId;
import mg.eni.app.metiers.EmployeMetier;
import mg.eni.app.metiers.EntrepriseMetier;
import mg.eni.app.metiers.TravailMetier;
import mg.eni.app.repository.EmployeRepository;
import mg.eni.app.repository.EntrepriseRepository;
import mg.eni.app.repository.TravailRepository;
import mg.eni.app.request.Request;
import mg.eni.app.request.TravailRequest;

@RestController
@RequestMapping("/api/travail")
public class TravailController {

    @Autowired
    private TravailRepository travailRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @GetMapping
    public List<Travail> getAllTravails() {
	return travailRepository.findAll();
    }

    @PostMapping("/{numEmploye}/employe")
    public Travail createTravailForEmploye(@PathVariable String numEmploye, @RequestBody TravailRequest request) {

	String numEntreprise = request.getNumEntreprise();
	Employe employe = EmployeMetier.findById(employeRepository, numEmploye);
	Entreprise entreprise = EntrepriseMetier.findById(entrepriseRepository, numEntreprise);
	TravailMetier.travailExists(travailRepository, numEmploye, numEntreprise);

	Travail travail = new Travail(numEmploye, numEntreprise, request);
	travail.setEmploye(employe);
	travail.setEntreprise(entreprise);

	return travailRepository.save(travail);
    }

    @PutMapping("/{numEmploye}/{numEntreprise}")
    public Travail editTravail(@PathVariable("numEmploye") String numEmploye,
	    @PathVariable("numEntreprise") String numEntreprise, @RequestBody @Valid Request request) {

	TravailId id = new TravailId(numEmploye, numEntreprise);
	Travail travail = TravailMetier.findById(travailRepository, id);
	travail.setNbHeures(request.getNbHeures());
	travail.setTauxHoraire(request.getTauxHoraire());

	return travailRepository.save(travail);
    }

    @DeleteMapping("/{numEmploye}/{numEntreprise}")
    public ResponseEntity<Travail> deleteTravail(@PathVariable("numEmploye") String numEmploye,
	    @PathVariable("numEntreprise") String numEntreprise) {

	TravailId id = new TravailId(numEmploye, numEntreprise);
	Travail travail = TravailMetier.findById(travailRepository, id);

	travailRepository.delete(travail);
	return ResponseEntity.ok().build();
    }
}
