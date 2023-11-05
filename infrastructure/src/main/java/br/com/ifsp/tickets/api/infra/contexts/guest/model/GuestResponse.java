package br.com.ifsp.tickets.api.infra.contexts.guest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record GuestResponse(
        @JsonProperty("id") String id,
        @JsonProperty("event_id") String eventId,
        @JsonProperty("enter_id") Integer enterId,
        @JsonProperty("name") String name,
        @JsonProperty("age") Integer age,
        @JsonProperty("document") String document,
        @JsonProperty("is_blocked") boolean blocked,
        @JsonProperty("is_entered") boolean entered,
        @JsonProperty("is_left") boolean left,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("email") String email,
        @JsonProperty("profile") String profile
) {
}
