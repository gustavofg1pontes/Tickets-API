package br.com.ifsp.tickets.api.infra.contexts.event.persistence;

import br.com.ifsp.tickets.api.infra.contexts.guest.persistence.GuestJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_events")
@Getter
@NoArgsConstructor
public class EventJpaEntity implements Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<GuestJpaEntity> guests = new HashSet<>();
    @Column(name = "max_guests",nullable = false)
    private Integer maxGuests;

}
