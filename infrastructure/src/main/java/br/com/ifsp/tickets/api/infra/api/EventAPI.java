package br.com.ifsp.tickets.api.infra.api;

import br.com.ifsp.tickets.api.domain.shared.search.Pagination;
import br.com.ifsp.tickets.api.infra.contexts.event.model.*;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.CreateGuestRequest;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.GuestResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ifsp/tickets/event")
public interface EventAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> createEvent(@RequestBody CreateEventRequest request);

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<EditEventResponse> editEvent(@PathVariable String id, @RequestBody EditEventRequest request);

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<EventResponse> getEvent(@PathVariable String id);

    @GetMapping(
            value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Pagination<ListEventResponse>> getEvents(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") Integer perPage,
            @RequestParam(name = "terms", required = false) String terms,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction
    );

    @DeleteMapping(
            value = "/{idEvent}"
    )
    ResponseEntity<?> deleteEvent(@PathVariable String idEvent);

    @PostMapping(
            value = "/{idEvent}/addGuest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> addGuest(@PathVariable String idEvent, @RequestBody CreateGuestRequest request);

    @DeleteMapping(
            value = "/{idEvent}/deleteGuest/{nameGuest}"
    )
    ResponseEntity<?> deleteGuestUsingEventAndName(@PathVariable String idEvent, @PathVariable String nameGuest);
}
