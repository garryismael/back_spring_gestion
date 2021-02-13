package mg.eni.app.request;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntrepriseRequest {
    @Column(nullable = false)
    @Length(min = 3, max = 50)
    private String design;

    @Column(nullable = false)
    @Length(min = 3, max = 50)
    private String adresse;
}
