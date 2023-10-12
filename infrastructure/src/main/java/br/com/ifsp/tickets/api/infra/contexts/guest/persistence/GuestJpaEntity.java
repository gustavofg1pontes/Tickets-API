package br.com.ifsp.tickets.api.infra.contexts.guest.persistence;

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
    @ManyToOne
    @Column(nullable = false)
    private EventJpaEntity event;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String document;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Profile profile;
}
