package br.com.ifsp.tickets.api.infra.contexts.guest.model;

import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EditGuestRequest(
        @JsonProperty("name") String name,
        @JsonProperty("age") Integer age,
        @JsonProperty("document") String document,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("email") String email,
        @JsonProperty("profile_id") Integer profileId
) {

    @JsonIgnore
    public Profile getProfile() {
        return Profile.get(profileId);
    }
}
