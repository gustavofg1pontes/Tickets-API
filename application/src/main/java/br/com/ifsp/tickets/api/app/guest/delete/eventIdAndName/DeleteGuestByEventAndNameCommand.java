package br.com.ifsp.tickets.api.app.guest.delete.eventIdAndName;

public record DeleteGuestByEventAndNameCommand(
        String eventId,
        String name
) {

    public static DeleteGuestByEventAndNameCommand with(String eventId, String name){
        return new DeleteGuestByEventAndNameCommand(
                eventId,
                name
        );
    }
}
