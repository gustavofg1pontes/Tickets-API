package br.com.ifsp.tickets.api.domain.guest.entity;

import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import br.com.ifsp.tickets.api.domain.guest.validator.GuestValidator;
import br.com.ifsp.tickets.api.domain.shared.domain.AggregateRoot;
import br.com.ifsp.tickets.api.domain.shared.validation.ValidationHandler;
import lombok.Getter;

@Getter
public class Guest extends AggregateRoot<GuestID> {
    private String name;
    private Integer age;
    private String document;
    private String phoneNumber;
    private String email;
    private Profile profile;

    public Guest(GuestID guestID, String name, Integer age, String document, String phoneNumber, String email, Profile profile) {
        super(guestID);
        this.name = name;
        this.age = age;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profile = profile;
    }

    public void update(String name, Integer age, String document, String phoneNumber, String email, Profile profile) {
        this.name = name;
        this.age = age;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profile = profile;
    }
    @Override
    public void validate(ValidationHandler handler) {
        new GuestValidator(this, handler).validate();
    }

    public Profile getProfile() {
        return profile;
    }
}
