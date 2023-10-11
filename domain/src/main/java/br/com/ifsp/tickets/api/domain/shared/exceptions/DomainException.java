package br.com.ifsp.tickets.api.domain.shared.exceptions;

import br.com.ifsp.tickets.api.domain.shared.validation.Error;

import java.util.List;
import java.util.stream.Collectors;

public class DomainException extends NoStacktraceException {

    protected final List<Error> errors;

    protected DomainException(final String aMessage, final List<Error> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<Error> anErrors) {

        return new DomainException(anErrors.stream().map(Error::message).collect(Collectors.joining("\n")), anErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
