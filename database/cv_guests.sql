CREATE TABLE tickets.cv_guests
(
    id           uuid    not null primary key,
    event_id     uuid    not null,
    enter_id     serial  not null,
    name         varchar not null,
    age          integer not null,
    document     varchar not null,
    blocked      bool    not null,
    entered      bool    not null,
    left         bool    not null,
    phone_number varchar not null,
    email        varchar not null,
    profile_id   integer not null
);

alter table tickets.cv_guests
    add constraint fk_event
        foreign key (event_id) references tickets.cv_events;


comment on table tickets.cv_guests is 'Tabela para armazenar convidados';