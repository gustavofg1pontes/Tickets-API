package br.com.ifsp.tickets.api.app.event.retrieve.get;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;

import java.util.function.Supplier;

public class DefaultGetEventByIdUseCase extends GetEventByIdUseCase {
    private final EventGateway eventGateway;
    private final GuestGateway guestGateway;


    public DefaultGetEventByIdUseCase(EventGateway eventGateway, GuestGateway guestGateway) {
        this.eventGateway = eventGateway;
        this.guestGateway = guestGateway;
    }

    @Override
    public EventOutput execute(String anIn) {
        final EventID eventID = EventID.from(anIn);
        final EventOutput eventOutput = EventOutput.from(
                eventGateway.findById(eventID).orElseThrow(notFound(eventID)),
                this.guestGateway.findAllByEventId(eventID)
        );
        return eventOutput;
    }


    private Supplier<NotFoundException> notFound(final EventID anId) {
        return () -> NotFoundException.with(Event.class, anId);
    }
}
