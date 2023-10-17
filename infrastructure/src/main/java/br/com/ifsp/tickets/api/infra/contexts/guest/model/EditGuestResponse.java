package br.com.ifsp.tickets.api.infra.contexts.guest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EditGuestResponse(
        @JsonProperty("id") String id,
        @JsonProperty("event_id") String eventId,
        @JsonProperty("name") String name,
        @JsonProperty("age") Integer age,
        @JsonProperty("document") String document,
        @JsonProperty("blocked") boolean blocked,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("email") String email,
        @JsonProperty("profile") String profile
) {
}