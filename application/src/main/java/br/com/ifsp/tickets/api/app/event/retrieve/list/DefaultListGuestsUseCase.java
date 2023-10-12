package br.com.ifsp.tickets.api.app.event.retrieve.list;

import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;

public class DefaultListGuestsUseCase extends ListEventsUseCase {
    private final EventGateway eventGateway;

    public DefaultListGuestsUseCase(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public Pagination<EventListOutput> execute(SearchQuery anIn) {
        return this.eventGateway.findAll(anIn).map(EventListOutput::from);
    }
}
