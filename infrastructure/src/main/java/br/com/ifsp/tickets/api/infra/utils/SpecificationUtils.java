package br.com.ifsp.tickets.api.infra.utils;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {
    private SpecificationUtils() {
    }

    public static <T> Specification<T> like(final String prop, final String term) {
        return (root, query, cb) -> cb.like(cb.upper(root.get(prop)), SqlUtils.like(term.toUpperCase()));
    }

    public static <T> Specification<T> equal(final String prop, final String term) {
        return (root, query, cb) -> cb.equal(cb.upper(root.get(prop)), term.toUpperCase());
    }

    public static <T> Specification<T> likeNumber(final String prop, final Number term) {
        return (root, query, cb) -> cb.like(root.get(prop), SqlUtils.like(term.toString()));
    }

    public static <T> Specification<T> equalNumber(final String prop, final Number term) {
        return (root, query, cb) -> cb.equal(root.get(prop), term);
    }

    public static <T> Specification<T> equalNumber(final String prop, final Number term, final String joinProp) {
        return (root, query, cb) -> {
            var table = root.join(prop);
            return cb.equal(table.get(joinProp), term);
        };
    }

    public static <T> Specification<T> equalInt(final String prop, final String term) {
        return equalNumber(prop, Integer.valueOf(term));
    }

    public static <T> Specification<T> equalInt(final String prop, final String term, final String joinProp) {
        return equalNumber(prop, Integer.valueOf(term), joinProp);
    }

    public static <T> Specification<T> equalDouble(final String prop, final String term) {
        return equalNumber(prop, Double.valueOf(term));
    }

    public static <T> Specification<T> equalLong(final String prop, final String term) {
        return equalNumber(prop, Long.valueOf(term));
    }

    public static <T> Specification<T> like(final String prop, final String term, final String joinProp) {
        return (root, query, cb) -> {
            var table = root.join(prop);
            return cb.like(cb.upper(table.get(joinProp)), SqlUtils.like(term.toUpperCase()));
        };
    }

}
