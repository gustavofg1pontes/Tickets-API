package br.com.ifsp.tickets.api.infra.contexts.event;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;
import br.com.ifsp.tickets.api.infra.contexts.event.persistence.EventJpaEntity;
import br.com.ifsp.tickets.api.infra.contexts.event.persistence.EventRepository;
import br.com.ifsp.tickets.api.infra.contexts.guest.persistence.GuestJpaEntity;
import br.com.ifsp.tickets.api.infra.contexts.guest.persistence.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventJpaGateway implements EventGateway {
    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;

    @Override
    public Event create(Event aEvent) {
        return this.save(aEvent);
    }

    @Override
    public Optional<Event> findById(EventID EventID) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(EventID EventID) {
        return false;
    }

    @Override
    public Pagination<Event> findAll(SearchQuery searchQuery) {
        return null;
    }

    @Override
    public Event update(Event Event) {
        return null;
    }

    @Override
    public void deleteById(EventID EventID) {

    }

    private Event save(Event event){
        final EventJpaEntity eventJpa = EventJpaEntity.from(event);
        Event event1 = this.eventRepository.save(eventJpa).toDomain();
        event1.setGuests(
                guestRepository.findAllByEvent(eventJpa
                        .getId())
                        .stream()
                        .map(GuestJpaEntity::toDomain)
                        .collect(Collectors.toSet()));
        return event1;
    }
}
