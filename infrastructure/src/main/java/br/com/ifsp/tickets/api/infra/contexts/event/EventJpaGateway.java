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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.ifsp.tickets.api.infra.utils.SpecificationUtils.like;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventJpaGateway implements EventGateway {

    private final EventRepository eventRepository;

    @Override
    public Event create(Event aEvent) {
        return this.save(aEvent);
    }

    @Override
    public Optional<Event> findById(EventID EventID) {
        return this.eventRepository.findById(EventID.getValue()).map(EventJpaEntity::toDomain);
    }

    @Override
    public boolean existsById(EventID EventID) {
        return this.eventRepository.existsById(EventID.getValue());
    }

    @Override
    public Pagination<Event> findAll(SearchQuery searchQuery) {
        final var page = PageRequest.of(
                searchQuery.page(),
                searchQuery.perPage(),
                Sort.by(Sort.Direction.fromString(searchQuery.direction()), searchQuery.sort())
        );

        final var specifications = Optional.ofNullable(searchQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.eventRepository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(EventJpaEntity::toDomain).toList()
        );
    }

    @Override
    public Event update(Event Event) {
        return this.save(Event);
    }

    @Override
    public void deleteById(EventID EventID) {
        this.eventRepository.deleteById(EventID.getValue());
    }

    private Event save(Event event){
        final EventJpaEntity eventJpa = EventJpaEntity.from(event);
        return this.eventRepository.save(eventJpa).toDomain();
    }

    private Specification<EventJpaEntity> assembleSpecification(final String str) {
        final Specification<EventJpaEntity> name = like("name", str);
        return name;
    }
}
