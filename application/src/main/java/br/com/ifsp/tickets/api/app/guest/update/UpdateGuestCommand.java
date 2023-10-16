package br.com.ifsp.tickets.api.app.guest.update;

import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;

public record UpdateGuestCommand (
        String id,
        String name,
        Integer age,
        String document,
        boolean blocked,
        String phoneNumber,
        String email,
        Profile profile
) {

    public static UpdateGuestCommand from(final String id, final String name, final Integer age, final String document,
                                          final boolean blocked, final String phoneNumber, final String email, final Profile profile){
        return new UpdateGuestCommand(
                id,
                name,
                age,
                document,
                blocked,
                phoneNumber,
                email,
                profile
        );
    }
}
