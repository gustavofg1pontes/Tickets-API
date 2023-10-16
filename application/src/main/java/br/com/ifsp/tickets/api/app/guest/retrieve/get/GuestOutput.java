package br.com.ifsp.tickets.api.app.guest.retrieve.get;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;

public record GuestOutput (
        String id,
        String name,
        Integer age,
        String document,
        boolean blocked,
        String phoneNumber,
        String email,
        String profile
){
    public static GuestOutput from(final Guest guest){
        return new GuestOutput(
                guest.getId().getValue().toString(),
                guest.getName(),
                guest.getAge(),
                guest.getDocument(),
                guest.isBlocked(),
                guest.getPhoneNumber(),
                guest.getEmail(),
                guest.getProfile().name()
        );
    }

}
