package br.com.ifsp.tickets.api.domain.guest.entity;

import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import br.com.ifsp.tickets.api.domain.guest.validator.GuestValidator;
import br.com.ifsp.tickets.api.domain.shared.domain.AggregateRoot;
import br.com.ifsp.tickets.api.domain.shared.validation.ValidationHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guest extends AggregateRoot<GuestID> {
    private String name;
    private EventID eventId;
    private Integer age;
    private String document;
    private boolean blocked;
    private String phoneNumber;
    private String email;
    private Profile profile;

    public Guest(GuestID guestID, String name, EventID eventId, Integer age, String document, boolean blocked, String phoneNumber, String email, Profile profile) {
        super(guestID);
        this.name = name;
        this.eventId = eventId;
        this.age = age;
        this.document = document;
        this.blocked = blocked;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profile = profile;
    }

    public static Guest with(GuestID guestID, EventID eventId, String name, Integer age, String document, boolean blocked, String phoneNumber, String email, Profile profile) {
        return new Guest(guestID, name, eventId, age, document, blocked, phoneNumber, email, profile);
    }

    public void update(String name, Integer age, String document, boolean blocked, String phoneNumber, String email, Profile profile) {
        this.name = name;
        this.age = age;
        this.document = document;
        this.blocked = blocked;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profile = profile;
    }

    public void toggleBlocked(){
        this.blocked = !this.blocked;
    }
    @Override
    public void validate(ValidationHandler handler) {
        new GuestValidator(this, handler).validate();
    }

    public Profile getProfile() {
        return profile;
    }
}
