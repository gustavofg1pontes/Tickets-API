package br.com.ifsp.tickets.api.app.guest.update;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.InternalErrorException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotificationException;
import br.com.ifsp.tickets.api.domain.shared.validation.handler.Notification;

import java.util.function.Supplier;

public class DefaultUpdateGuestUseCase extends UpdateGuestUseCase{
    private final GuestGateway guestGateway;

    public DefaultUpdateGuestUseCase(final GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }

    @Override
    public UpdateGuestOutput execute(UpdateGuestCommand anIn) {
        final GuestID guestID = GuestID.from(anIn.id());
        final String name = anIn.name();
        final Integer age = anIn.age();
        final String document = anIn.document();
        final boolean blocked = anIn.blocked();
        final String phoneNumber = anIn.phoneNumber();
        final String email = anIn.email();
        final String profile = anIn.profile();

        final Guest guest = guestGateway.findById(guestID).orElseThrow(notFound(guestID));
        guest.update(name, age, document, blocked, phoneNumber, email, Profile.valueOf(profile));

        final Notification notification = Notification.create();
        guest.validate(notification);

        if (notification.hasError())
            throw new NotificationException("Could not update guest", notification);

        return UpdateGuestOutput.from(this.update(guest));
    }

    private Guest update(final Guest aGuest) {
        try {
            return this.guestGateway.update(aGuest);
        } catch (final Throwable t) {
            throw InternalErrorException.with(
                    "An error on update guest was observed [guestID:%s]".formatted(aGuest.getId().getValue()),
                    t
            );
        }
    }

    private Supplier<NotFoundException> notFound(final GuestID anId) {
        return () -> NotFoundException.with(Guest.class, anId);
    }
}
