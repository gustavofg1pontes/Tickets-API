package br.com.ifsp.tickets.api.app.event.update;

import java.time.LocalDateTime;

public record UpdateEventCommand(
        String id,
        String name,
        LocalDateTime dateTime,
        Integer maxGuests
) {

    public static UpdateEventCommand from(final String id, final String name, final LocalDateTime dateTime, final Integer maxGuests) {
        return new UpdateEventCommand(
                id,
                name,
                dateTime,
                maxGuests
        );
    }
}
