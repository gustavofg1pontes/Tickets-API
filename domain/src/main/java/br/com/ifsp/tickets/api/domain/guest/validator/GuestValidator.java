package br.com.ifsp.tickets.api.domain.guest.validator;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.shared.validation.ValidationHandler;
import br.com.ifsp.tickets.api.domain.shared.validation.Validator;

public class GuestValidator extends Validator {
    private final Guest guest;

    public GuestValidator(final Guest guest, final ValidationHandler aHandler) {
        super(aHandler);
        this.guest = guest;
    }

    @Override
    public void validate() {
        // todo validations
    }
}
