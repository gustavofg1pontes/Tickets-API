package br.com.ifsp.tickets.api.infra.contexts.guest.presenters;

import br.com.ifsp.tickets.api.app.guest.retrieve.get.GuestOutput;
import br.com.ifsp.tickets.api.app.guest.retrieve.list.GuestListOutput;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestOutput;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.GuestResponse;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.ListGuestResponse;

public interface GuestApiPresenter {

    static GuestResponse present(final GuestOutput guestOutput) {
        return new GuestResponse(
                guestOutput.id(),
                guestOutput.eventId(),
                guestOutput.name(),
                guestOutput.age(),
                guestOutput.document(),
                guestOutput.blocked(),
                guestOutput.phoneNumber(),
                guestOutput.email(),
                guestOutput.profile());
    }

    static ListGuestResponse present(final GuestListOutput guestOutput) {
        return new ListGuestResponse(
                guestOutput.id(),
                guestOutput.eventId(),
                guestOutput.name(),
                guestOutput.age(),
                guestOutput.document(),
                guestOutput.blocked(),
                guestOutput.phoneNumber(),
                guestOutput.email(),
                guestOutput.profile()
        );
    }

    static GuestResponse present(final UpdateGuestOutput guestOutput){
        return new GuestResponse(
                guestOutput.id(),
                guestOutput.eventId(),
                guestOutput.name(),
                guestOutput.age(),
                guestOutput.document(),
                guestOutput.blocked(),
                guestOutput.phoneNumber(),
                guestOutput.email(),
                guestOutput.profile()
        );
    }
}
