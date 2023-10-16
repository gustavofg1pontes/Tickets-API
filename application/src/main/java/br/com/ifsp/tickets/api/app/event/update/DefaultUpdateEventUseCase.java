package br.com.ifsp.tickets.api.app.event.update;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.InternalErrorException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotificationException;
import br.com.ifsp.tickets.api.domain.shared.validation.handler.Notification;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class DefaultUpdateEventUseCase extends UpdateEventUseCase {
    private final EventGateway eventGateway;

    public DefaultUpdateEventUseCase(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public UpdateEventOutput execute(UpdateEventCommand anIn) {
        final EventID eventID = EventID.from(anIn.id());
        final String name = anIn.name();
        final LocalDateTime dateTime = anIn.dateTime();
        final Integer maxGuests = anIn.maxGuests();
        final Integer soldTickets = anIn.soldTickets();

        final Event event = eventGateway.findById(eventID).orElseThrow(notFound(eventID));
        event.update(name, dateTime, maxGuests, soldTickets);

        final Notification notification = Notification.create();
        event.validate(notification);

        if (notification.hasError())
            throw new NotificationException("Could not update guest", notification);

        return UpdateEventOutput.from(this.update(event));
    }

    private Event update(final Event event) {
        try {
            return this.eventGateway.update(event);
        } catch (final Throwable t) {
            throw InternalErrorException.with(
                    "An error on update event was observed [eventID:%s]".formatted(event.getId().getValue()),
                    t
            );
        }
    }


    private Supplier<NotFoundException> notFound(final EventID anId) {
        return () -> NotFoundException.with(Event.class, anId);
    }
}
