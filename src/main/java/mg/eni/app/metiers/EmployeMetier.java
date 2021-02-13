package mg.eni.app.metiers;

import mg.eni.app.entity.Employe;
import mg.eni.app.entity.Entreprise;
import mg.eni.app.entity.Travail;
import mg.eni.app.exception.ResourceFoundException;
import mg.eni.app.exception.ResourceNotFoundException;
import mg.eni.app.repository.EmployeRepository;
import mg.eni.app.repository.TravailRepository;
import mg.eni.app.request.EmployeRequest;
import mg.eni.app.request.TravailEmployeRequest;

public class EmployeMetier {
    public static Employe findById(EmployeRepository repository, String id) {
	return repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("numéro employé " + id + " n'existe pas."));
    }

    public static Travail create(TravailRepository repository, TravailEmployeRequest request, Entreprise entreprise) {

	String numEmploye = request.getNumEmploye();
	String numEntreprise = entreprise.getNumEntreprise();

	Travail travail = new Travail(numEmploye, numEntreprise, request);
	travail.setEmploye(request.getEmploye());
	travail.setEntreprise(entreprise);
	return repository.save(travail);
    }

    public static void exists(EmployeRepository repository, String id) {
	if (repository.existsById(id)) {
	    throw new ResourceFoundException("numéro employé " + id + " existe.");
	}
    }

    public static Employe saveEmploye(EmployeRepository repository, Employe employe, EmployeRequest req) {
	employe.setNom(req.getNom());
	employe.setAdresse(req.getAdresse());
	return repository.save(employe);
    }
}
