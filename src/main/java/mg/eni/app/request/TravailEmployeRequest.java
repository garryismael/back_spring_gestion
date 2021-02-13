package mg.eni.app.request;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.eni.app.entity.Employe;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravailEmployeRequest implements ITravailRequest{
    @Id
    @Pattern(regexp = "^E[0-9]{2,}$")
    private String numEmploye;

    @Column(nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String nom;

    @Column(nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String adresse;

    @NotNull
    @Pattern(regexp = "^EN[0-9]{2,}$")
    private String numEntreprise;

    @NotNull
    private int nbHeures;

    @NotNull
    private double tauxHoraire;
    
    public Employe getEmploye() {
	return new Employe(numEmploye, nom, adresse);
    }
}
