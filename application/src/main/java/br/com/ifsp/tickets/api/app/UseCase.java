package br.com.ifsp.tickets.api.app;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);

}
