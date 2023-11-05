package br.com.ifsp.tickets.api.infra.api.controllers;

import br.com.ifsp.tickets.api.app.event.retrieve.get.EventOutput;
import br.com.ifsp.tickets.api.app.event.retrieve.get.GetEventByIdUseCase;
import br.com.ifsp.tickets.api.app.guest.delete.id.DeleteGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.retrieve.get.GetGuestByIdUseCase;
import br.com.ifsp.tickets.api.app.guest.retrieve.get.GuestOutput;
import br.com.ifsp.tickets.api.app.guest.toggle.blocked.ToggleBlockedGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.enter.ToggleEnterGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.left.ToggleLeftGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestCommand;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestOutput;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.validate.ValidateGuestQROutput;
import br.com.ifsp.tickets.api.app.guest.validate.ValidateGuestQRUseCase;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.infra.api.GuestAPI;
import br.com.ifsp.tickets.api.infra.config.auth.EmailService;
import br.com.ifsp.tickets.api.infra.contexts.event.presenters.EventApiPresenter;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestRequest;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestResponse;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.GuestResponse;
import br.com.ifsp.tickets.api.infra.contexts.guest.presenters.GuestApiPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuestController implements GuestAPI {
    private final ToggleBlockedGuestUseCase toggleBlockedGuestUseCase;
    private final ToggleEnterGuestUseCase toggleEnterGuestUseCase;
    private final ToggleLeftGuestUseCase toggleLeftGuestUseCase;
    private final DeleteGuestUseCase deleteGuestUseCase;
    private final UpdateGuestUseCase updateGuestUseCase;
    private final ValidateGuestQRUseCase validateGuestQRUseCase;
    private final GetGuestByIdUseCase getGuestByIdUseCase;

    private final GetEventByIdUseCase getEventByIdUseCase;

    private final EmailService emailService;

    @Override
    public ResponseEntity<GuestResponse> getGuest(String id) {
        final GuestOutput output = this.getGuestByIdUseCase.execute(id);
        return ResponseEntity.ok(GuestApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<?> deleteGuest(String id) {
        this.deleteGuestUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<EditGuestResponse> editGuest(String id, EditGuestRequest request) {
        final UpdateGuestCommand command = UpdateGuestCommand.from(
                id,
                request.name(),
                request.age(),
                request.document(),
                request.phoneNumber(),
                request.email(),
                request.getProfile()
        );
        final UpdateGuestOutput output = this.updateGuestUseCase.execute(command);
        return ResponseEntity.ok(GuestApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<?> toggleBlocked(String id) {
        this.toggleBlockedGuestUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> toggleEnter(String id) {
        this.toggleEnterGuestUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> toggleLeft(String id) {
        this.toggleLeftGuestUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> sendQR(String id) {
        ValidateGuestQROutput output = null;
        GuestOutput guestOutput = null;
        EventOutput event = null;
        try {
            output = this.validateGuestQRUseCase.execute(id);
            guestOutput = this.getGuestByIdUseCase.execute(id);
            event = this.getEventByIdUseCase.execute(guestOutput.eventId());
            emailService.sendEmailWithQRCode(
                    output,
                    "Ticket Event IFSP: " + event.name(),
                    EmailService.valid(output.name()));
        }catch (Exception er){
            emailService.sendEmail(
                    output.email(),
                    "Ticket Event IFSP failed: " + event.name(),
                    EmailService.notValid(output.name()));
        }
        return ResponseEntity.noContent().build();
    }
}
