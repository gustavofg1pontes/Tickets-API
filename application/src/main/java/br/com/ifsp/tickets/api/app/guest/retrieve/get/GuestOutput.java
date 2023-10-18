package br.com.ifsp.tickets.api.app.guest.retrieve.get;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;

public record GuestOutput (
        String id,
        String eventId,
        String name,
        Integer age,
        String document,
        boolean blocked,
        boolean hasEntered,
        boolean hasLeft,
        String phoneNumber,
        String email,
        String profile
){
    public static GuestOutput from(final Guest guest){
        return new GuestOutput(
                guest.getId().getValue().toString(),
                guest.getEventId().getValue().toString(),
                guest.getName(),
                guest.getAge(),
                guest.getDocument(),
                guest.isBlocked(),
                guest.isHasEntered(),
                guest.isHasLeft(),
                guest.getPhoneNumber(),
                guest.getEmail(),
                guest.getProfile().name()
        );
    }

}
