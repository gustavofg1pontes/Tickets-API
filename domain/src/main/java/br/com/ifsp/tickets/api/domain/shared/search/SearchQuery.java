package br.com.ifsp.tickets.api.domain.shared.search;

public record SearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
