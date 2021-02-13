package mg.eni.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employes")
@Getter
@Setter
@NoArgsConstructor
public class Employe {
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

    @OneToMany(mappedBy = "id.numEmploye", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JsonBackReference
    private List<Travail> travails = new ArrayList<>();

    public Employe(String numEmploye, String name, String address) {
	this.numEmploye = numEmploye;
	this.nom = name;
	this.adresse = address;
    }
}
