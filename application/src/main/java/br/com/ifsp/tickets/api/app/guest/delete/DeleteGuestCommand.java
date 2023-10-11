package br.com.ifsp.tickets.api.app.guest.delete;

public record DeleteGuestCommand(
    String eventID,
    String guestID
) {
}
