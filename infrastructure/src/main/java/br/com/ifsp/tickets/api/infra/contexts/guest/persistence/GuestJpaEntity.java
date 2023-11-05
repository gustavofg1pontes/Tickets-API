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
    @Column(name="name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "enter_id", updatable = false, insertable = false, nullable = false)
    private Integer enterId;
    @Column(name = "document", nullable = false)
    private String document;
    @Column(name = "is_blocked", nullable = false)
    private boolean blocked;
    @Column(name = "is_entered", nullable = false)
    private boolean entered;
    @Column(name = "is_left", nullable = false)
    private boolean left;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "profile_id", nullable = false)
    private Integer profileId;

    @Builder
    public GuestJpaEntity(UUID id, String eventId, Integer enterId, String name, Integer age, String document, boolean blocked, boolean entered, boolean left, String phoneNumber, String email, Integer profileId) {
        this.id = id;
        this.eventId = UUIDUtils.getFromString(eventId);
        this.name = name;
        this.enterId = enterId;
        this.age = age;
        this.document = document;
        this.blocked = blocked;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileId = profileId;
        this.entered = entered;
        this.left = left;
    }

    public static GuestJpaEntity from(final Guest guest){
        return GuestJpaEntity.builder()
                .id(guest.getId().getValue())
                .eventId(guest.getEventId().getValue().toString())
                .enterId(guest.getEnterId())
                .name(guest.getName())
                .age(guest.getAge())
                .document(guest.getDocument())
                .blocked(guest.isBlocked())
                .entered(guest.isEntered())
                .left(guest.isLeft())
                .phoneNumber(guest.getPhoneNumber())
                .email(guest.getEmail())
                .profileId(guest.getProfile().getId())
                .build();
    }

    public Guest toDomain(){
        return Guest.with(
                new GuestID(id),
                new EventID(eventId),
                enterId,
                name,
                age,
                document,
                blocked,
                entered,
                left,
                phoneNumber,
                email,
                Profile.get(profileId));
    }
}
