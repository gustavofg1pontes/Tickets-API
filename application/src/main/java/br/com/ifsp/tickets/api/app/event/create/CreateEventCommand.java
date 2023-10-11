package br.com.ifsp.tickets.api.app.event.create;

import java.time.LocalDateTime;

public record CreateEventCommand(
        String name,
        LocalDateTime dateTime,
        Integer maxGuests
) {
    public static CreateEventCommand with(final String name, final LocalDateTime localDateTime, final Integer maxGuests){
        return new CreateEventCommand(name, localDateTime, maxGuests);
    }
}
