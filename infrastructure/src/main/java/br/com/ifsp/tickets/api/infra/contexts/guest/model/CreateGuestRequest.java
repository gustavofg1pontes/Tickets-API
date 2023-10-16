package br.com.ifsp.tickets.api.infra.contexts.guest.model;

import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CreateGuestRequest(
        @JsonProperty("event_id") String eventID,
        @JsonProperty("name") String name,
        @JsonProperty("age") Integer age,
        @JsonProperty("document") String document,
        @JsonProperty("blocked") boolean blocked,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("email") String email,
        @JsonProperty("profile_id") Integer profileId
) {

    public Profile getProfile() {
        return Profile.get(profileId);
    }
}
