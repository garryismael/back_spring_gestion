package mg.eni.app.request;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeRequest {
    @Column(nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String nom;

    @Column(nullable = false)
    @NotNull
    @Length(min = 3, max = 50)
    private String adresse;
}
