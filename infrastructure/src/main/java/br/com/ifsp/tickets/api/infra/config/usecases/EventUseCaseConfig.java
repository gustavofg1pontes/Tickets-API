package br.com.ifsp.tickets.api.infra.config.usecases;

import br.com.ifsp.tickets.api.app.event.create.CreateEventUseCase;
import br.com.ifsp.tickets.api.app.event.create.DefaultCreateEventUseCase;
import br.com.ifsp.tickets.api.app.event.delete.DefaultDeleteEventUseCase;
import br.com.ifsp.tickets.api.app.event.delete.DeleteEventUseCase;
import br.com.ifsp.tickets.api.app.event.retrieve.get.DefaultGetEventByIdUseCase;
import br.com.ifsp.tickets.api.app.event.retrieve.get.GetEventByIdUseCase;
import br.com.ifsp.tickets.api.app.event.retrieve.list.DefaultListEventUseCase;
import br.com.ifsp.tickets.api.app.event.retrieve.list.ListEventsUseCase;
import br.com.ifsp.tickets.api.app.event.update.DefaultUpdateEventUseCase;
import br.com.ifsp.tickets.api.app.event.update.UpdateEventUseCase;
import br.com.ifsp.tickets.api.domain.event.gateway.EventGateway;
import br.com.ifsp.tickets.api.domain.guest.gateway.GuestGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventUseCaseConfig {
    private final EventGateway eventGateway;
    private final GuestGateway guestGateway;

    @Bean
    public CreateEventUseCase createEventUseCase(){
        return new DefaultCreateEventUseCase(eventGateway);
    }
    @Bean
    public GetEventByIdUseCase getEventByIdUseCase(){
        return new DefaultGetEventByIdUseCase(eventGateway, guestGateway);
    }
    @Bean
    public UpdateEventUseCase updateEventUseCase(){
        return new DefaultUpdateEventUseCase(eventGateway);
    }
    @Bean
    public ListEventsUseCase listEventsUseCase(){
        return new DefaultListEventUseCase(eventGateway);
    }
    @Bean
    public DeleteEventUseCase deleteEventCommand(){
        return new DefaultDeleteEventUseCase(eventGateway);
    }

}
