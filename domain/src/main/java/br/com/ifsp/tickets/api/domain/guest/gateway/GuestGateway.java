package br.com.ifsp.tickets.api.domain.guest.gateway;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GuestGateway {
    Guest create(final Guest aGuest);

    Optional<Guest> findById(final GuestID aGuestID);
    Optional<Guest> findByEventIdAndName(EventID eventID, String name);

    boolean existsById(final GuestID aGuestID);

    Pagination<Guest> findAll(final SearchQuery searchQuery);

    Guest update(final Guest aGuest);

    void deleteById(final GuestID aGuestID);
    void deleteAllByEvent(final EventID eventID);
    void deleteByEventIdAndName(UUID eventId, String name);

    Set<Guest> findAllByEventId(final EventID eventID);

}
