package br.com.ifsp.tickets.api.infra.contexts.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record EditEventResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("date") LocalDateTime localDateTime,
        @JsonProperty("max_guests") Integer maxGuests,
        @JsonProperty("sold_tickets") Integer soldTickets
) {
}
