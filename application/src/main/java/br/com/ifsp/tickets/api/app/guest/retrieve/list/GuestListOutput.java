package br.com.ifsp.tickets.api.app.guest.retrieve.list;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;

public record GuestListOutput(
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
) {
    public static GuestListOutput from(final Guest guest) {
        return new GuestListOutput(
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