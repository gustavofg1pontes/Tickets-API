package br.com.ifsp.tickets.api.domain.shared.utils;

import br.com.ifsp.tickets.api.domain.shared.exceptions.DomainException;
import br.com.ifsp.tickets.api.domain.shared.validation.Error;

import java.util.List;
import java.util.UUID;

public class UUIDUtils {

    public static UUID getFromString(String uuidStr) {
        final UUID uuid;
        try {
            uuid = UUID.fromString(uuidStr);
        } catch (Exception ex) {
            throw DomainException.with(List.of(new Error("UUID is invalid")));
        }

        return uuid;
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }


    public static boolean isValidUUID(String uuid) {
        try {
            getFromString(uuid);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
