package br.com.ifsp.tickets.api.app.guest.retrieve.list;

import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;

public class DefaultListGuestsUseCase extends ListGuestsUseCase{
    private final GuestGateway guestGateway;

    public DefaultListGuestsUseCase(final GuestGateway guestGateway) {
        this.guestGateway = guestGateway;
    }
    @Override
    public Pagination<GuestListOutput> execute(SearchQuery anIn) {
        return this.guestGateway.findAll(anIn).map(GuestListOutput::from);
    }
}
