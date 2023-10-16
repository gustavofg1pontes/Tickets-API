package br.com.ifsp.tickets.api.infra.contexts.guest.persistence;

import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import br.com.ifsp.tickets.api.domain.shared.utils.UUIDUtils;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "CV_GUESTS")
@Getter
@NoArgsConstructor
public class GuestJpaEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "event_id", nullable = false)
    private UUID eventId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String document;
    @Column(nullable = false)
    private boolean blocked;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(name = "profile_id", nullable = false)
    private Integer profileId;

    @Builder
    public GuestJpaEntity(UUID id, String eventId, String name, Integer age, String document, boolean blocked, String phoneNumber, String email, Integer profileId) {
        this.id = id;
        this.eventId = UUIDUtils.getFromString(eventId);
        this.name = name;
        this.age = age;
        this.document = document;
        this.blocked = blocked;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileId = profileId;
    }

    public static GuestJpaEntity from(final Guest guest){
        return GuestJpaEntity.builder()
                .id(guest.getId().getValue())
                .eventId(guest.getEventId().getValue().toString())
                .name(guest.getName())
                .age(guest.getAge())
                .document(guest.getDocument())
                .blocked(guest.isBlocked())
                .phoneNumber(guest.getPhoneNumber())
                .email(guest.getEmail())
                .profileId(guest.getProfile().getId())
                .build();
    }

    public Guest toDomain(){
        return Guest.with(
                new GuestID(id),
                new EventID(eventId),
                name,
                age,
                document,
                blocked,
                phoneNumber,
                email,
                Profile.get(profileId));
    }
}
