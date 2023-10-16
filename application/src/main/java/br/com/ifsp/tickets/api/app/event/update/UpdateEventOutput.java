package br.com.ifsp.tickets.api.app.event.update;

import br.com.ifsp.tickets.api.domain.event.entity.Event;

import java.time.LocalDateTime;

public record UpdateEventOutput(
        String id,
        String name,
        LocalDateTime dateTime,
        Integer maxGuests,
        Integer soldTickets
) {

    public static UpdateEventOutput from(final Event event) {
        return new UpdateEventOutput(
                event.getId().getValue().toString(),
                event.getName(),
                event.getDateTime(),
                event.getMaxTickets(),
                event.getSoldTickets()
        );
    }
}
