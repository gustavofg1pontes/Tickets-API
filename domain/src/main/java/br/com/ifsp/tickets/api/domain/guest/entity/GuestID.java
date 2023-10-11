package br.com.ifsp.tickets.api.domain.guest.entity;

import br.com.ifsp.tickets.api.domain.shared.domain.Identifier;
import br.com.ifsp.tickets.api.domain.shared.utils.UUIDUtils;

import java.util.UUID;

public class GuestID extends Identifier<UUID> {
    private final UUID uuid;

    public GuestID(final UUID uuid){
        this.uuid = uuid;
    }

    public static GuestID from(final String anId) {
        if (anId == null || anId.isBlank()) return null;
        return new GuestID(UUIDUtils.getFromString(anId));
    }

    public static GuestID unique() {
        return GuestID.from(UUIDUtils.uuid());
    }

    @Override
    public UUID getValue() {
        return this.uuid;
    }
}
