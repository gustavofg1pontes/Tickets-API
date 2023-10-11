package br.com.ifsp.tickets.api.domain.event.gateway;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;

import java.util.Optional;

public interface EventGateway {
    Event create(final Event aEvent);

    Optional<Event> findById(final EventID EventID);

    boolean existsById(final EventID EventID);

    Pagination<Event> findAll(final SearchQuery searchQuery);

    Event update(final Event Event);

    void deleteById(final EventID EventID);
}
