package br.com.ifsp.tickets.api.infra.api;

import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestRequest;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ifsp/tickets/guest")
public interface GuestAPI {
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

}
