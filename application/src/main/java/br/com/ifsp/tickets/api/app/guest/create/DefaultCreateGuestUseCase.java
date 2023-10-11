package br.com.ifsp.tickets.api.app.guest.create;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.InternalErrorException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotificationException;
import br.com.ifsp.tickets.api.domain.shared.validation.handler.Notification;

import java.util.function.Supplier;

public class DefaultCreateGuestUseCase extends CreateGuestUseCase {
    private final GuestGateway guestGateway;
    private final EventGateway eventGateway;

    public DefaultCreateGuestUseCase(final GuestGateway guestGateway, final EventGateway eventGateway) {
        this.guestGateway = guestGateway;
        this.eventGateway = eventGateway;
    }

    @Override
    public CreateGuestOutput execute(final CreateGuestCommand anIn) {
        final EventID eventID = EventID.from(anIn.eventID());
        final String name = anIn.name();
        final Integer age = anIn.age();
        final String document = anIn.document();
        final String phoneNumber = anIn.phoneNumber();
        final String email = anIn.email();
        final String profile = anIn.profile();

        final Event event = eventGateway.findById(eventID).orElseThrow(notFound(eventID));
        final Guest guest = Guest.with(GuestID.unique(), name, age, document, phoneNumber,
                email, Profile.valueOf(profile));

        final Notification notification = Notification.create();
        guest.validate(notification);

        if (notification.hasError())
            throw new NotificationException("Could not create guest", notification);

        event.addGuest(guest);
        return CreateGuestOutput.from(this.update(event).getGuestById(guest.getId()));
    }

    private Event update(final Event event){
        try{
            return this.eventGateway.update(event);
        }catch (final Throwable t){
            throw InternalErrorException.with(
                    "an error on update event was observed [eventID:%s]".formatted(event.getId().getValue()),
                    t);
        }
    }

    private Guest create(final Guest aGuest) {
        try {
            return this.guestGateway.create(aGuest);
        } catch (final Throwable t) {
            throw InternalErrorException.with(
                    "An error on create guest was observed [guestID:%s]".formatted(aGuest.getId().getValue()),
                    t
            );
        }
    }

    private Supplier<NotFoundException> notFound(final GuestID anId) {
        return () -> NotFoundException.with(Guest.class, anId);
    }
    private Supplier<NotFoundException> notFound(final EventID anId) {
        return () -> NotFoundException.with(Event.class, anId);
    }

}
