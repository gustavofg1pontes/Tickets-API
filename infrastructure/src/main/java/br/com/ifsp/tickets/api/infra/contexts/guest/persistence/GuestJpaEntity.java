package br.com.ifsp.tickets.api.infra.contexts.guest.persistence;

import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.entity.profile.Profile;
import br.com.ifsp.tickets.api.infra.contexts.event.persistence.EventJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_guests")
@Getter
@NoArgsConstructor
public class GuestJpaEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "event_id", nullable = false)
    private String eventId;
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

    public Guest toDomain(){
        return Guest.with(
                new GuestID(id),
                EventID.from(eventId),
                name,
                age,
                document,
                blocked,
                phoneNumber,
                email,
                Profile.get(profileId));
    }
}
