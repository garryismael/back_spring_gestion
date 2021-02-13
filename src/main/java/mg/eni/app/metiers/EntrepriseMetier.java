package mg.eni.app.metiers;

import mg.eni.app.entity.Entreprise;
import mg.eni.app.exception.ResourceNotFoundException;
import mg.eni.app.repository.EntrepriseRepository;
import mg.eni.app.request.EntrepriseRequest;

public class EntrepriseMetier {
    public static Entreprise findById(EntrepriseRepository repository, String id) {
	return repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("numéro entreprise " + id + " n'existe pas"));
    }

    public static void exist(EntrepriseRepository repository, String id) {
	if (repository.existsById(id)) {
	    throw new ResourceNotFoundException("numéro entreprise " + id + " existe");
	}
    }

    public static Entreprise saveEntreprise(EntrepriseRepository repository, Entreprise existingEntreprise,
	    EntrepriseRequest req) {
	existingEntreprise.setDesign(req.getDesign());
	existingEntreprise.setAdresse(req.getAdresse());
	return repository.save(existingEntreprise);
    }

}
