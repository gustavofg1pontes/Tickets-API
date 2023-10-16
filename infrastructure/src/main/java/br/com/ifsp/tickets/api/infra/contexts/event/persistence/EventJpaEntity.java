package br.com.ifsp.tickets.api.infra.contexts.event.persistence;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.infra.contexts.guest.persistence.GuestJpaEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CV_EVENTS")
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
    @Column(name = "max_guests", nullable = false)
    private Integer maxGuests;
    @Column(name = "sold_tickets", nullable = false)
    private Integer soldTickets;


    @Builder
    public EventJpaEntity(UUID id, String name, LocalDateTime dateTime, Integer maxGuests, Integer soldTickets) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.maxGuests = maxGuests;
        this.soldTickets = soldTickets;
    }


    public static EventJpaEntity from(final Event event) {
        return EventJpaEntity.builder()
                .id(event.getId().getValue())
                .name(event.getName())
                .dateTime(event.getDateTime())
                .maxGuests(event.getMaxTickets())
                .soldTickets(event.getSoldTickets())
                .build();
    }

    public Event toDomain(){
        return Event.with(
                new EventID(this.id),
                this.getName(),
                this.getDateTime(),
                null,
                this.getMaxGuests(),
                this.getSoldTickets()
        );
    }

}
