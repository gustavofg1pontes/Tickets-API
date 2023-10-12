package br.com.ifsp.tickets.api.infra.contexts.event.presenters;

import br.com.ifsp.tickets.api.app.event.retrieve.get.EventOutput;
import br.com.ifsp.tickets.api.app.event.retrieve.list.EventListOutput;
import br.com.ifsp.tickets.api.app.event.update.UpdateEventOutput;
import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.infra.contexts.event.model.EditEventRequest;
import br.com.ifsp.tickets.api.infra.contexts.event.model.EventResponse;
import br.com.ifsp.tickets.api.infra.contexts.event.model.ListEventResponse;

public interface EventApiPresenter {

    static EventResponse present(final EventOutput eventOutput) {
        return new EventResponse(
                eventOutput.id(),
                eventOutput.name(),
                eventOutput.dateTime(),
                eventOutput.guests(),
                eventOutput.maxGuests()
        );
    }

    static ListEventResponse present(final EventListOutput eventListOutput) {
        return new ListEventResponse(
                eventListOutput.id(),
                eventListOutput.name(),
                eventListOutput.localDateTime(),
                eventListOutput.maxGuests()
        );
    }

    static EditEventRequest present(final UpdateEventOutput eventOutput){
        return new EditEventRequest(
                eventOutput.name(),
                eventOutput.dateTime(),
                eventOutput.maxGuests()
        );
    }
}
