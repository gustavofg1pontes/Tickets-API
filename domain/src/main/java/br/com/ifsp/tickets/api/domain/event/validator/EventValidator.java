package br.com.ifsp.tickets.api.domain.event.validator;

import br.com.ifsp.tickets.api.domain.event.entity.Event;
import br.com.ifsp.tickets.api.domain.event.entity.EventID;
import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.shared.utils.ValidationUtils;
import br.com.ifsp.tickets.api.domain.shared.validation.Error;
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
        checkMaxTicketsConstraints();
    }

    private void checkMaxTicketsConstraints(){
        if (event.getMaxTickets() == null)
            this.appendError(new Error("max tickets shouldn't be null"));
        else{
            final Integer maxTickets = event.getMaxTickets();
            if (!ValidationUtils.isPositiveInteger(maxTickets))
                this.appendError(new Error("'max tickets' has to be a positive value"));
        }
    }
}
