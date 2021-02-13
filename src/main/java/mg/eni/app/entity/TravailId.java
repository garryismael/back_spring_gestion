package mg.eni.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TravailId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "^E[0-9]{2,}$")
    private String numEmploye;

    @Pattern(regexp = "^EN[0-9]{2,}$")
    private String numEntreprise;

    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}
	TravailId travailId = (TravailId) o;
	return numEmploye.equals(travailId.getNumEmploye()) && numEntreprise.equals(travailId.getNumEntreprise());
    }

    @Override
    public int hashCode() {
	return Objects.hash(numEmploye, numEntreprise);
    }
}
