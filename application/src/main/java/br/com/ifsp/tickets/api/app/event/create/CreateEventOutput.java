package br.com.ifsp.tickets.api.app.event.create;

import br.com.ifsp.tickets.api.domain.event.entity.Event;

public record CreateEventOutput(
        String id
) {
    public static CreateEventOutput from(final String id){
        return new CreateEventOutput(id);
    }

    public static CreateEventOutput from(final Event event){
        return new CreateEventOutput(event.getId().getValue().toString());
    }
}
