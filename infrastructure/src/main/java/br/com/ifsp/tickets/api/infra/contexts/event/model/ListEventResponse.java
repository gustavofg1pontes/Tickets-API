package br.com.ifsp.tickets.api.infra.contexts.event.model;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ListEventResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("date") LocalDateTime localDateTime,
        @JsonProperty("max_guests") Integer maxGuests,
        @JsonProperty("sold_tickets") Integer soldTickets
) {
}
