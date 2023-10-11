package br.com.ifsp.tickets.api.app.guest.update;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;

public record UpdateGuestOutput(
        String id,
        String name,
        Integer age,
        String document,
        String phoneNumber,
        String email,
        String profile
) {
    public static UpdateGuestOutput from(final Guest guest) {
        return new UpdateGuestOutput(
                guest.getId().getValue().toString(),
                guest.getName(),
                guest.getAge(),
                guest.getDocument(),
                guest.getPhoneNumber(),
                guest.getEmail(),
                guest.getProfile().name()
        );
    }

}
