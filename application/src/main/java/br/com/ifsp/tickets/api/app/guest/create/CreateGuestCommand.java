package br.com.ifsp.tickets.api.app.guest.create;

import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;

public record CreateGuestCommand (
        String name,
        Integer age,
        String document,
        String phoneNumber,
        String email,
        String profile
) {

    public static CreateGuestCommand with(final String name, final  Integer age, final String document,
                                          final String phoneNumber, String email, final Profile profile){
        return new CreateGuestCommand(name, age, document, phoneNumber, email, profile.name());
    }
}
