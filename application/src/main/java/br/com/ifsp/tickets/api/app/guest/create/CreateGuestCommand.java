package br.com.ifsp.tickets.api.app.guest.create;

import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;

public record CreateGuestCommand (
        String eventID,
        String name,
        Integer age,
        String document,
        boolean blocked,
        String phoneNumber,
        String email,
        Profile profile
) {

    public static CreateGuestCommand with(final String eventID, final String name, final  Integer age, final String document,
                                          boolean blocked, final String phoneNumber, String email, final Profile profile){
        return new CreateGuestCommand(eventID, name, age, document, blocked, phoneNumber, email, profile);
    }
}
