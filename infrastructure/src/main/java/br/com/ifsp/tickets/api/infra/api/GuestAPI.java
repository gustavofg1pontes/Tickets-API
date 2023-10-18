package br.com.ifsp.tickets.api.infra.api;

import br.com.ifsp.tickets.api.infra.contexts.event.model.EventResponse;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestRequest;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestResponse;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.GuestResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ifsp/tickets/guest")
public interface GuestAPI {

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<GuestResponse> getGuest(@PathVariable String id);
    @DeleteMapping(
            value = "/{id}"
    )
    ResponseEntity<?> deleteGuest(@PathVariable String id);

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<EditGuestResponse> editGuest(@PathVariable String id, @RequestBody EditGuestRequest request);

    @PutMapping(
            value = "/toggle/blocked/{id}"
    )
    ResponseEntity<?> toggleBlocked(@PathVariable String id);

    @PutMapping(
            value = "/toggle/enter/{id}"
    )
    ResponseEntity<?> toggleEnter(@PathVariable String id);

    @PutMapping(
            value = "/toggle/left/{id}"
    )
    ResponseEntity<?> toggleLeft(@PathVariable String id);

    @PostMapping(
            value = "/qrcode/{id}"
    )
    ResponseEntity<?> sendQR(@PathVariable String id);
}
