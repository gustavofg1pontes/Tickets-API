package br.com.ifsp.tickets.api.infra.contexts.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EditEventResponse(
        @JsonProperty("name") String name,
        @JsonProperty("date") LocalDateTime localDateTime,
        @JsonProperty("maxGuests") Integer maxGuests,
        @JsonProperty("soldTickets") Integer soldTickets
) {
}
