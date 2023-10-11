package br.com.ifsp.tickets.api.app.guest.create;

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

    public DefaultCreateGuestUseCase(GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }

    @Override
    public CreateGuestOutput execute(final CreateGuestCommand anIn) {
        final String name = anIn.name();
        final Integer age = anIn.age();
        final String phoneNumber = anIn.phoneNumber();
        final String email = anIn.email();
        final String profile = anIn.profile();

        final Guest guest = new Guest(GuestID.unique(), name, age, phoneNumber,
                email, Profile.valueOf(profile));
        final Notification notification = Notification.create();
        guest.validate(notification);

        if (notification.hasError())
            throw new NotificationException("Could not create guest", notification);

        return CreateGuestOutput.from(this.create(guest));
    }

    private Guest create(final Guest anAccountant) {
        try {
            return this.guestGateway.create(anAccountant);
        } catch (final Throwable t) {
            throw InternalErrorException.with(
                    "An error on create accountant was observed [accountantID:%s]".formatted(anAccountant.getId().getValue()),
                    t
            );
        }
    }

    private Supplier<NotFoundException> notFound(final GuestID anId) {
        return () -> NotFoundException.with(Guest.class, anId);
    }

}
