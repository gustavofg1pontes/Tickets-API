package br.com.ifsp.tickets.api.app.event.create;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotificationException;
import br.com.ifsp.tickets.api.domain.shared.validation.handler.Notification;

import java.time.LocalDateTime;

public class DefaultCreateEventUseCase extends CreateEventUseCase {
    private final EventGateway eventGateway;

    public DefaultCreateEventUseCase(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public CreateEventOutput execute(CreateEventCommand anIn) {
        final String name = anIn.name();
        final LocalDateTime dateTime = anIn.dateTime();
        final Integer maxGuests = anIn.maxGuests();

        final Event event = Event.with(EventID.unique(), name, dateTime, maxGuests, 0);

        final Notification notification = Notification.create();
        event.validate(notification);

        if (notification.hasError())
            throw new NotificationException("Could not create event", notification);

        return CreateEventOutput.from(this.eventGateway.create(event));
    }

}
