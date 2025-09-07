package sn.dev.filter.service.Criteria;

import lombok.Data;
import lombok.NoArgsConstructor;
import sn.dev.filter.StringFilter;
import sn.dev.services.Criteria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
@Data
@NoArgsConstructor
public class JoueurCriteria implements Serializable, Criteria {


    @Serial
    private static final long serialVersionUID = 1L;
    private StringFilter nom;
    private StringFilter prenom;
    private boolean distinct;

    public JoueurCriteria(JoueurCriteria other) {
        this.nom = other.nom == null ? null : other.nom.copy();
        this.prenom = other.prenom  == null ? null : other.prenom .copy();


        this.distinct = other.distinct;
    }
    @Override
    public Criteria copy() {
        return new JoueurCriteria(this);
    }

    public StringFilter prenom () {
        return prenom ;
    }
    public StringFilter nom () {
        return nom ;
    }




    public Boolean getDistinct() {
        return distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoueurCriteria that = (JoueurCriteria) o;
        return distinct == that.distinct && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, distinct);
    }
}
