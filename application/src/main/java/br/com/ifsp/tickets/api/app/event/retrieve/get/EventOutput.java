package br.com.ifsp.tickets.api.app.event.retrieve.get;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;

import java.time.LocalDateTime;
import java.util.Set;

public record EventOutput(
        String id,
        String name,
        LocalDateTime dateTime,
        Set<Guest> guests,
        Integer maxGuests
) {

    public static EventOutput from(final Event event){
        return new EventOutput(
                event.getId().getValue().toString(),
                event.getName(),
                event.getDateTime(),
                event.getGuests(),
                event.getMaxQuantGuests()
        );
    }
}
