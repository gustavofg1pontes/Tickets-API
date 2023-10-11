package br.com.ifsp.tickets.api.app.guest.retrieve.get;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;

import java.util.function.Supplier;

public class DefaultGetGuestByIdUseCase extends GetGuestByIdUseCase{
    private final GuestGateway guestGateway;

    public DefaultGetGuestByIdUseCase(final GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }
    @Override
    public GuestOutput execute(String anIn) {
        final GuestID guestID = GuestID.from(anIn);
        final Guest guest = this.guestGateway.findById(guestID).orElseThrow(notFound(guestID));
        return GuestOutput.from(guest);
    }


    private Supplier<NotFoundException> notFound(final GuestID GuestID) {
        return () -> NotFoundException.with(Guest.class, GuestID);
    }
}
