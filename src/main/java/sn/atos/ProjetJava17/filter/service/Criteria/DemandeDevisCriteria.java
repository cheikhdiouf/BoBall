package sn.atos.ProjetJava17.filter.service.Criteria;

import lombok.Data;
import lombok.NoArgsConstructor;
import sn.atos.ProjetJava17.filter.StringFilter;
import sn.atos.ProjetJava17.services.Criteria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class DemandeDevisCriteria implements Serializable, Criteria {

    @Serial
    private static final long serialVersionUID = 1L;

    private StringFilter name;
    private StringFilter email;
    private StringFilter phone;
    private StringFilter tripType;
    private StringFilter pays;
    private StringFilter chambre;
    private StringFilter budget;
    private StringFilter message;

    private Boolean distinct;

    public DemandeDevisCriteria(DemandeDevisCriteria other) {
        this.name = other.name == null ? null : other.name.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.tripType = other.tripType == null ? null : other.tripType.copy();
        this.pays = other.pays == null ? null : other.pays.copy();
        this.chambre = other.chambre == null ? null : other.chambre.copy();
        this.budget = other.budget == null ? null : other.budget.copy();
        this.message = other.message == null ? null : other.message.copy();
        this.distinct = other.distinct;
    }

    @Override
    public Criteria copy() {
        return new DemandeDevisCriteria(this);
    }

    /* ===== Getters helpers (pattern JHipster) ===== */

    public StringFilter name() {
        return name;
    }

    public StringFilter email() {
        return email;
    }

    public StringFilter phone() {
        return phone;
    }

    public StringFilter tripType() {
        return tripType;
    }

    public StringFilter pays() {
        return pays;
    }

    public StringFilter chambre() {
        return chambre;
    }

    public StringFilter budget() {
        return budget;
    }

    public StringFilter message() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemandeDevisCriteria that)) return false;
        return Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && Objects.equals(phone, that.phone)
                && Objects.equals(tripType, that.tripType)
                && Objects.equals(pays, that.pays)
                && Objects.equals(chambre, that.chambre)
                && Objects.equals(budget, that.budget)
                && Objects.equals(message, that.message)
                && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name, email, phone, tripType
                , pays, chambre, budget, message, distinct
        );
    }
}
