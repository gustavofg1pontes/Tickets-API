package br.com.ifsp.tickets.api.app.event.delete;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;

public class DefaultDeleteEventUseCase extends DeleteEventUseCase{
    private final EventGateway eventGateway;

    public DefaultDeleteEventUseCase(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public void execute(String anIn) {
        final EventID eventID = EventID.from(anIn);

        if(!eventGateway.existsById(eventID))
            throw NotFoundException.with(Event.class, eventID);

        this.eventGateway.deleteById(eventID);
    }
}
