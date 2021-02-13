package mg.eni.app.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    @NotNull
    private int nbHeures;

    @NotNull
    private double tauxHoraire;
}
