package mg.eni.app.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.eni.app.request.ITravailRequest;

@Entity
@Table(name = "travails")
@Getter
@Setter
@NoArgsConstructor
public class Travail {
    @EmbeddedId
    @JsonBackReference
    private TravailId id = new TravailId();

    @ManyToOne
    @JoinColumn(name = "numEmploye")
    @MapsId("numEmploye")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "numEntreprise")
    @MapsId("numEntreprise")
    private Entreprise entreprise;

    @Column(nullable = false)
    private int nbHeures;

    @Column(columnDefinition = "REAL", nullable = false)
    private double tauxHoraire;

    @Column(columnDefinition = "DATE", updatable = false, nullable = false)
    private LocalDate dateEmbauche = LocalDate.now();

    public Travail(String numEmploye, String numEntreprise, ITravailRequest request) {
	this.id.setNumEmploye(numEmploye);
	this.id.setNumEntreprise(numEntreprise);
	this.nbHeures = request.getNbHeures();
	this.tauxHoraire = request.getTauxHoraire();
    }
}
