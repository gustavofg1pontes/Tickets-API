package br.com.ifsp.tickets.api.infra.config.usecases;

import br.com.ifsp.tickets.api.app.guest.create.CreateGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.create.DefaultCreateGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.delete.event.DefaultDeleteGuestsByEventUseCase;
import br.com.ifsp.tickets.api.app.guest.delete.event.DeleteGuestsByEventUseCase;
import br.com.ifsp.tickets.api.app.guest.delete.eventIdAndName.DefaultDeleteGuestByEventAndNameUseCase;
import br.com.ifsp.tickets.api.app.guest.delete.eventIdAndName.DeleteGuestByEventAndNameUseCase;
import br.com.ifsp.tickets.api.app.guest.delete.id.DefaultDeleteGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.delete.id.DeleteGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.retrieve.get.DefaultGetGuestByIdUseCase;
import br.com.ifsp.tickets.api.app.guest.retrieve.get.GetGuestByIdUseCase;
import br.com.ifsp.tickets.api.app.guest.retrieve.list.DefaultListGuestsUseCase;
import br.com.ifsp.tickets.api.app.guest.retrieve.list.ListGuestsUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.blocked.DefaultToggleBlockedGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.blocked.ToggleBlockedGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.enter.DefaultToggleEnterGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.enter.ToggleEnterGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.left.DefaultToggleLeftGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.toggle.left.ToggleLeftGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.update.DefaultUpdateGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.update.UpdateGuestUseCase;
import br.com.ifsp.tickets.api.app.guest.validate.DefaultValidateGuestQRUseCase;
import br.com.ifsp.tickets.api.app.guest.validate.ValidateGuestQRUseCase;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuestUseCaseConfig {
    private final GuestGateway guestGateway;
    private final EventGateway eventGateway;

    @Bean
    public CreateGuestUseCase createGuestUseCase() {
        return new DefaultCreateGuestUseCase(guestGateway, eventGateway);
    }

    @Bean
    public GetGuestByIdUseCase getGuestByIdUseCase() {
        return new DefaultGetGuestByIdUseCase(guestGateway);
    }

    @Bean
    public UpdateGuestUseCase updateGuestUseCase() {
        return new DefaultUpdateGuestUseCase(guestGateway);
    }

    @Bean
    public ListGuestsUseCase listGuestsUseCase() {
        return new DefaultListGuestsUseCase(guestGateway);
    }

    @Bean
    public DeleteGuestUseCase deleteGuestUseCase() {
        return new DefaultDeleteGuestUseCase(guestGateway, eventGateway);
    }

    @Bean
    public DeleteGuestByEventAndNameUseCase deleteGuestByEventAndNameUseCase() {
        return new DefaultDeleteGuestByEventAndNameUseCase(guestGateway, eventGateway);
    }

    @Bean
    public ToggleBlockedGuestUseCase toggleBlockedGuestUseCase() {
        return new DefaultToggleBlockedGuestUseCase(guestGateway);
    }

    @Bean
    public ToggleEnterGuestUseCase toggleEnterGuestUseCase() {
        return new DefaultToggleEnterGuestUseCase(guestGateway);
    }

    @Bean
    public ToggleLeftGuestUseCase toggleLeftGuestUseCase() {
        return new DefaultToggleLeftGuestUseCase(guestGateway);
    }

    @Bean
    public ValidateGuestQRUseCase validateGuestQRUseCase() {
        return new DefaultValidateGuestQRUseCase(guestGateway);
    }

    @Bean
    public DeleteGuestsByEventUseCase deleteGuestsByEventUseCase() {
        return new DefaultDeleteGuestsByEventUseCase(guestGateway, eventGateway);
    }
}
