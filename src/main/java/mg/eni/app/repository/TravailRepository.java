package mg.eni.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.eni.app.entity.Travail;
import mg.eni.app.entity.TravailId;

public interface TravailRepository extends JpaRepository<Travail, TravailId> {

}
