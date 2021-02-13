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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "entreprises")
@Getter
@Setter
public class Entreprise {

    @Id
    @Pattern(regexp = "^EN[0-9]{2,}$")
    private String numEntreprise;

    @Column(nullable = false)
    @Length(min = 3, max = 50)
    private String design;

    @Column(nullable = false)
    @Length(min = 3, max = 50)
    private String adresse;

    @OneToMany(mappedBy = "id.numEntreprise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Travail> travails = new ArrayList<>();
}
