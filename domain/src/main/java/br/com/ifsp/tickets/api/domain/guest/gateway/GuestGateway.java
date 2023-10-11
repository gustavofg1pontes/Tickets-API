package br.com.ifsp.tickets.api.domain.guest.gateway;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.guest.entity.GuestID;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;

import java.util.Optional;

public interface GuestGateway {
    Guest create(final Guest aGuest);

    Optional<Guest> findById(final GuestID aGuestID);

    boolean existsById(final GuestID aGuestID);

    Pagination<Guest> findAll(final SearchQuery searchQuery);

    Guest update(final Guest aGuest);

    void deleteById(final GuestID aGuestID);
}
