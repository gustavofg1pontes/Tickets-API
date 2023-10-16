package br.com.ifsp.tickets.api.infra.utils;

public class SqlUtils {
    public static String like(final String term) {
        if (term == null) return null;
        return "%" + term + "%";
    }
}
