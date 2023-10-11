package br.com.ifsp.tickets.api.domain.shared.events;

@FunctionalInterface
public interface DomainEventPublisher {

    void publishEvent(DomainEvent event);

}

