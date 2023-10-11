package br.com.ifsp.tickets.api.app.guest.create;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;

public record CreateGuestOutput (
        String id
){

    public static CreateGuestOutput from(final Guest guest){
        return new CreateGuestOutput(guest.getId().getValue().toString());
    }
}
