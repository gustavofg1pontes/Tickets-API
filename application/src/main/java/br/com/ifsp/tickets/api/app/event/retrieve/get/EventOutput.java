package br.com.ifsp.tickets.api.app.event.retrieve.get;

import br.com.ifsp.tickets.api.app.guest.retrieve.get.GuestOutput;
import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record EventOutput(
        String id,
        String name,
        LocalDateTime dateTime,
        Integer maxGuests,
        Integer soldTickets
) {

    public static EventOutput from(final Event event){
        return new EventOutput(
                event.getId().getValue().toString(),
                event.getName(),
                event.getDateTime(),
                event.getMaxTickets(),
                event.getSoldTickets()
        );
    }

}
