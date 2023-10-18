package br.com.ifsp.tickets.api.app.guest.toggle.enter;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;

import java.util.function.Supplier;

public class DefaultToggleEnterGuestUseCase extends ToggleEnterGuestUseCase{
    private final GuestGateway guestGateway;

    public DefaultToggleEnterGuestUseCase(GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }

    @Override
    public void execute(String anIn) {
        final GuestID guestID = GuestID.from(anIn);

        final Guest guest = this.guestGateway.findById(guestID).orElseThrow(notFound(guestID));
        guest.toggleEnter();

        this.guestGateway.update(guest);
    }

    private Supplier<NotFoundException> notFound(final GuestID GuestID) {
        return () -> NotFoundException.with(Guest.class, GuestID);
    }
}
