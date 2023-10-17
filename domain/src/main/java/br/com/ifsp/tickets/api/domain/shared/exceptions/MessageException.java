package br.com.ifsp.tickets.api.domain.shared.exceptions;

public class MessageException extends NoStacktraceException{
    public MessageException(String message) {
        super(message);
    }
}
