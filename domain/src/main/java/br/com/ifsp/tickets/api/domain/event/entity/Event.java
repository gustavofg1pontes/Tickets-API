package br.com.ifsp.tickets.api.domain.event.entity;

import br.com.ifsp.tickets.api.domain.event.validator.EventValidator;
import br.com.ifsp.tickets.api.domain.shared.domain.AggregateRoot;
import br.com.ifsp.tickets.api.domain.shared.validation.ValidationHandler;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Event extends AggregateRoot<EventID> {
    private String name;
    private LocalDateTime dateTime;
    private Integer maxTickets;
    private Integer soldTickets;

    public Event(EventID eventID, String name, LocalDateTime dateTime, Integer maxQuantGuests, Integer soldTickets) {
        super(eventID);
        this.name = name;
        this.dateTime = dateTime;
        this.maxTickets = maxQuantGuests;
        this.soldTickets = soldTickets;
    }

    public static Event with(EventID eventID, String name, LocalDateTime dateTime, Integer maxQuantGuests, Integer soldTickets) {
        return new Event(eventID, name, dateTime, maxQuantGuests, soldTickets);
    }

    public void update(String name, LocalDateTime localDateTime, Integer maxQuantGuests, Integer soldTickets) {
        this.name = name;
        this.dateTime = localDateTime;
        this.maxTickets = maxQuantGuests;
        this.soldTickets = soldTickets;
    }

    public void addTicketSold() {
        this.soldTickets++;
    }

    public void removeTicketSold() {
        this.soldTickets--;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new EventValidator(this, handler).validate();
    }
}
