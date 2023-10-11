package br.com.ifsp.tickets.api.app.guest.retrieve.list;

import br.com.ifsp.tickets.api.app.UseCase;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;

public abstract class ListGuestsUseCase extends UseCase<SearchQuery, Pagination<GuestListOutput>> {
}
