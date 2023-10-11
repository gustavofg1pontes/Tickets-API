package br.com.ifsp.tickets.api.app.guest.delete;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;

public class DefaultDeleteGuestUseCase extends DeleteGuestUseCase{
    private final GuestGateway guestGateway;

    public DefaultDeleteGuestUseCase(final GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }

    @Override
    public void execute(String anIn) {
        final GuestID guestID = GuestID.from(anIn);
        if (!this.guestGateway.existsById(guestID))
            throw NotFoundException.with(Guest.class, guestID);

        this.guestGateway.deleteById(guestID);
    }
}
