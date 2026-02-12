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
public class ReservationCriteria implements Serializable, Criteria {

    @Serial
    private static final long serialVersionUID = 1L;

    // Informations personnelles
    private StringFilter firstName;
    private StringFilter lastName;
    private StringFilter email;
    private StringFilter phone;
    private StringFilter passportNumber;

    // Choix du voyage
    private StringFilter packageType;
    private StringFilter roomType;
    private StringFilter paymentMethod;

    // Paiement
    private StringFilter mobileMoneyProvider;
    private StringFilter mobileMoneyNumber;
    private StringFilter bankName;

    // Infos supplémentaires
    private StringFilter specialRequests;

    private Boolean distinct;

    public ReservationCriteria(ReservationCriteria other) {
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.passportNumber = other.passportNumber == null ? null : other.passportNumber.copy();

        this.packageType = other.packageType == null ? null : other.packageType.copy();
        this.roomType = other.roomType == null ? null : other.roomType.copy();
        this.paymentMethod = other.paymentMethod == null ? null : other.paymentMethod.copy();

        this.mobileMoneyProvider = other.mobileMoneyProvider == null ? null : other.mobileMoneyProvider.copy();
        this.mobileMoneyNumber = other.mobileMoneyNumber == null ? null : other.mobileMoneyNumber.copy();
        this.bankName = other.bankName == null ? null : other.bankName.copy();

        this.specialRequests = other.specialRequests == null ? null : other.specialRequests.copy();

        this.distinct = other.distinct;
    }

    @Override
    public Criteria copy() {
        return new ReservationCriteria(this);
    }

    /* ===== Getters helpers (pattern JHipster) ===== */

    public StringFilter firstName() {
        return firstName;
    }

    public StringFilter lastName() {
        return lastName;
    }

    public StringFilter email() {
        return email;
    }

    public StringFilter phone() {
        return phone;
    }

    public StringFilter passportNumber() {
        return passportNumber;
    }

    public StringFilter packageType() {
        return packageType;
    }

    public StringFilter roomType() {
        return roomType;
    }

    public StringFilter paymentMethod() {
        return paymentMethod;
    }

    public StringFilter mobileMoneyProvider() {
        return mobileMoneyProvider;
    }

    public StringFilter mobileMoneyNumber() {
        return mobileMoneyNumber;
    }

    public StringFilter bankName() {
        return bankName;
    }

    public StringFilter specialRequests() {
        return specialRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationCriteria that)) return false;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(email, that.email)
                && Objects.equals(phone, that.phone)
                && Objects.equals(passportNumber, that.passportNumber)
                && Objects.equals(packageType, that.packageType)
                && Objects.equals(roomType, that.roomType)
                && Objects.equals(paymentMethod, that.paymentMethod)
                && Objects.equals(mobileMoneyProvider, that.mobileMoneyProvider)
                && Objects.equals(mobileMoneyNumber, that.mobileMoneyNumber)
                && Objects.equals(bankName, that.bankName)
                && Objects.equals(specialRequests, that.specialRequests)
                && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                firstName, lastName, email, phone, passportNumber,
                packageType, roomType, paymentMethod,
                mobileMoneyProvider, mobileMoneyNumber, bankName,
                specialRequests, distinct
        );
    }
}
