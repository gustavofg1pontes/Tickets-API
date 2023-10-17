package br.com.ifsp.tickets.api.infra.contexts.guest;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;
import br.com.ifsp.tickets.api.infra.contexts.guest.persistence.GuestJpaEntity;
import br.com.ifsp.tickets.api.infra.contexts.guest.persistence.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.ifsp.tickets.api.infra.utils.SpecificationUtils.like;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuestJpaGateway implements GuestGateway {
    private final GuestRepository guestRepository;

    @Override
    public Guest create(Guest aGuest) {
        return this.save(aGuest);
    }

    @Override
    public Optional<Guest> findById(GuestID aGuestID) {
        return this.guestRepository.findById(aGuestID.getValue()).map(GuestJpaEntity::toDomain);
    }

    @Override
    public Optional<Guest> findByEventIdAndName(EventID eventID, String name) {
        return this.guestRepository.findByEventIdAndName(eventID.getValue(), name).map(GuestJpaEntity::toDomain);
    }

    @Override
    public Set<Guest> findAllByEventId(EventID eventID) {
        return this.guestRepository.findAllByEvent(
                eventID.getValue())
                .stream()
                .map(GuestJpaEntity::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean existsById(GuestID aGuestID) {
        return this.guestRepository.existsById(aGuestID.getValue());
    }

    @Override
    public Pagination<Guest> findAll(SearchQuery searchQuery) {
        final var page = PageRequest.of(
                searchQuery.page(),
                searchQuery.perPage(),
                Sort.by(Sort.Direction.fromString(searchQuery.direction()), searchQuery.sort())
        );

        final var specifications = Optional.ofNullable(searchQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.guestRepository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(GuestJpaEntity::toDomain).toList()
        );
    }

    @Override
    public Guest update(Guest aGuest) {
        return this.save(aGuest);
    }

    @Override
    public void deleteById(GuestID aGuestID) {
        this.guestRepository.deleteById(aGuestID.getValue());
    }

    @Override
    public void deleteByEventIdAndName(UUID eventId, String name) {
        this.guestRepository.deleteByEventIdAndName(eventId, name);
    }


    private Guest save(Guest aGuest){
        final GuestJpaEntity guestJpaEntity = GuestJpaEntity.from(aGuest);
        return this.guestRepository.save(guestJpaEntity).toDomain();
    }

    private Specification<GuestJpaEntity> assembleSpecification(final String str) {
        final Specification<GuestJpaEntity> name = like("name", str);
        final Specification<GuestJpaEntity> document = like("document", str);
        final Specification<GuestJpaEntity> email = like("email", str);
        final Specification<GuestJpaEntity> phoneNumber = like("phoneNumber", str);
        return name.or(document).or(email).or(phoneNumber);
    }
}
