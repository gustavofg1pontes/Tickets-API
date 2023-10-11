package br.com.ifsp.tickets.api.app;

public abstract class UnitUseCase<IN> {

    public abstract void execute(IN anIn);

}