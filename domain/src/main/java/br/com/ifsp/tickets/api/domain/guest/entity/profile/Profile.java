package br.com.ifsp.tickets.api.domain.guest.entity.profile;

import br.com.ifsp.tickets.api.domain.shared.exceptions.NotFoundException;
import br.com.ifsp.tickets.api.domain.shared.validation.Error;

public enum Profile {
    STUDENT(0),
    EMPLOYEE(1),
    EX_STUDENT(2),
    EXTERNAL_PUBLIC(3);

    private Integer id;

    Profile(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Profile get(final Integer id) {
        for(Profile e : values()){
            if(e.id.equals(id)){
                return e;
            }
        }
        throw NotFoundException.with(new Error("unable to find enum"));
    }}
