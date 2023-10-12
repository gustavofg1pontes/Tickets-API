package br.com.ifsp.tickets.api.app.event.retrieve.list;

import br.com.ifsp.tickets.api.app.UseCase;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;

public abstract class ListEventsUseCase extends UseCase <SearchQuery, Pagination<EventListOutput>> {
}
