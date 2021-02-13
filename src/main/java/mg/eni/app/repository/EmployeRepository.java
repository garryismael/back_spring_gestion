package mg.eni.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.eni.app.entity.Employe;

public interface EmployeRepository extends JpaRepository<Employe, String>{

}
