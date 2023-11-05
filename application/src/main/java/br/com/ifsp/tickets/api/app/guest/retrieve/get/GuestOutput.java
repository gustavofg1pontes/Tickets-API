package br.com.ifsp.tickets.api.app.guest.retrieve.get;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;

public record GuestOutput (
        String id,
        String eventId,
        Integer enterId,
        String name,
        Integer age,
        String document,
        boolean isBlocked,
        boolean isEntered,
        boolean isLeft,
        String phoneNumber,
        String email,
        String profile
){
    public static GuestOutput from(final Guest guest){
        return new GuestOutput(
                guest.getId().getValue().toString(),
                guest.getEventId().getValue().toString(),
                guest.getEnterId(),
                guest.getName(),
                guest.getAge(),
                guest.getDocument(),
                guest.isBlocked(),
                guest.isEntered(),
                guest.isLeft(),
                guest.getPhoneNumber(),
                guest.getEmail(),
                guest.getProfile().name()
        );
    }

}
