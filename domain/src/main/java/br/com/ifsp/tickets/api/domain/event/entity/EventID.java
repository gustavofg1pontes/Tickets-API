package br.com.ifsp.tickets.api.domain.event.entity;

import br.com.ifsp.tickets.api.domain.shared.domain.Identifier;
import br.com.ifsp.tickets.api.domain.shared.utils.UUIDUtils;

import java.util.UUID;

public class EventID extends Identifier<UUID> {

    private final UUID uuid;

    public EventID(final UUID uuid) {
        this.uuid = uuid;
    }

    public static EventID from(final String anId) {
        if (anId == null || anId.isBlank()) return null;
        return new EventID(UUIDUtils.getFromString(anId));
    }

    public static EventID unique() {
        return EventID.from(UUIDUtils.uuid());
    }

    @Override
    public UUID getValue() {
        return this.uuid;
    }
}
