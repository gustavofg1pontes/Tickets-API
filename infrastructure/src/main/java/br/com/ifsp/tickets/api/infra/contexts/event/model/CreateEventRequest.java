package br.com.ifsp.tickets.api.infra.contexts.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Builder
public record CreateEventRequest(
        @JsonProperty("name") String name,
        @JsonProperty("date") String dateTime,
        @JsonProperty("max_guests") Integer maxGuests
) {

    public LocalDateTime getDateTime() {
        if (Pattern.matches(".*[+-]\\d{2}:\\d{2}$", dateTime)) {
            final ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return zonedDateTime.toLocalDateTime();
        } else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }

}
