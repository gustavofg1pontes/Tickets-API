package br.com.ifsp.tickets.api.app.guest.delete.event;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.InternalErrorException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;

import java.util.function.Supplier;

public class DefaultDeleteGuestsByEventUseCase extends DeleteGuestsByEventUseCase {

    private final GuestGateway guestGateway;
    private final EventGateway eventGateway;

    public DefaultDeleteGuestsByEventUseCase(GuestGateway guestGateway, EventGateway eventGateway) {
        this.guestGateway = guestGateway;
        this.eventGateway = eventGateway;
    }

    @Override
    public void execute(String anIn) {
        EventID eventID = EventID.from(anIn);

        if (!this.eventGateway.existsById(eventID))
            throw NotFoundException.with(Event.class, eventID);

        this.guestGateway.deleteAllByEvent(eventID);

        final Event event = eventGateway.findById(eventID)
                .orElseThrow(notFound(eventID));
        event.setSoldTickets(0);
        this.update(event);
    }

    private void update(final Event event){
        try{
            this.eventGateway.update(event);
        }catch (final Throwable t){
            throw InternalErrorException.with(
                    "an error on update event was observed [eventID:%s]".formatted(event.getId().getValue()),
                    t);
        }
    }

    private Supplier<NotFoundException> notFound(final GuestID anId) {
        return () -> NotFoundException.with(Guest.class, anId);
    }
    private Supplier<NotFoundException> notFound(final EventID anId) {
        return () -> NotFoundException.with(Event.class, anId);
    }

}
