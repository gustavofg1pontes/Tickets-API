CREATE TABLE cv_guests
(
    id           uuid    not null primary key,
    event_id     uuid    not null,
    enter_id     serial,
    name         varchar not null,
    age          integer not null,
    document     varchar(14) not null,
    is_blocked      bool    not null default false,
    is_entered      bool    not null default false,
    is_left         bool    not null default false,
    phone_number varchar(11) not null,
    email        varchar not null,
    profile_id   integer not null
);

alter table cv_guests
    add constraint fk_event
        foreign key (event_id) references cv_events;

comment on table cv_guests is 'Tabela para armazenar convidados';