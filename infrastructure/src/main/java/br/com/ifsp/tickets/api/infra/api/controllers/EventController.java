package br.com.ifsp.tickets.api.infra.api.controllers;

import br.com.ifsp.tickets.api.app.event.create.CreateEventCommand;
import br.com.ifsp.tickets.api.app.event.create.CreateEventOutput;
import br.com.ifsp.tickets.api.app.event.create.CreateEventUseCase;
import br.com.ifsp.tickets.api.app.event.delete.DeleteEventUseCase;
import br.com.ifsp.tickets.api.app.event.retrieve.get.EventOutput;
import br.com.ifsp.tickets.api.app.event.retrieve.get.GetEventByIdUseCase;
import br.com.ifsp.tickets.api.app.event.retrieve.list.ListEventsUseCase;
import br.com.ifsp.tickets.api.app.event.update.UpdateEventCommand;
import br.com.ifsp.tickets.api.app.event.update.UpdateEventOutput;
import br.com.ifsp.tickets.api.app.event.update.UpdateEventUseCase;
import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.domain.shared.search.SearchQuery;
import br.com.ifsp.tickets.api.infra.api.EventAPI;
import br.com.ifsp.tickets.api.infra.contexts.event.model.CreateEventRequest;
import br.com.ifsp.tickets.api.infra.contexts.event.model.EditEventRequest;
import br.com.ifsp.tickets.api.infra.contexts.event.model.EventResponse;
import br.com.ifsp.tickets.api.infra.contexts.event.model.ListEventResponse;
import br.com.ifsp.tickets.api.infra.contexts.event.presenters.EventApiPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController implements EventAPI {

    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final GetEventByIdUseCase getEventByIdUseCase;
    private final ListEventsUseCase listEventsUseCase;
    private final UpdateEventUseCase updateEventUseCase;

    @Override
    public ResponseEntity<?> createEvent(CreateEventRequest request) {
        final CreateEventCommand command = CreateEventCommand.with(
                request.name(),
                request.getDateTime(),
                request.maxGuests()
        );
        final CreateEventOutput output = this.createEventUseCase.execute(command);
        return ResponseEntity.created(URI.create("/ifsp/tickets" + output.id())).body(output);
    }

    @Override
    public ResponseEntity<EditEventRequest> editEvent(String id, EditEventRequest request) {
        final UpdateEventCommand command = UpdateEventCommand.from(
                id,
                request.name(),
                request.getDateTime(),
                request.maxGuests(),
                request.soldTickets()
        );
        final UpdateEventOutput output = this.updateEventUseCase.execute(command);
        return ResponseEntity.ok(EventApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<EventResponse> getEvent(String id) {
        final EventOutput output = this.getEventByIdUseCase.execute(id);
        return ResponseEntity.ok(EventApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<Pagination<ListEventResponse>> getEvents(Integer page, Integer perPage, String terms, String sort, String direction) {
        final SearchQuery searchQuery = new SearchQuery(page, perPage, terms, sort, direction);
        final Pagination<ListEventResponse> pagination = this.listEventsUseCase.execute(searchQuery).map(EventApiPresenter::present);
        return ResponseEntity.ok(pagination);
    }

    @Override
    public ResponseEntity<?> deleteEvent(String idEvent) {
        this.deleteEventUseCase.execute(idEvent);
        return ResponseEntity.noContent().build();
    }
}
