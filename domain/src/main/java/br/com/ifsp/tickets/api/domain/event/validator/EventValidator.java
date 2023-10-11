package br.com.ifsp.tickets.api.domain.event.validator;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.shared.validation.ValidationHandler;
import br.com.ifsp.tickets.api.domain.shared.validation.Validator;

public class EventValidator extends Validator {

    private final Event event;

    public EventValidator(final Event event, final ValidationHandler aHandler) {
        super(aHandler);
        this.event = event;
    }

    @Override
    public void validate() {
        // todo validations
    }
}
