package br.com.ifsp.tickets.api.infra.api.controllers;

import br.com.ifsp.tickets.api.app.guest.delete.id.DeleteGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.ToggleBlockedGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestCommand;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestOutput;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.validate.ValidateGuestQROutput;
import br.com.ifsp.tickets.api.app.guest.validate.ValidateGuestQRUseCase;
import br.com.ifsp.tickets.api.infra.api.GuestAPI;
import br.com.ifsp.tickets.api.infra.config.auth.EmailService;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestRequest;
import br.com.ifsp.tickets.api.infra.contexts.guest.model.EditGuestResponse;
import br.com.ifsp.tickets.api.infra.contexts.guest.presenters.GuestApiPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuestController implements GuestAPI {
    private final ToggleBlockedGuestUseCase toggleBlockedGuestUseCase;
    private final DeleteGuestUseCase deleteGuestUseCase;
    private final UpdateGuestUseCase updateGuestUseCase;
    private final ValidateGuestQRUseCase validateGuestQRUseCase;
    private final EmailService emailService;

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
                request.blocked(),
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
    public ResponseEntity<?> sendQR(String id) {
        ValidateGuestQROutput output = this.validateGuestQRUseCase.execute(id);
        emailService.sendEmailWithQRCode(output.email(), "Teste", "<h1>Gustavo</h1>",
                output.qrImage());
        return ResponseEntity.noContent().build();
    }
}
