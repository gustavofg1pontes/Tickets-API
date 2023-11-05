package br.com.ifsp.tickets.api.infra.contexts.event.model;

import br.com.ifsp.tickets.api.infra.contexts.guest.model.GuestResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record EventResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("date") LocalDateTime localDateTime,
        @JsonProperty("max_guests") Integer maxGuests,
        @JsonProperty("sold_tickets") Integer soldTickets
) {
}
