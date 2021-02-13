package mg.eni.app.metiers;

import mg.eni.app.entity.Employe;
import mg.eni.app.entity.Entreprise;
import mg.eni.app.entity.Travail;
import mg.eni.app.entity.TravailId;
import mg.eni.app.exception.ResourceFoundException;
import mg.eni.app.exception.ResourceNotFoundException;
import mg.eni.app.repository.TravailRepository;

public class TravailMetier {
    public static Travail findById(TravailRepository repository, TravailId id) {
	return repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Travail not found for num employe "
			+ id.getNumEmploye() + " with num entreprise " + id.getNumEntreprise()));
    }

    public static Travail saveTravailWithExistingEmploye(TravailRepository repository, Travail travail, Employe employe,
	    Entreprise entreprise) {

	travail.setEmploye(employe);
	travail.setEntreprise(entreprise);
	return repository.save(travail);
    }

    public static void travailExists(TravailRepository repository, String numEmploye, String numEntreprise) {

	TravailId id = new TravailId(numEmploye, numEntreprise);
	if (repository.existsById(id)) {
	    throw new ResourceFoundException(
		    "Travail exist for num employe = " + numEmploye + " and num entreprise " + numEntreprise);
	}
    }

}
