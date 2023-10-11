package br.com.ifsp.tickets.api.domain.shared.exceptions;

import br.com.ifsp.tickets.api.domain.shared.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}

