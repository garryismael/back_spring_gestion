package mg.eni.app.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TravailRequest implements ITravailRequest{
    @NotNull
    @Pattern(regexp = "^EN[0-9]{2,}$")
    private String numEntreprise;

    @NotNull
    private int nbHeures;

    @NotNull
    private double tauxHoraire;
}
