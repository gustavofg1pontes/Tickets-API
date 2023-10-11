package br.com.ifsp.tickets.api.domain.shared.events;


import java.io.Serializable;
import java.time.Instant;

public interface DomainEvent extends Serializable {

    Instant occurredOn();
}
