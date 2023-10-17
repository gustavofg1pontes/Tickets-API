package br.com.ifsp.tickets.api.app.guest.delete.eventIdAndName;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.InternalErrorException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;
import br.com.ifsp.tickets.api.domain.shared.validation.Error;

import java.util.function.Supplier;

public class DefaultDeleteGuestByEventAndNameUseCase extends DeleteGuestByEventAndNameUseCase {
    private final GuestGateway guestGateway;
    private final EventGateway eventGateway;

    public DefaultDeleteGuestByEventAndNameUseCase(GuestGateway guestGateway, EventGateway eventGateway) {
        this.guestGateway = guestGateway;
        this.eventGateway = eventGateway;
    }

    @Override
    public void execute(DeleteGuestByEventAndNameCommand anIn) {
        final EventID eventID = EventID.from(anIn.eventId());
        final String name = anIn.name();


        if (!this.eventGateway.existsById(eventID))
            throw NotFoundException.with(Event.class, eventID);

        final Guest guest = guestGateway.findByEventIdAndName(eventID, name).orElseThrow(notFound(eventID, name));
        System.out.println(guest.getId().getValue());
        final Event event = eventGateway.findById(guest.getEventId())
                .orElseThrow(notFound(guest.getEventId()));
        event.setGuests(this.guestGateway.findAllByEventId(event.getId()));

        event.withdrawGuest(guest);
        this.guestGateway.deleteByEventIdAndName(eventID.getValue(), name);
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

    private Supplier<NotFoundException> notFound(final EventID eventID, String name) {
        return () -> NotFoundException.with(new Error("guest not found"));
    }
    private Supplier<NotFoundException> notFound(final EventID anId) {
        return () -> NotFoundException.with(Event.class, anId);
    }
}
