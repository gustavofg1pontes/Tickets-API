package br.com.ifsp.tickets.api.domain.event.entity;

import br.com.ifsp.tickets.api.domain.event.validator.EventValidator;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.shared.domain.AggregateRoot;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;
import br.com.ifsp.tickets.api.domain.shared.validation.ValidationHandler;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Event extends AggregateRoot<EventID> {
    private String name;
    private LocalDateTime dateTime;
    private Set<Guest> guests;
    private Integer maxQuantGuests;

    public Event(EventID eventID, String name, LocalDateTime dateTime, Set<Guest> guests, Integer maxQuantGuests) {
        super(eventID);
        this.name = name;
        this.dateTime = dateTime;
        this.guests = guests == null ? new HashSet<>() : guests;
        this.maxQuantGuests = maxQuantGuests;
    }

    public static Event with(EventID eventID, String name, LocalDateTime dateTime, Set<Guest> guests, Integer maxQuantGuests) {
        return new Event(eventID, name, dateTime, guests, maxQuantGuests);
    }

    public void addGuest(Guest guest){
        this.guests.add(guest);
    }

    public void removeGuest(Guest guest){
        this.guests.removeIf(aGuest -> aGuest.getId().getValue().equals(guest.getId().getValue()));
    }

    public Guest getGuestById(GuestID guestID){
        return this.guests.stream()
                .filter(aGuest -> aGuest.getId().getValue().equals(guestID.getValue()))
                .findFirst()
                .orElseThrow(() -> NotFoundException.with(Guest.class, guestID));
    }

    @Override
    public void validate(ValidationHandler handler) {
        new EventValidator(this, handler).validate();
    }
}
