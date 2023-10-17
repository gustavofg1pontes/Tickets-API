package br.com.ifsp.tickets.api.app.guest.validate;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotificationException;
import br.com.ifsp.tickets.api.domain.shared.utils.QRCodeGenerator;
import br.com.ifsp.tickets.api.domain.shared.validation.handler.Notification;

import java.util.function.Supplier;

public class DefaultValidateGuestQRUseCase extends ValidateGuestQRUseCase{
    private final GuestGateway guestGateway;

    public DefaultValidateGuestQRUseCase(GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }

    @Override
    public ValidateGuestQROutput execute(String anIn) {
        final GuestID guestID = GuestID.from(anIn);

        if(!guestGateway.existsById(guestID))
            throw NotFoundException.with(Guest.class, guestID);

        final Guest guest = guestGateway.findById(guestID).orElseThrow(notFound(guestID));

        final Notification notification = Notification.create();
        guest.validate(notification);

        if (notification.hasError())
            throw new NotificationException("Could not create guest", notification);

        return ValidateGuestQROutput.from(QRCodeGenerator.generateQRCode(anIn), guest.getEmail());
    }

    private Supplier<NotFoundException> notFound(final GuestID anId) {
        return () -> NotFoundException.with(Guest.class, anId);
    }
}
