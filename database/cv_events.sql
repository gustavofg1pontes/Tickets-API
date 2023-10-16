CREATE TABLE tickets.cv_events
(
    id           uuid      not null primary key,
    name         varchar   not null,
    date         timestamp not null,
    max_guests   integer   not null,
    sold_tickets integer   not null
);

comment on table tickets.cv_events is 'Tabela para armazenar eventos';