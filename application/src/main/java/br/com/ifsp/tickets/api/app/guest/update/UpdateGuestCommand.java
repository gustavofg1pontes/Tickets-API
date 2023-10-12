package br.com.ifsp.tickets.api.app.guest.update;

import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;

public record UpdateGuestCommand (
        String id,
        String name,
        Integer age,
        String document,
        String phoneNumber,
        String email,
        String profile
) {

    public static UpdateGuestCommand from(final String id, final String name, final Integer age, final String document,
                                          final String phoneNumber, final String email, final Profile profile){
        return new UpdateGuestCommand(
                id,
                name,
                age,
                document,
                phoneNumber,
                email,
                profile.name()
        );
    }
}
