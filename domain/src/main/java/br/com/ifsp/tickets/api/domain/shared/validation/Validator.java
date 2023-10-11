package br.com.ifsp.tickets.api.domain.shared.validation;

public abstract class Validator {
    private final ValidationHandler handler;

    protected Validator(final ValidationHandler aHandler) {
        this.handler = aHandler;
    }

    public abstract void validate();

    protected ValidationHandler validationHandler() {
        return this.handler;
    }

    protected void appendError(Error error) {
        this.handler.append(error);
    }

    protected void appendError(String message) {
        this.handler.append(new Error(message));
    }

    protected void appendValidationHandler(ValidationHandler validationHandler) {
        this.handler.append(validationHandler);
    }
}
