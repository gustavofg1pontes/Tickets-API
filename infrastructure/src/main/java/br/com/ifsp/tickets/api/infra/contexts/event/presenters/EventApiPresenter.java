package br.com.ifsp.tickets.api.infra.contexts.event.presenters;

import br.com.ifsp.tickets.api.app.event.retrieve.get.EventOutput;
import br.com.ifsp.tickets.api.app.event.retrieve.list.EventListOutput;
import br.com.ifsp.tickets.api.app.event.update.UpdateEventOutput;
import br.com.ifsp.tickets.api.infra.contexts.event.model.EditEventRequest;
import br.com.ifsp.tickets.api.infra.contexts.event.model.EditEventResponse;
import br.com.ifsp.tickets.api.infra.contexts.event.model.EventResponse;
import br.com.ifsp.tickets.api.infra.contexts.event.model.ListEventResponse;
import br.com.ifsp.tickets.api.infra.contexts.guest.presenters.GuestApiPresenter;

import java.util.stream.Collectors;

public interface EventApiPresenter {
    static EventResponse present(final EventOutput eventOutput) {
        return new EventResponse(
                eventOutput.id(),
                eventOutput.name(),
                eventOutput.dateTime(),
                eventOutput.guests().stream().map(GuestApiPresenter::present).collect(Collectors.toSet()),
                eventOutput.maxGuests(),
                eventOutput.soldTickets()
        );
    }

    static ListEventResponse present(final EventListOutput eventListOutput) {
        return new ListEventResponse(
                eventListOutput.id(),
                eventListOutput.name(),
                eventListOutput.localDateTime(),
                eventListOutput.maxGuests(),
                eventListOutput.soldTickets()
        );
    }

    static EditEventResponse present(final UpdateEventOutput eventOutput) {
        return new EditEventResponse(
                eventOutput.id(),
                eventOutput.name(),
                eventOutput.dateTime(),
                eventOutput.maxGuests(),
                eventOutput.soldTickets()
        );
    }
}
