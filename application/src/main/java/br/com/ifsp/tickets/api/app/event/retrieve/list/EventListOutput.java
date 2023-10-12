package br.com.ifsp.tickets.api.app.event.retrieve.list;

import br.com.ifsp.tickets.api.domain.event.entity.Event;

import java.time.LocalDateTime;

public record EventListOutput(
        String id,
        String name,
        LocalDateTime localDateTime,
        Integer maxGuests
) {

    public static EventListOutput from(final Event event){
        return new EventListOutput(
                event.getId().getValue().toString(),
                event.getName(),
                event.getDateTime(),
                event.getMaxQuantGuests()
        );
    }
}
