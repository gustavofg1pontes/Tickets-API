package br.com.ifsp.tickets.api.domain.guest.validator;

import br.com.ifsp.tickets.api.domain.guest.entity.Guest;
import br.com.ifsp.tickets.api.domain.shared.utils.ValidationUtils;
import br.com.ifsp.tickets.api.domain.shared.validation.Error;
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
        this.checkDocumentConstraints();
        this.checkPhoneNumberConstraints();
        this.checkEmailConstraints();
    }

    private void checkDocumentConstraints() {
        if (guest.getDocument() == null)
            this.appendError(new Error("Document shouldn't be null"));
        else {
            final String document = guest.getDocument();
            if (document.isBlank()) this.appendError(new Error("'document' should not be empty"));
            if (!ValidationUtils.isValidDocument(document)) this.appendError(new Error("'document' is not a valid document"));
        }
    }

    private void checkPhoneNumberConstraints(){
        if (guest.getPhoneNumber() == null){
            this.appendError(new Error("phone number shouldn't be null"));
        }
        else{
            final String phoneNumber = guest.getPhoneNumber();
            if (phoneNumber.isBlank()) this.appendError(new Error("'phone number' should not be empty"));
            if (!ValidationUtils.isValidPhoneNumber(phoneNumber) && !ValidationUtils.isValidMobilePhoneNumber(phoneNumber))
                this.appendError(new Error("'phone number' is not a valid phone number"));
        }
    }

    private void checkEmailConstraints(){
        if (guest.getEmail() == null){
            this.appendError(new Error("email shouldn't be null"));
        }
        else{
            final String email = guest.getEmail();
            if (email.isBlank()) this.appendError(new Error("'email' should not be empty"));
            if (!ValidationUtils.isValidEmailAddress(email)) this.appendError(new Error("'email' is not a valid email"));
        }
    }
}
